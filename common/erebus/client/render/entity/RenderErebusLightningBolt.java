package erebus.client.render.entity;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.effect.EntityErebusLightningBolt;

@SideOnly(Side.CLIENT)
public class RenderErebusLightningBolt extends Render {
	public void doRenderErebusLightningBolt(EntityErebusLightningBolt entityErebusLightningBolt, double x, double y, double z, float rotationYaw, float partialTickTime) {
		Tessellator tessellator = Tessellator.instance;
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		double[] adouble = new double[8];
		double[] adouble1 = new double[8];
		double d3 = 0.0D;
		double d4 = 0.0D;
		Random random = new Random(entityErebusLightningBolt.boltVertex);

		for (int i = 7; i >= 0; --i) {
			adouble[i] = d3;
			adouble1[i] = d4;
			d3 += random.nextInt(11) - 5;
			d4 += random.nextInt(11) - 5;
		}

		for (int j = 0; j < 4; ++j) {
			Random random1 = new Random(entityErebusLightningBolt.boltVertex);

			for (int k = 0; k < 3; ++k) {
				int l = 7;
				int i1 = 0;

				if (k > 0)
					l = 7 - k;

				if (k > 0)
					i1 = l - 2;

				double d5 = adouble[l] - d3;
				double d6 = adouble1[l] - d4;

				for (int j1 = l; j1 >= i1; --j1) {
					double d7 = d5;
					double d8 = d6;

					if (k == 0) {
						d5 += random1.nextInt(11) - 5;
						d6 += random1.nextInt(11) - 5;
					} else {
						d5 += random1.nextInt(31) - 15;
						d6 += random1.nextInt(31) - 15;
					}

					tessellator.startDrawing(5);
					float f2 = 0.5F;
					tessellator.setColorRGBA_F(0.9F * f2, 0.9F * f2, 1.0F * f2, 0.3F);
					double d9 = 0.1D + j * 0.2D;

					if (k == 0)
						d9 *= j1 * 0.1D + 1.0D;

					double d10 = 0.1D + j * 0.2D;

					if (k == 0)
						d10 *= (j1 - 1) * 0.1D + 1.0D;

					for (int k1 = 0; k1 < 5; ++k1) {
						double d11 = x + 0.5D - d9;
						double d12 = z + 0.5D - d9;

						if (k1 == 1 || k1 == 2)
							d11 += d9 * 2.0D;

						if (k1 == 2 || k1 == 3)
							d12 += d9 * 2.0D;

						double d13 = x + 0.5D - d10;
						double d14 = z + 0.5D - d10;

						if (k1 == 1 || k1 == 2)
							d13 += d10 * 2.0D;

						if (k1 == 2 || k1 == 3)
							d14 += d10 * 2.0D;

						tessellator.addVertex(d13 + d5, y + j1 * 16, d14 + d6);
						tessellator.addVertex(d11 + d7, y + (j1 + 1) * 16, d12 + d8);
					}

					tessellator.draw();
				}
			}
		}

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	protected ResourceLocation func_110805_a(EntityErebusLightningBolt entityErebusLightningBolt) {
		return null;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return func_110805_a((EntityErebusLightningBolt) entity);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		doRenderErebusLightningBolt((EntityErebusLightningBolt) entity, x, y, z, rotationYaw, partialTickTime);
	}
}
