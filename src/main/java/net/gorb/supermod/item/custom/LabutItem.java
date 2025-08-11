package net.gorb.supermod.item.custom;

import net.gorb.supermod.entity.ModEntities; // Your registry of entities
import net.gorb.supermod.entity.custom.PinEntity; // Your actual pin entity class
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

public class LabutItem extends Item {
    public LabutItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();

        if (!world.isClient) {
            BlockPos pos = context.getBlockPos().offset(context.getSide());

            PinEntity pin = new PinEntity(ModEntities.PIN_ENTITY, world);
            pin.refreshPositionAndAngles(
                    pos.getX() + 0.5,
                    pos.getY(),
                    pos.getZ() + 0.5,
                    0, 0
            );

            world.spawnEntity(pin);

            // Remove 1 pin item if not in creative mode
            if (!context.getPlayer().isCreative()) {
                context.getStack().decrement(1);
            }
        }

        return ActionResult.SUCCESS;
    }
}