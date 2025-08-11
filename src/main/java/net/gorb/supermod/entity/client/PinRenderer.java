package net.gorb.supermod.entity.client;


import net.gorb.supermod.SuperMod;
import net.gorb.supermod.entity.custom.PinEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PinRenderer extends LivingEntityRenderer<PinEntity, PinModel<PinEntity>> {
    public PinRenderer(EntityRendererFactory.Context context) {
        super(context, new PinModel<>(context.getPart(PinModel.Pin)), 0.1f);
    }

    @Override
    public Identifier getTexture(PinEntity entity) {
        return Identifier.of(SuperMod.MOD_ID, "textures/entity/pin/pin.png");
    }
    @Override
    public void render(PinEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
    @Override
    protected boolean hasLabel(PinEntity livingEntity) {
        return false;
    }
}