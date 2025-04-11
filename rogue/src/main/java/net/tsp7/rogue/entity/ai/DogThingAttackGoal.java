package net.tsp7.rogue.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.Hand;
import net.tsp7.rogue.entity.custom.DogThingEntity;
import net.tsp7.rogue.entity.custom.EvilGolemEntity;

public class DogThingAttackGoal extends MeleeAttackGoal {
    private final DogThingEntity entity;

    public DogThingAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
        entity = ((DogThingEntity) mob);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    protected void attack(LivingEntity target, double distanceSq) {
        if (isEnemyWithinAttackDistance(target, distanceSq)) {
            mob.getLookControl().lookAt(target);
            if (!entity.isAttacking()) {
                entity.setAttacking(true);
                entity.attackTickCounter = 0;
                entity.attackTarget = target;
            }
        } else {
            entity.setAttacking(false);
            entity.attackTickCounter = -1;
            entity.attackTarget = null;
        }
    }


    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
        return pDistToEnemySqr <= this.getSquaredMaxAttackDistance(pEnemy);
    }

    @Override
    public void tick() {
        super.tick(); // Just to keep the pathing active
    }


    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }
}
