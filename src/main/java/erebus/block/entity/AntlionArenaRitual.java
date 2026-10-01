package erebus.block.entity;

import erebus.block.bamboo.BambooTorchBlock;
import erebus.block.types.EnumTorchBlockHalf;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.phys.Vec3;

import java.util.LinkedHashSet;
import java.util.Set;

final class AntlionArenaRitual {
    private AntlionArenaRitual() {
    }

    static boolean release(ServerLevel level, BlockPos seal) {
        var base = seal.offset(-16, 1, -27);
        if (!level.hasChunksAt(base.offset(11, -8, 11), base.offset(32, 3, 32))) return false;
        var boss = ModEntities.ANTLION_BOSS.get().create(level, EntitySpawnReason.TRIGGERED);
        if (boss == null) return false;
        var spawn = base.offset(21, -8, 21);
        boss.setPos(Vec3.atBottomCenterOf(spawn));
        boss.setInPyramid((byte) 1);
        boss.setSpawnOrigin(spawn);
        if (!level.addFreshEntity(boss)) return false;
        for (var pos : fields(base))
            if (level.getBlockState(pos).is(ModBlocks.FORCE_FIELD)) level.destroyBlock(pos, false);
        for (int x : new int[]{20, 23})
            for (int z : new int[]{20, 23}) {
                var pos = base.offset(x, 0, z);
                var lower = ModBlocks.BAMBOO_TORCH.get().defaultBlockState();
                if (level.isEmptyBlock(pos) && level.isEmptyBlock(pos.above()) && lower.canSurvive(level, pos)) {
                    level.setBlock(pos, lower, 2);
                    level.setBlock(pos.above(), lower.setValue(BambooTorchBlock.HALF, EnumTorchBlockHalf.UPPER), 3);
                }
            }
        for (var potion : java.util.List.of(Potions.NIGHT_VISION, Potions.FIRE_RESISTANCE)) {
            var stack = new ItemStack(Items.POTION);
            stack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
            var item = new ItemEntity(level, seal.getX() + 0.5, seal.getY() + 2, seal.getZ() + 0.5, stack);
            item.setDeltaMovement(Vec3.ZERO);
            level.addFreshEntity(item);
        }
        return true;
    }

    private static Set<BlockPos> fields(BlockPos base) {
        Set<BlockPos> result = new LinkedHashSet<>();
        for (int y = 0; y < 4; y++) {
            for (int w = y; w < 9; w++) {
                result.add(base.offset(11 + w, y, 21));
                result.add(base.offset(11 + w, y, 22));
                result.add(base.offset(21, y, 11 + w));
                result.add(base.offset(22, y, 11 + w));
                result.add(base.offset(21, y, 32 - w));
                result.add(base.offset(22, y, 32 - w));
                result.add(base.offset(32 - w, y, 21));
                result.add(base.offset(32 - w, y, 22));
            }
            for (int x = 20; x < 24; x++) for (int z = 20; z < 24; z++) result.add(base.offset(x, y, z));
        }
        return result;
    }
}
