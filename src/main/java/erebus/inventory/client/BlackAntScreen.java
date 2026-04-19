package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.server.BlackAntMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.*;

public class BlackAntScreen extends ErebusScreen<BlackAntMenu> {
	protected final BlackAntMenu container;
	
	public static Item[] ghostIcon = new Item[] { Items.STONE_HOE, Items.SHEARS, Items.BUCKET, Items.BONE, Items.WHEAT_SEEDS, Items.WHEAT };
	private ItemStack stack, stack2;
	int iconCountTool = 0;
	int iconCountCrop = 4;

	public BlackAntScreen(BlackAntMenu container, Inventory playerInventory, Component name) {
		super(container, playerInventory, name, Erebus.prefix("textures/gui/container/ant_gui_test.png"));
		stack = new ItemStack(ghostIcon[0]);
		stack2 = new ItemStack(ghostIcon[4]);
		this.container = container;
	}

	@Override
	protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
		super.extractLabels(graphics, xm, ym);
		graphics.text(font, title, imageWidth / 2 - font.width(title.getString()) / 2, 6, 16777215);
		graphics.text(font, Component.translatable("container.inventory"), imageWidth - 170, this.imageHeight - 93, 16777215);	
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
		super.extractBackground(graphics, mouseX, mouseY, a);
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);

		if (container.getSlot(0).getItem().isEmpty()) {
			graphics.item(stack, leftPos + 26, topPos + 18);
		}
		else {
			if (container.getSlot(1).getItem().isEmpty()) {
				if (container.getSlot(0).getItem().getItem() instanceof HoeItem) {
					stack2 = new ItemStack(ghostIcon[4]);
					graphics.item(stack2, leftPos + 80, topPos + 18);
				}
	
				if (container.getSlot(0).getItem().getItem() instanceof BucketItem)
					graphics.item(stack2, leftPos + 80, topPos + 18);
	
				if (container.getSlot(0).getItem().is(Items.BONE)) {
					stack2 = new ItemStack(Items.BONE_MEAL);
					graphics.item(stack2, leftPos + 80, topPos + 18);
				}
			}
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
