package erebus.client.model.entity;

import erebus.entity.EntityBotFlyLarva;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
	
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		EntityBotFlyLarva larva = (EntityBotFlyLarva) entity;
		int i;

		if (larva.getParasiteCount() > 0)
			for (i = 0; i < botFlyLarvaBodyParts.length; ++i)
				botFlyLarvaBodyParts[i].render(scale);

		if (larva.getParasiteCount() > 1) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.5F, -0.4F, 0.0F);
			for (i = 0; i < botFlyLarvaBodyParts.length; ++i)
				botFlyLarvaBodyParts[i].render(scale);
			GlStateManager.popMatrix();
		}

		if (larva.getParasiteCount() == 3) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(-0.5F, -0.4F, 0.0F);
			for (i = 0; i < botFlyLarvaBodyParts.length; ++i)
				botFlyLarvaBodyParts[i].render(scale);
			GlStateManager.popMatrix();
		}

	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
		for (int i = 0; i < botFlyLarvaBodyParts.length; ++i) {
			botFlyLarvaBodyParts[i].rotateAngleY = MathHelper.cos(ageInTicks * 0.9F + i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.05F * (1 + Math.abs(i - 2));
			botFlyLarvaBodyParts[i].rotationPointX = MathHelper.sin(ageInTicks * 0.9F + i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.2F * Math.abs(i - 2);
		}
	}
}
