package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.BeetleLarvaRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class BeetleLarvaModel extends EntityModel<BeetleLarvaRenderState> {
	public ModelPart root;
	private final ModelPart torso1;
	private final ModelPart torso2;
	private final ModelPart torso3;
	private final ModelPart torso4;
	private final ModelPart torso5;
	private final ModelPart torso6;
    private final ModelPart legleft1;
	private final ModelPart legright1;
	private final ModelPart legleft2;
	private final ModelPart legright2;
	private final ModelPart legleft3;
	private final ModelPart legright3;
	private final ModelPart head;
	private final ModelPart jawleft;
	private final ModelPart jawright;
	private final ModelPart mouthjaw;
	private final ModelPart sensorleft;
	private final ModelPart sensorright;
	private final ModelPart horn1;
	private final ModelPart horn2;
	private final ModelPart horn3;
	private final ModelPart titanL1;
	private final ModelPart titanL2;
	private final ModelPart titanR1;
	private final ModelPart titanR2;
	private final ModelPart jawStagLeft;
	private final ModelPart jawStagRight;

	public BeetleLarvaModel(ModelPart root) {
		super(root);
		this.root = root;
		this.torso1 = root.getChild("torso1");
		this.torso2 = root.getChild("torso2");
		this.torso3 = root.getChild("torso3");
		this.torso4 = root.getChild("torso4");
		this.torso5 = root.getChild("torso5");
		this.torso6 = root.getChild("torso6");
        root.getChild("torso7");
        root.getChild("torso8");
        this.legleft1 = root.getChild("legleft1");
		this.legright1 = root.getChild("legright1");
		this.legleft2 = root.getChild("legleft2");
		this.legright2 = root.getChild("legright2");
		this.legleft3 = root.getChild("legleft3");
		this.legright3 = root.getChild("legright3");
		this.head = root.getChild("head");
		this.jawleft = root.getChild("jawleft");
		this.jawright = root.getChild("jawright");
		this.mouthjaw = root.getChild("mouthjaw");
		this.sensorleft = root.getChild("sensorleft");
		this.sensorright = root.getChild("sensorright");
		this.horn1 = root.getChild("horn1");
		this.horn2 = root.getChild("horn2");
		this.horn3 = root.getChild("horn3");
		this.titanL1 = root.getChild("titanL1");
		this.titanL2 = root.getChild("titanL2");
		this.titanR1 = root.getChild("titanR1");
		this.titanR2 = root.getChild("titanR2");
		this.jawStagLeft = root.getChild("jawStagLeft");
		this.jawStagRight = root.getChild("jawStagRight");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();

        root.addOrReplaceChild("torso1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, 0.1487F, 0.0F, 0.0F));

        root.addOrReplaceChild("torso2", CubeListBuilder.create().texOffs(0, 8).addBox(-3.5F, -2.5F, 1.0F, 7.0F, 6.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, 0.0744F, 0.0F, 0.0F));

        root.addOrReplaceChild("torso3", CubeListBuilder.create().texOffs(0, 17).addBox(-3.5F, -3.5F, 2.5F, 7.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.0546F, 0.0F, 0.0F));

        root.addOrReplaceChild("torso4", CubeListBuilder.create().texOffs(0, 31).addBox(-3.5F, -3.1F, 8.0F, 7.0F, 7.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 20.0F, -10.0F));

        root.addOrReplaceChild("torso5", CubeListBuilder.create().texOffs(0, 44).addBox(-3.5F, -3.5F, 12.5F, 7.0F, 6.0F, 3.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.0766F, 0.0F, 0.0F));

        root.addOrReplaceChild("torso6", CubeListBuilder.create().texOffs(0, 54).addBox(-3.0F, -3.5F, 15.5F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.0766F, 0.0F, 0.0F));

        root.addOrReplaceChild("torso7", CubeListBuilder.create().texOffs(17, 54).addBox(-2.5F, -6.0F, 16.5F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.2603F, 0.0F, 0.0F));

        root.addOrReplaceChild("torso8", CubeListBuilder.create().texOffs(32, 54).addBox(-2.0F, -6.5F, 18.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.3346F, 0.0F, 0.0F));

        root.addOrReplaceChild("legleft1", CubeListBuilder.create().texOffs(65, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 21.5F, -7.0F, -0.2231F, 0.1115F, -0.1859F));

        root.addOrReplaceChild("legright1", CubeListBuilder.create().texOffs(70, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, -7.0F, -0.2231F, -0.1115F, 0.1859F));

        root.addOrReplaceChild("legleft2", CubeListBuilder.create().texOffs(65, 5).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 21.5F, -6.0F, 0.0F, 0.0F, -0.3346F));

        root.addOrReplaceChild("legright2", CubeListBuilder.create().texOffs(70, 5).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, -6.0F, 0.0F, 0.0F, 0.3346F));

        root.addOrReplaceChild("legleft3", CubeListBuilder.create().texOffs(65, 10).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 21.5F, -5.0F, 0.1859F, -0.2231F, -0.3346F));

        root.addOrReplaceChild("legright3", CubeListBuilder.create().texOffs(70, 10).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, -5.0F, 0.1859F, 0.2231F, 0.3346F));

        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(40, 0).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, 1.2269F, 0.0F, 0.0F));

        root.addOrReplaceChild("jawleft", CubeListBuilder.create().texOffs(40, 8).addBox(1.8F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.2603F, 0.0F, -0.1859F));

        root.addOrReplaceChild("jawright", CubeListBuilder.create().texOffs(45, 8).addBox(-2.8F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.2602F, 0.0F, 0.1859F));

        root.addOrReplaceChild("mouthjaw", CubeListBuilder.create().texOffs(40, 13).addBox(-1.5F, 2.3F, 1.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.632F, 0.0F, 0.0F));

        root.addOrReplaceChild("sensorleft", CubeListBuilder.create().texOffs(40, 16).addBox(1.0F, 0.0F, -1.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.7064F, -0.1487F, 0.0F));

        root.addOrReplaceChild("sensorright", CubeListBuilder.create().texOffs(43, 16).addBox(-2.0F, 0.0F, -1.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.7064F, 0.1487F, 0.0F));

        root.addOrReplaceChild("horn1", CubeListBuilder.create().texOffs(44, 3).addBox(-1.0F, -0.5F, -2.6F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, 0.3491F, 0.0F, 0.0F));

        root.addOrReplaceChild("horn2", CubeListBuilder.create().texOffs(45, 3).addBox(-0.5F, -1.0F, -2.4F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.1396F, 0.0F, 0.0F));

        root.addOrReplaceChild("horn3", CubeListBuilder.create().texOffs(46, 4).addBox(-0.5F, -3.0F, -2.4F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.1396F, 0.0F, 0.0F));

        root.addOrReplaceChild("titanL1", CubeListBuilder.create().texOffs(45, 3).addBox(0.5F, -1.0F, -1.4F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.1396F, 0.0F, 0.0F));

        root.addOrReplaceChild("titanL2", CubeListBuilder.create().texOffs(44, 0).addBox(0.5F, -4.0F, -1.4F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.1396F, 0.0F, 0.0F));

        root.addOrReplaceChild("titanR1", CubeListBuilder.create().texOffs(45, 3).addBox(-1.5F, -1.0F, -1.4F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.1396F, 0.0F, 0.0F));

        root.addOrReplaceChild("titanR2", CubeListBuilder.create().texOffs(44, 0).addBox(-1.5F, -4.0F, -1.4F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.1396F, 0.0F, 0.0F));

        root.addOrReplaceChild("jawStagLeft", CubeListBuilder.create().texOffs(35, 8).addBox(1.8F, 0.0F, 0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -1.0472F, -0.1309F, 0.0F));

        root.addOrReplaceChild("jawStagRight", CubeListBuilder.create().texOffs(50, 8).addBox(-2.8F, 0.0F, 0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -1.0472F, -0.1309F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(BeetleLarvaRenderState state) {
		float ba = Mth.cos(state.walkAnimationPos) * 1.5F * state.walkAnimationSpeed;
		float bb = Mth.cos(state.walkAnimationPos + 1.0f) * 2.25F * state.walkAnimationSpeed;
		float bc = Mth.cos(state.walkAnimationPos + 2.0F) * 3.F * state.walkAnimationSpeed;
		float bd = Mth.cos(state.walkAnimationPos + 3.0F) * 2.5F * state.walkAnimationSpeed;
		float be = Mth.cos(state.walkAnimationPos + 4.0F) * 1.5F * state.walkAnimationSpeed;
		float bf = Mth.cos(state.walkAnimationPos + 5.0F) * 0.75F * state.walkAnimationSpeed;

		head.x = bf;
		jawleft.x = bf;
		jawright.x = bf;
		mouthjaw.x = bf;
		sensorleft.x = bf;
		sensorright.x = bf;
		horn1.x = bf;
		horn2.x = bf;
		horn3.x = bf;
		titanL1.x = bf;
		titanL2.x = bf;
		titanR1.x = bf;
		titanR2.x = bf;
		jawStagLeft.x = bf;
		jawStagRight.x = bf;

		torso1.y = ba + 20F;

		torso2.y = bb + 20F;

		torso3.y = bc + 20F;

		torso4.y = bd + 20F;

		torso5.y = be + 20F;

		torso6.y = bf + 20F;

		legright1.y = bc + 21F;
		legleft1.y = bc + 21F;

		legright2.y = bc + 21F;
		legleft2.y = bc + 21F;

		legright3.y = bc + 21F;
		legleft3.y = bc + 21F;

		legright1.xRot = -Mth.cos(state.walkAnimationPos) * 1.3F * state.walkAnimationSpeed;
		legleft1.xRot = Mth.cos(state.walkAnimationPos) * 1.3F * state.walkAnimationSpeed;

		legright1.xRot = -Mth.cos(state.walkAnimationPos) * 0.5F * state.walkAnimationSpeed;
		legleft1.xRot = Mth.cos(state.walkAnimationPos) * 0.5F * state.walkAnimationSpeed;

		legright2.xRot = -Mth.cos(state.walkAnimationPos + (float) Math.PI) * 1.3F * state.walkAnimationSpeed;
		legleft2.xRot = Mth.cos(state.walkAnimationPos + (float) Math.PI) * 1.3F * state.walkAnimationSpeed;

		legright2.xRot = -Mth.cos(state.walkAnimationPos + 0.5F + (float) Math.PI) * 0.5F * state.walkAnimationSpeed;
		legleft2.xRot = Mth.cos(state.walkAnimationPos + 0.5F + (float) Math.PI) * 0.5F * state.walkAnimationSpeed;

		legright3.xRot = -Mth.cos(state.walkAnimationPos) * 1.3F * state.walkAnimationSpeed;
		legleft3.xRot = Mth.cos(state.walkAnimationPos) * 1.3F * state.walkAnimationSpeed;

		legright3.xRot = -Mth.cos(state.walkAnimationPos + 1.0f) * 0.5F * state.walkAnimationSpeed;
		legleft3.xRot = Mth.cos(state.walkAnimationPos + 1.0f) * 0.5F * state.walkAnimationSpeed;
	}
}
