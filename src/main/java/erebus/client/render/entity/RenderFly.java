package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelFly;
import erebus.entity.EntityFly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderFly extends RenderLiving {

	private int renderedFlySize;

	public RenderFly() {
		super(new ModelFly(), 0.25F);
		renderedFlySize = ((ModelFly) mainModel).getFlySize();
	}

	public void func_82443_a(EntityFly entityFly, double x, double y, double z, float rotationYaw, float partialTickTime) {
		int var10 = ((ModelFly) mainModel).getFlySize();

		if (var10 != renderedFlySize) {
			renderedFlySize = var10;
			mainModel = new ModelFly();
		}

		super.doRender(entityFly, x, y, z, rotationYaw, partialTickTime);
	}

	protected void func_82445_a(EntityFly entityFly, double x, double y, double z) {
		super.renderLivingAt(entityFly, x, y, z);
	}

	protected void func_82444_a(EntityFly entityFly, float par2, float par3, float par4) {
		if (!entityFly.getIsFlyHanging())
			GL11.glTranslatef(0.0F, MathHelper.cos(par2 * 0.3F) * 0.1F, 0.0F);
		else
			GL11.glTranslatef(0.0F, -0.1F, 0.0F);

		super.rotateCorpse(entityFly, par2, par3, par4);
	}

	protected void preRenderCallback(EntityLiving entityLiving, float partialTickTime) {
		GL11.glScalef(0.35F, 0.35F, 0.35F);
	}

	protected void rotateCorpse(EntityLiving entityLiving, float par2, float par3, float par4) {
		func_82444_a((EntityFly) entityLiving, par2, par3, par4);
	}

	protected void renderLivingAt(EntityLiving entityLiving, double x, double y, double z) {
		func_82445_a((EntityFly) entityLiving, x, y, z);
	}

	@Override
	public void doRender(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		func_82443_a((EntityFly) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		func_82443_a((EntityFly) entity, x, y, z, rotationYaw, partialTickTime);
	}

	private final ResourceLocation resource = new ResourceLocation("erebus:textures/entity/fly.png");

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return resource;
	}
}