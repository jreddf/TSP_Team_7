package net.tsp7.rogue.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BossBlazeEntity extends BlazeEntity {
    public BossBlazeEntity(EntityType<? extends BlazeEntity> entityType, World world) {
        super(entityType, world);
    }
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
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
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }
}
