package net.tsp7.rogue.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.tsp7.rogue.entity.animation.ModAnimations;
import net.tsp7.rogue.entity.custom.EvilVillagerEntity;
import net.tsp7.rogue.entity.custom.EvilWanderingTrader;

public class EvilWanderingTraderModel <T extends EvilWanderingTrader> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;

    public EvilWanderingTraderModel(ModelPart root) {
        this.body = root.getChild("evil_villager");
        this.head = body.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("evil_villager", ModelPartBuilder.create().uv(16, 20).cuboid(-4.0F, -24.0F, -3.0F, 8.0F, 12.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 38).cuboid(-4.0F, -24.0F, -3.0F, 8.0F, 20.0F, 6.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

        ModelPartData helmet = head.addChild("helmet", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData brim = head.addChild("brim", ModelPartBuilder.create().uv(30, 47).cuboid(-8.0F, -8.0F, -6.0F, 16.0F, 16.0F, 1.0F, new Dilation(0.1F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData nose = head.addChild("nose", ModelPartBuilder.create().uv(24, 0).cuboid(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));

        ModelPartData arms = body.addChild("arms", ModelPartBuilder.create().uv(40, 38).cuboid(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(44, 22).cuboid(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F))
                .uv(44, 22).mirrored().cuboid(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -22.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        ModelPartData RightLeg = body.addChild("RightLeg", ModelPartBuilder.create().uv(0, 22).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -12.0F, 0.0F));

        ModelPartData LeftLeg = body.addChild("LeftLeg", ModelPartBuilder.create().uv(0, 22).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(2.0F, -12.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return body;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.animateMovement(ModAnimations.evil_walk, limbAngle, limbDistance, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, ModAnimations.evil_idle, animationProgress, 1f);
        this.updateAnimation(entity.attackAnimationState, ModAnimations.evil_attack, animationProgress, 1f);
    }
}
