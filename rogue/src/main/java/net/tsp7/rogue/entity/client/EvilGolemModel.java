// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package net.tsp7.rogue.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.tsp7.rogue.entity.animation.ModAnimations;
import net.tsp7.rogue.entity.custom.EvilGolemEntity;

public class EvilGolemModel<T extends EvilGolemEntity> extends SinglePartEntityModel<T> {
	private final ModelPart evil_golem;
	private final ModelPart Head;
	public EvilGolemModel(ModelPart root) {
		this.evil_golem = root.getChild("evil_golem");
		this.Head = evil_golem.getChild("Head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData evil_golem = modelPartData.addChild("evil_golem", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData leg1 = evil_golem.addChild("leg1", ModelPartBuilder.create().uv(60, 0).mirrored().cuboid(-3.5F, -1.0F, -3.0F, 6.0F, 16.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(5.0F, -15.0F, 0.0F));

		ModelPartData leg0 = evil_golem.addChild("leg0", ModelPartBuilder.create().uv(37, 0).cuboid(-3.5F, -1.0F, -3.0F, 6.0F, 16.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -15.0F, 0.0F));

		ModelPartData arm1 = evil_golem.addChild("arm1", ModelPartBuilder.create().uv(60, 58).cuboid(-1.0F, -2.5F, -3.0F, 4.0F, 30.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(10.0F, -31.0F, 0.0F));

		ModelPartData arm0 = evil_golem.addChild("arm0", ModelPartBuilder.create().uv(60, 21).cuboid(-4.0F, -2.5F, -3.0F, 4.0F, 30.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-9.0F, -31.0F, 0.0F));

		ModelPartData Head = evil_golem.addChild("Head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -10.0F, -3.5F, 8.0F, 10.0F, 8.0F, new Dilation(0.0F))
		.uv(24, 0).cuboid(-1.0F, -3.0F, -5.5F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -33.0F, -4.0F));

		ModelPartData body = evil_golem.addChild("body", ModelPartBuilder.create().uv(0, 40).cuboid(-9.0F, -33.0F, -6.0F, 18.0F, 12.0F, 11.0F, new Dilation(0.0F))
		.uv(0, 70).cuboid(-4.5F, -21.0F, -3.0F, 9.0F, 5.0F, 6.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(EvilGolemEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ModAnimations.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.idle, ageInTicks, 1f);
		this.updateAnimation(entity.attackAnimationState, ModAnimations.attack, ageInTicks, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.Head.yaw = headYaw * 0.017453292F;
		this.Head.pitch = headPitch * 0.017453292F;
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		evil_golem.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
	@Override
	public ModelPart getPart(){
		return evil_golem;
	}
}