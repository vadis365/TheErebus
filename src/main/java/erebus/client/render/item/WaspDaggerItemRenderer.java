package erebus.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.item.ModelWaspDagger;
import erebus.entity.EntityWaspDagger;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class WaspDaggerItemRenderer extends Render implements IItemRenderer {
	private final ModelWaspDagger ModelWaspDagger;
	public static ResourceLocation texture = new ResourceLocation("erebus:textures/special/items/waspSword.png");

	public WaspDaggerItemRenderer() {
		ModelWaspDagger = new ModelWaspDagger();
	}

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
				renderDagger(0.0F, 0.0F, 0.0F, 0.25D);
				break;
			case EQUIPPED:
				renderEquipped(0.3F, 0.5F, 0.4F, 0.75D);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderDaggerFirstPerson(0.5F, 0.9F, 0.5F, 0.75D);
				break;
			case INVENTORY:
				renderDaggerInventory(0.0F, 0.0F, 0.0F, 0.75D);
				break;
			default:
				break;
		}
	}

	private void renderEquipped(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y + 0.6F, z + 0.5F);
		GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(225.0F, 0.0F, 1.0F, 0.0F);
		GL11.glScaled(-size, -size, size);
		ModelWaspDagger.render(0.0625F);
		GL11.glPopMatrix();
	}

	private void renderDagger(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		if (RenderItem.renderInFrame) {
			GL11.glPushMatrix();
			GL11.glTranslatef(x + 0.1F, y + 0.3F, z);
			GL11.glRotatef(180F, 1F, 0, 0);
			GL11.glRotatef(45F, 0, 0, 1F);
			GL11.glScaled(0.4F, 0.4F, 0.4F);
			ModelWaspDagger.render(0.0625F);
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(180F, 1F, 0, 0);
			GL11.glRotatef(-135F, 0, 1F, 0);
			GL11.glRotatef(70F, 0, 0, 1F);
			GL11.glScaled(size, size, size);
			ModelWaspDagger.render(0.0625F);
			GL11.glPopMatrix();
		}
	}

	private void renderDaggerFirstPerson(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180F, 1F, 0, 0);
		GL11.glRotatef(-45F, 0, 1F, 0);
		GL11.glScaled(size, size, size);
		ModelWaspDagger.render(0.0625F);
		GL11.glPopMatrix();
	}

	private void renderDaggerInventory(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(135F, 1F, 0, 0);
		GL11.glRotatef(135F, 0, 1F, 0);
		GL11.glRotatef(-20F, 0, 0, 1F);
		GL11.glScaled(size, size, size);
		ModelWaspDagger.render(0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderWaspDagger((EntityWaspDagger) entity, x, y, z, rotationYaw, partialTickTime);
	}

	public void renderWaspDagger(EntityWaspDagger entityWaspDagger, double x, double y, double z, float rotationYaw, float partialTickTime) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(entityWaspDagger.prevRotationYaw + (entityWaspDagger.rotationYaw - entityWaspDagger.prevRotationYaw) * partialTickTime - 90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(entityWaspDagger.prevRotationPitch + (entityWaspDagger.rotationPitch - entityWaspDagger.prevRotationPitch) * partialTickTime - EntityWaspDagger.rotationticks, 0.0F, 0.0F, 1.0F);
		GL11.glScaled(0.4F, 0.4F, 0.4F);
		ModelWaspDagger.render(0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}