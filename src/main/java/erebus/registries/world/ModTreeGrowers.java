package erebus.registries.world;

import erebus.Erebus;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {

    public static final TreeGrower BAOBAB = new TreeGrower(
            "%s:baobab".formatted(Erebus.MODID),
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.BAOBAB_KEY),
            Optional.empty()
    );
}
