package com.ssbaxys.createskiesonfire;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.util.RandomSource;

public class RocketThrusterBlock extends Block {
    private static final VoxelShape SHAPE = makeShape();

    public RocketThrusterBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    private static VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Block.box(4, 0, 4, 12, 4, 12), BooleanOp.OR);
        shape = Shapes.join(shape, Block.box(3, 4, 3, 13, 10, 13), BooleanOp.OR);
        shape = Shapes.join(shape, Block.box(2, 10, 2, 14, 16, 14), BooleanOp.OR);
        return shape;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (level.hasNeighborSignal(pos)) {
            level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pos.getX() + 0.5D, pos.getY() - 0.1D,
                    pos.getZ() + 0.5D, 0.0D, -0.1D, 0.0D);
            level.addParticle(ParticleTypes.FLAME, pos.getX() + 0.5D, pos.getY() + 0.1D, pos.getZ() + 0.5D, 0.0D, -0.2D,
                    0.0D);
            if (random.nextInt(10) == 0) {
                level.playLocalSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
                        SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }
        }
    }
}
