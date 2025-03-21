package net.tsp7.rogue.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.Rogue;
import net.tsp7.rogue.entity.custom.EvilGolemEntity;

public class ModEntities {
    public static final EntityType<EvilGolemEntity> EVIL_GOLEM = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Rogue.MOD_ID, "evil_golem"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EvilGolemEntity::new)
                    .dimensions(EntityDimensions.fixed(1.4f,2.7f)).build()); //numbers are size of hitbox
}
