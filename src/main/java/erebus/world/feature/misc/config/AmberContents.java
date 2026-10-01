package erebus.world.feature.misc.config;

import erebus.block.entity.PreservedBlockEntity;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Mth;
import net.minecraft.util.ProblemReporter;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Supplier;

final class AmberContents {
    private static final float PRESERVED_CHANCE = 0.01F;
    private static final float WAND_CHANCE = 0.05F;

    private static final List<Supplier<? extends EntityType<?>>> MOBS = List.of(
            ModEntities.BEETLE_LARVA,
            ModEntities.WASP,
            ModEntities.CENTIPEDE,
            ModEntities.BEETLE,
            ModEntities.FLY,
            ModEntities.MOSQUITO,
            ModEntities.TARANTULA,
            ModEntities.BOT_FLY,
            ModEntities.SCORPION,
            ModEntities.SOLIFUGE,
            ModEntities.GRASSHOPPER,
            ModEntities.LOCUST,
            ModEntities.MOTH,
            ModEntities.RHINO_BEETLE,
            ModEntities.ANTLION,
            ModEntities.BLACK_WIDOW,
            ModEntities.GLOW_WORM,
            ModEntities.BOMBARDIER_BEETLE,
            ModEntities.SCYTODES,
            ModEntities.MONEY_SPIDER,
            ModEntities.PRAYING_MANTIS,
            ModEntities.JUMPING_SPIDER,
            ModEntities.FIRE_ANT,
            ModEntities.WORKER_BEE,
            ModEntities.VELVET_WORM,
            ModEntities.DRAGON_FLY,
            ModEntities.TITAN_BEETLE,
            ModEntities.BOT_FLY_LARVA,
            ModEntities.FUNGAL_WEEVIL,
            ModEntities.CROP_WEEVIL,
            ModEntities.WOODLOUSE,
            ModEntities.CICADA,
            ModEntities.FIRE_ANT_SOLDIER,
            ModEntities.LAVA_WEB_SPIDER,
            ModEntities.ANTLION_MINI_BOSS,
            ModEntities.CHAMELEON_TICK,
            ModEntities.MIDGE_SWARM,
            ModEntities.PUNCHROOM,
            ModEntities.CRUSHROOM,
            ModEntities.BLACK_ANT,
            ModEntities.ZOMBIE_ANT,
            ModEntities.TARANTULA_MINI_BOSS,
            ModEntities.BABY_TARANTULA,
            ModEntities.POND_SKATER,
            ModEntities.BOG_MAW,
            ModEntities.MAGMA_CRAWLER,
            ModEntities.ANTLION_BOSS,
            ModEntities.HONEY_POT_ANT,
            ModEntities.BOMBARDIER_BEETLE_LARVA,
            ModEntities.ZOMBIE_ANT_SOLDIER,
            ModEntities.STAG_BEETLE);

    private AmberContents() {
    }

    static void place(WorldGenLevel level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() > PRESERVED_CHANCE) {
            level.setBlock(pos, ModBlocks.AMBER.get().defaultBlockState(), 2);
            return;
        }
        if (!level.setBlock(pos, ModBlocks.PRESERVED_AMBER.get().defaultBlockState(), 2)) return;
        if (!(level.getBlockEntity(pos) instanceof PreservedBlockEntity preserved)) return;

        boolean wand = random.nextFloat() <= WAND_CHANCE;
        var type = wand ? EntityType.ITEM : MOBS.get(random.nextInt(MOBS.size())).get();

        var output = TagValueOutput.createWithContext(ProblemReporter.DISCARDING, level.registryAccess());
        output.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(type).toString());
        output.store("Pos", Vec3.CODEC, Vec3.atBottomCenterOf(pos));
        output.store("UUID", UUIDUtil.CODEC, Mth.createInsecureUUID(random));
        if (wand) output.store("Item", ItemStack.CODEC, new ItemStack(ModItems.WAND_OF_PRESERVATION.get()));
        preserved.setGeneratedContents(output.buildResult(), (byte) random.nextInt(4));
    }
}
