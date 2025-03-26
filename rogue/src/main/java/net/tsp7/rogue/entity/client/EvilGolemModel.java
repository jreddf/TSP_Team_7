package net.tsp7.rogue.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.tsp7.rogue.entity.animation.ModAnimations;
import net.tsp7.rogue.entity.custom.EvilGolemEntity;

public class EvilGolemModel<T extends EvilGolemEntity> extends SinglePartEntityModel<T>{
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

		ModelPartData leg1 = evil_golem.addChild("leg1", ModelPartBuilder.create().uv(40, 69).cuboid(10.5F, 14.0F, -1.0F, 6.0F, 16.0F, 5.0F, new Dilation(0.0F))
				.uv(80, 24).cuboid(10.0F, 16.0F, -2.0F, 7.0F, 4.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(-9.0F, -30.0F, 0.0F));

		ModelPartData leg0 = evil_golem.addChild("leg0", ModelPartBuilder.create().uv(68, 53).cuboid(-16.5F, 14.0F, -1.0F, 6.0F, 16.0F, 5.0F, new Dilation(0.0F))
				.uv(62, 74).cuboid(-17.0F, 16.0F, -2.0F, 7.0F, 4.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(9.0F, -30.0F, 0.0F));

		ModelPartData arm1 = evil_golem.addChild("arm1", ModelPartBuilder.create().uv(20, 45).cuboid(0.0F, -3.5F, -3.0F, 4.0F, 30.0F, 6.0F, new Dilation(0.0F))
				.uv(58, 0).cuboid(-1.0F, 11.0F, -4.0F, 6.0F, 16.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(9.0F, -30.0F, 0.0F));

		ModelPartData arm0 = evil_golem.addChild("arm0", ModelPartBuilder.create().uv(0, 45).cuboid(-4.0F, -3.5F, -3.0F, 4.0F, 30.0F, 6.0F, new Dilation(0.0F))
				.uv(40, 45).cuboid(-5.0F, 11.0F, -4.0F, 6.0F, 16.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(-9.0F, -30.0F, 0.0F));

		ModelPartData Head = evil_golem.addChild("Head", ModelPartBuilder.create().uv(48, 24).cuboid(-4.0F, -10.0F, -3.5F, 8.0F, 10.0F, 8.0F, new Dilation(0.0F))
				.uv(90, 53).cuboid(-1.0F, -3.0F, -5.5F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
				.uv(90, 67).cuboid(-13.45F, -14.4F, 16.05F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(86, 6).cuboid(-8.5F, -19.5F, 7.15F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(86, 18).cuboid(5.5F, -19.5F, 7.15F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(90, 75).cuboid(12.45F, -14.4F, 16.05F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -33.0F, -4.0F));

		ModelPartData cube_r1 = Head.addChild("cube_r1", ModelPartBuilder.create().uv(76, 85).cuboid(-2.0F, -2.0F, -2.0F, 3.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -8.0F, 0.0F, -0.3927F, 0.0F, 0.6981F));

		ModelPartData cube_r2 = Head.addChild("cube_r2", ModelPartBuilder.create().uv(62, 85).cuboid(-1.0F, -2.0F, -2.0F, 3.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -8.0F, 0.0F, -0.3927F, 0.0F, -0.6981F));

		ModelPartData horn_r1 = Head.addChild("horn_r1", ModelPartBuilder.create().uv(56, 90).cuboid(-4.9F, -1.0F, 0.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(62, 69).cuboid(-30.7F, -1.0F, 0.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(17.8F, -19.6F, 19.55F, -1.0472F, 0.0F, 0.0F));

		ModelPartData horn_r2 = Head.addChild("horn_r2", ModelPartBuilder.create().uv(54, 42).cuboid(-1.5F, 0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(48, 42).cuboid(-28.5F, 0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(14.5F, -14.5F, 18.65F, 0.6109F, 0.0F, 0.0F));

		ModelPartData horn_r3 = Head.addChild("horn_r3", ModelPartBuilder.create().uv(90, 79).cuboid(-2.9F, -0.25F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(90, 71).cuboid(-29.8F, -0.25F, 0.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(15.85F, -14.85F, 20.45F, 1.0908F, 0.0F, 0.0F));

		ModelPartData horn_r4 = Head.addChild("horn_r4", ModelPartBuilder.create().uv(42, 90).cuboid(-0.6F, 0.5F, 0.0F, 2.0F, 1.0F, 4.5F, new Dilation(0.0F))
				.uv(14, 90).cuboid(-20.2F, 0.5F, 0.0F, 2.0F, 1.0F, 4.5F, new Dilation(0.0F)), ModelTransform.of(9.4F, -19.4F, 11.95F, -0.7854F, 0.0F, 0.0F));

		ModelPartData horn_r5 = Head.addChild("horn_r5", ModelPartBuilder.create().uv(28, 90).cuboid(-0.55F, 0.5F, 0.0F, 2.0F, 1.0F, 4.5F, new Dilation(0.0F))
				.uv(0, 90).cuboid(-23.25F, 0.5F, 0.0F, 2.0F, 1.0F, 4.5F, new Dilation(0.0F)), ModelTransform.of(10.9F, -16.55F, 14.05F, -0.3927F, 0.0F, 0.0F));

		ModelPartData horn_r6 = Head.addChild("horn_r6", ModelPartBuilder.create().uv(90, 63).cuboid(-0.9F, 0.5F, 0.5F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(90, 59).cuboid(-17.8F, 0.5F, 0.5F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(8.35F, -20.6F, 10.3F, -0.3927F, 0.0F, 0.0F));

		ModelPartData horn_r7 = Head.addChild("horn_r7", ModelPartBuilder.create().uv(86, 0).cuboid(-2.25F, 0.1F, 0.0F, 3.0F, 2.0F, 4.0F, new Dilation(0.0F))
				.uv(80, 35).cuboid(-13.25F, 0.1F, 0.0F, 3.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(6.25F, -18.2F, 5.2F, 0.3927F, 0.0F, 0.0F));

		ModelPartData horn_r8 = Head.addChild("horn_r8", ModelPartBuilder.create().uv(86, 11).cuboid(-2.8F, -1.2F, 2.4F, 3.0F, 4.0F, 3.0F, new Dilation(-0.1F))
				.uv(28, 81).cuboid(-11.65F, -1.2F, 2.4F, 3.0F, 4.0F, 3.0F, new Dilation(-0.1F)), ModelTransform.of(5.725F, -18.5F, 2.65F, -0.7854F, 0.0F, 0.0F));

		ModelPartData horn_r9 = Head.addChild("horn_r9", ModelPartBuilder.create().uv(14, 81).cuboid(-3.0F, -2.0F, 0.0F, 4.0F, 6.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 81).cuboid(-11.1F, -2.0F, 0.0F, 4.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(5.05F, -13.25F, 0.85F, -0.3927F, 0.0F, 0.0F));

		ModelPartData body = evil_golem.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-9.0F, -2.0F, -6.0F, 18.0F, 12.0F, 11.0F, new Dilation(0.0F))
				.uv(68, 42).cuboid(-5.5F, 10.0F, -3.0F, 11.0F, 5.0F, 6.0F, new Dilation(0.5F))
				.uv(0, 23).cuboid(-8.0F, 10.0F, -5.0F, 15.0F, 2.0F, 9.0F, new Dilation(0.0F))
				.uv(0, 34).cuboid(-7.0F, 10.0F, -5.0F, 15.0F, 2.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -31.0F, 0.0F));
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