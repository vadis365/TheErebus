package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.WorkerBeeRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class WorkerBeeModel extends EntityModel<WorkerBeeRenderState> {
	public ModelPart root;
	public ModelPart Thx;
	public ModelPart ThxS;
	public ModelPart Ab;
	public ModelPart AbF;
	public ModelPart AbSide;
	public ModelPart AbTop;
	public ModelPart AbBack;
	public ModelPart Head1;
	public ModelPart Sting;
	public ModelPart ThxTop;
	public ModelPart ThxRW;
	public ModelPart ThxLW;
	public ModelPart LeftFrontLeg;
	public ModelPart LeftMidLeg;
	public ModelPart LeftBackLeg;
	public ModelPart RightFrontLeg;
	public ModelPart RightMIdLeg;
	public ModelPart RightBackLeg;

	public WorkerBeeModel(ModelPart root) {
		super(root);
		this.root = root;
		this.Thx = root.getChild("Thx");
		this.ThxS = root.getChild("ThxS");
		this.Ab = root.getChild("Ab");
		this.AbF = root.getChild("AbF");
		this.AbSide = root.getChild("AbSide");
		this.AbTop = root.getChild("AbTop");
		this.AbBack = root.getChild("AbBack");
		this.Head1 = root.getChild("Head1");
		this.Sting = root.getChild("Sting");
		this.ThxTop = root.getChild("ThxTop");
		this.ThxRW = root.getChild("ThxRW");
		this.ThxLW = root.getChild("ThxLW");
		this.LeftFrontLeg = root.getChild("LeftFrontLeg");
		this.LeftMidLeg = root.getChild("LeftMidLeg");
		this.LeftBackLeg = root.getChild("LeftBackLeg");
		this.RightFrontLeg = root.getChild("RightFrontLeg");
		this.RightMIdLeg = root.getChild("RightMIdLeg");
		this.RightBackLeg = root.getChild("RightBackLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Thx = partdefinition.addOrReplaceChild("Thx", CubeListBuilder.create().texOffs(14, 14).addBox(-3.5F, -6.0F, 0.0F, 7.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

		PartDefinition ThxS = partdefinition.addOrReplaceChild("ThxS", CubeListBuilder.create().texOffs(5, 42).addBox(-4.5F, -4.5F, 1.0F, 9.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

		PartDefinition Ab = partdefinition.addOrReplaceChild("Ab", CubeListBuilder.create().texOffs(14, 100).addBox(-3.5F, -5.0F, -1.0F, 7.0F, 8.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, 11.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition AbF = partdefinition.addOrReplaceChild("AbF", CubeListBuilder.create().texOffs(23, 60).addBox(-2.0F, -3.0F, -5.0F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, 11.0F, -0.3316F, 0.0F, 0.0F));

		PartDefinition AbSide = partdefinition.addOrReplaceChild("AbSide", CubeListBuilder.create().texOffs(12, 71).addBox(-5.0F, -3.5F, 1.0F, 10.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, 11.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition AbTop = partdefinition.addOrReplaceChild("AbTop", CubeListBuilder.create().texOffs(15, 85).addBox(-2.5F, -6.5F, 1.0F, 5.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, 11.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition AbBack = partdefinition.addOrReplaceChild("AbBack", CubeListBuilder.create().texOffs(22, 121).addBox(-2.0F, -3.0F, 10.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, 11.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition Head1 = partdefinition.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(23, 0).addBox(-2.0F, -1.5F, -6.0F, 4.0F, 4.0F, 9.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 16.0F, -1.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition Head2 = Head1.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.5F, -5.0F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head3 = Head1.addOrReplaceChild("Head3", CubeListBuilder.create().texOffs(43, 41).addBox(-2.5F, -3.5F, -4.5F, 5.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Eyes = Head1.addOrReplaceChild("Eyes", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -1.5F, -4.0F, 8.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RMandible1 = Head1.addOrReplaceChild("RMandible1", CubeListBuilder.create().texOffs(57, 0).addBox(-2.0F, 6.5F, -4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RMandible2 = Head1.addOrReplaceChild("RMandible2", CubeListBuilder.create().texOffs(57, 12).addBox(-1.5F, 7.5F, -4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LMandible1 = Head1.addOrReplaceChild("LMandible1", CubeListBuilder.create().texOffs(50, 0).addBox(1.0F, 6.5F, -4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LMandible2 = Head1.addOrReplaceChild("LMandible2", CubeListBuilder.create().texOffs(50, 12).addBox(0.5F, 7.5F, -4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition AntLS = Head1.addOrReplaceChild("AntLS", CubeListBuilder.create().texOffs(51, 5).addBox(1.5F, 1.5F, -9.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition AntLE = Head1.addOrReplaceChild("AntLE", CubeListBuilder.create().texOffs(47, 17).addBox(-1.0F, -2.5F, -10.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 1.5708F));

		PartDefinition AntRS = Head1.addOrReplaceChild("AntRS", CubeListBuilder.create().texOffs(51, 5).addBox(-2.5F, 1.5F, -9.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition AntRE = Head1.addOrReplaceChild("AntRE", CubeListBuilder.create().texOffs(47, 17).addBox(-1.0F, 1.5F, -10.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 1.5708F));

		PartDefinition Sting = partdefinition.addOrReplaceChild("Sting", CubeListBuilder.create().texOffs(0, 122).addBox(-1.0F, -1.0F, 12.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, 11.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition ThxTop = partdefinition.addOrReplaceChild("ThxTop", CubeListBuilder.create().texOffs(23, 32).addBox(-3.0F, -7.0F, 1.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

		PartDefinition ThxRW = partdefinition.addOrReplaceChild("ThxRW", CubeListBuilder.create().texOffs(0, 55).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-4.0F, 13.0F, 4.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition RWingBack = ThxRW.addOrReplaceChild("RWingBack", CubeListBuilder.create().texOffs(42, 26).addBox(1.5F, -1.0F, 6.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RWingMid = ThxRW.addOrReplaceChild("RWingMid", CubeListBuilder.create().texOffs(19, 41).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RWingFront = ThxRW.addOrReplaceChild("RWingFront", CubeListBuilder.create().texOffs(42, 26).addBox(-2.5F, -1.0F, 6.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ThxLW = partdefinition.addOrReplaceChild("ThxLW", CubeListBuilder.create().texOffs(0, 55).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(4.0F, 13.0F, 4.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition LWingBack = ThxLW.addOrReplaceChild("LWingBack", CubeListBuilder.create().texOffs(42, 26).addBox(-2.5F, -1.0F, 6.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LWingMid = ThxLW.addOrReplaceChild("LWingMid", CubeListBuilder.create().texOffs(19, 41).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LWingFront = ThxLW.addOrReplaceChild("LWingFront", CubeListBuilder.create().texOffs(42, 26).addBox(1.5F, -1.0F, 6.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftFrontLeg = partdefinition.addOrReplaceChild("LeftFrontLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 18.0F, 2.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition LFL1 = LeftFrontLeg.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 97).addBox(0.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition LFL2 = LFL1.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 89).addBox(3.0F, 0.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LFL3 = LFL1.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(0, 75).addBox(1.5F, 4.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition LFL4 = LFL1.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(0, 70).addBox(0.5F, 8.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5672F));

		PartDefinition LeftMidLeg = partdefinition.addOrReplaceChild("LeftMidLeg", CubeListBuilder.create(), PartPose.offset(4.0F, 18.0F, 5.0F));

		PartDefinition LML1 = LeftMidLeg.addOrReplaceChild("LML1", CubeListBuilder.create().texOffs(0, 97).addBox(0.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition LML2 = LML1.addOrReplaceChild("LML2", CubeListBuilder.create().texOffs(0, 89).addBox(3.0F, 0.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LML3 = LML1.addOrReplaceChild("LML3", CubeListBuilder.create().texOffs(0, 75).addBox(1.5F, 4.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition LML4 = LML1.addOrReplaceChild("LML4", CubeListBuilder.create().texOffs(0, 70).addBox(0.5F, 8.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5672F));

		PartDefinition LeftBackLeg = partdefinition.addOrReplaceChild("LeftBackLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 18.0F, 8.0F, 0.0F, -0.6981F, 0.0F));

		PartDefinition LBL1 = LeftBackLeg.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(44, 95).mirror().addBox(-1.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5061F));

		PartDefinition LBL2 = LBL1.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 103).addBox(4.0F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LBL3 = LBL1.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(0, 82).addBox(2.5F, 5.5F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition LBL4 = LBL1.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(0, 70).addBox(1.5F, 9.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition RightFrontLeg = partdefinition.addOrReplaceChild("RightFrontLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 18.0F, 2.0F, 0.0F, -0.6981F, 0.0F));

		PartDefinition RFL1 = RightFrontLeg.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 97).addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition RFL2 = RFL1.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 89).addBox(-5.0F, 0.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RFL3 = RFL1.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(0, 75).addBox(-3.5F, 4.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition RFL4 = RFL1.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(0, 70).addBox(-1.5F, 8.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5672F));

		PartDefinition RightMIdLeg = partdefinition.addOrReplaceChild("RightMIdLeg", CubeListBuilder.create(), PartPose.offset(-4.0F, 18.0F, 5.0F));

		PartDefinition RML1 = RightMIdLeg.addOrReplaceChild("RML1", CubeListBuilder.create().texOffs(0, 97).addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition RML2 = RML1.addOrReplaceChild("RML2", CubeListBuilder.create().texOffs(0, 89).addBox(-5.0F, 0.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RML3 = RML1.addOrReplaceChild("RML3", CubeListBuilder.create().texOffs(0, 75).addBox(-3.5F, 4.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition RML4 = RML1.addOrReplaceChild("RML4", CubeListBuilder.create().texOffs(0, 70).addBox(-1.5F, 8.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5672F));

		PartDefinition RightBackLeg = partdefinition.addOrReplaceChild("RightBackLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 18.0F, 8.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition RBL1 = RightBackLeg.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(44, 95).addBox(-6.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5061F));

		PartDefinition RBL2 = RBL1.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 103).addBox(-7.0F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RBL3 = RBL1.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(0, 82).addBox(-5.5F, 5.5F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition RBL4 = RBL1.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(0, 70).addBox(-2.5F, 9.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void setupAnim(WorkerBeeRenderState state) {
		Head1.yRot = state.yRot / (180F / (float) Math.PI);
	}
}
