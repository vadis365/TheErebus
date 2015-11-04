package erebus.client.model.block;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

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

	@SideOnly(Side.CLIENT)
	public void render() {
		lid.render(0.0625F);
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(0.8F, 0.8F, 0.8F, 0.6F);
		neck.render(0.0625F);
		jar.render(0.0625F);
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
		GL11.glPopMatrix();
	}
}