package erebus.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.item.ModelWoodlouseBall;
import erebus.entity.EntityWoodlouseBall;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class WoodlouseBallItemRenderer extends Render implements IItemRenderer {
	private final ModelWoodlouseBall ModelWoodlouseBall;
	public static ResourceLocation texture = new ResourceLocation("erebus:textures/special/items/woodlouseBall.png");

	public WoodlouseBallItemRenderer() {
		ModelWoodlouseBall = new ModelWoodlouseBall();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type != ItemRenderType.FIRST_PERSON_MAP;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return helper != ItemRendererHelper.ENTITY_BOBBING && helper != ItemRendererHelper.ENTITY_ROTATION;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		switch (type) {
			case ENTITY:
				renderBall(0.0F, 0.0F, 0.0F, 1.2D);
				break;
			case EQUIPPED:
				renderEquipped(0.6F, 1.5F, 0.4F, 0.75D);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderBallFirstPerson(0F, 0F, 1.7F, 1.75D);
				break;
			case INVENTORY:
				renderBallInventory(0F, -1.7F, 0.0F, 1.5D);
				break;
			default:
				break;
		}
	}

	private void renderEquipped(float x, float y, float z, double size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y - 0.2F, z + 0.2F);
		GL11.glRotatef(45F, 0, 1F, 0);
		GL11.glScaled(-size, -size, size);
		ModelWoodlouseBall.render(0.0625F);
		GL11.glPopMatrix();
	}

	private void renderBall(float x, float y, float z, double size) {
		if (RenderItem.renderInFrame) {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y - 0.7F, z);
			GL11.glScaled(0.8F, 0.8F, 0.8F);
			ModelWoodlouseBall.render(0.0625F);
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glScaled(size, size, size);
			GL11.glRotatef(180F, 0, 0, 1F);
			GL11.glTranslatef(x, y - 1.4F, z);
			ModelWoodlouseBall.render(0.0625F);
			GL11.glPopMatrix();
		}
	}

	private void renderBallFirstPerson(float x, float y, float z, double size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(90F, 1F, 0, 0);
		GL11.glRotatef(-135F, 0, 0, 1F);
		GL11.glScaled(size, size, size);
		ModelWoodlouseBall.render(0.0625F);
		GL11.glPopMatrix();
	}

	private void renderBallInventory(float x, float y, float z, double size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glScaled(size, size, size);
		ModelWoodlouseBall.render(0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderWoodlouseBall((EntityWoodlouseBall) entity, x, y, z, rotationYaw, partialTickTime);
	}

	public void renderWoodlouseBall(EntityWoodlouseBall entityWoodlouseBall, double x, double y, double z, float rotationYaw, float partialTickTime) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y - 0.3F, (float) z);
		GL11.glRotatef(entityWoodlouseBall.prevRotationYaw + (entityWoodlouseBall.rotationYaw - entityWoodlouseBall.prevRotationYaw) * partialTickTime, 0.0F, 1.0F, 0.0F);
		GL11.glScaled(0.4F, 0.4F, 0.4F);
		ModelWoodlouseBall.render(0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}