package net.tsp7.rogue.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tsp7.rogue.entity.ai.BossBlazeCombatGoal;

public class BossBlazeEntity extends BlazeEntity {
    public BossBlazeEntity(EntityType<? extends BlazeEntity> entityType, World world) {
        super(entityType, world);
    }
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();

    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(BossBlazeEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    private final ServerBossBar bossBar = (ServerBossBar)new ServerBossBar(Text.literal("Boss Blaze"), BossBar.Color.PURPLE, BossBar.Style.PROGRESS).setDarkenSky(false);

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
    }

    @Override
    protected void initGoals() {
        // 3 = highest‐priority active combat goal
        this.goalSelector.add(3, new BossBlazeCombatGoal(this, 1.75, 8.0));
        // fallback wander & pathfinding
        this.goalSelector.add(5, new GoToWalkTargetGoal(this, 1.0));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0, 0.0F));
        // target selection
        this.targetSelector.add(1, new RevengeGoal(this).setGroupRevenge());
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }



    public static DefaultAttributeContainer.Builder createBlazeAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25F)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 300);
    }


    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        // Get the direct entity that hit us
        Entity attacker = source.getSource();
        if (attacker instanceof ProjectileEntity proj) {
            // Grab its current velocity
            Vec3d v = proj.getVelocity();
            // Invert horizontal components to “bounce” back
            Vec3d bounced = new Vec3d(-v.x, v.y, -v.z);
            proj.setVelocity(bounced);

            // Nudge it out of our hitbox so it doesn't immediately re‑collide
            Vec3d pos = this.getPos();
            proj.setPosition(
                    pos.x + bounced.x * 0.5,
                    pos.y + bounced.y * 0.5,
                    pos.z + bounced.z * 0.5
            );

            // Optional: make the boss “own” the projectile now
            proj.setOwner(this);

            // Cancel damage to the boss
            return false;
        }
        // For all other damage types, fall back to normal behavior
        return super.damage(source, amount);
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
