package erebus.client.gui;

import erebus.core.helper.Utils;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiPetrifiedWorkbench extends GuiCrafting {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/gui/container/petrified_crafting.png");

	public GuiPetrifiedWorkbench(InventoryPlayer player, World world, BlockPos pos) {
		super(player, world, pos);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRenderer.drawString(I18n.format(new TextComponentTranslation("container.crafting").getFormattedText()), 28, 6, Utils.getColour(255, 255, 255));
		fontRenderer.drawString(I18n.format(new TextComponentTranslation("container.inventory").getFormattedText()), 8, ySize - 96 + 2, Utils.getColour(255, 255, 255));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(TEXTURE);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}