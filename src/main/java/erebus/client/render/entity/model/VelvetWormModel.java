package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.VelvetWormRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class VelvetWormModel extends EntityModel<VelvetWormRenderState> {
	public ModelPart root;
	private final ModelPart Head1;
	private final ModelPart Body1;
	private final ModelPart Body1RightLeg;
	private final ModelPart Body1LeftLeg;
	private final ModelPart Body2;
	private final ModelPart Body2RightLeg;
	private final ModelPart Body2LeftLeg;
	private final ModelPart Tail;
	private final ModelPart TailFin;
	private final ModelPart LAnt;
	private final ModelPart RAnt;

	public VelvetWormModel(ModelPart root) {
		super(root);
		this.root = root;
		this.Head1 = root.getChild("Head1");
		this.Body1 = root.getChild("Body1");
		this.Body1RightLeg = root.getChild("Body1RightLeg");
		this.Body1LeftLeg = root.getChild("Body1LeftLeg");
		this.Body2 = root.getChild("Body2");
		this.Body2RightLeg = root.getChild("Body2RightLeg");
		this.Body2LeftLeg = root.getChild("Body2LeftLeg");
		this.Tail = root.getChild("Tail");
		this.TailFin = root.getChild("TailFin");
		LAnt = Head1.getChild("LAnt");
		RAnt =  Head1.getChild("RAnt");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head1 = partdefinition.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(12, 0).addBox(-2.5F, -2.0F, -5.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 1.0F));

		PartDefinition Head2 = Head1.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(26, 18).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 1.0F, -5.0F));

		PartDefinition Head3 = Head1.addOrReplaceChild("Head3", CubeListBuilder.create().texOffs(26, 18).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 1.0F, -5.0F));

		PartDefinition LAnt = Head1.addOrReplaceChild("LAnt", CubeListBuilder.create().texOffs(16, 21).addBox(-0.6014F, -0.5F, -6.7287F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -0.5F, -5.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition RAnt = Head1.addOrReplaceChild("RAnt", CubeListBuilder.create().texOffs(16, 21).addBox(-0.3986F, -0.5F, -6.7287F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -0.5F, -5.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition Body1 = partdefinition.addOrReplaceChild("Body1", CubeListBuilder.create().texOffs(0, 10).addBox(-3.0F, -2.5F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition Body1RightLeg = partdefinition.addOrReplaceChild("Body1RightLeg", CubeListBuilder.create().texOffs(0, 5).addBox(-3.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition Body1LeftLeg = partdefinition.addOrReplaceChild("Body1LeftLeg", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 21.5F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition Body2 = partdefinition.addOrReplaceChild("Body2", CubeListBuilder.create().texOffs(0, 10).addBox(-3.0F, -2.5F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition Body2RightLeg = partdefinition.addOrReplaceChild("Body2RightLeg", CubeListBuilder.create().texOffs(0, 5).addBox(-3.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition Body2LeftLeg = partdefinition.addOrReplaceChild("Body2LeftLeg", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 21.5F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition Tail = partdefinition.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(1, 24).addBox(-2.0F, -1.0F, -0.5F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0F));

		PartDefinition TailFin = partdefinition.addOrReplaceChild("TailFin", CubeListBuilder.create().texOffs(13, 9).addBox(-3.0F, 0.5F, -0.5F, 6.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(VelvetWormRenderState state) {
	}
}
