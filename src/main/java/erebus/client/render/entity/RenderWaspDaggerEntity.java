package erebus.client.render.entity;

import erebus.client.model.item.ModelWaspDagger;
import erebus.entity.EntityWaspDagger;
import erebus.lib.Reference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWaspDaggerEntity extends Render<EntityWaspDagger> {
	private final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/special/items/wasp_sword.png");
	private final ModelWaspDagger MODEL_DAGGER = new ModelWaspDagger();
	public RenderWaspDaggerEntity(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityWaspDagger entityWaspDagger, double x, double y, double z, float rotationYaw, float partialTickTime) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(TEXTURE);
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(entityWaspDagger.prevRotationYaw + (entityWaspDagger.rotationYaw - entityWaspDagger.prevRotationYaw) * partialTickTime - 90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(entityWaspDagger.prevRotationPitch + (entityWaspDagger.rotationPitch - entityWaspDagger.prevRotationPitch) * partialTickTime - EntityWaspDagger.rotationticks, 0.0F, 0.0F, 1.0F);
		GlStateManager.scale(0.4F, 0.4F, 0.4F);
		MODEL_DAGGER.render();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWaspDagger entityWaspDagger) {
		return TEXTURE;
	}

}
