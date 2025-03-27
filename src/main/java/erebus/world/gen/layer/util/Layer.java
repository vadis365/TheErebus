package erebus.world.gen.layer.util;

import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.util.Optional;

public class Layer {
    public final LazyArea area;

    public Layer(AreaFactory<LazyArea> area) {
        this.area = area.make();
    }

    public Holder<Biome> get(HolderGetter<Biome> registry, int x, int z) {
        int biomeID = area.get(x, z);
        Optional<Holder.Reference<Biome>> biome = ServerLifecycleHooks.getCurrentServer().registryAccess().lookupOrThrow(Registries.BIOME).get(Biomes.OCEAN);

        if (biome.isEmpty()) {
            if (SharedConstants.IS_RUNNING_IN_IDE) {
                throw Util.pauseInIde(new IllegalStateException("Unknown biome id: %d".formatted(biomeID)));
            } else {
                return registry.getOrThrow(Biomes.OCEAN);
            }
        }

        return biome.get();
    }
}
