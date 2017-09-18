package erebus.client.gui;

import org.lwjgl.opengl.GL11;

import erebus.core.helper.Utils;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiErebusBasic extends GuiErebus {

	private final int TEXT_COLOUR = Utils.getColour(0, 0, 0);

	protected final IInventory invt;
	protected final ResourceLocation texture;

	public GuiErebusBasic(Container container, ResourceLocation texture, IInventory invt) {
		super(container);
		this.invt = invt;
		this.texture = texture;
	}

	public GuiErebusBasic(Container container, ResourceLocation texture, IInventory invt, int ySize) {
		this(container, texture, invt);
		this.ySize = ySize;
	}

	public GuiErebusBasic(Container container, ResourceLocation texture, IInventory invt, int xSize, int ySize) {
		this(container, texture, invt);
		this.xSize = xSize;
		this.ySize = ySize;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = new TextComponentTranslation(invt.getName()).getFormattedText();
		fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, TEXT_COLOUR);
		fontRenderer.drawString(I18n.format(new TextComponentTranslation("container.inventory").getFormattedText()), 8, ySize - 96 + 2, TEXT_COLOUR);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}