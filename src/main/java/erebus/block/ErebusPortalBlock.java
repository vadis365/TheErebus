package erebus.block;

import erebus.registries.ModBlocks;
import erebus.registries.world.ModDimensionRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class ErebusPortalBlock extends Block implements Portal {

    public static final BlockPattern FRAME = BlockPatternBuilder.start()
            .aisle("SSSS")
            .aisle("SPPS")
            .aisle("SPPS")
            .aisle("SPPS")
            .aisle("SSSS")
            .where('S', block -> block.getState().is(Blocks.MOSSY_STONE_BRICKS))
            .where('P', block -> block.getState().is(ModBlocks.PORTAL) || block.getState().is(Blocks.OAK_LEAVES))
            .build();

    public ErebusPortalBlock(Properties properties) {
        super(properties);
    }

    public static boolean makePortal(Level level, BlockPos pos, Direction dir) {
        level.setBlock(pos.offset(dir.getStepZ(), 2, dir.getStepX()), Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 18);
        level.setBlock(pos.offset(0, 2, 0), Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 18);
        level.setBlock(pos.offset(-dir.getStepZ(), 2, -dir.getStepX()), Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 18);
        level.setBlock(pos.offset(dir.getStepZ(), 1, dir.getStepX()), Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 18);
        level.setBlock(pos.offset(-dir.getStepZ(), 1, -dir.getStepX()), Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 18);
        level.setBlock(pos.offset(dir.getStepZ(), 0, dir.getStepX()), Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 18);
        level.setBlock(pos.offset(-dir.getStepZ(), 0, -dir.getStepX()), Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 18);
        level.setBlock(pos.offset(dir.getStepZ(), -1, dir.getStepX()), Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 18);
        level.setBlock(pos.offset(0, -1, 0), Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 18);
        level.setBlock(pos.offset(-dir.getStepZ(), -1, -dir.getStepX()), Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 18);

        if (isPatternValid(level, pos)) {
            level.setBlock(pos, ModBlocks.PORTAL.get().defaultBlockState(), 18);
            level.setBlock(pos, ModBlocks.PORTAL.get().defaultBlockState(), 18);
            return true;
        }
        return false;
    }

    public static boolean isPatternValid(LevelAccessor level, BlockPos pos) {
        return FRAME.find(level, pos) != null;
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity.canUsePortal(false)) {
            entity.setAsInsidePortal(this, entity.blockPosition());
        }
    }

    @Override
    public @Nullable DimensionTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {
        ResourceKey<Level> dimension = level.dimension() != ModDimensionRegistries.DIMENSION_KEY ? ModDimensionRegistries.DIMENSION_KEY : Level.OVERWORLD;
        ServerLevel server = level.getServer().getLevel(dimension);

        if (server == null) {
            return null;
        }

        WorldBorder border = server.getWorldBorder();
        double scale = DimensionType.getTeleportationScale(level.dimensionType(), server.dimensionType());
        BlockPos newPos = border.clampToBounds(pos.getX() * scale, pos.getY(), pos.getZ() * scale);
        return createTransition(server, entity, newPos);
    }

    @Override
    public int getPortalTransitionTime(ServerLevel level, Entity entity) {
        return entity instanceof Player player ? player.getAbilities().invulnerable ? 1 : 80 : 0;
    }

    public DimensionTransition createTransition(ServerLevel level, Entity entity, BlockPos pos) {
        return new DimensionTransition(level, Vec3.atCenterOf(pos), Vec3.ZERO, entity.getYRot(), entity.getXRot(), DimensionTransition.PLACE_PORTAL_TICKET);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        for (int i = 0; i < 4; i++) {
            double particleX = pos.getX() + random.nextFloat();
            double particleY = pos.getY() + random.nextFloat();
            double particleZ = pos.getZ() + random.nextFloat();
            double motionX;
            double motionY;
            double motionZ;
            float multi = (random.nextFloat() * 2.0F - 1.0F) / 4.0F;

            motionX = (random.nextFloat() - 0.5D) * 0.25D;
            motionY = (random.nextFloat() - 0.5D) * 0.25D;
            motionZ = (random.nextFloat() - 0.5D) * 0.25D;

            if (!level.getBlockState(pos.offset(-1, 0, 0)).is(this) && !level.getBlockState(pos.offset(1, 0, 0)).is(this)) {
                particleX = pos.getX() + 0.5D + 0.25D * multi;
                motionX = random.nextFloat() * 2.0F * multi;
            } else {
                particleZ = pos.getZ() + 0.5D + 0.25D * multi;
                motionZ = random.nextFloat() * 2.0F * multi;
            }

            level.addParticle(ParticleTypes.PORTAL, particleX, particleY, particleZ, motionX, motionY, motionZ);
        }

        if (random.nextInt(20) == 0) {
            level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.3F, random.nextFloat() * 0.4F + 0.8F, true);
        }
    }
}
