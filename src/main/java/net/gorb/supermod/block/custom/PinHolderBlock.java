package net.gorb.supermod.block.custom;

import com.mojang.serialization.MapCodec;
import net.gorb.supermod.block.entity.custom.PinHolderBlockEntity;
import net.gorb.supermod.entity.ModEntities;
import net.gorb.supermod.entity.custom.PinEntity;
import net.gorb.supermod.item.custom.LabutItem;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PinHolderBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    private static final List<Vec3d> PIN_LAYOUT;
    public static final MapCodec<PinHolderBlock> CODEC = PinHolderBlock.createCodec(PinHolderBlock::new);
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);

    static {
        List<Vec3d> layout = new ArrayList<>();

        final double ROW_SEPARATION = 0.8;
        final double PIN_SEPARATION_IN_ROW = 0.8;
        final double HEIGHT_OFFSET = 0.55;
        final double OVERALL_X_OFFSET = -1.0;
        final double OVERALL_Z_OFFSET = 0.5;

        // Row 1 (head pin)
        double x1 = OVERALL_X_OFFSET;
        layout.add(new Vec3d(x1, HEIGHT_OFFSET, OVERALL_Z_OFFSET));

        // Row 2
        double x2 = x1 + ROW_SEPARATION;
        layout.add(new Vec3d(x2, HEIGHT_OFFSET, OVERALL_Z_OFFSET - 0.5 * PIN_SEPARATION_IN_ROW));
        layout.add(new Vec3d(x2, HEIGHT_OFFSET, OVERALL_Z_OFFSET + 0.5 * PIN_SEPARATION_IN_ROW));

        // Row 3
        double x3 = x2 + ROW_SEPARATION;
        layout.add(new Vec3d(x3, HEIGHT_OFFSET, OVERALL_Z_OFFSET - PIN_SEPARATION_IN_ROW));
        layout.add(new Vec3d(x3, HEIGHT_OFFSET, OVERALL_Z_OFFSET));
        layout.add(new Vec3d(x3, HEIGHT_OFFSET, OVERALL_Z_OFFSET + PIN_SEPARATION_IN_ROW));

        // Row 4
        double x4 = x3 + ROW_SEPARATION;
        layout.add(new Vec3d(x4, HEIGHT_OFFSET, OVERALL_Z_OFFSET - 1.5 * PIN_SEPARATION_IN_ROW));
        layout.add(new Vec3d(x4, HEIGHT_OFFSET, OVERALL_Z_OFFSET - 0.5 * PIN_SEPARATION_IN_ROW));
        layout.add(new Vec3d(x4, HEIGHT_OFFSET, OVERALL_Z_OFFSET + 0.5 * PIN_SEPARATION_IN_ROW));
        layout.add(new Vec3d(x4, HEIGHT_OFFSET, OVERALL_Z_OFFSET + 1.5 * PIN_SEPARATION_IN_ROW));

        PIN_LAYOUT = Collections.unmodifiableList(layout);
    }

    public PinHolderBlock(Settings settings) {
        super(settings.noCollision());
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PinHolderBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            return;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof PinHolderBlockEntity pin_holder && !world.isClient) {
            pin_holder.clearSpawnedPins((ServerWorld) world);
            ItemScatterer.spawn(world, pos, (PinHolderBlockEntity) blockEntity);
            world.updateComparators(pos, this);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof PinHolderBlockEntity pinHolder)) {
            return ActionResult.FAIL;
        }

        ItemStack heldItem = player.getStackInHand(Hand.MAIN_HAND);

        if (heldItem.getItem() instanceof LabutItem) {
            int emptySlot = -1;
            for (int i = 0; i < pinHolder.size(); i++) {
                if (pinHolder.getStack(i).isEmpty()) {
                    emptySlot = i;
                    break;
                }
            }

            if (emptySlot != -1) {
                Vec3d offset = rotateOffset(PIN_LAYOUT.get(emptySlot), state.get(FACING));

                double spawnX = pos.getX() + offset.x;
                double spawnY = pos.getY() + offset.y;
                double spawnZ = pos.getZ() + offset.z;

                ItemStack toStore = heldItem.copy();
                toStore.setCount(1);
                pinHolder.setStack(emptySlot, toStore);

                if (world instanceof ServerWorld serverWorld) {
                    PinEntity pinEntity = ModEntities.PIN_ENTITY.create(serverWorld);
                    if (pinEntity != null) {
                        pinEntity.refreshPositionAndAngles(
                                spawnX, spawnY, spawnZ,
                                world.random.nextFloat() * 360,
                                0);
                        serverWorld.spawnEntity(pinEntity);
                        pinHolder.setSpawnedPin(emptySlot, pinEntity.getUuid());
                    }
                }

                if (!player.getAbilities().creativeMode) {
                    heldItem.decrement(1);
                }

                pinHolder.markDirty();
                return ActionResult.SUCCESS;
            } else {
                player.sendMessage(Text.literal("Pin Holder is full!"), true);
                return ActionResult.CONSUME;
            }
        }

        return ActionResult.PASS;
    }

    private static Vec3d rotateOffset(Vec3d original, Direction facing) {
        double cx = original.x - 0.5;
        double cz = original.z - 0.5;

        double rx = cx;
        double rz = cz;

        switch (facing) {
            case NORTH:
                rx = -cz;
                rz = cx;
                break;
            case EAST:
                rx = -cx;
                rz = -cz;
                break;
            case SOUTH:
                rx = cz;
                rz = -cx;
                break;
            case WEST:
            default:
                break;
        }
        return new Vec3d(rx + 0.5, original.y, rz + 0.5);
    }
}
