package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.BotFlyRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BotFlyModel extends EntityModel<BotFlyRenderState> {
	public ModelPart root;
    private final ModelPart RWing1;
	private final ModelPart RWing2;
	private final ModelPart LWing1;
	private final ModelPart LWing2;

    public BotFlyModel(ModelPart root) {
		super(root);
		this.root = root;
        root.getChild("Head");
        root.getChild("HeadFront");
        root.getChild("HeadTop");
        root.getChild("HeadBottom");
        root.getChild("EyeR");
        root.getChild("EveL");
        root.getChild("Thorax1");
        root.getChild("Thorax2");
        root.getChild("Thorax3");
        root.getChild("Ab1");
        root.getChild("Ab2");
        root.getChild("Ab3");
        root.getChild("Ab4");
        this.RWing1 = root.getChild("RWing1");
		this.RWing2 = root.getChild("RWing2");
		this.LWing1 = root.getChild("LWing1");
		this.LWing2 = root.getChild("LWing2");
        root.getChild("LegRF1");
        root.getChild("LegRF2");
        root.getChild("LegRF3");
        root.getChild("LegLF1");
        root.getChild("LegLF2");
        root.getChild("LegLF3");
        root.getChild("LegRM1");
        root.getChild("LegRM2");
        root.getChild("LegRM3");
        root.getChild("LegLM1");
        root.getChild("LegLM2");
        root.getChild("LegLM3");
        root.getChild("LegRB1");
        root.getChild("LegRB2");
        root.getChild("LegRB3");
        root.getChild("LegLB1");
        root.getChild("LegLB2");
        root.getChild("LegLB3");
    }

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(24, 0).addBox(-3.0F, -2.0F, -4.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));

        partdefinition.addOrReplaceChild("HeadFront", CubeListBuilder.create().texOffs(15, 0).addBox(-2.0F, -1.5F, -4.5F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));

        partdefinition.addOrReplaceChild("HeadTop", CubeListBuilder.create().texOffs(11, 5).addBox(-2.0F, -2.5F, -3.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));

        partdefinition.addOrReplaceChild("HeadBottom", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 1.5F, -3.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));

        partdefinition.addOrReplaceChild("EyeR", CubeListBuilder.create().texOffs(0, 25).addBox(-4.0F, -1.0F, -3.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -4.0F, -0.2967F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("EveL", CubeListBuilder.create().texOffs(0, 25).addBox(3.0F, -1.0F, -3.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -4.0F, -0.2967F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("Thorax1", CubeListBuilder.create().texOffs(20, 10).addBox(-4.0F, -4.0F, 2.0F, 8.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -4.0F, -0.2793F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("Thorax2", CubeListBuilder.create().texOffs(19, 22).addBox(-3.5F, -2.0F, 0.0F, 7.0F, 5.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 15.0F, -4.0F));

        partdefinition.addOrReplaceChild("Thorax3", CubeListBuilder.create().texOffs(37, 28).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));

        partdefinition.addOrReplaceChild("Ab1", CubeListBuilder.create().texOffs(47, 0).addBox(-3.0F, -3.5F, 5.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -4.0F, -0.2793F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("Ab2", CubeListBuilder.create().texOffs(42, 7).addBox(-4.0F, -4.0F, 6.0F, 8.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -4.0F, -0.2793F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("Ab3", CubeListBuilder.create().texOffs(44, 18).addBox(-3.5F, -3.5F, 9.0F, 7.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -4.0F, -0.2793F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("Ab4", CubeListBuilder.create().texOffs(52, 27).addBox(-2.5F, -2.5F, 11.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -4.0F, -0.2793F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("RWing1", CubeListBuilder.create().texOffs(0, 20).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 11.2F, -1.0F, 0.0F, -1.5708F, 0.0F));

        partdefinition.addOrReplaceChild("RWing2", CubeListBuilder.create().texOffs(0, 9).addBox(-3.5F, -0.5F, 1.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 11.2F, -1.0F, 0.0F, -1.5708F, 0.0F));

        partdefinition.addOrReplaceChild("LWing1", CubeListBuilder.create().texOffs(0, 20).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 11.2F, -1.0F, 0.0F, 1.5708F, 0.0F));

        partdefinition.addOrReplaceChild("LWing2", CubeListBuilder.create().texOffs(0, 9).addBox(1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 11.2F, -1.0F, 0.0F, 1.5708F, 0.0F));

        partdefinition.addOrReplaceChild("LegRF1", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -3.0F, -1.3963F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("LegRF2", CubeListBuilder.create().texOffs(5, 19).addBox(-0.5F, -1.3F, -3.2F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -3.0F, 0.6981F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("LegRF3", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, 2.7F, -0.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -3.0F, -0.4363F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("LegLF1", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -3.0F, -1.3963F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("LegLF2", CubeListBuilder.create().texOffs(5, 19).addBox(-0.5F, -1.3F, -3.2F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(3.0F, 18.0F, -3.0F, 0.6981F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("LegLF3", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, 2.7F, -0.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -3.0F, -0.4363F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("LegRM1", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -2.0F, -1.3963F, 1.5708F, 0.0F));

        partdefinition.addOrReplaceChild("LegRM2", CubeListBuilder.create().texOffs(5, 19).addBox(-0.5F, -1.3F, -3.2F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -2.0F, 0.6981F, 1.5708F, 0.0F));

        partdefinition.addOrReplaceChild("LegRM3", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, 2.7F, -0.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -2.0F, -0.4363F, 1.5708F, 0.0F));

        partdefinition.addOrReplaceChild("LegLM1", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -2.0F, -1.3963F, -1.5708F, 0.0F));

        partdefinition.addOrReplaceChild("LegLM2", CubeListBuilder.create().texOffs(5, 19).addBox(-0.5F, -1.3F, -3.2F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(3.0F, 18.0F, -2.0F, 0.6981F, -1.5708F, 0.0F));

        partdefinition.addOrReplaceChild("LegLM3", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, 2.7F, -0.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -2.0F, -0.4363F, -1.5708F, 0.0F));

        partdefinition.addOrReplaceChild("LegRB1", CubeListBuilder.create().texOffs(0, 10).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -1.0F, -1.5708F, 2.618F, 0.0F));

        partdefinition.addOrReplaceChild("LegRB2", CubeListBuilder.create().texOffs(5, 19).addBox(-0.5F, 0.5F, -4.4F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -1.0F, -0.1047F, 2.618F, 0.0F));

        partdefinition.addOrReplaceChild("LegRB3", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, 4.3F, -3.1F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -1.0F, -0.4363F, 2.618F, 0.0F));

        partdefinition.addOrReplaceChild("LegLB1", CubeListBuilder.create().texOffs(0, 10).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -1.0F, -1.5708F, -2.618F, 0.0F));

        partdefinition.addOrReplaceChild("LegLB2", CubeListBuilder.create().texOffs(5, 19).addBox(-0.5F, 0.5F, -4.4F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(3.0F, 18.0F, -1.0F, -0.1047F, -2.618F, 0.0F));

        partdefinition.addOrReplaceChild("LegLB3", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, 4.3F, -3.1F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -1.0F, -0.4363F, -2.618F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(BotFlyRenderState state) {
		RWing1.xRot = state.flap;
		RWing2.xRot = state.flap;
		LWing1.xRot = state.flap;
		LWing2.xRot = state.flap;
	}
}
