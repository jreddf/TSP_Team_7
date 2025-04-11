package net.tsp7.rogue.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.Rogue;
import net.tsp7.rogue.entity.custom.BossBlazeEntity;
import net.tsp7.rogue.entity.custom.DogThingEntity;
import net.tsp7.rogue.entity.custom.EvilGolemEntity;
import net.tsp7.rogue.item.entity.FireballEntity;

public class ModEntities {
    public static final EntityType<EvilGolemEntity> EVIL_GOLEM = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Rogue.MOD_ID, "evil_golem"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EvilGolemEntity::new)
                    .dimensions(EntityDimensions.fixed(1.4f,2.7f)).build()); //numbers are size of hitbox

    public static final EntityType<DogThingEntity> DOG_THING = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Rogue.MOD_ID, "dog_thing"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, DogThingEntity::new)
                    .dimensions(EntityDimensions.fixed(1.7f,2f)).build()); //numbers are size of hitbox

    public static final EntityType<BossBlazeEntity> BOSS_BLAZE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Rogue.MOD_ID, "boss_blaze"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, BossBlazeEntity::new)
                    .dimensions(EntityDimensions.fixed(2f,2.5f)).build()); //numbers are size of hitbox

    public static final EntityType<FireballEntity> FIREBALL = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Rogue.MOD_ID, "fireball"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType.EntityFactory<FireballEntity>) FireballEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                    .trackRangeBlocks(64)
                    .trackedUpdateRate(10)
                    .build());
}
