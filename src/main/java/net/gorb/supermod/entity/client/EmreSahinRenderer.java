package net.gorb.supermod.entity.client;

import net.gorb.supermod.SuperMod;
import net.gorb.supermod.entity.custom.EmreSahinEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class EmreSahinRenderer extends MobEntityRenderer<EmreSahinEntity, EmreSahinModel<EmreSahinEntity>> {

    public EmreSahinRenderer(EntityRendererFactory.Context context) {
        super(context, new EmreSahinModel<>(context.getPart(EmreSahinModel.EmreSahin)), 0.5f);
    }

    @Override
    public Identifier getTexture(EmreSahinEntity entity) {
        return Identifier.of(SuperMod.MOD_ID, "textures/entity/emre/emre_sahin.png");
    }
    @Override
    public void render(EmreSahinEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if (livingEntity.isBaby()){
            matrixStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            matrixStack.scale(1.0F, 1.0F, 1.0F);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
