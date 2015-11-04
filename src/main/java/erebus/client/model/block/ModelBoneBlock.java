package erebus.client.model.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelBoneBlock extends ModelBase {
	ModelRenderer Skull_Main;
	ModelRenderer SkullTop;
	ModelRenderer SkullLeft;
	ModelRenderer SkullRight;
	ModelRenderer Skull_Back;
	ModelRenderer SkullNose;
	ModelRenderer SkullJaw;
	ModelRenderer BoneL1;
	ModelRenderer BoneL2;
	ModelRenderer BoneL3;
	ModelRenderer BoneL4;
	ModelRenderer BoneR2;
	ModelRenderer BoneR3;
	ModelRenderer BoneR4;
	ModelRenderer BoneR1;

	public ModelBoneBlock() {
		textureWidth = 64;
		textureHeight = 32;

		Skull_Main = new ModelRenderer(this, 0, 0);
		Skull_Main.addBox(-3.5F, -9F, 0F, 8, 8, 7);
		Skull_Main.setRotationPoint(0F, 24F, 0F);
		setRotation(Skull_Main, -0.122173F, 0.5235988F, 0F);
		SkullTop = new ModelRenderer(this, 35, 0);
		SkullTop.addBox(-2.5F, -10.5F, 1F, 6, 2, 5);
		SkullTop.setRotationPoint(0F, 24F, 0F);
		setRotation(SkullTop, -0.1396263F, 0.5235988F, 0F);
		SkullLeft = new ModelRenderer(this, 32, 7);
		SkullLeft.addBox(4F, -8F, 1F, 1, 5, 5);
		SkullLeft.setRotationPoint(0F, 24F, 0F);
		setRotation(SkullLeft, -0.1396263F, 0.5235988F, 0F);
		SkullRight = new ModelRenderer(this, 45, 7);
		SkullRight.addBox(-4F, -8F, 1F, 1, 5, 5);
		SkullRight.setRotationPoint(0F, 24F, 0F);
		setRotation(SkullRight, -0.1396263F, 0.5235988F, 0F);
		Skull_Back = new ModelRenderer(this, 38, 18);
		Skull_Back.addBox(-2.5F, -8F, 7F, 6, 6, 1);
		Skull_Back.setRotationPoint(0F, 24F, 0F);
		setRotation(Skull_Back, -0.122173F, 0.5235988F, 0F);
		SkullNose = new ModelRenderer(this, 21, 16);
		SkullNose.addBox(0F, -5F, 1.3F, 1, 2, 1);
		SkullNose.setRotationPoint(0F, 24F, 0F);
		setRotation(SkullNose, 0.296706F, 0.5235988F, 0F);
		SkullJaw = new ModelRenderer(this, 15, 20);
		SkullJaw.addBox(-2.5F, -3F, 0F, 5, 3, 6);
		SkullJaw.setRotationPoint(0F, 24F, -1F);
		setRotation(SkullJaw, -0.122173F, 0.5235988F, 0F);
		BoneL1 = new ModelRenderer(this, 0, 26);
		BoneL1.addBox(4.5F, -1F, 2F, 3, 1, 2);
		BoneL1.setRotationPoint(0F, 24F, 0F);
		setRotation(BoneL1, 0F, 0.837758F, 0F);
		BoneL2 = new ModelRenderer(this, 0, 23);
		BoneL2.addBox(1F, -1F, -3F, 5, 1, 1);
		BoneL2.setRotationPoint(0F, 24F, 0F);
		setRotation(BoneL2, 0F, 0.3490659F, 0F);
		BoneL3 = new ModelRenderer(this, 0, 23);
		BoneL3.addBox(1F, -1F, -5F, 5, 1, 1);
		BoneL3.setRotationPoint(0F, 24F, 0F);
		setRotation(BoneL3, 0F, 0.296706F, 0F);
		BoneL4 = new ModelRenderer(this, 0, 23);
		BoneL4.addBox(0F, -1F, -7F, 5, 1, 1);
		BoneL4.setRotationPoint(0F, 24F, 0F);
		setRotation(BoneL4, 0F, 0.2268928F, 0F);
		BoneR2 = new ModelRenderer(this, 0, 23);
		BoneR2.addBox(-5F, -1F, -3F, 5, 1, 1);
		BoneR2.setRotationPoint(0F, 24F, 0F);
		setRotation(BoneR2, 0F, 0.5235988F, 0F);
		BoneR3 = new ModelRenderer(this, 0, 23);
		BoneR3.addBox(-5F, -1F, -5F, 5, 1, 1);
		BoneR3.setRotationPoint(0F, 24F, 0F);
		setRotation(BoneR3, 0F, 0.4363323F, 0F);
		BoneR4 = new ModelRenderer(this, 0, 23);
		BoneR4.addBox(-6F, -1F, -7F, 5, 1, 1);
		BoneR4.setRotationPoint(0F, 24F, 0F);
		setRotation(BoneR4, 0F, 0.3490659F, 0F);
		BoneR1 = new ModelRenderer(this, 0, 17);
		BoneR1.addBox(-6.5F, -2F, 4F, 4, 2, 3);
		BoneR1.setRotationPoint(0F, 24F, 0F);
		setRotation(BoneR1, 0F, -0.2094395F, 0F);
	}

	public void render() {
		Skull_Main.render(0.0625F);
		SkullTop.render(0.0625F);
		SkullLeft.render(0.0625F);
		SkullRight.render(0.0625F);
		Skull_Back.render(0.0625F);
		SkullNose.render(0.0625F);
		SkullJaw.render(0.0625F);
		BoneL1.render(0.0625F);
		BoneL2.render(0.0625F);
		BoneL3.render(0.0625F);
		BoneL4.render(0.0625F);
		BoneR2.render(0.0625F);
		BoneR3.render(0.0625F);
		BoneR4.render(0.0625F);
		BoneR1.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
