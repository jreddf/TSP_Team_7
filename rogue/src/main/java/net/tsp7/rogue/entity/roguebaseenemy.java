package net.tsp7.rogue.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class roguebaseenemy extends MobEntity {

    public roguebaseenemy(EntityType<? extends MobEntity> entityType, World world)
    {
        super( entityType, world);
    }
}
