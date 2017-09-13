package erebus.client.model.entity;

import erebus.entity.EntityMoth;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMoth extends ModelBase {

	ModelRenderer body;
	ModelRenderer head;
	ModelRenderer rearend;
	ModelRenderer head2;
	ModelRenderer rightwing;
	ModelRenderer leftwing;

	public ModelMoth() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this, 0, 0);
		body.addBox(-4F, -4F, 0F, 8, 7, 10);
		body.setRotationPoint(0F, 7F, 0F);
		setRotation(body, 0F, 0F, 0F);
		head = new ModelRenderer(this, 70, 0);
		head.addBox(-3F, -3F, -5F, 6, 5, 5);
		head.setRotationPoint(0F, 7F, 0F);
		setRotation(head, 0F, 0F, 0F);
		rearend = new ModelRenderer(this, 37, 0);
		rearend.addBox(-3F, -3F, 0F, 6, 6, 10);
		rearend.setRotationPoint(0F, 7F, 10F);
		setRotation(rearend, 0F, 0F, 0.7853982F);
		head2 = new ModelRenderer(this, -4, 18);
		head2.addBox(-7F, -2F, -15F, 14, 0, 11);
		head2.setRotationPoint(0F, 7F, 0F);
		setRotation(head2, -0.2602503F, 0F, 0F);
		rightwing = new ModelRenderer(this, -28, 30);
		rightwing.addBox(-32F, 0F, -15F, 33, 0, 37);
		rightwing.setRotationPoint(-4F, 4F, 3F);
		setRotation(rightwing, 0F, 0F, 0F);
		leftwing = new ModelRenderer(this, -28, 70);
		leftwing.addBox(0F, 0F, -15F, 33, 0, 37);
		leftwing.setRotationPoint(4F, 4F, 3F);
		setRotation(leftwing, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		body.render(unitPixel);
		head.render(unitPixel);
		rearend.render(unitPixel);
		head2.render(unitPixel);
		rightwing.render(unitPixel);
		leftwing.render(unitPixel);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAngle, float partialTicks) {
		EntityMoth moth = (EntityMoth) entity;
		float smoothedTicks = moth.ticksExisted + (moth.ticksExisted - (moth.ticksExisted - 1)) * partialTicks;
		float flap = MathHelper.sin((smoothedTicks) * 1.2F) * 0.5F;
		rightwing.rotateAngleZ = flap;
		leftwing.rotateAngleZ = -flap;
	}
}