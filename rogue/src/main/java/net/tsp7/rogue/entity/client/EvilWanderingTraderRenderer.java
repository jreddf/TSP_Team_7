package net.tsp7.rogue.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.Rogue;
import net.tsp7.rogue.entity.custom.EvilVillagerEntity;
import net.tsp7.rogue.entity.custom.EvilWanderingTrader;

public class EvilWanderingTraderRenderer extends MobEntityRenderer<EvilWanderingTrader, EvilWanderingTraderModel<EvilWanderingTrader>> {
    private static final Identifier TEXTURE = new Identifier(Rogue.MOD_ID, "textures/entity/villager_v2.png");

    public EvilWanderingTraderRenderer(EntityRendererFactory.Context context) {
        super(context, new EvilWanderingTraderModel<>(context.getPart(ModModelLayers.EVIL_VILLAGER)), 0.9f);
    }

    @Override
    public Identifier getTexture(EvilWanderingTrader entity) {
        return TEXTURE;
    }
}
