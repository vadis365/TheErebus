package erebus.client.model.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityBotFlyLarva;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelBotFlyLarva extends ModelBase {

	private final ModelRenderer[] botFlyLarvaBodyParts = new ModelRenderer[7];
	private final float[] field_78170_c = new float[7];

	private static final int[][] botFlyLarvaBoxLength = new int[][] { { 3, 2, 2 }, { 4, 3, 2 }, { 6, 4, 3 }, { 3, 3, 3 }, { 2, 2, 3 }, { 2, 1, 2 }, { 1, 1, 2 } };

	private static final int[][] botFlyLarvaTexturePositions = new int[][] { { 0, 0 }, { 0, 4 }, { 0, 9 }, { 0, 16 }, { 0, 22 }, { 11, 0 }, { 13, 4 } };

	public ModelBotFlyLarva() {
		float f = -3.5F;

		for (int i = 0; i < botFlyLarvaBodyParts.length; ++i) {
			botFlyLarvaBodyParts[i] = new ModelRenderer(this, botFlyLarvaTexturePositions[i][0], botFlyLarvaTexturePositions[i][1]);
			botFlyLarvaBodyParts[i].addBox(botFlyLarvaBoxLength[i][0] * -0.5F, 0.0F, botFlyLarvaBoxLength[i][2] * -0.5F, botFlyLarvaBoxLength[i][0], botFlyLarvaBoxLength[i][1], botFlyLarvaBoxLength[i][2]);
			botFlyLarvaBodyParts[i].setRotationPoint(0.0F, 24 - botFlyLarvaBoxLength[i][1], f);
			field_78170_c[i] = f;

			if (i < botFlyLarvaBodyParts.length - 1)
				f += (botFlyLarvaBoxLength[i][2] + botFlyLarvaBoxLength[i + 1][2]) * 0.5F;
		}

	}

	@Override
	public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, entity);
		EntityBotFlyLarva larva = (EntityBotFlyLarva) entity;
		int i;

		if (larva.getParasiteCount() > 0)
			for (i = 0; i < botFlyLarvaBodyParts.length; ++i)
				botFlyLarvaBodyParts[i].render(par7);

		if (larva.getParasiteCount() > 1) {
			GL11.glPushMatrix();
			GL11.glTranslatef(0.5F, -0.4F, 0.0F);
			for (i = 0; i < botFlyLarvaBodyParts.length; ++i)
				botFlyLarvaBodyParts[i].render(par7);
			GL11.glPopMatrix();
		}

		if (larva.getParasiteCount() == 3) {
			GL11.glPushMatrix();
			GL11.glTranslatef(-0.5F, -0.4F, 0.0F);
			for (i = 0; i < botFlyLarvaBodyParts.length; ++i)
				botFlyLarvaBodyParts[i].render(par7);
			GL11.glPopMatrix();
		}

	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
		for (int i = 0; i < botFlyLarvaBodyParts.length; ++i) {
			botFlyLarvaBodyParts[i].rotateAngleY = MathHelper.cos(par3 * 0.9F + i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.05F * (1 + Math.abs(i - 2));
			botFlyLarvaBodyParts[i].rotationPointX = MathHelper.sin(par3 * 0.9F + i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.2F * Math.abs(i - 2);
		}

	}
}
