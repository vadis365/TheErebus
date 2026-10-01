package erebus.item.armour;

import erebus.Erebus;
import erebus.network.data.SprintLeggingsDataHolder;
import erebus.registries.data.ModArmorMaterials;
import erebus.registries.data.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class SprintLeggings extends Item {
    public static final int MAX_UPGRADES = 9;

    public SprintLeggings() {
        super(new Item.Properties().humanoidArmor(ModArmorMaterials.SPRINT, ArmorType.LEGGINGS)
                .setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("sprint_leggings"))));
    }

    public static int upgrades(ItemStack stack) {
        return Math.clamp(stack.getOrDefault(ModDataComponents.SPRINT_LEGGINGS, SprintLeggingsDataHolder.DEFAULT).tier(), 0, MAX_UPGRADES);
    }

    @Override
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, Consumer<Component> lines, @NonNull TooltipFlag flag) {
        lines.accept(Component.translatable("tooltip.erebus.sprint_leggings_tier", " " + (1 + upgrades(stack))).withStyle(ChatFormatting.YELLOW));
    }
}
