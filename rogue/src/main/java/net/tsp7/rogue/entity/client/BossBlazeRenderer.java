package net.tsp7.rogue.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.Rogue;
import net.tsp7.rogue.entity.custom.BossBlazeEntity;

public class BossBlazeRenderer extends MobEntityRenderer<BossBlazeEntity, BossBlazeModel<BossBlazeEntity>> {
    private static final Identifier TEXTURE = new Identifier(Rogue.MOD_ID, "textures/entity/blazeboss.png");

    public BossBlazeRenderer(EntityRendererFactory.Context context) {
        super(context, new BossBlazeModel<>(context.getPart(ModModelLayers.BOSS_BLAZE)), 0.6f); //number is size of shadow
    }

    @Override
    public Identifier getTexture(BossBlazeEntity entity){
        return TEXTURE;
    }

    @Override
    public void render(BossBlazeEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby()){
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }
        else {
            matrixStack.scale(1f, 1f, 1f);
        }
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
