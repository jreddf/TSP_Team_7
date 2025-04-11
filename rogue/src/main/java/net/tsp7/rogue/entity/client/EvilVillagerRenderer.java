package net.tsp7.rogue.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.Rogue;
import net.tsp7.rogue.entity.custom.EvilVillagerEntity;

public class EvilVillagerRenderer extends MobEntityRenderer<EvilVillagerEntity, EvilVillagerModel<EvilVillagerEntity>> {

    private static final Identifier TEXTURE = new Identifier(Rogue.MOD_ID, "textures/entity/villager_v2.png");

    public EvilVillagerRenderer(EntityRendererFactory.Context context)
    {
        super(context, new EvilVillagerModel<>(context.getPart(ModModelLayers.EVIL_VILLAGER)), 0.9f);
    }

    @Override
    public Identifier getTexture(EvilVillagerEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(EvilVillagerEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
