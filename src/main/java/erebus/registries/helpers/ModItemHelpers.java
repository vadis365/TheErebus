package erebus.registries.helpers;

import erebus.item.PaxelItem;
import erebus.item.shield.ErebusShieldItem;
import erebus.item.shield.IShieldType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.UseRemainder;
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
        return ITEMS.register("%s_spawn_egg".formatted(name), () -> new SpawnEggItem(new Item.Properties().spawnEgg(entityType.get())));
    }

    public static DeferredItem<Item> registerFoodItem(String name, FoodProperties foodProperties, Consumable consumable) {
        return registerItem(name, () -> new Item(new Item.Properties().food(foodProperties, consumable)));
    }

    public static DeferredItem<Item> registerFoodItem(String name, FoodProperties foodProperties, Consumable consumable, ItemLike convertsTo) {
        return registerItem(name, () -> new Item(new Item.Properties()
                .food(foodProperties, consumable)
                .component(DataComponents.USE_REMAINDER, new UseRemainder(ItemStackTemplate.fromNonEmptyStack(new ItemStack(convertsTo))))
        ));
    }

    public static DeferredItem<Item> registerArmor(String name, ArmorMaterial armorMaterial, ArmorType armorType) {
        return registerItem(name, () -> new Item(new Item.Properties().humanoidArmor(armorMaterial, armorType)));
    }

    public static DeferredItem<Item> registerSword(String name, ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        return registerItem(name, () -> new Item(new Item.Properties().sword(material, attackDamageBaseline, attackSpeedBaseline)));
    }

    public static DeferredItem<Item> registerPickaxe(String name, ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        return registerItem(name, () -> new Item(new Item.Properties().pickaxe(material, attackDamageBaseline, attackSpeedBaseline)));
    }

    public static DeferredItem<AxeItem> registerAxe(String name, ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        return ITEMS.register(name, () -> new AxeItem(material, attackDamageBaseline, attackSpeedBaseline, new Item.Properties()));
    }

    public static DeferredItem<ShovelItem> registerShovel(String name, ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        return ITEMS.register(name, () -> new ShovelItem(material, attackDamageBaseline, attackSpeedBaseline, new Item.Properties()));
    }

    public static DeferredItem<PaxelItem> registerPaxel(String name, ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        return ITEMS.register(name, () -> new PaxelItem(new Item.Properties().sword(material, attackDamageBaseline, attackSpeedBaseline)));
    }

    public static DeferredItem<HoeItem> registerHoe(String name, ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        return ITEMS.register(name, () -> new HoeItem(material, attackDamageBaseline, attackSpeedBaseline, new Item.Properties()));
    }

    public static DeferredItem<ErebusShieldItem> registerShield(String name, int maxDamage, IShieldType shieldType) {
        return ITEMS.register(name, () -> new ErebusShieldItem(new Item.Properties().durability(maxDamage), shieldType));
    }
}
