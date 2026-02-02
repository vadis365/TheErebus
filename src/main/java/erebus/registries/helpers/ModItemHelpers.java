package erebus.registries.helpers;

import erebus.Erebus;
import erebus.item.PaxelItem;
import erebus.item.shield.ErebusShieldItem;
import erebus.item.shield.IShieldType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

import static erebus.registries.item.ModItems.ITEMS;

public class ModItemHelpers {

    public static DeferredItem<Item> registerItem(String name) {
        return ITEMS.registerSimpleItem(name);
    }

    public static <I extends Item> DeferredItem<Item> registerItem(String name, Supplier<? extends I> item) {
        return ITEMS.register(name, item);
    }

    public static DeferredItem<Item> registerSpawnEgg(String name, Supplier<? extends EntityType<?>> entityType) {
        return registerItem("%s_spawn_egg".formatted(name), () -> new SpawnEggItem(new Item.Properties().spawnEgg(entityType.get()).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("%s_spawn_egg".formatted(name))))));
    }

    public static DeferredItem<Item> registerFoodItem(String name, FoodProperties foodProperties, Consumable consumable) {
        return registerItem(name, () -> new Item(new Item.Properties().food(foodProperties, consumable).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix(name)))));
    }

    public static DeferredItem<Item> registerFoodItem(String name, FoodProperties foodProperties, Consumable consumable, ItemLike convertsTo) {
        return registerItem(name, () -> new Item(new Item.Properties()
                .food(foodProperties, consumable)
                .usingConvertsTo(convertsTo.asItem())
                .setId(ResourceKey.create(Registries.ITEM, Erebus.prefix(name)))
        ));
    }

    public static DeferredItem<Item> registerArmor(String name, ArmorMaterial armorMaterial, ArmorType armorType) {
        return registerItem(name, () -> new Item(new Item.Properties().humanoidArmor(armorMaterial, armorType).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix(name)))));
    }

    public static DeferredItem<Item> registerSword(String name, ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        return registerItem(name, () -> new Item(new Item.Properties().sword(material, attackDamageBaseline, attackSpeedBaseline).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix(name)))));
    }

    public static DeferredItem<Item> registerPickaxe(String name, ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        return registerItem(name, () -> new Item(new Item.Properties().pickaxe(material, attackDamageBaseline, attackSpeedBaseline).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix(name)))));
    }

    public static DeferredItem<AxeItem> registerAxe(String name, ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        return ITEMS.register(name, () -> new AxeItem(material, attackDamageBaseline, attackSpeedBaseline, new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix(name)))));
    }

    public static DeferredItem<ShovelItem> registerShovel(String name, ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        return ITEMS.register(name, () -> new ShovelItem(material, attackDamageBaseline, attackSpeedBaseline, new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix(name)))));
    }

    public static DeferredItem<PaxelItem> registerPaxel(String name, ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        return ITEMS.register(name, () -> new PaxelItem(new Item.Properties().sword(material, attackDamageBaseline, attackSpeedBaseline).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix(name)))));
    }

    public static DeferredItem<HoeItem> registerHoe(String name, ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        return ITEMS.register(name, () -> new HoeItem(material, attackDamageBaseline, attackSpeedBaseline, new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix(name)))));
    }

    public static DeferredItem<ErebusShieldItem> registerShield(String name, int maxDamage, IShieldType shieldType) {
        return ITEMS.register(name, () -> new ErebusShieldItem(new Item.Properties().durability(maxDamage).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix(name))), shieldType));
    }
}
