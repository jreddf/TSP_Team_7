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
import net.tsp7.rogue.entity.custom.EvilVillagerEntity;
import net.tsp7.rogue.entity.custom.EvilWanderingTrader;

public class ModEntities {
    public static final EntityType<EvilGolemEntity> EVIL_GOLEM = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Rogue.MOD_ID, "evil_golem"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EvilGolemEntity::new)
                    .dimensions(EntityDimensions.fixed(1.4f,2.7f)).build()); //numbers are size of hitbox

    public static final EntityType<EvilVillagerEntity> EVIL_VILLAGER = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Rogue.MOD_ID, "evil_villager"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EvilVillagerEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());

    public static final EntityType<EvilWanderingTrader> EVIL_TRADER = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Rogue.MOD_ID, "evil_trader"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EvilWanderingTrader::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.9f)).build());
}
