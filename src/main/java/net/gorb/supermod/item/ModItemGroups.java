package net.gorb.supermod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.gorb.supermod.SuperMod;
import net.gorb.supermod.block.ModBLocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static void registerItemGroups() {
        SuperMod.LOGGER.info("Registering ItemGroups for SuperMod");
    }
    public static final ItemGroup SUPER_MOD_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(SuperMod.MOD_ID, "super_mod_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.LABUT)).displayName(Text.translatable("itemgroup.supermod.super_mod_items"))
                    .entries(((displayContext, entries) -> {
                        entries.add(new ItemStack(ModItems.LABUT));
                        entries.add(new ItemStack(ModBLocks.BOWLINGIUM_BLOCK));
                        entries.add(new ItemStack(ModItems.BOWLINGIUM));
                        entries.add(new ItemStack(ModItems.BOWLINGBALL));
                        entries.add(new ItemStack(ModItems.EMRE_SAHIN_SPAWN_EGG));
                        entries.add(new ItemStack(ModBLocks.PIN_HOLDER));
                    })).build());

}
