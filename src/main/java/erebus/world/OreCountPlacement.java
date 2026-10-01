package erebus.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.Config;
import erebus.registries.world.ModOrePlacements;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.world.level.levelgen.placement.RepeatingPlacement;
import org.jspecify.annotations.NonNull;

public final class OreCountPlacement extends RepeatingPlacement {
    public static final MapCodec<OreCountPlacement> CODEC = RecordCodecBuilder.<OreCountPlacement>mapCodec(instance -> instance.group(
            Codec.floatRange(0, 1).fieldOf("chance").forGetter(p -> p.chance),
            Codec.intRange(0, 4096).fieldOf("min").forGetter(p -> p.min),
            Codec.intRange(0, 4096).fieldOf("max").forGetter(p -> p.max),
            Codec.intRange(0, 4096).fieldOf("extra_min").forGetter(p -> p.extraMin),
            Codec.intRange(0, 4096).fieldOf("extra_max").forGetter(p -> p.extraMax)
    ).apply(instance, OreCountPlacement::new)).validate(p -> p.min <= p.max && p.extraMin <= p.extraMax ? DataResult.success(p) : DataResult.error(() -> "Ore count minimum exceeds maximum"));

    private final float chance;
    private final int min, max, extraMin, extraMax;

    public OreCountPlacement(float chance, int min, int max, int extraMin, int extraMax) {
        this.chance = chance;
        this.min = min;
        this.max = max;
        this.extraMin = extraMin;
        this.extraMax = extraMax;
    }

    @Override
    protected int count(RandomSource random, @NonNull BlockPos origin) {
        if (random.nextFloat() >= chance) return 0;
        boolean extra = Config.generateCopperOre || Config.generateTinOre || Config.generateSilverOre || Config.generateLeadOre || Config.generateAluminumOre;
        int low = extra ? extraMin : min, high = extra ? extraMax : max;
        return low + random.nextInt(high - low + 1);
    }

    @Override
    public @NonNull PlacementModifierType<?> type() {
        return ModOrePlacements.ORE_COUNT.get();
    }
}
