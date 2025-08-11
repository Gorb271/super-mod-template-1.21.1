package net.gorb.supermod.entity.client;

import net.gorb.supermod.SuperMod;
import net.gorb.supermod.entity.custom.BowlingBallEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BowlingBallModel<T extends BowlingBallEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer BowlingBall = new EntityModelLayer(Identifier.of(SuperMod.MOD_ID, "bowling_ball"), "main");

    private final ModelPart bowling_ball;
    public BowlingBallModel(ModelPart root) {
        this.bowling_ball = root.getChild("bowling_ball");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData BOWLING_BALL = modelPartData.addChild("bowling_ball", ModelPartBuilder.create().uv(0, 20).cuboid(-2.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(24, 4).cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 6).cuboid(-1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 10).cuboid(-3.0F, -2.0F, -2.0F, 6.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(12, 23).cuboid(-2.0F, -2.0F, -3.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(22, 23).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-3.0F, -6.0F, -3.0F, 6.0F, 4.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 15).cuboid(-3.0F, -7.0F, -2.0F, 6.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(24, 0).cuboid(-2.0F, -7.0F, 2.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 2).cuboid(-2.0F, -7.0F, -3.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 8).cuboid(-1.0F, -8.0F, -2.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 19).cuboid(-1.0F, -8.0F, 1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(20, 10).cuboid(-2.0F, -8.0F, -1.0F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(24, 21).cuboid(-1.0F, -6.0F, -4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 25).cuboid(-1.0F, -3.0F, -4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(18, 25).cuboid(-1.0F, -6.0F, 3.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 25).cuboid(-1.0F, -3.0F, 3.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = BOWLING_BALL.addChild("cube_r1", ModelPartBuilder.create().uv(18, 27).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 27).cuboid(-1.0F, 2.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -5.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r2 = BOWLING_BALL.addChild("cube_r2", ModelPartBuilder.create().uv(6, 26).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 26).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -2.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r3 = BOWLING_BALL.addChild("cube_r3", ModelPartBuilder.create().uv(0, 23).cuboid(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -4.0F, -1.0F, 0.0F, 1.5708F, 1.5708F));

        ModelPartData cube_r4 = BOWLING_BALL.addChild("cube_r4", ModelPartBuilder.create().uv(20, 16).cuboid(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -4.0F, -1.0F, 0.0F, 1.5708F, 1.5708F));

        ModelPartData cube_r5 = BOWLING_BALL.addChild("cube_r5", ModelPartBuilder.create().uv(20, 13).cuboid(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -4.0F, -4.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData cube_r6 = BOWLING_BALL.addChild("cube_r6", ModelPartBuilder.create().uv(12, 20).cuboid(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -4.0F, 3.0F, -1.5708F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        bowling_ball.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return bowling_ball;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}