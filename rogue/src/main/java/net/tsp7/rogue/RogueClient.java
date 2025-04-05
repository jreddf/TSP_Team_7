package net.tsp7.rogue;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.tsp7.rogue.entity.ModEntities;
import net.tsp7.rogue.entity.client.EvilGolemModel;
import net.tsp7.rogue.entity.client.EvilGolemRenderer;
import net.tsp7.rogue.entity.client.ModModelLayers;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.entity.rogueRenderer;
import net.tsp7.rogue.entity.roguebaseenemyModel;
import net.tsp7.rogue.item.entity.FireballEntityRenderer;
import net.tsp7.rogue.screen.ModScreenHandlers;
import net.tsp7.rogue.screen.UpgradeTableScreen;

public class RogueClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier("entitytesting", "rogue"), "main");
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.EVIL_GOLEM, EvilGolemRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.EVIL_GOLEM, EvilGolemModel::getTexturedModelData);

        EntityRendererRegistry.register(Rogue.Enemy, rogueRenderer::new);
        EntityRendererRegistry.register(ModEntities.FIREBALL, FireballEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, roguebaseenemyModel::getTexturedModelData);

        HandledScreens.register(ModScreenHandlers.UPGRADE_TABLE_SCREEN_HANDLER, UpgradeTableScreen::new);

    }
}
