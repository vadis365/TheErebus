package erebus.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.helper.Utils;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class GuiErebusBasic extends GuiErebus {

	private final int TEXT_COLOUR = Utils.getColour(192, 192, 192);

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
		String name = StatCollector.translateToLocal(invt.getInventoryName());
		fontRendererObj.drawString(name, xSize / 2 - fontRendererObj.getStringWidth(name) / 2, 6, TEXT_COLOUR);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, TEXT_COLOUR);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}