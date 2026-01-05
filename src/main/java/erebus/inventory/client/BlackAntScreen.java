package erebus.inventory.client;

import com.mojang.blaze3d.systems.RenderSystem;
import erebus.Erebus;
import erebus.inventory.server.BlackAntMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class BlackAntScreen extends ErebusScreen<BlackAntMenu> {
	protected final BlackAntMenu container;
	
	public static Item[] ghostIcon = new Item[] { Items.STONE_HOE, Items.SHEARS, Items.BUCKET, Items.BONE, Items.WHEAT_SEEDS, Items.WHEAT };
	private ItemStack stack, stack2;
	int iconCountTool = 0;
	int iconCountCrop = 4;

	public BlackAntScreen(BlackAntMenu container, Inventory playerInventory, Component name) {
		super(container, playerInventory, name, Erebus.prefix("textures/gui/container/ant_gui_test.png"));
		imageHeight = 131;
		imageWidth = 176;
		stack = new ItemStack(ghostIcon[0]);
		stack2 = new ItemStack(ghostIcon[4]);
		this.container = container;
	}

	@Override
	protected void renderLabels(@Nonnull GuiGraphics gg, int mouseX, int mouseY) {
		gg.drawString(font, title, getXSize() / 2 - font.width(title.getString()) / 2, 6, 16777215, true);
		gg.drawString(font, Component.translatable("container.inventory"), imageWidth - 170, this.imageHeight - 93, 16777215, true);	
	}

	@Override
	protected void renderBg(GuiGraphics gg, float partialTicks, int mouseX, int mouseY) {
		gg.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);

		RenderSystem.enableBlend();
		RenderSystem.setShaderColor(0.5F, 0.5F, 0.5F, 0.2F);

		if (container.getSlot(0).getItem().isEmpty()) {
			gg.renderFakeItem(stack, getGuiLeft() + 26, getGuiTop() + 18);
		}
		else {
			if (container.getSlot(1).getItem().isEmpty()) {
				if (container.getSlot(0).getItem().getItem() instanceof HoeItem) {
					stack2 = new ItemStack(ghostIcon[4]);
					gg.renderFakeItem(stack2, getGuiLeft() + 80, getGuiTop() + 18);
					// gg.renderFakeItem(stack2, getGuiLeft() + 134, getGuiTop() + 18);
				}
	
				if (container.getSlot(0).getItem().getItem() instanceof BucketItem)
					gg.renderFakeItem(stack2, getGuiLeft() + 80, getGuiTop() + 18);
	
				if (container.getSlot(0).getItem().is(Items.BONE)) {
					stack2 = new ItemStack(Items.BONE_MEAL);
					gg.renderFakeItem(stack2, getGuiLeft() + 80, getGuiTop() + 18);
				}
			}
		}
		RenderSystem.disableBlend();
	}

    @Override
    protected void containerTick() {
		if (minecraft.level.getGameTime() % 40 == 0) {
			stack = new ItemStack(ghostIcon[iconCountTool]);
			stack2 = new ItemStack(ghostIcon[iconCountCrop]);
			iconCountTool++;
			iconCountCrop++;
			if (iconCountTool > 3)
				iconCountTool = 0;
			if (iconCountCrop > 5)
				iconCountCrop = 4;
		}
	}
}
