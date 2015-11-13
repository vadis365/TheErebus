package erebus.client.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiInvisibleButton extends GuiButton {

	public GuiInvisibleButton(int id, int xPosition, int yPosition, int width, int height) {
		super(id, xPosition, yPosition, width, height, "");
	}

	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
	}
}
