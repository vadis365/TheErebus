package erebus.item.armour;

import erebus.Erebus;
import erebus.registries.data.ModArmorMaterials;
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

public class JumpBoots extends Item {
    public JumpBoots() {
        super(new Item.Properties().humanoidArmor(ModArmorMaterials.JUMP_BOOTS, ArmorType.BOOTS).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("jump_boots"))));
    }

    @Override
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, Consumer<Component> lines, @NonNull TooltipFlag flag) {
        lines.accept(Component.translatable("tooltip.erebus.jump_boots").withStyle(ChatFormatting.YELLOW));
    }

}
