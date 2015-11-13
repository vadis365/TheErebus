package erebus.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.helper.Utils;
import erebus.inventory.ContainerAntInventory;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class GuiAntInventory extends GuiErebus {

	private static final ResourceLocation GUI_ANT_INVENTORY = new ResourceLocation("erebus:textures/gui/container/antGuiTest.png");
	public static Item[] ghostIcon = new Item[] { Items.stone_hoe, Items.shears, Items.bucket, Items.bone, Items.wheat_seeds, Items.wheat };
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
		fontRendererObj.drawString(StatCollector.translateToLocal("container.antInventory"), xSize / 2 - fontRendererObj.getStringWidth(StatCollector.translateToLocal("container.antInventory")) / 2, 6, color);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), xSize - 170, ySize - 93, color);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(GUI_ANT_INVENTORY);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if (antInventory.getStackInSlot(0) == null) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glColor4f(1f, 1f, 1f, 0.2f);
			IIcon iicon1 = stack.getIconIndex();
			mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
			drawTexturedModelRectFromIcon(guiLeft + 26, guiTop + 18, iicon1, 16, 16);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}

		if (antInventory.getStackInSlot(0) != null && antInventory.getStackInSlot(0).getItem() instanceof ItemHoe) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glColor4f(1f, 1f, 1f, 0.2f);
			stack2 = new ItemStack(ghostIcon[4]);
			IIcon iicon1 = stack2.getIconIndex();
			mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
			drawTexturedModelRectFromIcon(guiLeft + 80, guiTop + 18, iicon1, 16, 16);
			drawTexturedModelRectFromIcon(guiLeft + 134, guiTop + 18, iicon1, 16, 16);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}

		if (antInventory.getStackInSlot(0) != null && antInventory.getStackInSlot(0).getItem() instanceof ItemBucket && antInventory.getStackInSlot(1) == null) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glColor4f(1f, 1f, 1f, 0.2f);
			IIcon iicon1 = stack2.getIconIndex();
			mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
			drawTexturedModelRectFromIcon(guiLeft + 80, guiTop + 18, iicon1, 16, 16);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}

		if (antInventory.getStackInSlot(0) != null && antInventory.getStackInSlot(0).getItem() == Items.bone && antInventory.getStackInSlot(1) == null) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glColor4f(1f, 1f, 1f, 0.2f);
			stack2 = new ItemStack(Items.dye, 0, 15);
			IIcon iicon1 = stack2.getIconIndex();
			mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
			drawTexturedModelRectFromIcon(guiLeft + 80, guiTop + 18, iicon1, 16, 16);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		if (mc.theWorld.getWorldTime() % 40 == 0) {
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