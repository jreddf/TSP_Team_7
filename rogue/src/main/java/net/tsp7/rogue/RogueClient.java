package net.tsp7.rogue;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.Enemy.rogueRenderer;
import net.tsp7.rogue.Enemy.roguebaseenemyModel;

public class RogueClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier("entitytesting", "rogue"), "main");
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Rogue.Enemy, rogueRenderer::new);


        EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, roguebaseenemyModel::getTexturedModelData);
    }
}
