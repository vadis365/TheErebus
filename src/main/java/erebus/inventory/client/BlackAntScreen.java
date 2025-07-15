package erebus.inventory.client;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.systems.RenderSystem;

import erebus.Erebus;
import erebus.inventory.server.BlackAntMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

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

		if (container.getSlot(0).getItem().isEmpty()) {
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			RenderSystem.enableBlend();
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.2F);
			RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
			gg.renderFakeItem(stack, getGuiLeft() + 26, getGuiTop() + 18);
			RenderSystem.disableBlend();
		}

		if (!container.getSlot(0).getItem().isEmpty() && container.getSlot(0).getItem().getItemHolder() instanceof HoeItem) {
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			RenderSystem.enableBlend();
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.2F);
			RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
			stack2 = new ItemStack(ghostIcon[4]);
			
			gg.renderFakeItem(stack2, getGuiLeft() + 80, getGuiTop() + 18);
			gg.renderFakeItem(stack2, getGuiLeft() + 134, getGuiTop() + 18);
			RenderSystem.disableBlend();
		}

		if (!container.getSlot(0).getItem().isEmpty() && container.getSlot(0).getItem().getItemHolder() instanceof BucketItem && container.getSlot(1).getItem().isEmpty()) {
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			RenderSystem.enableBlend();
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.2F);
			RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
			gg.renderFakeItem(stack2, getGuiLeft() + 80, getGuiTop() + 18);
			RenderSystem.disableBlend();
		}

		if (!container.getSlot(0).getItem().isEmpty() && container.getSlot(0).getItem().is(Items.BONE) && container.getSlot(1).getItem().isEmpty()) {
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			RenderSystem.enableBlend();
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.2F);
			RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
			stack2 = new ItemStack(Items.BONE_MEAL);
			gg.renderFakeItem(stack2, getGuiLeft() + 80, getGuiTop() + 18);
			RenderSystem.disableBlend();
		}
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