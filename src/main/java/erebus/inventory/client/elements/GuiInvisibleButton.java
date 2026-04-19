package erebus.inventory.client.elements;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

public class GuiInvisibleButton extends Button {

	public GuiInvisibleButton(int xPosition, int yPosition, int width, int height, Component title, OnPress pressedAction) {
		super(xPosition, yPosition, width, height, title, pressedAction, DEFAULT_NARRATION);
	}

    @Override
    protected void extractContents(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {

    }
}
