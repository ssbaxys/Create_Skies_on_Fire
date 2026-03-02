package com.ssbaxys.createskiesonfire;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RocketNoseConeBlock extends Block {
    private static final VoxelShape SHAPE = makeShape();

    public RocketNoseConeBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    private static VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Block.box(2, 0, 2, 14, 6, 14), BooleanOp.OR);
        shape = Shapes.join(shape, Block.box(4, 6, 4, 12, 10, 12), BooleanOp.OR);
        shape = Shapes.join(shape, Block.box(6, 10, 6, 10, 14, 10), BooleanOp.OR);
        shape = Shapes.join(shape, Block.box(7, 14, 7, 9, 16, 9), BooleanOp.OR);
        return shape;
    }
}
