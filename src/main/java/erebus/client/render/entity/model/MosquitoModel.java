package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.MosquitoRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class MosquitoModel extends EntityModel<MosquitoRenderState> {
    public ModelPart root;
    private final ModelPart Body;
    private final ModelPart Tail0, Tail1;
    private final ModelPart Head1_0, Head1_1, Head2_0, Head2_1, Head3_0, Head3_1, Head4_0, Head4_1;
    private final ModelPart LegLeft1_0, LegLeft1_1, LegLeft2_0, LegLeft2_1, LegLeft3_0, LegLeft3_1;
    private final ModelPart LegRight1_0, LegRight1_1, LegRight2_0, LegRight2_1, LegRight3_0, LegRight3_1;
    private final ModelPart ArmLeft1, ArmLeft2, ArmRight1, ArmRight2;
    private final ModelPart WingLeft, WingRight;

    public MosquitoModel(ModelPart root) {
        super(root);
        this.root = root;
        this.Body = root.getChild("Body");
        this.Tail0 = root.getChild("Tail0");
        this.Tail1 = root.getChild("Tail1");
        this.Head1_0 = root.getChild("Head1_0");
        this.Head1_1 = root.getChild("Head1_1");
        this.Head2_0 = root.getChild("Head2_0");
        this.Head2_1 = root.getChild("Head2_1");
        this.Head3_0 = root.getChild("Head3_0");
        this.Head3_1 = root.getChild("Head3_1");
        this.Head4_0 = root.getChild("Head4_0");
        this.Head4_1 = root.getChild("Head4_1");
        this.LegLeft1_0 = root.getChild("LegLeft1_0");
        this.LegLeft1_1 = root.getChild("LegLeft1_1");
        this.LegLeft2_0 = root.getChild("LegLeft2_0");
        this.LegLeft2_1 = root.getChild("LegLeft2_1");
        this.LegLeft3_0 = root.getChild("LegLeft3_0");
        this.LegLeft3_1 = root.getChild("LegLeft3_1");
        this.LegRight1_0 = root.getChild("LegRight1_0");
        this.LegRight1_1 = root.getChild("LegRight1_1");
        this.LegRight2_0 = root.getChild("LegRight2_0");
        this.LegRight2_1 = root.getChild("LegRight2_1");
        this.LegRight3_0 = root.getChild("LegRight3_0");
        this.LegRight3_1 = root.getChild("LegRight3_1");
        this.ArmLeft1 = root.getChild("ArmLeft1");
        this.ArmLeft2 = root.getChild("ArmLeft2");
        this.ArmRight1 = root.getChild("ArmRight1");
        this.ArmRight2 = root.getChild("ArmRight2");
        this.WingLeft = root.getChild("WingLeft");
        this.WingRight = root.getChild("WingRight");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -8.0F, 0.0F, 16, 16, 16), PartPose.offset(0.0F, 0.0F, 0.0F));

        root.addOrReplaceChild("Tail0", CubeListBuilder.create().texOffs(0, 41).addBox(-3.0F, -3.0F, 16.0F, 6, 6, 40), PartPose.offset(0.0F, 0.0F, 0.0F));
        root.addOrReplaceChild("Tail1", CubeListBuilder.create().texOffs(0, 41).addBox(-3.0F, -3.0F, 16.0F, 6, 6, 40), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3141593F, 0.0F, 0.0F));

        root.addOrReplaceChild("Head1_0", CubeListBuilder.create().texOffs(0, 61).addBox(-5.0F, 0.0F, 0.0F, 10, 10, 10), PartPose.offsetAndRotation(0.0F, 0.0F, 5.0F, -2.356194F, 0.0F, 0.0F));
        root.addOrReplaceChild("Head1_1", CubeListBuilder.create().texOffs(0, 61).addBox(-5.0F, 0.0F, 0.0F, 10, 10, 10), PartPose.offsetAndRotation(0.0F, 0.0F, 5.0F, -2.129302F, 0.0F, 0.0F));

        root.addOrReplaceChild("Head2_0", CubeListBuilder.create().texOffs(0, 45).addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8), PartPose.offset(0.0F, 4.0F, -4.0F));
        root.addOrReplaceChild("Head2_1", CubeListBuilder.create().texOffs(0, 45).addBox(-4.0F, -4.0F, -2.0F, 8, 8, 8), PartPose.offsetAndRotation(0.0F, 4.0F, -4.0F, -0.2268928F, 0.0F, 0.0F));

        root.addOrReplaceChild("Head3_0", CubeListBuilder.create().texOffs(0, 33).addBox(-2.0F, 3.0F, -3.0F, 4, 8, 4), PartPose.offsetAndRotation(0.0F, 4.0F, -4.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("Head3_1", CubeListBuilder.create().texOffs(0, 33).addBox(-2.0F, 3.0F, -3.0F, 4, 8, 4), PartPose.offsetAndRotation(0.0F, 4.0F, -4.0F, 0.1745329F, 0.0F, 0.0F));

        root.addOrReplaceChild("Head4_0", CubeListBuilder.create().texOffs(16, 35).addBox(-1.0F, 10.0F, 0.0F, 2, 8, 2), PartPose.offsetAndRotation(0.0F, 4.0F, -4.0F, -0.3490659F, 0.0F, 0.0F));
        root.addOrReplaceChild("Head4_1", CubeListBuilder.create().texOffs(16, 35).addBox(-1.0F, 10.0F, 0.0F, 2, 8, 2), PartPose.offsetAndRotation(0.0F, 4.0F, -4.0F, 0.0174533F, 0.0F, 0.0F));

        root.addOrReplaceChild("LegLeft1_0", CubeListBuilder.create().texOffs(52, 53).addBox(-2.0F, -2.0F, -24.0F, 4, 4, 24), PartPose.offsetAndRotation(6.0F, 6.0F, 16.0F, 0.7853982F, 3.141593F, 0.0F));
        root.addOrReplaceChild("LegLeft1_1", CubeListBuilder.create().texOffs(52, 53).addBox(-2.0F, -2.0F, -24.0F, 4, 4, 24), PartPose.offsetAndRotation(6.0F, 6.0F, 16.0F, 1.22173F, 3.141593F, -0.2617994F));

        root.addOrReplaceChild("LegLeft2_0", CubeListBuilder.create().texOffs(80, 6).addBox(-1.0F, 2.0F, -23.0F, 2, 16, 2), PartPose.offsetAndRotation(6.0F, 6.0F, 16.0F, 0.7853982F, 3.141593F, 0.0F));
        root.addOrReplaceChild("LegLeft2_1", CubeListBuilder.create().texOffs(80, 6).addBox(-1.0F, -13.0F, -20.0F, 2, 16, 2), PartPose.offsetAndRotation(6.0F, 6.0F, 16.0F, 1.884956F, 3.141593F, -0.2617994F));

        root.addOrReplaceChild("LegLeft3_0", CubeListBuilder.create().texOffs(64, 26).addBox(-0.5F, 3.5F, -51.0F, 1, 1, 24), PartPose.offsetAndRotation(6.0F, 6.0F, 16.0F, 1.308997F, 3.141593F, 0.0F));
        root.addOrReplaceChild("LegLeft3_1", CubeListBuilder.create().texOffs(64, 26).addBox(-9.5F, -4.5F, -41.0F, 1, 1, 24), PartPose.offsetAndRotation(6.0F, 6.0F, 16.0F, 2.303835F, 3.167191F, 0.2617994F));

        root.addOrReplaceChild("LegRight1_0", CubeListBuilder.create().texOffs(52, 53).addBox(-2.0F, -2.0F, -24.0F, 4, 4, 24), PartPose.offsetAndRotation(-6.0F, 6.0F, 16.0F, 0.7853982F, 3.141593F, 0.0F));
        root.addOrReplaceChild("LegRight1_1", CubeListBuilder.create().texOffs(52, 53).addBox(-2.0F, -2.0F, -24.0F, 4, 4, 24), PartPose.offsetAndRotation(-6.0F, 6.0F, 16.0F, 1.22173F, 3.141593F, 0.2617994F));

        root.addOrReplaceChild("LegRight2_0", CubeListBuilder.create().texOffs(80, 6).addBox(-1.0F, 2.0F, -23.0F, 2, 16, 2), PartPose.offsetAndRotation(-6.0F, 6.0F, 16.0F, 0.7853982F, 3.141593F, 0.0F));
        root.addOrReplaceChild("LegRight2_1", CubeListBuilder.create().texOffs(80, 6).addBox(-1.0F, -13.0F, -20.0F, 2, 16, 2), PartPose.offsetAndRotation(-6.0F, 6.0F, 16.0F, 1.884956F, 3.141593F, 0.2617994F));

        root.addOrReplaceChild("LegRight3_0", CubeListBuilder.create().texOffs(64, 26).addBox(-0.5F, 3.5F, -51.0F, 1, 1, 24), PartPose.offsetAndRotation(-6.0F, 6.0F, 16.0F, 1.308997F, 3.141593F, 0.0F));
        root.addOrReplaceChild("LegRight3_1", CubeListBuilder.create().texOffs(64, 26).addBox(8.5F, -4.5F, -41.0F, 1, 1, 24), PartPose.offsetAndRotation(-6.0F, 6.0F, 16.0F, 2.303835F, 3.141593F, -0.2617994F));

        root.addOrReplaceChild("ArmLeft1", CubeListBuilder.create().texOffs(64, 0).addBox(-1.0F, -1.0F, -24.0F, 2, 2, 24), PartPose.offsetAndRotation(6.0F, 8.0F, 4.0F, 1.570796F, 0.0F, 0.0F));
        root.addOrReplaceChild("ArmLeft2", CubeListBuilder.create().texOffs(64, 0).addBox(-1.0F, -1.0F, -24.0F, 2, 2, 24), PartPose.offsetAndRotation(6.0F, 8.0F, 8.0F, 1.570796F, 0.0F, 0.0F));

        root.addOrReplaceChild("ArmRight1", CubeListBuilder.create().texOffs(64, 0).addBox(-1.0F, -1.0F, -24.0F, 2, 2, 24), PartPose.offsetAndRotation(-6.0F, 8.0F, 4.0F, 1.570796F, 0.0F, 0.0F));
        root.addOrReplaceChild("ArmRight2", CubeListBuilder.create().texOffs(64, 0).addBox(-1.0F, -1.0F, -24.0F, 2, 2, 24), PartPose.offsetAndRotation(-6.0F, 8.0F, 8.0F, 1.570796F, 0.0F, 0.0F));

        root.addOrReplaceChild("WingLeft", CubeListBuilder.create().texOffs(0, 87).addBox(-16.0F, 0.0F, 0.0F, 16, 1, 32), PartPose.offset(-4.0F, -9.0F, 4.0F));
        root.addOrReplaceChild("WingRight", CubeListBuilder.create().texOffs(0, 87).addBox(0.0F, 0.0F, 0.0F, 16, 1, 32), PartPose.offset(4.0F, -9.0F, 4.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }
}
