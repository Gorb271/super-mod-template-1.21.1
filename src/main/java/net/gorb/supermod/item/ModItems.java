package net.gorb.supermod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gorb.supermod.SuperMod;
import net.gorb.supermod.item.custom.BowlingBallItem;
import net.gorb.supermod.item.custom.LabutItem;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static Item BOWLINGIUM = registerItem("bowlingium", new Item(new Item.Settings()));
    public static Item LABUT = registerItem("labut", new LabutItem(new Item.Settings()));
    public static Item BOWLINGBALL = registerItem("bowling_ball", new BowlingBallItem(new Item.Settings()));
    public static Item EMRE_SAHIN_SPAWN_EGG = registerItem("emre_sahin_spawn_egg", new SpawnEggItem( EntityType.PIGLIN, 0,1,new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(SuperMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SuperMod.LOGGER.info("Registering ModItems");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(LABUT);
            fabricItemGroupEntries.add(BOWLINGBALL);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(BOWLINGIUM);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(EMRE_SAHIN_SPAWN_EGG);
        });
    }
}
