package com.ssbaxys.createskiesonfire;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RocketBaseBlock extends Block {
    private static final VoxelShape SHAPE = makeShape();

    public RocketBaseBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    private static VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Block.box(1, 0, 1, 15, 16, 15), BooleanOp.OR);
        return shape;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos,
            boolean isMoving) {
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
        if (!level.isClientSide) {
            if (level.hasNeighborSignal(pos)) {
                launchRocket((ServerLevel) level, pos);
            }
        }
    }

    private void launchRocket(ServerLevel level, BlockPos startPos) {
        // Find the height of the rocket
        int height = 0;
        BlockPos currentPos = startPos;
        while (level.getBlockState(currentPos).getBlock() instanceof RocketBaseBlock ||
                level.getBlockState(currentPos).getBlock() instanceof RocketHullBlock ||
                level.getBlockState(currentPos).getBlock() instanceof RocketNoseConeBlock ||
                level.getBlockState(currentPos).getBlock() instanceof RocketThrusterBlock) {
            height++;
            currentPos = currentPos.above();
        }

        if (height > 0) {
            // Play launch sound
            level.playSound(null, startPos, SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.BLOCKS, 2.0F, 0.5F);

            // In a simple implementation, we can just delete the blocks to "launch" it,
            // or we could replace them with a falling block entity moving upwards.
            // Let's spawn an explosion effect and remove the blocks for now.
            for (int i = height - 1; i >= 0; i--) {
                BlockPos p = startPos.above(i);
                level.setBlock(p, Blocks.AIR.defaultBlockState(), 3);
            }

            // Spawn some explosion particles
            level.sendParticles(net.minecraft.core.particles.ParticleTypes.EXPLOSION, startPos.getX() + 0.5,
                    startPos.getY(), startPos.getZ() + 0.5, 10, 1, 1, 1, 0.1);
        }
    }
}
