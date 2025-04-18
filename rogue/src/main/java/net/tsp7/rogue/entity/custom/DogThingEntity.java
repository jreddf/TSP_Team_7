package net.tsp7.rogue.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tsp7.rogue.entity.ai.DogThingAttackGoal;
import org.jetbrains.annotations.Nullable;

public class DogThingEntity extends HostileEntity {
    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(DogThingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> ATTACK_FRAME =
            DataTracker.registerData(DogThingEntity.class, TrackedDataHandlerRegistry.INTEGER);

    private boolean playedAttackAnimation = false;


    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public int attackTickCounter = -1;
    public LivingEntity attackTarget = null;

    private final ServerBossBar bossBar = (ServerBossBar)new ServerBossBar(Text.literal("Dog Thing"), BossBar.Color.GREEN, BossBar.Style.PROGRESS).setDarkenSky(false);

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(30) + 60;
            this.idleAnimationState.start(this.age);
        } else {
            this.idleAnimationTimeout--;
        }

        int frame = this.dataTracker.get(ATTACK_FRAME);

        // Safely trigger animation near start of attack
        if ((frame >= 0 && frame <= 1) && !playedAttackAnimation) {
            attackAnimationTimeout = 10;
            attackAnimationState.start(this.age);
            playedAttackAnimation = true;
        } else if (frame > 1) {
            playedAttackAnimation = false;
        }

        if (!this.isAttacking() || frame >= 10) {
            attackAnimationState.stop();
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            setupAnimationStates();
        } else {
            // Handle invisibility based on player gaze
            boolean anyPlayerLooking = false;
            for (PlayerEntity player : this.getWorld().getPlayers()) {
                if (player.squaredDistanceTo(this) < 256 && isPlayerLookingAtEntity(player)) {
                    anyPlayerLooking = true;
                   break;
               }
            }

            if (anyPlayerLooking) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 20, 0, false, false));
            } else {
                this.removeStatusEffect(StatusEffects.INVISIBILITY);
            }

            // Attack logic with cooldown
            if (attackCooldownTicks > 0) {
                attackCooldownTicks--;
            }
            else {
                if (isAttacking()) {
                    attackTickCounter++;
                    this.dataTracker.set(ATTACK_FRAME, attackTickCounter);

                    if (attackTickCounter == 5 && attackTarget != null && this.canSee(attackTarget)) {
                        if (attackCooldownTicks <= 0) {
                            this.swingHand(Hand.MAIN_HAND);
                            this.tryAttack(attackTarget);
                            attackCooldownTicks = 8; // shorter cooldown
                        }
                    }

                    if (attackTickCounter >= 10) {
                        setAttacking(false);
                        attackTarget = null;
                        attackTickCounter = -1;
                        this.dataTracker.set(ATTACK_FRAME, -1);
                    }
                }
            }
        }
    }

    private boolean isPlayerLookingAtEntity(PlayerEntity player) {
        Vec3d lookVec = player.getRotationVec(1.0F).normalize();
        Vec3d toEntity = this.getBoundingBox().getCenter().subtract(player.getCameraPosVec(1.0F));
        double distance = toEntity.length();

        toEntity = toEntity.normalize();
        double dot = lookVec.dotProduct(toEntity);

        // 1.0 - θ defines how narrow the cone is. 0.03 allows about 10°.
        return dot > 1.0 - 0.15 / distance && player.canSee(this);
    }


    private int attackCooldownTicks = 0;


    public DogThingEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void initGoals(){
        super.initGoals();
        this.setPersistent();
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new DogThingAttackGoal(this, 1.2, true));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.8));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new RevengeGoal(this));
    }

    public static DefaultAttributeContainer.Builder createDogThingAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0);
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f;
        if (this.getPose() == EntityPose.STANDING) {
            f = Math.min(posDelta * 6.0F, 1.0F);
        } else {
            f = 0.0F;
        }

        this.limbAnimator.updateLimbs(f, 0.2F);
    }

    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING, false);
        this.dataTracker.startTracking(ATTACK_FRAME, -1);

    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_HUSK_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRON_GOLEM_DEATH;
    }

    @Override
    public boolean cannotDespawn() {
        return true; // Forces the entity to never despawn
    }

    @Override
    public boolean isDisallowedInPeaceful() {
        return false; // Set to true if you want it to despawn in peaceful mode.
    }
    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return false; // Prevents it from despawning naturally.
    }

    /*BOSS BAR*/

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    protected void mobTick() {
        super.mobTick();
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }
}