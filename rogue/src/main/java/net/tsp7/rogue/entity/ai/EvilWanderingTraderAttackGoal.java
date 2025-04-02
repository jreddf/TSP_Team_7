package net.tsp7.rogue.entity.ai;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tsp7.rogue.entity.custom.EvilWanderingTrader;

public class EvilWanderingTraderAttackGoal extends ProjectileAttackGoal {
    private final EvilWanderingTrader entity;
    private int attackDelay = 4;
    private int ticksUntilNextAttack = 10;
    private boolean shouldCountTillNextAttack = false;

    public EvilWanderingTraderAttackGoal(RangedAttackMob mob, double mobSpeed, int intervalTicks, float maxShootRange) {
        super(mob, mobSpeed, intervalTicks, maxShootRange);
        entity = ((EvilWanderingTrader) mob);
    }

    @Override
    public void start()
    {
        super.start();
        attackDelay = 4;
        ticksUntilNextAttack = 10;
    }
}
