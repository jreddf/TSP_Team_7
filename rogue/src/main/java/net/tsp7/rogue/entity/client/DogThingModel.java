package net.tsp7.rogue.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.tsp7.rogue.entity.animation.ModAnimations;
import net.tsp7.rogue.entity.custom.DogThingEntity;

public class DogThingModel<T extends DogThingEntity> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart skull;

    public DogThingModel(ModelPart root) {
        this.body = root.getChild("body");
        this.skull = body.getChild("torso").getChild("head").getChild("neck").getChild("skull");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, -8.0F));

        ModelPartData torso = body.addChild("torso", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData tail = torso.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -13.0F, 21.0F));

        ModelPartData tail1 = tail.addChild("tail1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 13.0F, -16.0F));

        ModelPartData cube_r1 = tail1.addChild("cube_r1", ModelPartBuilder.create().uv(72, 14).cuboid(-2.0F, -2.0F, -1.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 15.0F, -0.3054F, 0.0F, 0.0F));

        ModelPartData tail2 = tail1.addChild("tail2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -13.0F, 18.0F));

        ModelPartData cube_r2 = tail2.addChild("cube_r2", ModelPartBuilder.create().uv(72, 6).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

        ModelPartData tail3 = tail2.addChild("tail3", ModelPartBuilder.create().uv(42, 85).cuboid(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 2.0F, new Dilation(0.0F))
                .uv(54, 85).cuboid(-1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 5.0F));

        ModelPartData mainbody = torso.addChild("mainbody", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -1.0F, -3.0F, 12.0F, 6.0F, 24.0F, new Dilation(0.0F))
                .uv(0, 30).cuboid(-5.0F, -2.0F, -2.0F, 10.0F, 2.0F, 22.0F, new Dilation(0.0F))
                .uv(0, 54).cuboid(-5.0F, 5.0F, -2.0F, 10.0F, 1.0F, 22.0F, new Dilation(0.0F))
                .uv(64, 42).cuboid(-5.0F, 0.0F, -4.0F, 10.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -13.0F, 0.0F));

        ModelPartData ridges_r1 = mainbody.addChild("ridges_r1", ModelPartBuilder.create().uv(22, 85).cuboid(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 85).cuboid(-3.0F, -4.0F, -1.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 17.0F, 0.6981F, 0.0F, 0.0F));

        ModelPartData ridges_r2 = mainbody.addChild("ridges_r2", ModelPartBuilder.create().uv(80, 84).cuboid(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(12, 85).cuboid(-3.0F, -4.0F, -1.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 13.0F, 0.6981F, 0.0F, 0.0F));

        ModelPartData ridges_r3 = mainbody.addChild("ridges_r3", ModelPartBuilder.create().uv(80, 67).cuboid(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(84, 21).cuboid(-3.0F, -4.0F, -1.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 9.0F, 0.6981F, 0.0F, 0.0F));

        ModelPartData ridges_r4 = mainbody.addChild("ridges_r4", ModelPartBuilder.create().uv(70, 84).cuboid(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(60, 84).cuboid(-3.0F, -4.0F, -1.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 5.0F, 0.6981F, 0.0F, 0.0F));

        ModelPartData ridges_r5 = mainbody.addChild("ridges_r5", ModelPartBuilder.create().uv(80, 59).cuboid(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(80, 51).cuboid(-3.0F, -4.0F, -1.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 1.0F, 0.6981F, 0.0F, 0.0F));

        ModelPartData head = torso.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -13.0F, 0.0F));

        ModelPartData neck = head.addChild("neck", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, -1.0F));

        ModelPartData neck_r1 = neck.addChild("neck_r1", ModelPartBuilder.create().uv(64, 47).cuboid(-2.0F, -5.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.6545F, 0.0F, 0.0F));

        ModelPartData skull = neck.addChild("skull", ModelPartBuilder.create().uv(64, 30).cuboid(-3.0F, -3.0F, -6.0F, 6.0F, 3.0F, 9.0F, new Dilation(0.0F))
                .uv(80, 47).cuboid(-2.0F, 0.0F, -4.0F, 4.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 86).cuboid(-2.0F, -5.0F, 0.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(72, 0).cuboid(-2.0F, -4.0F, -5.0F, 4.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, -2.0F));

        ModelPartData rightridge_r1 = skull.addChild("rightridge_r1", ModelPartBuilder.create().uv(64, 66).cuboid(-1.0F, -2.0F, -6.0F, 1.0F, 2.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -2.0F, 1.0F, 0.0F, 0.0F, 0.6545F));

        ModelPartData leftridge_r1 = skull.addChild("leftridge_r1", ModelPartBuilder.create().uv(64, 57).cuboid(0.0F, -2.0F, -6.0F, 1.0F, 2.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -2.0F, 1.0F, 0.0F, 0.0F, -0.6545F));

        ModelPartData frontrightleg = body.addChild("frontrightleg", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData rightupperleg = frontrightleg.addChild("rightupperleg", ModelPartBuilder.create().uv(72, 21).cuboid(-7.0F, 2.0F, -1.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -13.0F, 0.0F));

        ModelPartData cube_r3 = rightupperleg.addChild("cube_r3", ModelPartBuilder.create().uv(88, 6).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 3.0F, 0.0F, 0.0F, 0.0F, 0.48F));

        ModelPartData rightlowerleg = rightupperleg.addChild("rightlowerleg", ModelPartBuilder.create().uv(12, 77).cuboid(-2.0F, 0.0F, -1.0F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 8.0F, 0.0F));

        ModelPartData frontleftleg = body.addChild("frontleftleg", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData leftupperleg = frontleftleg.addChild("leftupperleg", ModelPartBuilder.create().uv(64, 75).cuboid(4.0F, 2.0F, -1.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -13.0F, 0.0F));

        ModelPartData cube_r4 = leftupperleg.addChild("cube_r4", ModelPartBuilder.create().uv(88, 11).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, 3.0F, 0.0F, 0.0F, 0.0F, -0.48F));

        ModelPartData leftlowerleg = leftupperleg.addChild("leftlowerleg", ModelPartBuilder.create().uv(24, 77).cuboid(-1.0F, 0.0F, 0.0F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 8.0F, -1.0F));

        ModelPartData backrightleg = body.addChild("backrightleg", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, -8.0F, 18.0F));

        ModelPartData backrightupperleg = backrightleg.addChild("backrightupperleg", ModelPartBuilder.create().uv(0, 77).cuboid(-2.0F, 0.0F, -1.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-9.0F, -3.0F, 0.0F));

        ModelPartData cube_r5 = backrightupperleg.addChild("cube_r5", ModelPartBuilder.create().uv(88, 16).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.48F));

        ModelPartData backrightlowerleg = backrightupperleg.addChild("backrightlowerleg", ModelPartBuilder.create().uv(48, 77).cuboid(-2.0F, 0.0F, -1.0F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.0F, 0.0F));

        ModelPartData backleftleg = body.addChild("backleftleg", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, -8.0F, 18.0F));

        ModelPartData backleftupperleg = backleftleg.addChild("backleftupperleg", ModelPartBuilder.create().uv(76, 75).cuboid(-1.0F, 0.0F, -1.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(9.0F, -3.0F, 0.0F));

        ModelPartData cube_r6 = backleftupperleg.addChild("cube_r6", ModelPartBuilder.create().uv(86, 42).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.48F));

        ModelPartData backleftlowerleg = backleftupperleg.addChild("backleftlowerleg", ModelPartBuilder.create().uv(36, 77).cuboid(-1.0F, 0.0F, -1.0F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }
    @Override
    public void setAngles(DogThingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(ModAnimations.dog_walk, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, ModAnimations.dog_idle, ageInTicks, 1f);
        this.updateAnimation(entity.attackAnimationState, ModAnimations.dog_attack, ageInTicks, 1f);
    }
    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.skull.yaw = headYaw * 0.017453292F;
        this.skull.pitch = headPitch * 0.017453292F;
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
    @Override
    public ModelPart getPart() {
        return body;
    }
}
