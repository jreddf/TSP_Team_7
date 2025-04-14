package net.tsp7.rogue.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.tsp7.rogue.entity.animation.ModAnimations;
import net.tsp7.rogue.entity.custom.BossBlazeEntity;
import net.tsp7.rogue.entity.custom.EvilGolemEntity;

public class BossBlazeModel<T extends BossBlazeEntity> extends SinglePartEntityModel<T> {
    private final ModelPart bossblaze;
    private final ModelPart head;

public BossBlazeModel(ModelPart root) {
    this.bossblaze = root.getChild("bossblaze");
    this.head = bossblaze.getChild("head");
}
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bossblaze = modelPartData.addChild("bossblaze", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData topthings = bossblaze.addChild("topthings", ModelPartBuilder.create().uv(0, 32).cuboid(-16.0F, -4.0F, -5.0F, 3.0F, 15.0F, 3.0F, new Dilation(0.0F))
                .uv(36, 32).cuboid(2.0F, -4.0F, -16.0F, 3.0F, 15.0F, 3.0F, new Dilation(0.0F))
                .uv(24, 32).cuboid(-5.0F, -4.0F, 14.0F, 3.0F, 15.0F, 3.0F, new Dilation(0.0F))
                .uv(12, 32).cuboid(14.0F, -4.0F, 2.0F, 3.0F, 15.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -39.0F, 2.0F));

        ModelPartData middlethings = bossblaze.addChild("middlethings", ModelPartBuilder.create().uv(24, 50).cuboid(-1.0F, -2.0F, -12.0F, 2.0F, 11.0F, 2.0F, new Dilation(0.0F))
                .uv(32, 50).cuboid(10.0F, -2.0F, -1.0F, 2.0F, 11.0F, 2.0F, new Dilation(0.0F))
                .uv(40, 50).cuboid(-1.0F, -2.0F, 10.0F, 2.0F, 11.0F, 2.0F, new Dilation(0.0F))
                .uv(60, 32).cuboid(-12.0F, -2.0F, -1.0F, 2.0F, 11.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -23.0F, 2.0F));

        ModelPartData bottomthings = bossblaze.addChild("bottomthings", ModelPartBuilder.create().uv(48, 32).cuboid(4.0F, -6.0F, 4.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
                .uv(48, 46).cuboid(4.0F, -6.0F, -7.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 50).cuboid(-7.0F, -6.0F, 4.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F))
                .uv(12, 50).cuboid(-7.0F, -6.0F, -7.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -7.0F, 2.0F));

        ModelPartData head = bossblaze.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -41.0F, -6.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }
        @Override
        public void setAngles(BossBlazeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            this.getPart().traverse().forEach(ModelPart::resetTransform);
            this.setHeadAngles(netHeadYaw, headPitch);

            this.animateMovement(ModAnimations.boss_walk, limbSwing, limbSwingAmount, 2f, 2.5f);
            this.updateAnimation(entity.idleAnimationState, ModAnimations.idle, ageInTicks, 1f);
            this.updateAnimation(entity.attackAnimationState, ModAnimations.boss_attack, ageInTicks, 1f);
        }
        private void setHeadAngles(float headYaw, float headPitch) {
            headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
            headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

            this.head.yaw = headYaw * 0.017453292F;
            this.head.pitch = headPitch * 0.017453292F;
        }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        bossblaze.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart(){
        return bossblaze;
    }
}