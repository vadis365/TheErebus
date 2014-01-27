package erebus.client.gui;

import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.utils.Utils;

@SideOnly(Side.CLIENT)
public class GuiPetrifiedWorkbench extends GuiCrafting {

	public GuiPetrifiedWorkbench(InventoryPlayer player, World world, int x, int y, int z) {
		super(player, world, x, y, z);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString(I18n.getString("container.crafting"), 28, 6, Utils.getColour(255, 255, 255));
		fontRenderer.drawString(I18n.getString("container.inventory"), 8, ySize - 96 + 2, Utils.getColour(255, 255, 255));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(new ResourceLocation("erebus:textures/gui/container/petrifiedCrafting.png"));
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
}