package net.gorb.supermod.entity.client;

import net.gorb.supermod.SuperMod;
import net.gorb.supermod.entity.custom.PinEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class PinModel<T extends PinEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer Pin = new EntityModelLayer(Identifier.of(SuperMod.MOD_ID, "pin"), "main");

    private final ModelPart pin;
    public PinModel(ModelPart root) {
        this.pin = root.getChild("pin");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData PIN = modelPartData.addChild("pin", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -8.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(-3.0F, -6.0F, -2.0F, 1.0F, 5.0F, 4.0F, new Dilation(0.0F))
                .uv(10, 12).cuboid(2.0F, -6.0F, -2.0F, 1.0F, 5.0F, 4.0F, new Dilation(0.0F))
                .uv(26, 0).cuboid(-3.0F, -7.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(26, 3).cuboid(2.0F, -7.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 21).cuboid(-2.0F, -9.0F, -1.0F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(24, 26).cuboid(-1.0F, -9.0F, -2.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 28).cuboid(-1.0F, -9.0F, 1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(20, 18).cuboid(-1.0F, -15.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
                .uv(12, 21).cuboid(1.0F, -14.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 24).cuboid(-2.0F, -14.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = PIN.addChild("cube_r1", ModelPartBuilder.create().uv(12, 25).cuboid(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -12.0F, 1.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r2 = PIN.addChild("cube_r2", ModelPartBuilder.create().uv(6, 24).cuboid(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -12.0F, -2.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r3 = PIN.addChild("cube_r3", ModelPartBuilder.create().uv(18, 26).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.0F, 2.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r4 = PIN.addChild("cube_r4", ModelPartBuilder.create().uv(26, 6).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.0F, -3.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r5 = PIN.addChild("cube_r5", ModelPartBuilder.create().uv(20, 9).cuboid(0.0F, -5.0F, -1.0F, 1.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -1.0F, -3.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r6 = PIN.addChild("cube_r6", ModelPartBuilder.create().uv(16, 0).cuboid(0.0F, -5.0F, -1.0F, 1.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -1.0F, 2.0F, 0.0F, -1.5708F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        pin.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return pin;
    }


    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}
