package erebus.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFirebrat extends ModelBase {

	private final ModelRenderer[] firebratBodyParts = new ModelRenderer[7];
	private final ModelRenderer[] firebratWings;
	private final float[] field_78170_c = new float[7];

	private static final int[][] firebratBoxLength = new int[][] { { 3, 2, 2 }, { 4, 3, 2 }, { 6, 4, 3 }, { 3, 3, 3 }, { 2, 2, 3 }, { 2, 1, 2 }, { 1, 1, 2 } };

	private static final int[][] firebratTexturePositions = new int[][] { { 0, 0 }, { 0, 4 }, { 0, 9 }, { 0, 16 }, { 0, 22 }, { 11, 0 }, { 13, 4 } };

	public ModelFirebrat() {
		float f = -3.5F;

		for (int i = 0; i < firebratBodyParts.length; ++i) {
			firebratBodyParts[i] = new ModelRenderer(this, firebratTexturePositions[i][0], firebratTexturePositions[i][1]);
			firebratBodyParts[i].addBox(firebratBoxLength[i][0] * -0.5F, 0.0F, firebratBoxLength[i][2] * -0.5F, firebratBoxLength[i][0], firebratBoxLength[i][1], firebratBoxLength[i][2]);
			firebratBodyParts[i].setRotationPoint(0.0F, 24 - firebratBoxLength[i][1], f);
			field_78170_c[i] = f;

			if (i < firebratBodyParts.length - 1)
				f += (firebratBoxLength[i][2] + firebratBoxLength[i + 1][2]) * 0.5F;
		}

		firebratWings = new ModelRenderer[3];
		firebratWings[0] = new ModelRenderer(this, 20, 0);
		firebratWings[0].addBox(-5.0F, 0.0F, firebratBoxLength[2][2] * -0.5F, 10, 8, firebratBoxLength[2][2]);
		firebratWings[0].setRotationPoint(0.0F, 16.0F, field_78170_c[2]);
		firebratWings[1] = new ModelRenderer(this, 20, 11);
		firebratWings[1].addBox(-3.0F, 0.0F, firebratBoxLength[4][2] * -0.5F, 6, 4, firebratBoxLength[4][2]);
		firebratWings[1].setRotationPoint(0.0F, 20.0F, field_78170_c[4]);
		firebratWings[2] = new ModelRenderer(this, 20, 18);
		firebratWings[2].addBox(-3.0F, 0.0F, firebratBoxLength[4][2] * -0.5F, 6, 5, firebratBoxLength[1][2]);
		firebratWings[2].setRotationPoint(0.0F, 19.0F, field_78170_c[1]);
	}

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	@Override
	public void render(Entity entity, float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		setRotationAngles(limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		int i;

		for (i = 0; i < firebratBodyParts.length; ++i)
			firebratBodyParts[i].render(unitPixel);

		for (i = 0; i < firebratWings.length; ++i)
			firebratWings[i].render(unitPixel);
	}

	@Override
	public void setRotationAngles(float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		for (int i = 0; i < firebratBodyParts.length; ++i) {
			firebratBodyParts[i].rotateAngleY = MathHelper.cos(entityTickTime * 0.9F + i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.05F * (1 + Math.abs(i - 2));
			firebratBodyParts[i].rotationPointX = MathHelper.sin(entityTickTime * 0.9F + i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.2F * Math.abs(i - 2);
		}

		firebratWings[0].rotateAngleY = firebratBodyParts[2].rotateAngleY;
		firebratWings[1].rotateAngleY = firebratBodyParts[4].rotateAngleY;
		firebratWings[1].rotationPointX = firebratBodyParts[4].rotationPointX;
		firebratWings[2].rotateAngleY = firebratBodyParts[1].rotateAngleY;
		firebratWings[2].rotationPointX = firebratBodyParts[1].rotationPointX;
	}
}
