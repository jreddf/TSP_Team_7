package net.tsp7.rogue.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.tsp7.rogue.entity.projectile.BossFireballEntity;


import java.util.EnumSet;

public class BossBlazeEntity extends BlazeEntity {
    public BossBlazeEntity(EntityType<? extends BlazeEntity> entityType, World world) {
        super(entityType, world);
    }
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    private int attackAnimationTimeout = 0;

    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(BossBlazeEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING, false);
    }

    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }


    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 40;
            this.idleAnimationState.start(this.age);
        } else {
            this.idleAnimationTimeout--;
        }
//
//        if(this.isAttacking() && attackAnimationTimeout <= 0) {
//            attackAnimationTimeout = 20;
//            attackAnimationState.start(this.age);
//        } else {
//            --this.attackAnimationTimeout;
//        }
//
//        if(!this.isAttacking()) {
//            attackAnimationState.stop();
//            attackAnimationTimeout = 0;
//        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(4, new BossBlazeShootFireballGoal(this)); // custom goal
        this.goalSelector.add(5, new GoToWalkTargetGoal(this, 1.0));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0, 0.0F));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this).setGroupRevenge());
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }


    public static DefaultAttributeContainer.Builder createBlazeAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25F)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 200);
    }

    static class BossBlazeShootFireballGoal extends Goal {
        private final BlazeEntity blaze;
        private int fireballsFired;
        private int fireballCooldown;
        private int targetNotVisibleTicks;

        public BossBlazeShootFireballGoal(BlazeEntity blaze) {
            this.blaze = blaze;
            this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
        }

        @Override
        public boolean canStart() {
            LivingEntity target = blaze.getTarget();
            return target != null && target.isAlive() && blaze.canTarget(target);
        }

        @Override
        public void start() {
            fireballsFired = 0;
        }

        @Override
        public void stop() {
            // Removed: blaze.setFireActive(false);
            targetNotVisibleTicks = 0;
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            fireballCooldown--;
            LivingEntity target = blaze.getTarget();
            if (target != null) {
                boolean canSee = blaze.getVisibilityCache().canSee(target);
                if (canSee) {
                    targetNotVisibleTicks = 0;
                } else {
                    targetNotVisibleTicks++;
                }

                double distance = blaze.squaredDistanceTo(target);
                if (distance < 4.0) {
                    if (!canSee) return;

                    if (fireballCooldown <= 0) {
                        fireballCooldown = 20;
                        blaze.tryAttack(target);
                    }

                    blaze.getMoveControl().moveTo(target.getX(), target.getY(), target.getZ(), 1.0);
                } else if (distance < getFollowRange() * getFollowRange() && canSee) {
                    double dx = target.getX() - blaze.getX();
                    double dy = target.getBodyY(0.5) - blaze.getBodyY(0.5);
                    double dz = target.getZ() - blaze.getZ();

                    if (fireballCooldown <= 0) {
                        fireballsFired++;
                        if (fireballsFired == 1) {
                            fireballCooldown = 30; // shorter delay before first fireball
                        } else if (fireballsFired <= 6) {
                            fireballCooldown = 4;  // faster between shots
                        } else {
                            fireballCooldown = 30; // shorter recovery time
                            fireballsFired = 0;
                        }

                        if (fireballsFired > 1) {
                            if (blaze instanceof BossBlazeEntity boss) {
                                boss.setAttacking(true);
                            }
                            double spread = Math.sqrt(Math.sqrt(distance)) * 0.25;
                            if (!blaze.isSilent()) {
                                blaze.getWorld().syncWorldEvent(null, WorldEvents.BLAZE_SHOOTS, blaze.getBlockPos(), 0);
                            }

                            BossFireballEntity fireball = new BossFireballEntity(
                                    blaze.getWorld(),
                                    blaze,
                                    blaze.getRandom().nextTriangular(dx, 2.297 * spread),
                                    dy,
                                    blaze.getRandom().nextTriangular(dz, 2.297 * spread)
                            );
                            fireball.setPosition(fireball.getX(), blaze.getBodyY(0.5) + 0.5, fireball.getZ());
                            blaze.getWorld().spawnEntity(fireball);
                        }
                        else if (blaze instanceof BossBlazeEntity boss) {
                            boss.setAttacking(false);
                        }
                    }

                    blaze.getLookControl().lookAt(target, 10.0F, 10.0F);
                } else if (targetNotVisibleTicks < 5) {
                    blaze.getMoveControl().moveTo(target.getX(), target.getY(), target.getZ(), 1.0);
                }
            }
        }

        private double getFollowRange() {
            return blaze.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            setupAnimationStates();

            boolean shouldAttack = this.isAttacking();
            if (shouldAttack && !attackAnimationState.isRunning()) {
                attackAnimationState.start(this.age);
            } else if (!shouldAttack && attackAnimationState.isRunning()) {
                attackAnimationState.stop();
            }
        }

    }

}
