package net.gorb.supermod.entity.custom.ai.goal;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.item.Item;

import java.util.EnumSet;
import java.util.List;

public class PickUpItemGoal extends Goal {
    private final PathAwareEntity mob;
    private final Item targetItem;
    private final double speed;
    private ItemEntity targetItemEntity;

    public PickUpItemGoal(PathAwareEntity mob, Item targetItem, double speed) {
        this.mob = mob;
        this.targetItem = targetItem;
        this.speed = speed;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (this.mob.getEquippedStack(net.minecraft.entity.EquipmentSlot.MAINHAND).isEmpty()) {
            List<ItemEntity> itemEntities = this.mob.getWorld().getEntitiesByClass(ItemEntity.class, this.mob.getBoundingBox().expand(8.0D, 8.0D, 8.0D), itemEntity -> itemEntity.getStack().getItem() == this.targetItem);
            if (!itemEntities.isEmpty()) {
                this.targetItemEntity = itemEntities.get(0);
                return true;
            }
        }
        return false;
    }

    @Override
    public void start() {
        if (this.targetItemEntity != null) {
            this.mob.getNavigation().startMovingTo(this.targetItemEntity, this.speed);
        }
    }

    @Override
    public boolean shouldContinue() {
        return this.targetItemEntity != null && !this.targetItemEntity.isRemoved() && this.mob.getEquippedStack(net.minecraft.entity.EquipmentSlot.MAINHAND).isEmpty() && !this.mob.getNavigation().isIdle();
    }

    @Override
    public void tick() {
        if (this.targetItemEntity != null && !this.targetItemEntity.isRemoved() && this.mob.getPos().distanceTo(this.targetItemEntity.getPos()) < 2.0D) {
            this.mob.equipStack(net.minecraft.entity.EquipmentSlot.MAINHAND, this.targetItemEntity.getStack().copy());
            this.targetItemEntity.discard();
            this.targetItemEntity = null;
        }
    }

    @Override
    public void stop() {
        this.targetItemEntity = null;
        this.mob.getNavigation().stop();
    }
}