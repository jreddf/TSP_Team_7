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
import net.tsp7.rogue.screen.ModScreenHandlers;
import net.tsp7.rogue.screen.UpgradeTableScreen;

public class RogueClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.EVIL_GOLEM, EvilGolemRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.EVIL_GOLEM, EvilGolemModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.EVIL_VILLAGER, EvilVillagerRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.EVIL_VILLAGER, EvilVillagerModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.EVIL_TRADER, EvilWanderingTraderRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.EVIL_TRADER, EvilWanderingTraderModel::getTexturedModelData);
        HandledScreens.register(ModScreenHandlers.UPGRADE_TABLE_SCREEN_HANDLER, UpgradeTableScreen::new);

    }
}
