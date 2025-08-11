package net.gorb.supermod.entity;

import net.gorb.supermod.SuperMod;
import net.gorb.supermod.entity.custom.BowlingBallEntity;
import net.gorb.supermod.entity.custom.EmreSahinEntity;
import net.gorb.supermod.entity.custom.PinEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<EmreSahinEntity> EMRE_SAHIN_ENTITY = Registry.register(Registries.ENTITY_TYPE, Identifier.of(SuperMod.MOD_ID, "emre_sahin"),
            EntityType.Builder.create(EmreSahinEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f,2.5f).build());

    public static final EntityType<PinEntity> PIN_ENTITY =  Registry.register(Registries.ENTITY_TYPE, Identifier.of(SuperMod.MOD_ID, "pin"),
            EntityType.Builder.create(PinEntity::new, SpawnGroup.MISC)
                    .dimensions(0.4f, 0.7f).build());

    public static final EntityType<BowlingBallEntity> BOWLING_BALL_ENTITY = Registry.register(Registries.ENTITY_TYPE, Identifier.of(SuperMod.MOD_ID, "bowling_ball"),
            EntityType.Builder.create(BowlingBallEntity::new, SpawnGroup.MISC)
                    .build());


    public static void registerModEntities() {
        SuperMod.LOGGER.info("Registering ModEntities");

    }

}
