package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.CentipedeRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CentipedeModel extends EntityModel<CentipedeRenderState> {
	public ModelPart root;
	private final ModelPart Head1;
	private final ModelPart Neck;
	public final ModelPart BodA1;
	private final ModelPart RightLeg1;
	private final ModelPart RightLeg2;
	private final ModelPart BodB1;
	private final ModelPart LeftLeg1;
	private final ModelPart LeftLeg2;
	private final ModelPart Tail;
	private final ModelPart RAnt;
	private final ModelPart LAnt;

	public CentipedeModel(ModelPart root) {
		super(root);
		this.root = root;
		this.Head1 = root.getChild("Head1");
		this.RAnt = Head1.getChild("RAnt");
		this.LAnt = Head1.getChild("LAnt");
		this.Neck = root.getChild("Neck");
		this.BodA1 = root.getChild("BodA1");
		this.RightLeg1 = root.getChild("RightLeg1");
		this.RightLeg2 = root.getChild("RightLeg2");
		this.BodB1 = root.getChild("BodB1");
		this.LeftLeg1 = root.getChild("LeftLeg1");
		this.LeftLeg2 = root.getChild("LeftLeg2");
		this.Tail = root.getChild("Tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Head1 = partdefinition.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(23, 14).addBox(-2.5F, -1.5F, -2.0F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition Head3 = Head1.addOrReplaceChild("Head3", CubeListBuilder.create().texOffs(26, 9).addBox(-1.5F, -1.5F, -7.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition RAnt = Head1.addOrReplaceChild("RAnt", CubeListBuilder.create().texOffs(23, 0).addBox(-0.4307F, -0.5F, -6.4327F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -0.5F, -4.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition LAnt = Head1.addOrReplaceChild("LAnt", CubeListBuilder.create().texOffs(23, 0).addBox(-0.4693F, -0.5F, -6.4327F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.5F, -4.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition RMand3 = Head1.addOrReplaceChild("RMand3", CubeListBuilder.create().texOffs(40, 0).addBox(-4.0F, -0.5F, -2.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition RMand1 = Head1.addOrReplaceChild("RMand1", CubeListBuilder.create().texOffs(56, 0).addBox(-4.8F, 0.5F, -5.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition RMand2 = Head1.addOrReplaceChild("RMand2", CubeListBuilder.create().texOffs(49, 0).addBox(-4.3F, 0.5F, -3.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition LMand1 = Head1.addOrReplaceChild("LMand1", CubeListBuilder.create().texOffs(56, 0).addBox(3.8F, 0.5F, -5.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition LMand2 = Head1.addOrReplaceChild("LMand2", CubeListBuilder.create().texOffs(49, 0).addBox(3.3F, 0.5F, -3.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition LMand3 = Head1.addOrReplaceChild("LMand3", CubeListBuilder.create().texOffs(40, 0).addBox(2.0F, -0.5F, -2.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition Neck = partdefinition.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 20).addBox(-1.5F, -0.5F, -2.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 21.0F, -3.0F));

		PartDefinition BodA1 = partdefinition.addOrReplaceChild("BodA1", CubeListBuilder.create().texOffs(0, 7).addBox(-3.0F, -0.5F, -3.0F, 6.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition BodA2 = BodA1.addOrReplaceChild("BodA2", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, -1.5F, -1.5F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightLeg1 = partdefinition.addOrReplaceChild("RightLeg1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition RLA2 = RightLeg1.addOrReplaceChild("RLA2", CubeListBuilder.create().texOffs(11, 0).addBox(0.6F, -1.2F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition RLA3 = RLA2.addOrReplaceChild("RLA3", CubeListBuilder.create().texOffs(11, 4).addBox(-0.0708F, -0.6F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(3.5F, -0.2F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition RightLeg2 = partdefinition.addOrReplaceChild("RightLeg2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition RMLA2 = RightLeg2.addOrReplaceChild("RMLA2", CubeListBuilder.create().texOffs(11, 0).addBox(0.6F, -1.2F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition RMLA3 = RMLA2.addOrReplaceChild("RMLA3", CubeListBuilder.create().texOffs(11, 4).addBox(-0.0708F, -0.6F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(3.5F, -0.2F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition BodB1 = partdefinition.addOrReplaceChild("BodB1", CubeListBuilder.create().texOffs(0, 7).addBox(-3.0F, -0.5F, -3.0F, 6.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition BodB2 = BodB1.addOrReplaceChild("BodB2", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, -1.5F, -1.5F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftLeg1 = partdefinition.addOrReplaceChild("LeftLeg1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 21.5F, 0.0F));

		PartDefinition LLA1 = LeftLeg1.addOrReplaceChild("LLA1", CubeListBuilder.create().texOffs(11, 0).addBox(0.6F, -1.2F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition LLA2 = LLA1.addOrReplaceChild("LLA2", CubeListBuilder.create().texOffs(11, 4).addBox(-0.0708F, -0.6F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(3.5F, -0.2F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition LeftLeg2 = partdefinition.addOrReplaceChild("LeftLeg2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 21.5F, 0.0F));

		PartDefinition LMLA2 = LeftLeg2.addOrReplaceChild("LMLA2", CubeListBuilder.create().texOffs(11, 0).addBox(0.6F, -1.2F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition LMLA3 = LMLA2.addOrReplaceChild("LMLA3", CubeListBuilder.create().texOffs(11, 4).addBox(-0.0708F, -0.6F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(3.5F, -0.2F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition Tail = partdefinition.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(0, 20).addBox(-2.0F, -0.5F, -3.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition BodF2 = Tail.addOrReplaceChild("BodF2", CubeListBuilder.create().texOffs(0, 28).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RLF1 = Tail.addOrReplaceChild("RLF1", CubeListBuilder.create().texOffs(40, 4).addBox(0.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.5F, 1.0F, 0.0F, -1.9199F, 0.0F));

		PartDefinition RLF2 = Tail.addOrReplaceChild("RLF2", CubeListBuilder.create().texOffs(51, 5).addBox(3.0F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.5F, 1.0F, 0.0F, -1.9199F, 0.0F));

		PartDefinition RLF3 = Tail.addOrReplaceChild("RLF3", CubeListBuilder.create().texOffs(11, 4).addBox(6.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.5F, 1.0F, 0.0F, -1.9199F, 0.0F));

		PartDefinition LLF1 = Tail.addOrReplaceChild("LLF1", CubeListBuilder.create().texOffs(40, 4).addBox(0.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.5F, 1.0F, 0.0F, -1.2217F, 0.0F));

		PartDefinition LLF2 = Tail.addOrReplaceChild("LLF2", CubeListBuilder.create().texOffs(51, 5).addBox(3.0F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.5F, 1.0F, 0.0F, -1.2217F, 0.0F));

		PartDefinition LLF3 = Tail.addOrReplaceChild("LLF3", CubeListBuilder.create().texOffs(11, 4).addBox(6.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.5F, 1.0F, 0.0F, -1.2217F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(CentipedeRenderState state) {

	}
}
