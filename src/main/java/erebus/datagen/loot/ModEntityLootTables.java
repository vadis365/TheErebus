package erebus.datagen.loot;

import java.util.function.Supplier;
import java.util.stream.Stream;

import erebus.registries.ModItems;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModEntityLootTables extends EntityLootSubProvider {

    public ModEntityLootTables(HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void generate() {
    	//TODO work out how TF this all works now
    	//this.noLoot(ModEntities.ANIMATED_BLOCK);

		this.add(ModEntities.BLACK_WIDOW.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(ModItems.POISON_GLAND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
							.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0, 1))))));

		this.noLoot(ModEntities.LAVA_WEB_SPIDER);

		this.noLoot(ModEntities.SCYTODES);

		this.noLoot(ModEntities.MONEY_SPIDER);

		this.noLoot(ModEntities.WASP);
		this.noLoot(ModEntities.WORKER_BEE);

		this.noLoot(ModEntities.MOTH);

		this.noLoot(ModEntities.VELVET_WORM);

		this.noLoot(ModEntities.ANTLION);

		this.noLoot(ModEntities.BOT_FLY);
		this.noLoot(ModEntities.BOT_FLY_LARVA);
		this.noLoot(ModEntities.FLY);
		this.noLoot(ModEntities.DRAGON_FLY);
		this.noLoot(ModEntities.CENTIPEDE);
		this.noLoot(ModEntities.GRASSHOPPER);
		this.noLoot(ModEntities.LOCUST);
		this.noLoot(ModEntities.BEETLE_LARVA);
		this.noLoot(ModEntities.BOMBARDIER_BEETLE_LARVA);
		this.noLoot(ModEntities.BEETLE);
		this.noLoot(ModEntities.WORKER_BEE);
		this.noLoot(ModEntities.BOMBARDIER_BEETLE);
    }
    
	public <T extends Entity> void noLoot(DeferredHolder<EntityType<?>, EntityType<T>> type) {
		this.add(type.get(), LootTable.lootTable());
	}

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return ModEntities.ENTITY_TYPES.getEntries().stream().map(Supplier::get);
    }
}
