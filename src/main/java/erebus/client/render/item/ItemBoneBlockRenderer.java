package erebus.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelBoneBlock;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class ItemBoneBlockRenderer implements IItemRenderer {

	private final ModelBoneBlock ModelBoneBlock = new ModelBoneBlock();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type != ItemRenderType.FIRST_PERSON_MAP;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return helper != ItemRendererHelper.BLOCK_3D;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type) {
			case ENTITY:
				renderBlock(0.0F, 0.5F, 0.0F, 0.5D);
				break;
			case EQUIPPED:
				renderEquipped(0.4F, 2.0F, 1.25F, 1.0D);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderFirstPerson(0.5F, 2.0F, 0.5F, 1.0D);
				break;
			case INVENTORY:
				renderInventory(0.1F, 1.5F, 0.0F, 1.4D);
				break;
			default:
				break;
		}
	}

	private void renderBlock(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(new ResourceLocation("erebus:textures/special/tiles/boneBlock.png"));
		if (RenderItem.renderInFrame) {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y - 0.25F, z + 1.5F);
			GL11.glRotatef(-90F, 1F, 0, 0);
			GL11.glScaled(1F, 1F, 1F);
			ModelBoneBlock.render();
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(180F, 1F, 0, 0);
			GL11.glRotatef(90F, 0, 1F, 0);
			GL11.glScaled(size, size, size);
			ModelBoneBlock.render();
			GL11.glPopMatrix();
		}
	}

	private void renderEquipped(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(new ResourceLocation("erebus:textures/special/tiles/boneBlock.png"));
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180F, 1F, 0, 0);
		GL11.glRotatef(90F, 0, 1F, 0);
		GL11.glScaled(size, size, size);
		ModelBoneBlock.render();
		GL11.glPopMatrix();
	}

	private void renderFirstPerson(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(new ResourceLocation("erebus:textures/special/tiles/boneBlock.png"));
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180F, 1F, 0, 0);
		GL11.glRotatef(90F, 0, 1F, 0);
		GL11.glScaled(size, size, size);
		ModelBoneBlock.render();
		GL11.glPopMatrix();
	}

	private void renderInventory(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(new ResourceLocation("erebus:textures/special/tiles/boneBlock.png"));
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y + 0.2F, z);
		GL11.glRotatef(180F, 1F, 0, 0);
		GL11.glRotatef(-90F, 0, 1F, 0);
		GL11.glScaled(size, size, size);
		ModelBoneBlock.render();
		GL11.glPopMatrix();
	}

}