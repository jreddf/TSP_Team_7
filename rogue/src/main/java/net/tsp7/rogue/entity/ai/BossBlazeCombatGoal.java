package net.tsp7.rogue.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldEvents;
import net.tsp7.rogue.entity.custom.BossBlazeEntity;
import net.tsp7.rogue.entity.projectile.BossFireballEntity;

import java.util.EnumSet;
import java.util.Random;

public class BossBlazeCombatGoal extends Goal {
    private final BlazeEntity blaze;
    private LivingEntity target;

    // circling params
    private final double speed;
    private final double radius;
    private int ticksUntilSwitch;
    private int direction; // +1 = clockwise, -1 = counter

    // shooting params
    private int fireballsFired;
    private int cooldown;
    private int unseenTicks;

    private final Random rng = new Random();

    public BossBlazeCombatGoal(BlazeEntity blaze, double speed, double radius) {
        this.blaze = blaze;
        this.speed = speed;
        this.radius = radius;
        // we need both MOVE and LOOK here
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        LivingEntity t = blaze.getTarget();
        return t != null && t.isAlive();
    }

    @Override
    public void start() {
        this.target         = blaze.getTarget();
        this.fireballsFired = 0;
        this.cooldown       = 0;
        this.unseenTicks    = 0;

        // pick circling direction and switch time (2–5s)
        this.direction       = rng.nextBoolean() ? 1 : -1;
        this.ticksUntilSwitch= 40 + rng.nextInt(60);
    }

    @Override
    public boolean shouldContinue() {
        LivingEntity t = blaze.getTarget();
        return t != null && t.isAlive();
    }

    @Override
    public void stop() {
        if (blaze instanceof BossBlazeEntity boss) boss.setAttacking(false);
    }

    @Override
    public void tick() {
        // 1) refresh target
        target = blaze.getTarget();
        if (target == null) return;

        // 2) face the player
        faceTarget(blaze, target);

        // 3) circling movement
        if (--ticksUntilSwitch <= 0) {
            direction = -direction;
            ticksUntilSwitch = 40 + rng.nextInt(60);
        }
        double dx    = blaze.getX() - target.getX();
        double dz    = blaze.getZ() - target.getZ();
        double angle = Math.atan2(dz, dx) + direction * Math.toRadians(4);
        double destX = target.getX() + Math.cos(angle) * radius;
        double destZ = target.getZ() + Math.sin(angle) * radius;
        double destY = blaze.getY();

        // apply boost in mid‐air
        double actualSpeed = blaze.isOnGround() ? speed : speed * 3;
        blaze.getMoveControl().moveTo(destX, destY, destZ, actualSpeed);


        // 4) shooting logic
        if (cooldown > 0) {
            cooldown--;
        }
        // visibility check
        if (!blaze.getVisibilityCache().canSee(target)) {
            unseenTicks++;
            return;
        } else {
            unseenTicks = 0;
        }

        double dist2 = blaze.squaredDistanceTo(target);
        double followRange = blaze.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE);

        // melee if too close
        if (dist2 < 4.0) {
            if (cooldown <= 0) {
                cooldown = 20;
                blaze.tryAttack(target);
            }
            if (blaze instanceof BossBlazeEntity boss) boss.setAttacking(false);

            // ranged if in range
        } else if (dist2 < followRange * followRange) {
            if (cooldown <= 0) {
                fireballsFired++;
                 if (fireballsFired <= 8) {
                    cooldown = 4;
                } else {
                    cooldown       = 20;
                    fireballsFired = 0;
                }

                if (fireballsFired > 1) {
                    if (blaze instanceof BossBlazeEntity boss) boss.setAttacking(true);
                    if (!blaze.isSilent()) {
                        blaze.getWorld().syncWorldEvent(null, WorldEvents.BLAZE_SHOOTS, blaze.getBlockPos(), 0);
                    }
                    shootFireballAtTarget(blaze, target, dist2);
                }
            }
        }

    }

    private void shootFireballAtTarget(BlazeEntity blaze, LivingEntity target, double dist2) {
        Vec3d diff = target.getPos().subtract(blaze.getPos());
        double spread = Math.sqrt(Math.sqrt(dist2)) * 0.3;
        BossFireballEntity fb = new BossFireballEntity(
                blaze.getWorld(),
                blaze,
                blaze.getRandom().nextTriangular(diff.x, 2.297 * spread),
                diff.y,
                blaze.getRandom().nextTriangular(diff.z, 2.297 * spread)
        );
        fb.setPosition(fb.getX(), blaze.getBodyY(0.5) + 0.5, fb.getZ());
        blaze.getWorld().spawnEntity(fb);
    }

    private void faceTarget(BlazeEntity blaze, LivingEntity target) {
        double dx = target.getX() - blaze.getX();
        double dz = target.getZ() - blaze.getZ();
        double dy = target.getBodyY(0.5) - blaze.getBodyY(0.5);
        double horiz = Math.sqrt(dx*dx + dz*dz);

        float yaw   = (float)(MathHelper.atan2(dz, dx) * 180.0D/Math.PI) - 90F;
        float pitch = (float)(-MathHelper.atan2(dy, horiz) * 180.0D/Math.PI);

        blaze.setYaw(yaw);
        blaze.bodyYaw = yaw;
        blaze.headYaw = yaw;
        blaze.setPitch(pitch);
    }
}
