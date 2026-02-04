package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.WaspRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class WaspModel extends EntityModel<WaspRenderState> {
	public ModelPart root;
	public ModelPart Thx;
	public ModelPart ThxS;
	public ModelPart ThxTop;
	public ModelPart Ab;
	public ModelPart AbF;
	public ModelPart AbSide;
	public ModelPart AbTop;
	public ModelPart AbBack;
	public ModelPart Neck;
	public ModelPart Head1;
	public ModelPart Head2;
	public ModelPart Head3;
	public ModelPart RMandible1;
	public ModelPart RMandible2;
	public ModelPart LMandible1;
	public ModelPart LMandible2;
	public ModelPart Eyes;
	public ModelPart AntLE;
	public ModelPart AntRE;
	public ModelPart AntRS;
	public ModelPart AntLS;
	public ModelPart LBL1;
	public ModelPart LBL2;
	public ModelPart LBL3;
	public ModelPart LBL4;
	public ModelPart LML1;
	public ModelPart LML2;
	public ModelPart LML3;
	public ModelPart LML4;
	public ModelPart LFL1;
	public ModelPart LFL2;
	public ModelPart LFL3;
	public ModelPart LFL4;
	public ModelPart RFL1;
	public ModelPart RFL2;
	public ModelPart RFL3;
	public ModelPart RFL4;
	public ModelPart RML1;
	public ModelPart RML2;
	public ModelPart RML3;
	public ModelPart RML4;
	public ModelPart RBL1;
	public ModelPart RBL2;
	public ModelPart RBL3;
	public ModelPart RBL4;
	public ModelPart Sting;
	public ModelPart ThxRW;
	public ModelPart ThxLW;

	public WaspModel(ModelPart root) {
		super(root);
		this.root = root;
		this.Thx = root.getChild("Thx");
		this.ThxS = root.getChild("Thx").getChild("ThxS");
		this.ThxTop = root.getChild("ThxTop");
		this.Sting = root.getChild("AbF").getChild("Ab").getChild("Sting");
		this.ThxRW = root.getChild("ThxRW");
		this.ThxLW = root.getChild("ThxLW");
		this.Ab = root.getChild("AbF").getChild("Ab");
		this.AbF = root.getChild("AbF");
		this.AbSide = root.getChild("AbF").getChild("Ab").getChild("AbSide");
		this.AbTop = root.getChild("AbF").getChild("Ab").getChild("AbTop");
		this.AbBack = root.getChild("AbF").getChild("Ab").getChild("AbBack");
		this.Neck = root.getChild("Thx").getChild("Neck");
		this.Head1 = root.getChild("Head1");
		this.Head2 = root.getChild("Head1").getChild("Head2");
		this.Head3 = root.getChild("Head1").getChild("Head3");
		this.RMandible1 = root.getChild("Head1").getChild("RMandible1");
		this.RMandible2 = root.getChild("Head1").getChild("RMandible1").getChild("RMandible2");
		this.LMandible1 = root.getChild("Head1").getChild("LMandible1");
		this.LMandible2 = root.getChild("Head1").getChild("LMandible1").getChild("LMandible2");
		this.Eyes = root.getChild("Head1").getChild("Eyes");
		this.AntLE = root.getChild("Head1").getChild("AntLS").getChild("AntLE");
		this.AntRE = root.getChild("Head1").getChild("AntRS").getChild("AntRE");
		this.AntRS = root.getChild("Head1").getChild("AntRS");
		this.AntLS = root.getChild("Head1").getChild("AntLS");
		this.LBL1 = root.getChild("LBL1");
		this.LBL2 = root.getChild("LBL2");
		this.LBL3 = root.getChild("LBL3");
		this.LBL4 = root.getChild("LBL4");
		this.LML1 = root.getChild("LML1");
		this.LML2 = root.getChild("LML2");
		this.LML3 = root.getChild("LML3");
		this.LML4 = root.getChild("LML4");
		this.LFL1 = root.getChild("LFL1");
		this.LFL2 = root.getChild("LFL2");
		this.LFL3 = root.getChild("LFL3");
		this.LFL4 = root.getChild("LFL4");
		this.RFL1 = root.getChild("RFL1");
		this.RFL2 = root.getChild("RFL2");
		this.RFL3 = root.getChild("RFL3");
		this.RFL4 = root.getChild("RFL4");
		this.RML1 = root.getChild("RML1");
		this.RML2 = root.getChild("RML2");
		this.RML3 = root.getChild("RML3");
		this.RML4 = root.getChild("RML4");
		this.RBL1 = root.getChild("RBL1");
		this.RBL2 = root.getChild("RBL2");
		this.RBL3 = root.getChild("RBL3");
		this.RBL4 = root.getChild("RBL4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();

		root.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Thx = root.addOrReplaceChild("Thx", CubeListBuilder.create().texOffs(14, 13).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));
		Thx.addOrReplaceChild("ThxS", CubeListBuilder.create().texOffs(5, 39).addBox(-4.0F, -2.5F, 1.0F, 8.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		Thx.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 12).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition Head1 = root.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(21, 0).addBox(-1.0F, -0.5F, -5.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -1.0F, -0.5236F, 0.0F, 0.0F));
		Head1.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -1.5F, -4.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		Head1.addOrReplaceChild("Head3", CubeListBuilder.create().texOffs(44, 50).addBox(-2.0F, -2.5F, -3.5F, 4.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		Head1.addOrReplaceChild("Eyes", CubeListBuilder.create().texOffs(0, 30).addBox(-3.5F, -0.5F, -4.0F, 7.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition RMandible1 = Head1.addOrReplaceChild("RMandible1", CubeListBuilder.create().texOffs(47, 0).addBox(1.0F, 5.5F, -2.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		RMandible1.addOrReplaceChild("RMandible2", CubeListBuilder.create().texOffs(52, 9).addBox(0.5F, 6.5F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LMandible1 = Head1.addOrReplaceChild("LMandible1", CubeListBuilder.create().texOffs(52, 0).addBox(-2.0F, 5.5F, -2.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		LMandible1.addOrReplaceChild("LMandible2", CubeListBuilder.create().texOffs(47, 9).addBox(-1.5F, 6.5F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition AntLS = Head1.addOrReplaceChild("AntLS", CubeListBuilder.create().texOffs(53, 4).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, -4.0F, -0.3491F, -0.1745F, -0.8727F));
		AntLS.addOrReplaceChild("AntLE", CubeListBuilder.create().texOffs(47, 15).addBox(-6.0F, -0.5F, -1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, -2.0F));
		PartDefinition AntRS = Head1.addOrReplaceChild("AntRS", CubeListBuilder.create().texOffs(53, 4).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.0F, -4.0F, -0.3491F, 0.1745F, 0.9878F));
		AntRS.addOrReplaceChild("AntRE", CubeListBuilder.create().texOffs(47, 15).addBox(0.0F, -0.5F, -1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, -2.0F));

		PartDefinition AbF = root.addOrReplaceChild("AbF", CubeListBuilder.create().texOffs(23, 57).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 11.0F));

		PartDefinition Ab = AbF.addOrReplaceChild("Ab", CubeListBuilder.create().texOffs(9, 100).addBox(-3.5F, -4.5F, -1.0F, 7.0F, 7.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));
		Ab.addOrReplaceChild("AbSide", CubeListBuilder.create().texOffs(10, 66).addBox(-5.0F, -2.0F, 1.0F, 10.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));
		Ab.addOrReplaceChild("AbTop", CubeListBuilder.create().texOffs(15, 80).addBox(-2.0F, -4.5F, 1.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));
		Ab.addOrReplaceChild("AbBack", CubeListBuilder.create().texOffs(22, 121).addBox(-2.0F, -2.0F, 11.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));
		Ab.addOrReplaceChild("Sting", CubeListBuilder.create().texOffs(0, 122).addBox(-0.5F, -0.5F, 13.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		root.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(44, 95).addBox(-6.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 18.0F, 8.0F, 0.2795F, 0.6485F, 0.4436F));
		root.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 88).addBox(-7.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 18.0F, 8.0F, 0.2795F, 0.6485F, 0.4436F));
		root.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(0, 82).addBox(-5.5F, 5.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 18.0F, 8.0F, 0.4946F, 0.5148F, 0.8309F));
		root.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(0, 76).addBox(-3.5F, 9.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 18.0F, 8.0F, 0.5713F, 0.4259F, 0.9995F));
		root.addOrReplaceChild("LML1", CubeListBuilder.create().texOffs(0, 95).addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 18.0F, 5.0F, 0.0F, 0.0F, 0.3491F));
		root.addOrReplaceChild("LML2", CubeListBuilder.create().texOffs(0, 88).addBox(-5.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 18.0F, 5.0F, 0.0F, 0.0F, 0.3491F));
		root.addOrReplaceChild("LML3", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 18.0F, 5.0F, 0.0F, 0.0F, 0.6981F));
		root.addOrReplaceChild("LML4", CubeListBuilder.create().texOffs(0, 76).addBox(-1.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 18.0F, 5.0F, 0.0F, 0.0F, 0.8727F));
		root.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 95).addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 18.0F, 2.0F, -0.2795F, -0.6485F, 0.4436F));
		root.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 88).addBox(-5.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 18.0F, 2.0F, -0.2795F, -0.6485F, 0.4436F));
		root.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 18.0F, 2.0F, -0.4946F, -0.5148F, 0.8309F));
		root.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(0, 76).addBox(-1.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 18.0F, 2.0F, -0.5713F, -0.4259F, 0.9995F));
		root.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 95).addBox(0.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 18.0F, 2.0F, -0.2795F, 0.6485F, -0.4436F));
		root.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 88).addBox(3.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 18.0F, 2.0F, -0.2795F, 0.6485F, -0.4436F));
		root.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(0, 82).addBox(1.5F, 4.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 18.0F, 2.0F, -0.4946F, 0.5148F, -0.8309F));
		root.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(0, 76).addBox(0.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 18.0F, 2.0F, -0.5713F, 0.4259F, -0.9995F));
		root.addOrReplaceChild("RML1", CubeListBuilder.create().texOffs(0, 95).addBox(0.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 18.0F, 5.0F, 0.0F, 0.0F, -0.3491F));
		root.addOrReplaceChild("RML2", CubeListBuilder.create().texOffs(0, 88).addBox(3.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 18.0F, 5.0F, 0.0F, 0.0F, -0.3491F));
		root.addOrReplaceChild("RML3", CubeListBuilder.create().texOffs(0, 82).addBox(1.5F, 4.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 18.0F, 5.0F, 0.0F, 0.0F, -0.6981F));
		root.addOrReplaceChild("RML4", CubeListBuilder.create().texOffs(0, 76).addBox(0.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 18.0F, 5.0F, 0.0F, 0.0F, -0.8727F));
		root.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(44, 95).addBox(-1.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 18.0F, 8.0F, 0.2795F, -0.6485F, -0.4436F));
		root.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 88).addBox(5.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 18.0F, 8.0F, 0.2795F, -0.6485F, -0.4436F));
		root.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(0, 82).addBox(3.5F, 5.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 18.0F, 8.0F, 0.4946F, -0.5148F, -0.8309F));
		root.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(0, 76).addBox(2.5F, 9.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 18.0F, 8.0F, 0.5713F, -0.4259F, -0.9995F));
		root.addOrReplaceChild("ThxTop", CubeListBuilder.create().texOffs(21, 30).addBox(-2.0F, -4.0F, 1.0F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));
		root.addOrReplaceChild("ThxRW", CubeListBuilder.create().texOffs(0, 52).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 13.0F, 4.0F));
		root.addOrReplaceChild("ThxLW", CubeListBuilder.create().texOffs(0, 52).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 13.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void setupAnim(WaspRenderState state) {
		super.setupAnim(state);
		float smoothedTicks = state.animationTicks + (state.animationTicks - state.prevAnimationTicks) * state.partialTick;
		float flap2 = Mth.sin(smoothedTicks * 0.5F) * 0.25F;
		float flap3 = Mth.cos(smoothedTicks * 0.5F) * 0.25F;
		AntLS.zRot = -0.8727F + flap2;
		AntLE.yRot = -0.1745F + flap3;
		AntRS.zRot = 0.9878F - flap3;
		AntRE.yRot = 0.1745F - flap2;

		Head1.yRot = state.yRot / (180F / (float) Math.PI);
		Head1.xRot = state.xRot / (180F / (float) Math.PI) - 1.0F;

		if(state.isFlying) {
			LBL1.xRot = +0.25F;
			LBL2.xRot = +0.25F;
			LBL3.xRot = +0.5F;
			LBL4.xRot = +0.61F;
			LML1.xRot = 0F;
			LML2.xRot = 0F;
			LML3.xRot = 0F;
			LML4.xRot = 0F;
			LFL1.xRot = -0.25F;
			LFL2.xRot = -0.25F;
			LFL3.xRot = -0.5F;
			LFL4.xRot = -0.64F;
			RBL1.xRot = +0.25F;
			RBL2.xRot = +0.25F;
			RBL3.xRot = +0.5F;
			RBL4.xRot = +0.61F;
			RML1.xRot = 0F;
			RML2.xRot = 0F;
			RML3.xRot = 0F;
			RML4.xRot = 0F;
			RFL1.xRot = -0.25F;
			RFL2.xRot = -0.25F;
			RFL3.xRot = -0.5F;
			RFL4.xRot = -0.64F;

			AbF.xRot = -0.8F;
		} else {
			float legX = Mth.cos(state.walkAnimationPos * 2.0F + Mth.PI) * 0.7F * state.walkAnimationSpeed;
			float legX2 = Mth.cos(state.walkAnimationPos * 2.0F) * 0.7F * state.walkAnimationSpeed;
			LBL1.xRot = legX + 0.25F;
			LBL2.xRot = legX + 0.25F;
			LBL3.xRot = legX + 0.5F;
			LBL4.xRot = legX + 0.61F;
			LML1.xRot = legX2;
			LML2.xRot = legX2;
			LML3.xRot = legX2;
			LML4.xRot = legX2;
			LFL1.xRot = legX - 0.25F;
			LFL2.xRot = legX - 0.25F;
			LFL3.xRot = legX - 0.5F;
			LFL4.xRot = legX - 0.64F;
			RBL1.xRot = legX2 + 0.25F;
			RBL2.xRot = legX2 + 0.25F;
			RBL3.xRot = legX2 + 0.5F;
			RBL4.xRot = legX2 + 0.61F;
			RML1.xRot = legX;
			RML2.xRot = legX;
			RML3.xRot = legX;
			RML4.xRot = legX;
			RFL1.xRot = legX2 - 0.25F;
			RFL2.xRot = legX2 - 0.25F;
			RFL3.xRot = legX2 - 0.5F;
			RFL4.xRot = legX2 - 0.64F;

			AbF.xRot = -0.2F;
		}
	}
}
