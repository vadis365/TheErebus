package erebus.datagen.providers;

import erebus.datagen.advancement.AgricultureAdvancements;
import erebus.datagen.advancement.ExplorationAdvancements;
import erebus.datagen.advancement.PortalAdvancements;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModAdvancementProvider extends AdvancementProvider {
    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(
                new AgricultureAdvancements(),
                new ExplorationAdvancements(),
                new PortalAdvancements()
        ));
    }
}
