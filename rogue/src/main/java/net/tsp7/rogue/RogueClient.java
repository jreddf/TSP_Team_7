package net.tsp7.rogue;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.tsp7.rogue.entity.ModEntities;
import net.tsp7.rogue.entity.client.*;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.entity.rogueRenderer;
import net.tsp7.rogue.entity.roguebaseenemyModel;

public class RogueClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier("entitytesting", "rogue"), "main");
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.EVIL_GOLEM, EvilGolemRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.EVIL_GOLEM, EvilGolemModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.DOG_THING, DogThingRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.DOG_THING, DogThingModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.BOSS_BLAZE, BossBlazeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BOSS_BLAZE, BossBlazeModel::getTexturedModelData);

        EntityRendererRegistry.register(Rogue.Enemy, rogueRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, roguebaseenemyModel::getTexturedModelData);
    }
}
