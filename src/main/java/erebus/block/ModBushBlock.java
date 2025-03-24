package erebus.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BushBlock;

public class ModBushBlock extends BushBlock {
    public static final MapCodec<ModBushBlock> CODEC = simpleCodec(ModBushBlock::new);

    public ModBushBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }


}
