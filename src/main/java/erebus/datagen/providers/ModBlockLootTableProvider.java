package erebus.datagen.providers;

import erebus.block.PricklyPearBlock;
import erebus.block.util.ModCropBlock;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.HashMap;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), new HashMap<>(), provider);
    }

    public void dropSelf(Supplier<? extends Block> block) {
        dropSelf(block.get());
    }

    public void slab(Supplier<? extends SlabBlock> slab) {
        add(slab.get(), this::createSlabItemTable);
    }

    public void dropOther(Supplier<? extends Block> brokenBlock, ItemLike droppedBlock) {
        dropOther(brokenBlock.get(), droppedBlock);
    }

    public void dropAsSilk(Supplier<? extends Block> block) {
        dropWhenSilkTouch(block.get());
    }

    public void dropWithSilk(Supplier<? extends Block> block, Supplier<? extends ItemLike> drop) {
        add(block.get(), (result) -> createSingleItemTableWithSilkTouch(result, drop.get()));
    }

    public void ore(Supplier<? extends Block> block, Supplier<? extends Item> drop) {
        add(block.get(), (result) -> createOreDrop(result, drop.get()));
    }

    public void ore(Supplier<? extends Block> block, Item drop) {
        add(block.get(), (result) -> createOreDrop(result, drop));
    }

    public void nuggetOre(Supplier<? extends Block> block, Item drop) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
        add(block.get(), (ore) -> createSilkTouchDispatchTable(ore, applyExplosionDecay(ore, LootItem.lootTableItem(drop).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))).apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))));
    }

    public void dropCropBasedOffCondition(Supplier<? extends CropBlock> crop, Supplier<? extends Item> grownDrop, Supplier<? extends Item> seed) {
        LootItemCondition.Builder condition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(crop.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ModCropBlock.AGE, 3));
        add(crop.get(), createCropDrops(crop.get(), grownDrop.get(), seed.get(), condition));
    }

    public void dropPricklyPearBasedOffCondition(Supplier<? extends Block> blockIn, Supplier<? extends Block> blockToDrop, Supplier<? extends Item> itemTodrop) {
        LootItemCondition.Builder condition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(blockIn.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PricklyPearBlock.AGE, 11));
        add(blockIn.get(), createCropDrops(blockIn.get(), itemTodrop.get(), blockToDrop.get().asItem(), condition));
    }

    public void dropComponents(Supplier<? extends Block> blockSupplier, Consumer<LootPool.Builder> lootFunctionSupplier) {
        LootPool.Builder lootPool = LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(blockSupplier.get()));
        lootFunctionSupplier.accept(lootPool);
        add(blockSupplier.get(), LootTable.lootTable().withPool(applyExplosionCondition(blockSupplier.get(), lootPool)));
    }
}
