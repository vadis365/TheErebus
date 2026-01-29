package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.FlyRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class FlyModel extends EntityModel<FlyRenderState> {
	public ModelPart root;
	private final ModelPart thorax;
	private final ModelPart abdomen;
	private final ModelPart right_eye;
	private final ModelPart left_eye;
	private final ModelPart leg_left_back;
	private final ModelPart head;
	private final ModelPart leg_left_front;
	private final ModelPart leg_left_mid;
	private final ModelPart leg_right_back;
	private final ModelPart leg_right_front;
	private final ModelPart leg_right_mid;
	private final ModelPart wing_right;
	private final ModelPart wing_left;

	public FlyModel(ModelPart root) {
		super(root);
		this.root = root;
		this.thorax = root.getChild("thorax");
		this.abdomen = root.getChild("abdomen");
		this.right_eye = root.getChild("right_eye");
		this.left_eye = root.getChild("left_eye");
		this.leg_left_back = root.getChild("leg_left_back");
		this.head = root.getChild("head");
		this.leg_left_front = root.getChild("leg_left_front");
		this.leg_left_mid = root.getChild("leg_left_mid");
		this.leg_right_back = root.getChild("leg_right_back");
		this.leg_right_front = root.getChild("leg_right_front");
		this.leg_right_mid = root.getChild("leg_right_mid");
		this.wing_right = root.getChild("wing_right");
		this.wing_left = root.getChild("wing_left");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition thorax = partdefinition.addOrReplaceChild("thorax", CubeListBuilder.create().texOffs(24, 7).addBox(-2.5F, -2.5F, -2.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));

		PartDefinition abdomen = partdefinition.addOrReplaceChild("abdomen", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, 2.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));

		PartDefinition right_eye = partdefinition.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-2.5F, -2.5F, -3.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 19.0F, -2.0F));

		PartDefinition left_eye = partdefinition.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(24, 0).addBox(0.5F, -2.5F, -3.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, -2.0F));

		PartDefinition leg_left_back = partdefinition.addOrReplaceChild("leg_left_back", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, 0.0F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 19.0F, 0.0F, -0.3876F, -0.3613F, 0.8571F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(34, 0).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, -2.0F));

		PartDefinition leg_left_front = partdefinition.addOrReplaceChild("leg_left_front", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, 0.0F, -1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 19.0F, -1.0F, 0.3876F, 0.3613F, 0.8571F));

		PartDefinition leg_left_mid = partdefinition.addOrReplaceChild("leg_left_mid", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, 0.0F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 19.0F, -0.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition leg_right_back = partdefinition.addOrReplaceChild("leg_right_back", CubeListBuilder.create().texOffs(0, 12).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 19.0F, 0.0F, -0.3876F, 0.3613F, -0.8571F));

		PartDefinition leg_right_front = partdefinition.addOrReplaceChild("leg_right_front", CubeListBuilder.create().texOffs(0, 12).addBox(-6.0F, 0.0F, -1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 19.0F, -1.0F, 0.3876F, -0.3613F, -0.8571F));

		PartDefinition leg_right_mid = partdefinition.addOrReplaceChild("leg_right_mid", CubeListBuilder.create().texOffs(0, 12).addBox(-6.0F, 0.0F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 19.0F, -0.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition wing_right = partdefinition.addOrReplaceChild("wing_right", CubeListBuilder.create().texOffs(0, 25).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 16.0F, 0.0F, 0.5236F, -0.1745F, 0.0F));

		PartDefinition wing_left = partdefinition.addOrReplaceChild("wing_left", CubeListBuilder.create().texOffs(0, 25).mirror().addBox(0.0F, 0.0F, 0.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 16.0F, 0.0F, 0.5236F, 0.1745F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(FlyRenderState state) {

	}
}
