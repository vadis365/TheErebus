package erebus.block.plants;

import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import erebus.datagen.loot.ModHarvestLootTables;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class TallFernBlock extends DoublePlantBlock {
    public static final MapCodec<TallFernBlock> CODEC = simpleCodec(TallFernBlock::new);
    public static final Identifier SEEDS = Erebus.prefix("fern_seeds");

    public TallFernBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull MapCodec<TallFernBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NonNull List<ItemStack> getDrops(@NonNull BlockState state, LootParams.Builder params) {
        params.withDynamicDrop(SEEDS, output -> {
            var drops = seedTable(params.getLevel()).getRandomItems(params.create(LootContextParamSets.BLOCK));
            if (drops.isEmpty()) output.accept(new ItemStack(this));
            else drops.forEach(output);
        });
        return super.getDrops(state, params);
    }

    protected LootTable seedTable(ServerLevel level) {
        return level.getServer().reloadableRegistries().getLootTable(ModHarvestLootTables.FERN_SEEDS);
    }
}
