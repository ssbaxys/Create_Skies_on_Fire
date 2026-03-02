package com.ssbaxys.createskiesonfire;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RocketHullBlock extends Block {
    private static final VoxelShape SHAPE = makeShape();

    public RocketHullBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    private static VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Block.box(2, 0, 0, 14, 16, 16), BooleanOp.OR);
        shape = Shapes.join(shape, Block.box(0, 0, 2, 16, 16, 14), BooleanOp.OR);
        return shape;
    }
}
