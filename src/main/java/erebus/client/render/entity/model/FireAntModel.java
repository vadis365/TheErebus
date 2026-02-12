package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.FireAntRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class FireAntModel extends EntityModel<FireAntRenderState> {
    public ModelPart root;
    private final ModelPart Thx, ThxTop, ThxS, ThxToAb;
    private final ModelPart Ab, AbF, AbSide, AbTop, AbBack;
    private final ModelPart Neck;
    private final ModelPart HeadA, HeadB, RMandibleA, RMandibleB, LMandibleA, LMandibleB, Eyes, AntLS, AntLE, AntRS, AntRE;
    private final ModelPart LBLA, LBLB, LBLC, LBLD;
    private final ModelPart LMLA, LMLB, LMLC, LMLD;
    private final ModelPart LFLA, LFLB, LFLC, LFLD;
    private final ModelPart RFLA, RFLB, RFLC, RFLD;
    private final ModelPart RMLA, RMLB, RMLC, RMLD;
    private final ModelPart RBLA, RBLB, RBLC, RBLD;

    public FireAntModel(ModelPart root) {
        super(root);
        this.root = root;
        this.Thx = root.getChild("Thx");
        this.ThxTop = root.getChild("ThxTop");
        this.ThxS = root.getChild("ThxS");
        this.ThxToAb = root.getChild("ThxToAb");
        this.Ab = root.getChild("Ab");
        this.AbF = root.getChild("AbF");
        this.AbSide = root.getChild("AbSide");
        this.AbTop = root.getChild("AbTop");
        this.AbBack = root.getChild("AbBack");
        this.Neck = root.getChild("Neck");
        this.HeadA = root.getChild("HeadA");
        this.HeadB = HeadA.getChild("HeadB");
        this.RMandibleA = HeadA.getChild("RMandibleA");
        this.RMandibleB = HeadA.getChild("RMandibleB");
        this.LMandibleA = HeadA.getChild("LMandibleA");
        this.LMandibleB = HeadA.getChild("LMandibleB");
        this.Eyes = HeadA.getChild("Eyes");
        this.AntLS = HeadA.getChild("AntLS");
        this.AntLE = HeadA.getChild("AntLE");
        this.AntRS = HeadA.getChild("AntRS");
        this.AntRE = HeadA.getChild("AntRE");
        this.LBLA = root.getChild("LBLA");
        this.LBLB = LBLA.getChild("LBLB");
        this.LBLC = LBLA.getChild("LBLC");
        this.LBLD = LBLA.getChild("LBLD");
        this.LMLA = root.getChild("LMLA");
        this.LMLB = LMLA.getChild("LMLB");
        this.LMLC = LMLA.getChild("LMLC");
        this.LMLD = LMLA.getChild("LMLD");
        this.LFLA = root.getChild("LFLA");
        this.LFLB = LFLA.getChild("LFLB");
        this.LFLC = LFLA.getChild("LFLC");
        this.LFLD = LFLA.getChild("LFLD");
        this.RFLA = root.getChild("RFLA");
        this.RFLB = RFLA.getChild("RFLB");
        this.RFLC = RFLA.getChild("RFLC");
        this.RFLD = RFLA.getChild("RFLD");
        this.RMLA = root.getChild("RMLA");
        this.RMLB = RMLA.getChild("RMLB");
        this.RMLC = RMLA.getChild("RMLC");
        this.RMLD = RMLA.getChild("RMLD");
        this.RBLA = root.getChild("RBLA");
        this.RBLB = RBLA.getChild("RBLB");
        this.RBLC = RBLA.getChild("RBLC");
        this.RBLD = RBLA.getChild("RBLD");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("Thx", CubeListBuilder.create().texOffs(14, 13).addBox(-3.5F, -3.5F, 0F, 7, 7, 9), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("ThxTop", CubeListBuilder.create().texOffs(21, 30).addBox(-2.5F, -4.5F, 1F, 5, 1, 7), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("ThxS", CubeListBuilder.create().texOffs(15, 39).addBox(-4.5F, -2.5F, 1F, 9, 5, 7), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("ThxToAb", CubeListBuilder.create().texOffs(27, 52).addBox(-1.5F, -1.5F, 0F, 3, 3, 1), PartPose.offset(0F, 17F, 9F));
        root.addOrReplaceChild("Ab", CubeListBuilder.create().texOffs(9, 100).addBox(-5.5F, -4.5F, 0F, 11, 9, 12), PartPose.offset(0F, 17F, 11F));
        root.addOrReplaceChild("AbF", CubeListBuilder.create().texOffs(23, 57).addBox(-3.5F, -3.5F, -1F, 7, 7, 1), PartPose.offset(0F, 17F, 11F));
        root.addOrReplaceChild("AbSide", CubeListBuilder.create().texOffs(10, 66).addBox(-6.5F, -2.5F, 2F, 13, 5, 8), PartPose.offset(0F, 17F, 11F));
        root.addOrReplaceChild("AbTop", CubeListBuilder.create().texOffs(15, 80).addBox(-4F, -5.5F, 2F, 8, 11, 8), PartPose.offset(0F, 17F, 11F));
        root.addOrReplaceChild("AbBack", CubeListBuilder.create().texOffs(22, 122).addBox(-3.5F, -2.5F, 12F, 7, 5, 1), PartPose.offset(0F, 17F, 11F));
        root.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 12).addBox(-1.5F, -1.5F, 0F, 3, 3, 3), PartPose.offset(0F, 17F, -3F));

        PartDefinition HeadA = root.addOrReplaceChild("HeadA", CubeListBuilder.create().texOffs(21, 0).addBox(-2F, -0.5F, -4F, 4, 5, 6), PartPose.offsetAndRotation(0F, 17F, -1F, -1F, 0F, 0F));
        HeadA.addOrReplaceChild("HeadB", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, -0.5F, -3F, 6, 7, 4), PartPose.ZERO);
        HeadA.addOrReplaceChild("RMandibleA", CubeListBuilder.create().texOffs(52, 0).addBox(-3F, 5.5F, -2F, 1, 3, 1), PartPose.ZERO);
        HeadA.addOrReplaceChild("RMandibleB", CubeListBuilder.create().texOffs(52, 9).addBox(-2F, 5.5F, -2F, 1, 4, 1), PartPose.ZERO);
        HeadA.addOrReplaceChild("LMandibleA", CubeListBuilder.create().texOffs(47, 0).addBox(2F, 5.5F, -2F, 1, 3, 1), PartPose.ZERO);
        HeadA.addOrReplaceChild("LMandibleB", CubeListBuilder.create().texOffs(47, 9).addBox(1F, 5.5F, -2F, 1, 4, 1), PartPose.ZERO);
        HeadA.addOrReplaceChild("Eyes", CubeListBuilder.create().texOffs(0, 30).addBox(-4F, 1.5F, -2F, 8, 2, 2), PartPose.ZERO);
        HeadA.addOrReplaceChild("AntLS", CubeListBuilder.create().texOffs(42, 6).addBox(3F, 4.5F, -3F, 3, 1, 1), PartPose.rotation(0F, 0F, 0.1745329F));
        HeadA.addOrReplaceChild("AntLE", CubeListBuilder.create().texOffs(42, 0).addBox(6F, 5.5F, -3F, 1, 4, 1), PartPose.rotation(0F, 0F, 0.1745329F));
        HeadA.addOrReplaceChild("AntRS", CubeListBuilder.create().texOffs(53, 6).addBox(-6F, 4.5F, -3F, 3, 1, 1), PartPose.rotation(0F, 0F, -0.1745329F));
        HeadA.addOrReplaceChild("AntRE", CubeListBuilder.create().texOffs(57, 0).addBox(-7F, 5.5F, -3F, 1, 4, 1), PartPose.rotation(0F, 0F, -0.1745329F));

        PartDefinition LBLA = root.addOrReplaceChild("LBLA", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(4F, 17F, 8F, 0.25F, -0.7F, -0.5F));
        LBLA.addOrReplaceChild("LBLB", CubeListBuilder.create().texOffs(0, 88).addBox(3F, 0F, -1F, 2, 4, 2), PartPose.ZERO);
        LBLA.addOrReplaceChild("LBLC", CubeListBuilder.create().texOffs(0, 82).addBox(1.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0.25F, 0.1F, -0.45F)); // 0.5-0.25, -0.6-(-0.7), -0.95-(-0.5)
        LBLA.addOrReplaceChild("LBLD", CubeListBuilder.create().texOffs(0, 76).addBox(0.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0.39F, 0F, -0.77F)); // 0.64-0.25, -0.7-(-0.7), -1.27-(-0.5)

        PartDefinition LMLA = root.addOrReplaceChild("LMLA", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(4F, 17F, 5F, 0F, 0F, -0.3490659F));
        LMLA.addOrReplaceChild("LMLB", CubeListBuilder.create().texOffs(0, 88).addBox(3F, 0F, -1F, 2, 4, 2), PartPose.ZERO);
        LMLA.addOrReplaceChild("LMLC", CubeListBuilder.create().texOffs(0, 82).addBox(1.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, -0.3490658F)); // -0.6981317 - (-0.3490659)
        LMLA.addOrReplaceChild("LMLD", CubeListBuilder.create().texOffs(0, 76).addBox(0.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, -0.5235987F)); // -0.8726646 - (-0.3490659)

        PartDefinition LFLA = root.addOrReplaceChild("LFLA", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(4F, 17F, 2F, -0.25F, 0.7F, -0.5F));
        LFLA.addOrReplaceChild("LFLB", CubeListBuilder.create().texOffs(0, 88).addBox(3F, 0F, -1F, 2, 4, 2), PartPose.ZERO);
        LFLA.addOrReplaceChild("LFLC", CubeListBuilder.create().texOffs(0, 82).addBox(1.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(-0.25F, -0.1F, -0.45F)); // -0.5-(-0.25), 0.6-0.7, -0.95-(-0.5)
        LFLA.addOrReplaceChild("LFLD", CubeListBuilder.create().texOffs(0, 76).addBox(0.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(-0.39F, 0F, -0.77F)); // -0.64-(-0.25), 0.7-0.7, -1.27-(-0.5)

        PartDefinition RFLA = root.addOrReplaceChild("RFLA", CubeListBuilder.create().texOffs(0, 95).addBox(-4F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(-4F, 17F, 2F, -0.25F, -0.7F, 0.5F));
        RFLA.addOrReplaceChild("RFLB", CubeListBuilder.create().texOffs(0, 88).addBox(-5F, 0F, -1F, 2, 4, 2), PartPose.ZERO);
        RFLA.addOrReplaceChild("RFLC", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(-0.25F, 0.1F, 0.45F)); // -0.5-(-0.25), -0.6-(-0.7), 0.95-0.5
        RFLA.addOrReplaceChild("RFLD", CubeListBuilder.create().texOffs(0, 76).addBox(-1.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(-0.39F, 0F, 0.77F)); // -0.64-(-0.25), -0.7-(-0.7), 1.27-0.5

        PartDefinition RMLA = root.addOrReplaceChild("RMLA", CubeListBuilder.create().texOffs(0, 95).addBox(-4F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(-4F, 17F, 5F, 0F, 0F, 0.3490659F));
        RMLA.addOrReplaceChild("RMLB", CubeListBuilder.create().texOffs(0, 88).addBox(-5F, 0F, -1F, 2, 4, 2), PartPose.ZERO);
        RMLA.addOrReplaceChild("RMLC", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, 0.3490658F)); // 0.6981317-0.3490659
        RMLA.addOrReplaceChild("RMLD", CubeListBuilder.create().texOffs(0, 76).addBox(-1.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, 0.5235987F)); // 0.8726646-0.3490659

        PartDefinition RBLA = root.addOrReplaceChild("RBLA", CubeListBuilder.create().texOffs(0, 95).addBox(-4F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(-4F, 17F, 8F, 0.25F, 0.7F, 0.5F));
        RBLA.addOrReplaceChild("RBLB", CubeListBuilder.create().texOffs(0, 88).addBox(-5F, 0F, -1F, 2, 4, 2), PartPose.ZERO);
        RBLA.addOrReplaceChild("RBLC", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0.25F, -0.1F, 0.45F)); // 0.5-0.25, 0.6-0.7, 0.95-0.5
        RBLA.addOrReplaceChild("RBLD", CubeListBuilder.create().texOffs(0, 76).addBox(-1.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0.39F, 0F, 0.77F)); // 0.64-0.25, 0.7-0.7, 1.27-0.5

        return LayerDefinition.create(mesh, 64, 128);
    }
}
