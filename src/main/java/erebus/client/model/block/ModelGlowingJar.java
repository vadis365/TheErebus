package erebus.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelGlowingJar extends ModelBase {

	private final ModelRenderer jar, lid, neck;

	public ModelGlowingJar() {
		textureWidth = 128;
		textureHeight = 64;
		jar = new ModelRenderer(this, 0, 27);
		jar.addBox(-6F, -1F, -6F, 12, 1, 12);
		jar.setRotationPoint(0F, 12F, 0F);
		lid = new ModelRenderer(this, 0, 41);
		lid.addBox(-7F, -3F, -7F, 14, 3, 14);
		lid.setRotationPoint(0F, 11F, 0F);
		neck = new ModelRenderer(this, 0, 0);
		neck.addBox(-7F, 0F, -7F, 14, 12, 14);
		neck.setRotationPoint(0F, 12F, 0F);
	}

	public void render() {
		lid.render(0.0625F);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(0.8F, 0.8F, 0.8F, 0.6F);
		neck.render(0.0625F);
		jar.render(0.0625F);
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}
}