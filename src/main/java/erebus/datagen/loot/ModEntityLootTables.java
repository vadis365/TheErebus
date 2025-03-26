package erebus.datagen.loot;

import erebus.registries.entity.ModEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ModEntityLootTables extends EntityLootSubProvider {

    public ModEntityLootTables(HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void generate() {

    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return ModEntities.ENTITY_TYPES.getEntries().stream().map(Supplier::get);
    }
}
