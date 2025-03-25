package erebus.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import erebus.entity.BlackWidow;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
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
		this.FrontMidLegLeft = root.getChild("FrontMidLegLeft");
		this.BackMidLegLeft = root.getChild("BackMidLegLeft");
		this.BackLegLeft = root.getChild("BackLegLeft");
		this.FrontLegRight = root.getChild("FrontLegRight");
		this.FrontMidLegRight = root.getChild("FrontMidLegRight");
		this.BackMidLegRight = root.getChild("BackMidLegRight");
		this.BackLegRight = root.getChild("BackLegRight");
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

		PartDefinition LegFL1Child_4 = LegFL1.addOrReplaceChild("LegFL1Child_4", CubeListBuilder.create().texOffs(0, 5).addBox(-16.0F, -15.0F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.9722F));

		PartDefinition LegFL1Child_3 = LegFL1.addOrReplaceChild("LegFL1Child_3", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -11.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition LegFL1Child = LegFL1.addOrReplaceChild("LegFL1Child", CubeListBuilder.create().texOffs(0, 8).addBox(-5.95F, -5.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition LegFL1Child_2 = LegFL1.addOrReplaceChild("LegFL1Child_2", CubeListBuilder.create().texOffs(0, 8).addBox(-11.0F, -7.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition LegFL1Child_1 = LegFL1.addOrReplaceChild("LegFL1Child_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, -6.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition FrontMidLegLeft = partdefinition.addOrReplaceChild("FrontMidLegLeft", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 15.0F, -5.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition LegMFL1 = FrontMidLegLeft.addOrReplaceChild("LegMFL1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition LegMFL1Child_3 = LegMFL1.addOrReplaceChild("LegMFL1Child_3", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -11.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition LegMFL1Child_1 = LegMFL1.addOrReplaceChild("LegMFL1Child_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, -6.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition LegMFL1Child_2 = LegMFL1.addOrReplaceChild("LegMFL1Child_2", CubeListBuilder.create().texOffs(0, 8).addBox(-11.0F, -7.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition LegMFL1Child_4 = LegMFL1.addOrReplaceChild("LegMFL1Child_4", CubeListBuilder.create().texOffs(0, 5).addBox(-16.0F, -15.0F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.9722F));

		PartDefinition LegMFL1Child = LegMFL1.addOrReplaceChild("LegMFL1Child", CubeListBuilder.create().texOffs(0, 8).addBox(-5.95F, -5.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition BackMidLegLeft = partdefinition.addOrReplaceChild("BackMidLegLeft", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 15.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition LegMBL1 = BackMidLegLeft.addOrReplaceChild("LegMBL1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition LegMBL1Child_2 = LegMBL1.addOrReplaceChild("LegMBL1Child_2", CubeListBuilder.create().texOffs(0, 8).addBox(-11.0F, -7.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition LegMBL1Child_3 = LegMBL1.addOrReplaceChild("LegMBL1Child_3", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -11.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition LegMBL1Child = LegMBL1.addOrReplaceChild("LegMBL1Child", CubeListBuilder.create().texOffs(0, 8).addBox(-5.95F, -5.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition LegMBL1Child_4 = LegMBL1.addOrReplaceChild("LegMBL1Child_4", CubeListBuilder.create().texOffs(0, 5).addBox(-16.0F, -15.0F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.9722F));

		PartDefinition LegMBL1Child_1 = LegMBL1.addOrReplaceChild("LegMBL1Child_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, -6.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition BackLegLeft = partdefinition.addOrReplaceChild("BackLegLeft", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 15.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition LegBL1 = BackLegLeft.addOrReplaceChild("LegBL1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition LegBL1Child_4 = LegBL1.addOrReplaceChild("LegBL1Child_4", CubeListBuilder.create().texOffs(0, 5).addBox(-16.0F, -15.0F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.9722F));

		PartDefinition LegBL1Child = LegBL1.addOrReplaceChild("LegBL1Child", CubeListBuilder.create().texOffs(0, 8).addBox(-5.95F, -5.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition LegBL1Child_2 = LegBL1.addOrReplaceChild("LegBL1Child_2", CubeListBuilder.create().texOffs(0, 8).addBox(-11.0F, -7.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition LegBL1Child_1 = LegBL1.addOrReplaceChild("LegBL1Child_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, -6.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition LegBL1Child_3 = LegBL1.addOrReplaceChild("LegBL1Child_3", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -11.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition FrontLegRight = partdefinition.addOrReplaceChild("FrontLegRight", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 15.0F, -7.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition LegFR1 = FrontLegRight.addOrReplaceChild("LegFR1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition LegFR1Child_4 = LegFR1.addOrReplaceChild("LegFR1Child_4", CubeListBuilder.create().texOffs(0, 5).addBox(-16.0F, -15.0F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -1.1694F));

		PartDefinition LegFR1Child_2 = LegFR1.addOrReplaceChild("LegFR1Child_2", CubeListBuilder.create().texOffs(0, 8).addBox(-11.0F, -7.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.0944F));

		PartDefinition LegFR1Child = LegFR1.addOrReplaceChild("LegFR1Child", CubeListBuilder.create().texOffs(0, 8).addBox(-5.95F, -5.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.3562F));

		PartDefinition LegFR1Child_3 = LegFR1.addOrReplaceChild("LegFR1Child_3", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -11.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -1.5708F));

		PartDefinition LegFR1Child_1 = LegFR1.addOrReplaceChild("LegFR1Child_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, -6.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.0944F));

		PartDefinition FrontMidLegRight = partdefinition.addOrReplaceChild("FrontMidLegRight", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 15.0F, -5.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition LegMFR1 = FrontMidLegRight.addOrReplaceChild("LegMFR1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition LegMFR1Child_1 = LegMFR1.addOrReplaceChild("LegMFR1Child_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, -6.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.0944F));

		PartDefinition LegMFR1Child = LegMFR1.addOrReplaceChild("LegMFR1Child", CubeListBuilder.create().texOffs(0, 8).addBox(-5.95F, -5.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.3562F));

		PartDefinition LegMFR1Child_3 = LegMFR1.addOrReplaceChild("LegMFR1Child_3", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -11.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -1.5708F));

		PartDefinition LegMFR1Child_4 = LegMFR1.addOrReplaceChild("LegMFR1Child_4", CubeListBuilder.create().texOffs(0, 5).addBox(-16.0F, -15.0F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -1.1694F));

		PartDefinition LegMFR1Child_2 = LegMFR1.addOrReplaceChild("LegMFR1Child_2", CubeListBuilder.create().texOffs(0, 8).addBox(-11.0F, -7.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.0944F));

		PartDefinition BackMidLegRight = partdefinition.addOrReplaceChild("BackMidLegRight", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 15.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition LegMBR1 = BackMidLegRight.addOrReplaceChild("LegMBR1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition LegMBR1Child_4 = LegMBR1.addOrReplaceChild("LegMBR1Child_4", CubeListBuilder.create().texOffs(0, 5).addBox(-16.0F, -15.0F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -1.1694F));

		PartDefinition LegMBR1Child_3 = LegMBR1.addOrReplaceChild("LegMBR1Child_3", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -11.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -1.5708F));

		PartDefinition LegMBR1Child = LegMBR1.addOrReplaceChild("LegMBR1Child", CubeListBuilder.create().texOffs(0, 8).addBox(-5.95F, -5.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.3562F));

		PartDefinition LegMBR1Child_1 = LegMBR1.addOrReplaceChild("LegMBR1Child_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, -6.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.0944F));

		PartDefinition LegMBR1Child_2 = LegMBR1.addOrReplaceChild("LegMBR1Child_2", CubeListBuilder.create().texOffs(0, 8).addBox(-11.0F, -7.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.0944F));

		PartDefinition BackLegRight = partdefinition.addOrReplaceChild("BackLegRight", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 15.0F, 0.0F, 0.0F, -0.7418F, 0.0F));

		PartDefinition LegBR1 = BackLegRight.addOrReplaceChild("LegBR1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition LegBR1Child_1 = LegBR1.addOrReplaceChild("LegBR1Child_1", CubeListBuilder.create().texOffs(0, 0).addBox(-9.5F, -6.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.0944F));

		PartDefinition LegBR1Child = LegBR1.addOrReplaceChild("LegBR1Child", CubeListBuilder.create().texOffs(0, 8).addBox(-5.95F, -5.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.3562F));

		PartDefinition LegBR1Child_4 = LegBR1.addOrReplaceChild("LegBR1Child_4", CubeListBuilder.create().texOffs(0, 5).addBox(-16.0F, -15.0F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -1.1694F));

		PartDefinition LegBR1Child_3 = LegBR1.addOrReplaceChild("LegBR1Child_3", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -11.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -1.5708F));

		PartDefinition LegBR1Child_2 = LegBR1.addOrReplaceChild("LegBR1Child_2", CubeListBuilder.create().texOffs(0, 8).addBox(-11.0F, -7.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -2.0944F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float sin = (float) (Math.sin(limbSwing * 0.7F) * 0.4F * limbSwingAmount);
		float cos = (float) (Math.cos(limbSwing * 0.7F) * 0.4F * limbSwingAmount);
		
	//	float sin = (float) (Math.sin(ageInTicks) * 0.4F);
	//	float cos = (float) (Math.cos(ageInTicks) * 0.4F);
		
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
		BackMidLegLeft.zRot = 0F + sin;
		FrontMidLegLeft.zRot = 0F + cos;
		FrontLegLeft.zRot = 0F + sin;
		BackLegRight.zRot = 0F + sin;
		BackMidLegRight.zRot = 0F + cos;
		FrontMidLegRight.zRot = 0F + sin;
		FrontLegRight.zRot = 0F + cos;

		BackLegLeft.yRot = 0.7854F - sin;
		BackMidLegLeft.yRot = 0.2618F + cos;
		FrontMidLegLeft.yRot = -0.2618F - sin;
		FrontLegLeft.yRot = -0.7418F + cos;

		BackLegRight.yRot = -0.7418F + cos;
		BackMidLegRight.yRot = -0.2618F - sin;
		FrontMidLegRight.yRot = 0.2618F + cos;
		FrontLegRight.yRot = 0.7854F - sin;
/*
		float x = 0.7853982F;
		float b = 1.2F;
		float x2 = -2.3561946F;

		float movcos1 = MathHelper.cos(limbSwing * 0.7F) * 0.4F * prevLimbSwing;
		float movsin1 = MathHelper.sin(limbSwing * 0.7F) * 0.4F * prevLimbSwing;
		float movcos2 = MathHelper.cos(limbSwing * 0.7F) * 0.5F * prevLimbSwing;
		float movcos3 = MathHelper.cos(limbSwing * 0.0F) * 0.0F * prevLimbSwing;

		LegFL1.yRot = -0.698F + movcos1;
		LegFL1.xRot = -0.698F - movsin1;
		LegFL1.rotateAngleZ = b + movcos2;
		LegFL2.rotateAngleZ = -x + movcos3;
		LegFL3.rotateAngleZ = -0.2617994F - x + movcos3;
		LegFL4.rotateAngleZ = -0.2617994F - x + movcos3;
		LegFL5.rotateAngleZ = -0.7853982F - x + movcos3;
		LegFL6.rotateAngleZ = -1.186824F - x + movcos3;

		LegMFL1.yRot = -0.175F - movcos1;
		LegMFL1.xRot = -0.175F + movsin1;
		LegMFL1.rotateAngleZ = x - movcos2;
		LegMFL2.rotateAngleZ = -x - movcos3;
		LegMFL3.rotateAngleZ = -0.2617994F - x - movcos3;
		LegMFL4.rotateAngleZ = -0.2617994F - x - movcos3;
		LegMFL5.rotateAngleZ = -0.7853982F - x - movcos3;
		LegMFL6.rotateAngleZ = -1.186824F - x - movcos3;

		LegMBL1.yRot = 0.175F + movcos1;
		LegMBL1.xRot = 0.175F - movsin1;
		LegMBL1.rotateAngleZ = x + movcos2;
		LegMBL2.rotateAngleZ = -x + movcos3;
		LegMBL3.rotateAngleZ = -0.2617994F - x + movcos3;
		LegMBL4.rotateAngleZ = -0.2617994F - x + movcos3;
		LegMBL5.rotateAngleZ = -0.7853982F - x + movcos3;
		LegMBL6.rotateAngleZ = -1.186824F - x + movcos3;

		LegBL1.yRot = 0.698F - movcos1;
		LegBL1.xRot = 0.698F + movsin1;
		LegBL1.rotateAngleZ = b - movcos2;
		LegBL2.rotateAngleZ = -x - movcos3;
		LegBL3.rotateAngleZ = -0.2617994F - x - movcos3;
		LegBL4.rotateAngleZ = -0.2617994F - x - movcos3;
		LegBL5.rotateAngleZ = -0.7853982F - x - movcos3;
		LegBL6.rotateAngleZ = -1.186824F - x - movcos3;

		LegFR1.yRot = 3.84F + movcos1;
		LegFR1.xRot = 3.84F - movsin1;
		LegFR1.rotateAngleZ = -b + movcos2;
		LegFR2.rotateAngleZ = x2 + movcos3;
		LegFR3.rotateAngleZ = 0.2617994F + x2 + movcos3;
		LegFR4.rotateAngleZ = 0.2617994F + x2 + movcos3;
		LegFR5.rotateAngleZ = 0.7853982F + x2 + movcos3;
		LegFR6.rotateAngleZ = 1.186824F + x2 + movcos3;

		LegMFR1.yRot = 3.317F - movcos1;
		LegMFR1.xRot = 3.317F + movsin1;
		LegMFR1.rotateAngleZ = -x - movcos2;
		LegMFR2.rotateAngleZ = x2 - movcos3;
		LegMFR3.rotateAngleZ = 0.2617994F + x2 - movcos3;
		LegMFR4.rotateAngleZ = 0.2617994F + x2 - movcos3;
		LegMFR5.rotateAngleZ = 0.7853982F + x2 - movcos3;
		LegMFR6.rotateAngleZ = 1.186824F + x2 - movcos3;

		LegMBR1.yRot = 2.967F + movcos1;
		LegMBR1.xRot = 2.967F - movsin1;
		LegMBR1.rotateAngleZ = -x + movcos2;
		LegMBR2.rotateAngleZ = x2 + movcos3;
		LegMBR3.rotateAngleZ = 0.2617994F + x2 + movcos3;
		LegMBR4.rotateAngleZ = 0.2617994F + x2 + movcos3;
		LegMBR5.rotateAngleZ = 0.7853982F + x2 + movcos3;
		LegMBR6.rotateAngleZ = 1.186824F + x2 + movcos3;

		LegBR1.yRot = 2.443F - movcos1;
		LegBR1.xRot = 2.443F + movsin1;
		LegBR1.rotateAngleZ = -b - movcos2;
		LegBR2.rotateAngleZ = x2 - movcos3;
		LegBR3.rotateAngleZ = 0.2617994F + x2 - movcos3;
		LegBR4.rotateAngleZ = 0.2617994F + x2 - movcos3;
		LegBR5.rotateAngleZ = 0.7853982F + x2 - movcos3;
		LegBR6.rotateAngleZ = 1.186824F + x2 - movcos3;
*/
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