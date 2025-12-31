package erebus.registries.helpers;

import de.cech12.bucketlib.api.item.UniversalBucketItem;
import erebus.item.PaxelItem;
import erebus.item.SmoothieItem;
import erebus.item.shield.ErebusShieldItem;
import erebus.item.shield.IShieldType;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static erebus.registries.item.ModItems.ITEMS;

public class ModItemHelpers {

    public static DeferredItem<Item> registerItem(String name) {
        return ITEMS.registerSimpleItem(name);
    }

    public static <I extends Item> DeferredItem<Item> registerItem(String name, Supplier<? extends I> item) {
        return ITEMS.register(name, item);
    }

    public static DeferredItem<Item> registerFoodItem(String name, int nutrition, float saturation) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(saturation)
                .build())));
    }

    public static DeferredItem<Item> registerFoodItem(String name, int nutrition, float saturation, Holder<MobEffect> effect, int duration, int amplifier) {
        return ITEMS.register(name, () -> new Item(
                new Item.Properties()
                        .food(
                                new FoodProperties.Builder()
                                        .nutrition(nutrition)
                                        .saturationModifier(saturation)
                                        .effect(() -> new MobEffectInstance(effect, duration, amplifier), 100)
                                        .build()
                        )
                )
        );
    }

    public static DeferredItem<Item> registerFoodItem(String name, int nutrition, float saturation, Holder<MobEffect> effect, int duration, int amplifier, ItemLike convertsTo) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(saturation)
                .effect(() -> new MobEffectInstance(effect, duration, amplifier), 100)
                .usingConvertsTo(convertsTo)
                .build())));
    }

    public static DeferredItem<Item> registerFoodItem(String name, int nutrition, float saturation, ItemLike convertsTo) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(saturation)
                .usingConvertsTo(convertsTo)
                .build())));
    }

    public static DeferredItem<Item> registerSmoothieItem(String name, int nutrition, float saturation) {
        return ITEMS.register(name, () -> new SmoothieItem(new Item.Properties().food(new FoodProperties.Builder()
                .alwaysEdible()
                .nutrition(nutrition)
                .saturationModifier(saturation)
                .build())));
    }

    public static DeferredItem<Item> registerSmoothieItem(String name, int nutrition, float saturation, Holder<MobEffect> effect, int duration, int amplifier) {
        return ITEMS.register(name, () -> new SmoothieItem(new Item.Properties().food(new FoodProperties.Builder()
                .alwaysEdible()
                .nutrition(nutrition)
                .saturationModifier(saturation)
                .effect(() -> new MobEffectInstance(effect, duration, amplifier), 100)
                .build())));
    }

    public static DeferredItem<Item> registerBryufsBrew() {
        return ITEMS.register("bryufs_brew", () -> new SmoothieItem(new Item.Properties().food(new FoodProperties.Builder()
                .alwaysEdible()
                .nutrition(2)
                .saturationModifier(0)
                .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1000, 2), 100)
                .effect(() -> new MobEffectInstance(MobEffects.JUMP, 1000, 2), 100)
                .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1000, 2), 100)
                .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 1000, 1), 100)
                .effect(() -> new MobEffectInstance(MobEffects.HEAL, 2, 1), 100)
                .build())));
    }

    public static Map<String, DeferredItem<ArmorItem>> registerArmorSet(String prefix, ArmorMaterial armorMaterial) {
        DeferredItem<ArmorItem> helm = registerHelmet("%s_helmet".formatted(prefix), armorMaterial);
        DeferredItem<ArmorItem> chest = registerChestplate("%s_chestplate".formatted(prefix), armorMaterial);
        DeferredItem<ArmorItem> legs = registerLeggings("%s_leggings".formatted(prefix), armorMaterial);
        DeferredItem<ArmorItem> boots = registerBoots("%s_boots".formatted(prefix), armorMaterial);

        Map<String, DeferredItem<ArmorItem>> ret = new HashMap<>();
        ret.put("helm", helm);
        ret.put("chest", chest);
        ret.put("legs", legs);
        ret.put("boots", boots);
        return ret;
    }

    public static DeferredItem<ArmorItem> registerHelmet(String name, ArmorMaterial armorMaterial) {
        return ITEMS.register(name, () -> new ArmorItem(armorMaterial, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(11))));
    }

    public static DeferredItem<ArmorItem> registerChestplate(String name, ArmorMaterial armorMaterial) {
        return ITEMS.register(name, () -> new ArmorItem(armorMaterial, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(16))));
    }

    public static DeferredItem<ArmorItem> registerLeggings(String name, ArmorMaterial armorMaterial) {
        return ITEMS.register(name, () -> new ArmorItem(armorMaterial, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(15))));
    }

    public static DeferredItem<ArmorItem> registerBoots(String name, ArmorMaterial armorMaterial) {
        return ITEMS.register(name, () -> new ArmorItem(armorMaterial, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(13))));
    }

    public static DeferredItem<SwordItem> registerSword(String name, Tier tier, float attack, float attackSpeed) {
        return ITEMS.register(name, () -> new SwordItem(tier, new Item.Properties().attributes(SwordItem.createAttributes(tier, attack, attackSpeed))));
    }

    public static DeferredItem<PickaxeItem> registerPickaxe(String name, Tier tier, float attack, float attackSpeed) {
        return ITEMS.register(name, () -> new PickaxeItem(tier, new Item.Properties().attributes(SwordItem.createAttributes(tier, attack, attackSpeed))));
    }

    public static DeferredItem<AxeItem> registerAxe(String name, Tier tier, float attack, float attackSpeed) {
        return ITEMS.register(name, () -> new AxeItem(tier, new Item.Properties().attributes(SwordItem.createAttributes(tier, attack, attackSpeed))));
    }

    public static DeferredItem<ShovelItem> registerShovel(String name, Tier tier, float attack, float attackSpeed) {
        return ITEMS.register(name, () -> new ShovelItem(tier, new Item.Properties().attributes(SwordItem.createAttributes(tier, attack, attackSpeed))));
    }

    public static DeferredItem<PaxelItem> registerPaxel(String name, Tier tier, float attack, float attackSpeed) {
        return ITEMS.register(name, () -> new PaxelItem(tier, new Item.Properties().attributes(SwordItem.createAttributes(tier, attack, attackSpeed))));
    }

    public static DeferredItem<HoeItem> registerHoe(String name, Tier tier, float attack, float attackSpeed) {
        return ITEMS.register(name, () -> new HoeItem(tier, new Item.Properties().attributes(SwordItem.createAttributes(tier, attack, attackSpeed))));
    }

    public static DeferredItem<Item> registerBucket(String name) {
        return ITEMS.register(name, () -> new UniversalBucketItem(new UniversalBucketItem.Properties()));
    }

    public static DeferredItem<ErebusShieldItem> registerShield(String name, int maxDamage, IShieldType shieldType) {
        return ITEMS.register(name, () -> new ErebusShieldItem(new Item.Properties().durability(maxDamage), shieldType));
    }
}
