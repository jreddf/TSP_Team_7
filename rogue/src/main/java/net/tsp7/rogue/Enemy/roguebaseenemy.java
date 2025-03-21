package net.tsp7.rogue.Enemy;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class roguebaseenemy extends PathAwareEntity {

    public roguebaseenemy(EntityType<? extends PathAwareEntity> entityType, World world)
    {
        super(entityType, world);
    }
}
