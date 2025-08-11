package net.gorb.supermod.block.entity;

import net.gorb.supermod.SuperMod;
import net.gorb.supermod.block.ModBLocks;
import net.gorb.supermod.block.entity.custom.PinHolderBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<PinHolderBlockEntity> PIN_HOLDER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(SuperMod.MOD_ID, "pin_holder_block_entity"),
                    BlockEntityType.Builder.create(PinHolderBlockEntity::new, ModBLocks.PIN_HOLDER).build(null));

    public static void registerBlockEntities() {
        SuperMod.LOGGER.info("Registering block entities");

    }
}
