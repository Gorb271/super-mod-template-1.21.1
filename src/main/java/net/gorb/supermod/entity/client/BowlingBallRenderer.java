package net.gorb.supermod.entity.client;

import net.gorb.supermod.SuperMod;
import net.gorb.supermod.entity.custom.BowlingBallEntity;
import net.gorb.supermod.entity.custom.PinEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BowlingBallRenderer extends LivingEntityRenderer<BowlingBallEntity, BowlingBallModel<BowlingBallEntity>> {

    public BowlingBallRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new BowlingBallModel<>(ctx.getPart(BowlingBallModel.BowlingBall)), 0.2f);
    }

    @Override
    public Identifier getTexture(BowlingBallEntity entity) {
        return Identifier.of(SuperMod.MOD_ID, "textures/entity/bowling_ball/bowling_ball.png");
    }
    @Override
    public void render(BowlingBallEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
