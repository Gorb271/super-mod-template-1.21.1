package net.gorb.supermod.block.entity.custom;

import net.gorb.supermod.block.entity.ImplementedInventory;
import net.gorb.supermod.block.entity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class PinHolderBlockEntity extends BlockEntity implements ImplementedInventory {
    private final UUID[] spawnedPinUuids = new UUID[10];
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(10, ItemStack.EMPTY);

    public PinHolderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PIN_HOLDER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);

        NbtList list = new NbtList();
        for (UUID uuid : this.spawnedPinUuids) {
            if (uuid != null) {
                list.add(NbtString.of(uuid.toString()));
            } else {
                list.add(NbtString.of(""));
            }
        }
        nbt.put("spawnedPins", list);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);

        NbtList list = nbt.getList("spawnedPins", 8);
        for (int i = 0; i < spawnedPinUuids.length && i < list.size(); i++) {
            String uuid = list.getString(i);
            spawnedPinUuids[i] = uuid.isEmpty() ? null : UUID.fromString(uuid);
        }
    }

    public void setSpawnedPin(int slot, @Nullable UUID uuid) {
        if (slot >= 0 && slot < spawnedPinUuids.length) {
            spawnedPinUuids[slot] = uuid;
        }
        markDirty();
    }

    public void clearSpawnedPin(int slot) {
        if (slot >= 0 && slot < spawnedPinUuids.length) {
            spawnedPinUuids[slot] = null;
        }
        markDirty();
    }

    @Nullable
    public UUID getSpawnedPin(int slot) {
        if (slot >= 0 && slot < spawnedPinUuids.length) {
            return spawnedPinUuids[slot];
        }
        return null;
    }

    public void clearSpawnedPins(ServerWorld world) {
        for (int i = 0; i < spawnedPinUuids.length; i++) {
            UUID uuid = spawnedPinUuids[i];
            if (uuid == null) continue;
            Entity pin = world.getEntity(uuid);
            if (pin != null) {
                pin.remove(Entity.RemovalReason.DISCARDED);
            }
            spawnedPinUuids[i] = null;
        }
        markDirty();
    }
}

