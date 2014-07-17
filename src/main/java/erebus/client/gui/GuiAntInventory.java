package erebus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.helper.Utils;
import erebus.inventory.ContainerAntInventory;

@SideOnly(Side.CLIENT)
public class GuiAntInventory extends GuiContainer {

	private static final ResourceLocation GUI_ANT_INVENTORY = new ResourceLocation("erebus:textures/gui/container/antGuiTest.png");
	public static Item[] ghostIcon = new Item[] {Items.stone_hoe, Items.shears, Items.bucket};
	private ItemStack stack;
	int iconCount=0;
	public GuiAntInventory(InventoryPlayer inventory, Entity entityInventory) {
		super(new ContainerAntInventory(inventory, (IInventory) entityInventory));
		xSize = 176;
		ySize = 131;
		stack = new ItemStack(ghostIcon[0]);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int color = Utils.getColour(0, 0, 0);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.antInventory"), xSize / 2 - fontRendererObj.getStringWidth(StatCollector.translateToLocal("container.extenderThingy")) / 2 -10, 6, color);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), xSize - 170, ySize - 93, color);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(GUI_ANT_INVENTORY);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glColor4f(1f, 1f, 1f, 0.2f);
		IIcon iicon1 = stack.getIconIndex();
		this.mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
		this.drawTexturedModelRectFromIcon(guiLeft + 26, guiTop +18, iicon1, 16, 16);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
    }
		
	@Override
    public void updateScreen() {
        super.updateScreen();
        if(mc.theWorld.getWorldTime() % 40 == 0) {
        	stack = new ItemStack(ghostIcon[iconCount]);
        	iconCount++;
        	if(iconCount > 2)
        		iconCount = 0;
        	}
        }
}