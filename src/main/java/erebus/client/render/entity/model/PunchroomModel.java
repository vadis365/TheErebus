package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.PunchroomRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class PunchroomModel extends EntityModel<PunchroomRenderState> {
	public ModelPart root;
	private final ModelPart mushBase;
	private final ModelPart mushBase2;
	private final ModelPart mushBase3;
	private final ModelPart mushSpores;
	private final ModelPart mushCap1;
	private final ModelPart mushCap2;
	private final ModelPart mushCap3;

	public PunchroomModel(ModelPart root) {
		super(root);
		this.root = root;
		this.mushBase = root.getChild("mushBase");
		this.mushBase2 = root.getChild("mushBase2");
		this.mushBase3 = root.getChild("mushBase3");
		this.mushSpores = root.getChild("mushSpores");
		this.mushCap1 = root.getChild("mushCap1");
		this.mushCap2 = root.getChild("mushCap2");
		this.mushCap3 = root.getChild("mushCap3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition mushBase = partdefinition.addOrReplaceChild("mushBase", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -5.0F, -3.5F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition mushBase2 = partdefinition.addOrReplaceChild("mushBase2", CubeListBuilder.create().texOffs(0, 12).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.5F, 0.0F));
		PartDefinition mushBase3 = partdefinition.addOrReplaceChild("mushBase3", CubeListBuilder.create().texOffs(0, 23).addBox(-2.5F, -7.0F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));
		PartDefinition mushSpores = partdefinition.addOrReplaceChild("mushSpores", CubeListBuilder.create().texOffs(64, 0).addBox(-5.5F, -1.0F, -5.5F, 11.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));
		PartDefinition mushCap1 = partdefinition.addOrReplaceChild("mushCap1", CubeListBuilder.create().texOffs(64, 12).addBox(-6.0F, -2.0F, -6.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.5F, 0.0F));
		PartDefinition mushCap2 = partdefinition.addOrReplaceChild("mushCap2", CubeListBuilder.create().texOffs(64, 26).addBox(-5.5F, -2.0F, -5.5F, 11.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.5F, 0.0F));
		PartDefinition mushCap3 = partdefinition.addOrReplaceChild("mushCap3", CubeListBuilder.create().texOffs(64, 39).addBox(-4.0F, -2.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 10.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(PunchroomRenderState state) {

	}
}
