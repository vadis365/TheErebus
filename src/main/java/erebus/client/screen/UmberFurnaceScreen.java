package erebus.client.screen;

import erebus.Erebus;
import erebus.inventory.UmberFurnaceMenu;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class UmberFurnaceScreen extends AbstractFurnaceScreen<UmberFurnaceMenu> {

    private static final ResourceLocation LIT_PROGRESS_SPRITE = ResourceLocation.withDefaultNamespace("container/furnace/lit_progress");
    private static final ResourceLocation BURN_PROGRESS_SPRITE = ResourceLocation.withDefaultNamespace("container/furnace/burn_progress");
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Erebus.MODID, "textures/gui/container/umber_furnace.png");

    public UmberFurnaceScreen(UmberFurnaceMenu menu, Inventory inv) {
        super(menu, new SmeltingRecipeBookComponent(), inv, Component.translatable("container.umberfurnace"), TEXTURE, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE);
    }
}
