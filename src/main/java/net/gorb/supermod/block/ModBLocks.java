package net.gorb.supermod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gorb.supermod.SuperMod;
import net.gorb.supermod.block.custom.PinHolderBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBLocks {

    public static Block PIN_HOLDER = registerBlock("pin_holder", new PinHolderBlock(AbstractBlock.Settings.create().nonOpaque()));

    public static Block BOWLINGIUM_BLOCK = registerBlock("bowlingium_block",
            new ExperienceDroppingBlock(UniformIntProvider.create(2,5), AbstractBlock.Settings.create().strength(4f).requiresTool().sounds(BlockSoundGroup.BASALT)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(SuperMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(SuperMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));

    }

    public static void registerModBlocks() {
        SuperMod.LOGGER.info("Registering Mod Blocks");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(fabricItemGroupEntries ->
        {
            fabricItemGroupEntries.add(ModBLocks.BOWLINGIUM_BLOCK);
        });
    }
}
