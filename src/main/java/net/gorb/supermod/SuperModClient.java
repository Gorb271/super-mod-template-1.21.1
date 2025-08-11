package net.gorb.supermod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.gorb.supermod.entity.ModEntities;
import net.gorb.supermod.entity.client.*;

public class SuperModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityModelLayerRegistry.registerModelLayer(EmreSahinModel.EmreSahin, EmreSahinModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.EMRE_SAHIN_ENTITY, EmreSahinRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(BowlingBallModel.BowlingBall, BowlingBallModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BOWLING_BALL_ENTITY, BowlingBallRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(PinModel.Pin, PinModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.PIN_ENTITY, PinRenderer::new);
    }
}
