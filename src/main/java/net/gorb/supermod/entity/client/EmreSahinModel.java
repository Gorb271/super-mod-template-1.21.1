package net.gorb.supermod.entity.client;

import net.gorb.supermod.SuperMod;
import net.gorb.supermod.entity.custom.EmreSahinEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class EmreSahinModel<T extends EmreSahinEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer EmreSahin = new EntityModelLayer(Identifier.of(SuperMod.MOD_ID, "emre"), "main");

    private final ModelPart EMRE;
    private final ModelPart solbacak;
    private final ModelPart sagbacak;
    private final ModelPart govde;
    private final ModelPart solkol;
    private final ModelPart sagkol;
    private final ModelPart kafa;
    public EmreSahinModel(ModelPart root) {
        this.EMRE = root.getChild("EMre");
        this.solbacak = this.EMRE.getChild("solbacak");
        this.sagbacak = this.EMRE.getChild("sagbacak");
        this.govde = this.EMRE.getChild("govde");
        this.solkol = this.EMRE.getChild("solkol");
        this.sagkol = this.EMRE.getChild("sagkol");
        this.kafa = this.EMRE.getChild("kafa");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        // The following line has been changed to apply a 90-degree rotation to the right (Y-axis)
        ModelPartData EMre = modelPartData.addChild("EMre", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData solbacak = EMre.addChild("solbacak", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -6.0F, 2.0F));

        ModelPartData sagbacak = EMre.addChild("sagbacak", ModelPartBuilder.create().uv(32, 48).cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -6.0F, -2.0F));

        ModelPartData govde = EMre.addChild("govde", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -18.0F, 0.0F));

        ModelPartData cube_r1 = govde.addChild("cube_r1", ModelPartBuilder.create().uv(5, 36).cuboid(-3.0F, -12.0F, -1.0F, 4.0F, 12.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 6.0F, 3.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData solkol = EMre.addChild("solkol", ModelPartBuilder.create().uv(48, 48).cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 4.0F));

        ModelPartData sagkol = EMre.addChild("sagkol", ModelPartBuilder.create().uv(40, 16).cuboid(-2.0F, 0.0F, -4.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, -4.0F));

        ModelPartData kafa = EMre.addChild("kafa", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

        ModelPartData cube_r2 = kafa.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void setAngles(EmreSahinEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(EmreSahinAnimations.EMRE_WALK, limbSwing, limbSwingAmount,40f,50f);
        this.updateAnimation(entity.idleAnimationState, EmreSahinAnimations.EMRE_IDLE, ageInTicks, 1f);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        EMRE.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return EMRE;
    }
    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0f);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.kafa.yaw = headYaw * 0.017453292F;
        // Because the model is rotated, we apply pitch to the roll axis.
        // The value is negated to ensure the direction is correct.
        this.kafa.roll = -headPitch * 0.017453292F;
    }
}