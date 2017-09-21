package erebus.client.gui;

import erebus.core.helper.Utils;
import erebus.inventory.ContainerAntInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAntInventory extends GuiErebus {

	private static final ResourceLocation GUI_ANT_INVENTORY = new ResourceLocation("erebus:textures/gui/container/ant_gui_test.png");
	public static Item[] ghostIcon = new Item[] { Items.STONE_HOE, Items.SHEARS, Items.BUCKET, Items.BONE, Items.WHEAT_SEEDS, Items.WHEAT };
	private ItemStack stack, stack2;
	int iconCountTool = 0;
	int iconCountCrop = 4;
	private IInventory antInventory;

	public GuiAntInventory(InventoryPlayer inventory, Entity entityInventory) {
		super(new ContainerAntInventory(inventory, (IInventory) entityInventory));
		xSize = 176;
		ySize = 131;
		stack = new ItemStack(ghostIcon[0]);
		stack2 = new ItemStack(ghostIcon[4]);
		antInventory = (IInventory) entityInventory;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int color = Utils.getColour(0, 0, 0);
		fontRenderer.drawString(I18n.format(new TextComponentTranslation("container.antInventory").getFormattedText()), xSize / 2 - fontRenderer.getStringWidth(I18n.format(new TextComponentTranslation("container.antInventory").getFormattedText())) / 2, 6, color);
		fontRenderer.drawString(I18n.format(new TextComponentTranslation("container.inventory").getFormattedText()), xSize - 170, ySize - 93, color);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(GUI_ANT_INVENTORY);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if (antInventory.getStackInSlot(0).isEmpty()) {
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.color(1f, 1f, 1f, 0.2f);
			mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			mc.getRenderItem().renderItemIntoGUI(stack, guiLeft + 26, guiTop + 18);
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
		}

		if (!antInventory.getStackInSlot(0).isEmpty() && antInventory.getStackInSlot(0).getItem() instanceof ItemHoe) {
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.color(1f, 1f, 1f, 0.2f);
			stack2 = new ItemStack(ghostIcon[4]);
			mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			mc.getRenderItem().renderItemIntoGUI(stack2, guiLeft + 80, guiTop + 18);
			mc.getRenderItem().renderItemIntoGUI(stack2, guiLeft + 134, guiTop + 18);
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
		}

		if (!antInventory.getStackInSlot(0).isEmpty() && antInventory.getStackInSlot(0).getItem() instanceof ItemBucket && antInventory.getStackInSlot(1).isEmpty()) {
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.color(1f, 1f, 1f, 0.2f);
			mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			mc.getRenderItem().renderItemIntoGUI(stack2, guiLeft + 80, guiTop + 18);
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
		}

		if (!antInventory.getStackInSlot(0).isEmpty() && antInventory.getStackInSlot(0).getItem() == Items.BONE && antInventory.getStackInSlot(1).isEmpty()) {
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.color(1f, 1f, 1f, 0.2f);
			stack2 = new ItemStack(Items.DYE, 0, 15);
			mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			mc.getRenderItem().renderItemIntoGUI(stack2, guiLeft + 80, guiTop + 18);
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
		}
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		if (mc.world.getWorldTime() % 40 == 0) {
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