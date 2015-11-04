package erebus.client.render.item;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.client.render.block.BlockRenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class PreservedBlockitemRenderer implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type != ItemRenderType.FIRST_PERSON_MAP;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack stack, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
		Object renderer = data[0];
		if (renderer instanceof RenderBlocks)
			switch (type) {
				case ENTITY:
					render(stack, -0.5F, -0.5F, -0.5F, (RenderBlocks) renderer);
					break;
				case EQUIPPED:
					render(stack, 0.0F, 0.0F, 0.0F, (RenderBlocks) renderer);
					break;
				case EQUIPPED_FIRST_PERSON:
					render(stack, 0.0F, 0.0F, 0.0F, (RenderBlocks) renderer);
					break;
				case INVENTORY:
					render(stack, -0.5F, -0.5F, -0.5F, (RenderBlocks) renderer);
					break;
				default:
					break;
			}
	}

	private void render(ItemStack stack, float x, float y, float z, RenderBlocks renderer) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x + 0.5F, y + 0.125F, z + 0.5F);
		if (stack.hasTagCompound()) {
			GL11.glPushMatrix();
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glRotatef(180.0F, 0.0F, 0.0F, 0.0F);
			GL11.glScaled(0.5, 0.5, 0.5);
			Entity entity = EntityList.createEntityFromNBT(stack.getTagCompound().getCompoundTag("EntityNBT"), Minecraft.getMinecraft().theWorld);
			entity.setLocationAndAngles(0, 0, 0, 0, 0);
			RenderManager.instance.renderEntityWithPosYaw(entity, 0, 0, 0, 0, 0);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);

			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		boolean wasBlendOn = GL11.glIsEnabled(GL11.GL_BLEND);
		if (!wasBlendOn) {
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		renderer.setRenderBoundsFromBlock(ModBlocks.preservedBlock);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.preservedBlock, 0, renderer);
		if (!wasBlendOn)
			GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
}