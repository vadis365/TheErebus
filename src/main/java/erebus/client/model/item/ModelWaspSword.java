package erebus.client.model.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelWaspSword extends ModelBase {

	ModelRenderer Point;
	ModelRenderer Blade;
	ModelRenderer SpikeTop1;
	ModelRenderer SpikeTop2;
	ModelRenderer SpikeMid1;
	ModelRenderer SpikeMid2;
	ModelRenderer SpikeBot1;
	ModelRenderer SpikeBot2;
	ModelRenderer DecL4;
	ModelRenderer DecL3;
	ModelRenderer DecL1;
	ModelRenderer DecL2;
	ModelRenderer DecR1;
	ModelRenderer DecR2;
	ModelRenderer DecR3;
	ModelRenderer DecR4;
	ModelRenderer Tang;
	ModelRenderer TangJewel;
	ModelRenderer Hilt;
	ModelRenderer Pommel;

	public ModelWaspSword() {
		textureWidth = 32;
		textureHeight = 64;

		Point = new ModelRenderer(this, 23, 0);
		Point.addBox(-1.5F, -1.5F, -0.5F, 3, 3, 1);
		Point.setRotationPoint(0F, -41F, 0F);
		setRotation(Point, 0F, 0F, 0.7853982F);
		Blade = new ModelRenderer(this, 0, 0);
		Blade.addBox(-2F, -65F, -0.5F, 4, 45, 1);
		Blade.setRotationPoint(0F, 24F, 0F);
		setRotation(Blade, 0F, 0F, 0F);
		SpikeTop1 = new ModelRenderer(this, 11, 0);
		SpikeTop1.addBox(1F, -60F, -1F, 3, 2, 2);
		SpikeTop1.setRotationPoint(0F, 24F, 0F);
		setRotation(SpikeTop1, 0F, 0F, 0F);
		SpikeTop2 = new ModelRenderer(this, 23, 6);
		SpikeTop2.addBox(0F, 0F, -0.5F, 1, 4, 1);
		SpikeTop2.setRotationPoint(3.2F, -35.5F, 0F);
		setRotation(SpikeTop2, 0F, 0F, -0.5235988F);
		SpikeMid1 = new ModelRenderer(this, 11, 6);
		SpikeMid1.addBox(1F, -52F, -1F, 3, 2, 2);
		SpikeMid1.setRotationPoint(0F, 24F, 0F);
		setRotation(SpikeMid1, 0F, 0F, 0F);
		SpikeMid2 = new ModelRenderer(this, 28, 6);
		SpikeMid2.addBox(0F, 0F, -0.5F, 1, 4, 1);
		SpikeMid2.setRotationPoint(3.2F, -27.5F, 0F);
		setRotation(SpikeMid2, 0F, 0F, -0.5235988F);
		SpikeBot1 = new ModelRenderer(this, 11, 12);
		SpikeBot1.addBox(1F, -44F, -1F, 3, 2, 2);
		SpikeBot1.setRotationPoint(0F, 24F, 0F);
		setRotation(SpikeBot1, 0F, 0F, 0F);
		SpikeBot2 = new ModelRenderer(this, 23, 12);
		SpikeBot2.addBox(0F, 0F, -0.5F, 1, 4, 1);
		SpikeBot2.setRotationPoint(3.2F, -19.5F, 0F);
		setRotation(SpikeBot2, 0F, 0F, -0.5235988F);
		DecL4 = new ModelRenderer(this, 25, 20);
		DecL4.addBox(3.5F, -27F, -0.5F, 1, 2, 1);
		DecL4.setRotationPoint(0F, 24F, 0F);
		setRotation(DecL4, 0F, 0F, 0F);
		DecL3 = new ModelRenderer(this, 25, 24);
		DecL3.addBox(15.5F, -20F, -1F, 1, 4, 2);
		DecL3.setRotationPoint(0F, 24F, 0F);
		setRotation(DecL3, 0F, 0F, -0.5235988F);
		DecL1 = new ModelRenderer(this, 27, 52);
		DecL1.addBox(3F, -18F, -0.5F, 1, 2, 1);
		DecL1.setRotationPoint(0F, 24F, 0F);
		setRotation(DecL1, 0F, 0F, 0F);
		DecL2 = new ModelRenderer(this, 27, 31);
		DecL2.addBox(-7F, -22F, -0.5F, 1, 4, 1);
		DecL2.setRotationPoint(0F, 24F, 0F);
		setRotation(DecL2, 0F, 0F, 0.5235988F);
		DecR1 = new ModelRenderer(this, 11, 52);
		DecR1.addBox(-4F, -18F, -0.5F, 1, 2, 1);
		DecR1.setRotationPoint(0F, 24F, 0F);
		setRotation(DecR1, 0F, 0F, 0F);
		DecR2 = new ModelRenderer(this, 11, 31);
		DecR2.addBox(6F, -22F, -0.5F, 1, 4, 1);
		DecR2.setRotationPoint(0F, 24F, 0F);
		setRotation(DecR2, 0F, 0F, -0.5235988F);
		DecR3 = new ModelRenderer(this, 11, 24);
		DecR3.addBox(-16.5F, -20F, -1F, 1, 4, 2);
		DecR3.setRotationPoint(0F, 24F, 0F);
		setRotation(DecR3, 0F, 0F, 0.5235988F);
		DecR4 = new ModelRenderer(this, 13, 20);
		DecR4.addBox(-4.5F, -27F, -0.5F, 1, 2, 1);
		DecR4.setRotationPoint(0F, 24F, 0F);
		setRotation(DecR4, 0F, 0F, 0F);
		Tang = new ModelRenderer(this, 10, 46);
		Tang.addBox(-4F, -20F, -1.5F, 8, 2, 3);
		Tang.setRotationPoint(0F, 24F, 0F);
		setRotation(Tang, 0F, 0F, 0F);
		TangJewel = new ModelRenderer(this, 13, 37);
		TangJewel.addBox(-15F, -15F, -2F, 4, 4, 4);
		TangJewel.setRotationPoint(0F, 24F, 0F);
		setRotation(TangJewel, 0F, 0F, 0.7853982F);
		Hilt = new ModelRenderer(this, 0, 49);
		Hilt.addBox(-1.5F, -18F, -1F, 3, 13, 2);
		Hilt.setRotationPoint(0F, 24F, 0F);
		setRotation(Hilt, 0F, 0F, 0F);
		Pommel = new ModelRenderer(this, 13, 56);
		Pommel.addBox(-5F, -5F, -2F, 4, 4, 4);
		Pommel.setRotationPoint(0F, 24F, 0F);
		setRotation(Pommel, 0F, 0F, 0.7853982F);
	}

	public void render() {
		Point.render(0.0625F);
		Blade.render(0.0625F);
		SpikeTop1.render(0.0625F);
		SpikeTop2.render(0.0625F);
		SpikeMid1.render(0.0625F);
		SpikeMid2.render(0.0625F);
		SpikeBot1.render(0.0625F);
		SpikeBot2.render(0.0625F);
		DecL4.render(0.0625F);
		DecL3.render(0.0625F);
		DecL1.render(0.0625F);
		DecL2.render(0.0625F);
		DecR1.render(0.0625F);
		DecR2.render(0.0625F);
		DecR3.render(0.0625F);
		DecR4.render(0.0625F);
		Tang.render(0.0625F);
		TangJewel.render(0.0625F);
		Hilt.render(0.0625F);
		Pommel.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par6Entity) {

	}
}
