package erebus.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import erebus.entity.BlackWidow;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlackWidowModel<T extends BlackWidow> extends HierarchicalModel<T> {
	public ModelPart root;
	private final ModelPart Ab2;
	private final ModelPart Ab1;
	private final ModelPart Ab4;
	private final ModelPart Ab3;
	private final ModelPart Ab6;
	private final ModelPart Ab5;
	private final ModelPart Head;
	private final ModelPart Lant1;
	private final ModelPart Lant2;
	private final ModelPart Rant2;
	private final ModelPart Rant1;
	private final ModelPart Thorax2;
	private final ModelPart Thorax1;
	private final ModelPart MandibleR;
	private final ModelPart MandibleL;
	private final ModelPart FrontLegLeft;
	private final ModelPart FrontMidLegLeft;
	private final ModelPart BackMidLegLeft;
	private final ModelPart BackLegLeft;
	private final ModelPart FrontLegRight;
	private final ModelPart FrontMidLegRight;
	private final ModelPart BackMidLegRight;
	private final ModelPart BackLegRight;
	private final ModelPart LegFL1Child_3;
	private final ModelPart LegMFL1Child_3;
	private final ModelPart LegMBL1Child_3;
	private final ModelPart LegBL1Child_3;
	private final ModelPart LegFR1Child_3;
	private final ModelPart LegMFR1Child_3;
	private final ModelPart LegMBR1Child_3;
	private final ModelPart LegBR1Child_3;

	public BlackWidowModel(ModelPart root) {
		this.root = root;
		this.Ab2 = root.getChild("Ab2");
		this.Ab1 = root.getChild("Ab1");
		this.Ab4 = root.getChild("Ab4");
		this.Ab3 = root.getChild("Ab3");
		this.Ab6 = root.getChild("Ab6");
		this.Ab5 = root.getChild("Ab5");
		this.Head = root.getChild("Head");
		this.Lant1 = root.getChild("Lant1");
		this.Lant2 = root.getChild("Lant2");
		this.Rant2 = root.getChild("Rant2");
		this.Rant1 = root.getChild("Rant1");
		this.Thorax2 = root.getChild("Thorax2");
		this.Thorax1 = root.getChild("Thorax1");
		this.MandibleR = root.getChild("MandibleR");
		this.MandibleL = root.getChild("MandibleL");
		
		this.FrontLegLeft = root.getChild("FrontLegLeft");
		this.LegFL1Child_3 = FrontLegLeft.getChild("LegFL1").getChild("LegFL1Child_3");
		
		this.FrontMidLegLeft = root.getChild("FrontMidLegLeft");
		this.LegMFL1Child_3 = FrontMidLegLeft.getChild("LegMFL1").getChild("LegMFL1Child_3");
		
		this.BackMidLegLeft = root.getChild("BackMidLegLeft");
		this.LegMBL1Child_3 = BackMidLegLeft.getChild("LegMBL1").getChild("LegMBL1Child_3");
		
		this.BackLegLeft = root.getChild("BackLegLeft");
		this.LegBL1Child_3 = BackLegLeft.getChild("LegBL1").getChild("LegBL1Child_3");
		
		this.FrontLegRight = root.getChild("FrontLegRight");
		this.LegFR1Child_3 = FrontLegRight.getChild("LegFR1").getChild("LegFR1Child_3");
		
		this.FrontMidLegRight = root.getChild("FrontMidLegRight");
		this.LegMFR1Child_3 = FrontMidLegRight.getChild("LegMFR1").getChild("LegMFR1Child_3");
		
		this.BackMidLegRight = root.getChild("BackMidLegRight");
		this.LegMBR1Child_3 = BackMidLegRight.getChild("LegMBR1").getChild("LegMBR1Child_3");
		
		this.BackLegRight = root.getChild("BackLegRight");
		this.LegBR1Child_3 = BackLegRight.getChild("LegBR1").getChild("LegBR1Child_3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Ab2 = partdefinition.addOrReplaceChild("Ab2", CubeListBuilder.create().texOffs(41, 29).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, 2.0F));

		PartDefinition Ab1 = partdefinition.addOrReplaceChild("Ab1", CubeListBuilder.create().texOffs(82, 11).addBox(-3.0F, -5.0F, 1.0F, 8.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 13.0F, 2.0F));

		PartDefinition Ab4 = partdefinition.addOrReplaceChild("Ab4", CubeListBuilder.create().texOffs(41, 14).addBox(-6.0F, -3.0F, 2.0F, 12.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, 2.0F));

		PartDefinition Ab3 = partdefinition.addOrReplaceChild("Ab3", CubeListBuilder.create().texOffs(82, 23).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, 2.0F));

		PartDefinition Ab6 = partdefinition.addOrReplaceChild("Ab6", CubeListBuilder.create().texOffs(0, 15).addBox(-3.0F, -6.0F, 2.0F, 6.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, 2.0F));

		PartDefinition Ab5 = partdefinition.addOrReplaceChild("Ab5", CubeListBuilder.create().texOffs(0, 25).addBox(-5.0F, -4.999F, 1.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 2.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(12, 8).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -8.0F));

		PartDefinition Lant1 = partdefinition.addOrReplaceChild("Lant1", CubeListBuilder.create().texOffs(29, 15).addBox(-2.5F, 0.5F, -4.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -8.0F, 0.0F, 0.349F, 0.0F));

		PartDefinition Lant2 = partdefinition.addOrReplaceChild("Lant2", CubeListBuilder.create().texOffs(29, 15).addBox(-5.0F, 0.5F, -6.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -8.0F, 0.0F, -0.349F, 0.0F));

		PartDefinition Rant2 = partdefinition.addOrReplaceChild("Rant2", CubeListBuilder.create().texOffs(29, 15).addBox(4.0F, 0.5F, -6.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -8.0F, 0.0F, 0.349F, 0.0F));

		PartDefinition Rant1 = partdefinition.addOrReplaceChild("Rant1", CubeListBuilder.create().texOffs(29, 15).addBox(1.5F, 0.5F, -4.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -8.0F, 0.0F, -0.349F, 0.0F));

		PartDefinition Thorax2 = partdefinition.addOrReplaceChild("Thorax2", CubeListBuilder.create().texOffs(70, 0).addBox(-2.5F, -3.0F, -8.0F, 5.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, 1.0F, 0.1047F, 0.0F, 0.0F));

		PartDefinition Thorax1 = partdefinition.addOrReplaceChild("Thorax1", CubeListBuilder.create().texOffs(32, 0).addBox(-4.5F, -2.0F, -9.0F, 9.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 1.0F));

		PartDefinition MandibleR = partdefinition.addOrReplaceChild("MandibleR", CubeListBuilder.create().texOffs(23, 0).addBox(0.5F, -0.5F, -5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -8.0F, 0.698F, 0.0F, 0.0F));

		PartDefinition MandibleL = partdefinition.addOrReplaceChild("MandibleL", CubeListBuilder.create().texOffs(23, 0).addBox(-1.5F, -0.5F, -5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -8.0F, 0.698F, 0.0F, 0.0F));

		PartDefinition FrontLegLeft = partdefinition.addOrReplaceChild("FrontLegLeft", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 15.0F, -7.0F, 0.0F, -0.7418F, 0.0F));

		PartDefinition LegFL1 = FrontLegLeft.addOrReplaceChild("LegFL1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition LegFL1Child_3 = LegFL1.addOrReplaceChild("LegFL1Child_3", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -1.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, 6.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition LegFL1Child_4 = LegFL1Child_3.addOrReplaceChild("LegFL1Child_4", CubeListBuilder.create().texOffs(0, 5).addBox(-6.9F, -0.3F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition LegFL1Child = LegFL1.addOrReplaceChild("LegFL1Child", CubeListBuilder.create().texOffs(0, 8).addBox(-5.95F, -5.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition LegFL1Child_2 = LegFL1.addOrReplaceChild("LegFL1Child_2", CubeListBuilder.create().texOffs(0, 8).addBox(-11.0F, -7.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition LegFL1Child_1 = LegFL1.addOrReplaceChild("LegFL1Child_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, -6.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition FrontMidLegLeft = partdefinition.addOrReplaceChild("FrontMidLegLeft", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 15.0F, -5.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition LegMFL1 = FrontMidLegLeft.addOrReplaceChild("LegMFL1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition LegMFL1Child_3 = LegMFL1.addOrReplaceChild("LegMFL1Child_3", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -1.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, 6.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition LegMFL1Child_4 = LegMFL1Child_3.addOrReplaceChild("LegMFL1Child_4", CubeListBuilder.create().texOffs(0, 5).addBox(-6.9F, -0.3F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition LegMFL1Child_1 = LegMFL1.addOrReplaceChild("LegMFL1Child_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, -6.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition LegMFL1Child_2 = LegMFL1.addOrReplaceChild("LegMFL1Child_2", CubeListBuilder.create().texOffs(0, 8).addBox(-11.0F, -7.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition LegMFL1Child = LegMFL1.addOrReplaceChild("LegMFL1Child", CubeListBuilder.create().texOffs(0, 8).addBox(-5.95F, -5.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition BackMidLegLeft = partdefinition.addOrReplaceChild("BackMidLegLeft", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 15.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition LegMBL1 = BackMidLegLeft.addOrReplaceChild("LegMBL1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition LegMBL1Child_2 = LegMBL1.addOrReplaceChild("LegMBL1Child_2", CubeListBuilder.create().texOffs(0, 8).addBox(-11.0F, -7.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition LegMBL1Child_3 = LegMBL1.addOrReplaceChild("LegMBL1Child_3", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -1.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, 6.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition LegMBL1Child_4 = LegMBL1Child_3.addOrReplaceChild("LegMBL1Child_4", CubeListBuilder.create().texOffs(0, 5).addBox(-6.9604F, -0.1368F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition LegMBL1Child = LegMBL1.addOrReplaceChild("LegMBL1Child", CubeListBuilder.create().texOffs(0, 8).addBox(-5.95F, -5.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition LegMBL1Child_1 = LegMBL1.addOrReplaceChild("LegMBL1Child_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, -6.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition BackLegLeft = partdefinition.addOrReplaceChild("BackLegLeft", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 15.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition LegBL1 = BackLegLeft.addOrReplaceChild("LegBL1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition LegBL1Child = LegBL1.addOrReplaceChild("LegBL1Child", CubeListBuilder.create().texOffs(0, 8).addBox(-5.95F, -5.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition LegBL1Child_2 = LegBL1.addOrReplaceChild("LegBL1Child_2", CubeListBuilder.create().texOffs(0, 8).addBox(-11.0F, -7.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition LegBL1Child_1 = LegBL1.addOrReplaceChild("LegBL1Child_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, -6.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition LegBL1Child_3 = LegBL1.addOrReplaceChild("LegBL1Child_3", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -1.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, 6.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition LegBL1Child_4 = LegBL1Child_3.addOrReplaceChild("LegBL1Child_4", CubeListBuilder.create().texOffs(0, 5).addBox(-6.9F, -0.3F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition FrontLegRight = partdefinition.addOrReplaceChild("FrontLegRight", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 15.0F, -7.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition LegFR1 = FrontLegRight.addOrReplaceChild("LegFR1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition LegFR1Child_2 = LegFR1.addOrReplaceChild("LegFR1Child_2", CubeListBuilder.create().texOffs(0, 8).addBox(-11.0F, -7.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.0944F));

		PartDefinition LegFR1Child = LegFR1.addOrReplaceChild("LegFR1Child", CubeListBuilder.create().texOffs(0, 8).addBox(-5.95F, -5.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.3562F));

		PartDefinition LegFR1Child_3 = LegFR1.addOrReplaceChild("LegFR1Child_3", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -1.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -6.0F, 0.0F, 0.0F, 3.1416F, -1.5708F));

		PartDefinition LegFR1Child_4 = LegFR1Child_3.addOrReplaceChild("LegFR1Child_4", CubeListBuilder.create().texOffs(0, 5).addBox(-6.0F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition LegFR1Child_1 = LegFR1.addOrReplaceChild("LegFR1Child_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, -6.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.0944F));

		PartDefinition FrontMidLegRight = partdefinition.addOrReplaceChild("FrontMidLegRight", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 15.0F, -5.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition LegMFR1 = FrontMidLegRight.addOrReplaceChild("LegMFR1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition LegMFR1Child_1 = LegMFR1.addOrReplaceChild("LegMFR1Child_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, -6.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.0944F));

		PartDefinition LegMFR1Child = LegMFR1.addOrReplaceChild("LegMFR1Child", CubeListBuilder.create().texOffs(0, 8).addBox(-5.95F, -5.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.3562F));

		PartDefinition LegMFR1Child_3 = LegMFR1.addOrReplaceChild("LegMFR1Child_3", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -1.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -6.0F, 0.0F, 0.0F, 3.1416F, -1.5708F));

		PartDefinition LegMFR1Child_4 = LegMFR1Child_3.addOrReplaceChild("LegMFR1Child_4", CubeListBuilder.create().texOffs(0, 5).addBox(-6.9F, -0.3F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition LegMFR1Child_2 = LegMFR1.addOrReplaceChild("LegMFR1Child_2", CubeListBuilder.create().texOffs(0, 8).addBox(-11.0F, -7.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.0944F));

		PartDefinition BackMidLegRight = partdefinition.addOrReplaceChild("BackMidLegRight", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 15.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition LegMBR1 = BackMidLegRight.addOrReplaceChild("LegMBR1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition LegMBR1Child_3 = LegMBR1.addOrReplaceChild("LegMBR1Child_3", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -1.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -5.0F, 0.0F, 0.0F, 3.1416F, -1.5708F));

		PartDefinition LegMBR1Child_4 = LegMBR1Child_3.addOrReplaceChild("LegMBR1Child_4", CubeListBuilder.create().texOffs(0, 5).addBox(-7.2F, 0.7F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition LegMBR1Child = LegMBR1.addOrReplaceChild("LegMBR1Child", CubeListBuilder.create().texOffs(0, 8).addBox(-5.95F, -5.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.3562F));

		PartDefinition LegMBR1Child_1 = LegMBR1.addOrReplaceChild("LegMBR1Child_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, -6.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.0944F));

		PartDefinition LegMBR1Child_2 = LegMBR1.addOrReplaceChild("LegMBR1Child_2", CubeListBuilder.create().texOffs(0, 8).addBox(-11.0F, -7.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.0944F));

		PartDefinition BackLegRight = partdefinition.addOrReplaceChild("BackLegRight", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 15.0F, 0.0F, 0.0F, -0.7418F, 0.0F));

		PartDefinition LegBR1 = BackLegRight.addOrReplaceChild("LegBR1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition LegBR1Child_1 = LegBR1.addOrReplaceChild("LegBR1Child_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, -6.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.0944F));

		PartDefinition LegBR1Child = LegBR1.addOrReplaceChild("LegBR1Child", CubeListBuilder.create().texOffs(0, 8).addBox(-5.95F, -5.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.3562F));

		PartDefinition LegBR1Child_3 = LegBR1.addOrReplaceChild("LegBR1Child_3", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -1.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -6.0F, 0.0F, 0.0F, 3.1416F, -1.5708F));

		PartDefinition LegBR1Child_4 = LegBR1Child_3.addOrReplaceChild("LegBR1Child_4", CubeListBuilder.create().texOffs(0, 5).addBox(-6.9F, -0.3F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition LegBR1Child_2 = LegBR1.addOrReplaceChild("LegBR1Child_2", CubeListBuilder.create().texOffs(0, 8).addBox(-11.0F, -7.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.0944F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float sizeModifier = 1F / entity.getWidowSize() * 1F;
		float sin = (float) (Math.sin(limbSwing * (0.3F + sizeModifier)) * 0.4F * limbSwingAmount);
		float cos = (float) (Math.cos(limbSwing * (0.3F + sizeModifier)) * 0.4F * limbSwingAmount);
		
		Head.yRot = netHeadYaw / (180F / (float) Math.PI);
		MandibleR.yRot = netHeadYaw / (180F / (float) Math.PI);
		MandibleL.yRot = netHeadYaw / (180F / (float) Math.PI);
		Rant1.yRot = netHeadYaw / (180F / (float) Math.PI) - 0.349F;
		Rant2.yRot = netHeadYaw / (180F / (float) Math.PI) + 0.349F;
		Lant1.yRot = netHeadYaw / (180F / (float) Math.PI) + 0.349F;
		Lant2.yRot = netHeadYaw / (180F / (float) Math.PI) - 0.349F;

		Head.xRot = headPitch / (180F / (float) Math.PI);
		MandibleR.xRot = headPitch / (180F / (float) Math.PI) + 0.698F;
		MandibleL.xRot = headPitch / (180F / (float) Math.PI) + 0.698F;
		Rant1.xRot = headPitch / (180F / (float) Math.PI);
		Rant2.xRot = headPitch / (180F / (float) Math.PI);
		Lant1.xRot = headPitch / (180F / (float) Math.PI);
		Lant2.xRot = headPitch / (180F / (float) Math.PI);
		
		BackLegLeft.zRot = 0F + cos;
		LegBL1Child_3.zRot = -1.5708F - cos;
		
		BackMidLegLeft.zRot = 0F + sin;
		LegMBL1Child_3.zRot = -1.5708F - sin;
		
		FrontMidLegLeft.zRot = 0F + cos;
		LegMFL1Child_3.zRot = -1.5708F - cos;
		
		FrontLegLeft.zRot = 0F + sin;
		LegFL1Child_3.zRot = -1.5708F - sin;
		
		BackLegRight.zRot = 0F + sin;
		LegBR1Child_3.zRot = -1.5708F - sin;
		
		BackMidLegRight.zRot = 0F + cos;
		LegMBR1Child_3.zRot = -1.5708F - cos;
		
		FrontMidLegRight.zRot = 0F + sin;
		LegMFR1Child_3.zRot = -1.5708F - sin;
		
		FrontLegRight.zRot = 0F + cos;
		LegFR1Child_3.zRot = -1.5708F - cos;

		BackLegLeft.yRot = 0.7854F - sin;
		BackMidLegLeft.yRot = 0.2618F + cos;
		FrontMidLegLeft.yRot = -0.2618F - sin;
		FrontLegLeft.yRot = -0.7418F + cos;

		BackLegRight.yRot = -0.7418F + cos;
		BackMidLegRight.yRot = -0.2618F - sin;
		FrontMidLegRight.yRot = 0.2618F + cos;
		FrontLegRight.yRot = 0.7854F - sin;
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		Ab2.render(stack, consumer, light, overlay, colour);
		Ab1.render(stack, consumer, light, overlay, colour);
		Ab4.render(stack, consumer, light, overlay, colour);
		Ab3.render(stack, consumer, light, overlay, colour);
		Ab6.render(stack, consumer, light, overlay, colour);
		Ab5.render(stack, consumer, light, overlay, colour);
		Head.render(stack, consumer, light, overlay, colour);
		Lant1.render(stack, consumer, light, overlay, colour);
		Lant2.render(stack, consumer, light, overlay, colour);
		Rant2.render(stack, consumer, light, overlay, colour);
		Rant1.render(stack, consumer, light, overlay, colour);
		Thorax2.render(stack, consumer, light, overlay, colour);
		Thorax1.render(stack, consumer, light, overlay, colour);
		MandibleR.render(stack, consumer, light, overlay, colour);
		MandibleL.render(stack, consumer, light, overlay, colour);
		FrontLegLeft.render(stack, consumer, light, overlay, colour);
		FrontMidLegLeft.render(stack, consumer, light, overlay, colour);
		BackMidLegLeft.render(stack, consumer, light, overlay, colour);
		BackLegLeft.render(stack, consumer, light, overlay, colour);
		FrontLegRight.render(stack, consumer, light, overlay, colour);
		FrontMidLegRight.render(stack, consumer, light, overlay, colour);
		BackMidLegRight.render(stack, consumer, light, overlay, colour);
		BackLegRight.render(stack, consumer, light, overlay, colour);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}