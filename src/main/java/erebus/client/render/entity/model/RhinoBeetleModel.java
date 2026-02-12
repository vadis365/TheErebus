package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.RhinoBeetleRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class RhinoBeetleModel extends EntityModel<RhinoBeetleRenderState> {
    public ModelPart root;
    private final ModelPart Ab, AbSide, AbTop, AbBack;
    private final ModelPart LBL1, LBL2, LBL3, LBL4;
    private final ModelPart LFL1, LFL2, LFL3, LFL4;
    private final ModelPart LML1, LML2, LML3, LML4;
    private final ModelPart RFL1, RFL2, RFL3, RFL4;
    private final ModelPart RML1, RML2, RML3, RML4;
    private final ModelPart RBL1, RBL2, RBL3, RBL4;
    private final ModelPart HeadA, HeadB, HeadC, Eyes, Lplate, Rplate, TplateA, TplateB, TplateC, NoseA, NoseB, NoseC, Neck;

    public RhinoBeetleModel(ModelPart root) {
        super(root);
        this.root = root;
        this.Ab = root.getChild("Ab");
        this.AbSide = root.getChild("AbSide");
        this.AbTop = root.getChild("AbTop");
        this.AbBack = root.getChild("AbBack");

        this.LBL1 = root.getChild("LBL1");
        this.LBL2 = LBL1.getChild("LBL2");
        this.LBL3 = LBL1.getChild("LBL3");
        this.LBL4 = LBL1.getChild("LBL4");

        this.LFL1 = root.getChild("LFL1");
        this.LFL2 = LFL1.getChild("LFL2");
        this.LFL3 = LFL1.getChild("LFL3");
        this.LFL4 = LFL1.getChild("LFL4");

        this.LML1 = root.getChild("LML1");
        this.LML2 = LML1.getChild("LML2");
        this.LML3 = LML1.getChild("LML3");
        this.LML4 = LML1.getChild("LML4");

        this.RFL1 = root.getChild("RFL1");
        this.RFL2 = RFL1.getChild("RFL2");
        this.RFL3 = RFL1.getChild("RFL3");
        this.RFL4 = RFL1.getChild("RFL4");

        this.RML1 = root.getChild("RML1");
        this.RML2 = RML1.getChild("RML2");
        this.RML3 = RML1.getChild("RML3");
        this.RML4 = RML1.getChild("RML4");

        this.RBL1 = root.getChild("RBL1");
        this.RBL2 = RBL1.getChild("RBL2");
        this.RBL3 = RBL1.getChild("RBL3");
        this.RBL4 = RBL1.getChild("RBL4");

        this.HeadA = root.getChild("HeadA");
        this.HeadB = root.getChild("HeadB");
        this.HeadC = root.getChild("HeadC");
        this.Eyes = root.getChild("Eyes");
        this.Lplate = root.getChild("Lplate");
        this.Rplate = root.getChild("Rplate");
        this.TplateA = root.getChild("TplateA");
        this.TplateB = root.getChild("TplateB");
        this.TplateC = root.getChild("TplateC");
        this.NoseA = root.getChild("NoseA");
        this.NoseB = root.getChild("NoseB");
        this.NoseC = root.getChild("NoseC");
        this.Neck = root.getChild("Neck");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("Ab", CubeListBuilder.create().texOffs(9, 95).addBox(-5.5F, -4.5F, 0F, 11, 9, 15), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("AbSide", CubeListBuilder.create().texOffs(10, 64).addBox(-6.5F, -3.5F, 2F, 13, 5, 11), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("AbTop", CubeListBuilder.create().texOffs(15, 81).addBox(-4F, -6.5F, 2F, 8, 2, 11), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("AbBack", CubeListBuilder.create().texOffs(27, 120).addBox(-3.5F, -2.5F, 15F, 7, 5, 2), PartPose.offset(0F, 17F, 0F));

        PartDefinition LBL1 = root.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(0, 95).addBox(-4F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(5F, 17F, 15F, 0F, 2.617994F, -0.3490659F));
        LBL1.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 88).addBox(-5F, 0F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, 0.3490659F + 0.3490659F));
        LBL1.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, 0.6981317F + 0.3490659F));
        LBL1.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(0, 76).addBox(-1.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, 0.8726646F + 0.3490659F));

        PartDefinition LFL1 = root.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 95).addBox(-4F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(5F, 17F, 2F, 0F, -2.617994F, -0.3490659F));
        LFL1.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 88).addBox(-5F, 0F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, 0.3490659F + 0.3490659F));
        LFL1.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, 0.6981317F + 0.3490659F));
        LFL1.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(0, 76).addBox(-1.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, 0.8726646F + 0.3490659F));

        PartDefinition LML1 = root.addOrReplaceChild("LML1", CubeListBuilder.create().texOffs(0, 95).addBox(-4F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(5F, 17F, 8.5F, 0F, -3.141593F, -0.3490659F));
        LML1.addOrReplaceChild("LML2", CubeListBuilder.create().texOffs(0, 88).addBox(-5F, 0F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, 0.3490659F + 0.3490659F));
        LML1.addOrReplaceChild("LML3", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, 0.6981317F + 0.3490659F));
        LML1.addOrReplaceChild("LML4", CubeListBuilder.create().texOffs(0, 76).addBox(-1.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, 0.8726646F + 0.3490659F));

        PartDefinition RFL1 = root.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 95).addBox(-4F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(-7F, 17F, 2F, 0F, -0.5235988F, 0.3490659F));
        RFL1.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 88).addBox(-5F, 0F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, 0.0f));
        RFL1.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, 0.6981317F - 0.3490659F));
        RFL1.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(0, 76).addBox(-1.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, 0.8726646F - 0.3490659F));

        PartDefinition RML1 = root.addOrReplaceChild("RML1", CubeListBuilder.create().texOffs(0, 95).addBox(-4F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(-7F, 17F, 8.5F, 0F, 0F, 0.3490659F));
        RML1.addOrReplaceChild("RML2", CubeListBuilder.create().texOffs(0, 88).addBox(-5F, 0F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, 0.0f));
        RML1.addOrReplaceChild("RML3", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, 0.6981317F - 0.3490659F));
        RML1.addOrReplaceChild("RML4", CubeListBuilder.create().texOffs(0, 76).addBox(-1.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, 0.8726646F - 0.3490659F));

        PartDefinition RBL1 = root.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(0, 95).addBox(-4F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(-7F, 17F, 15F, 0F, 0F, 0.3490659F));
        RBL1.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 88).addBox(-5F, 0F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, 0.0f));
        RBL1.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, 0.6981317F - 0.3490659F));
        RBL1.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(0, 76).addBox(-1.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, 0.8726646F - 0.3490659F));

        root.addOrReplaceChild("HeadA", CubeListBuilder.create().texOffs(22, 40).addBox(-2F, -3F, -3F, 4, 6, 7), PartPose.offset(0F, 17F, -4F));
        root.addOrReplaceChild("HeadB", CubeListBuilder.create().texOffs(28, 33).addBox(-2F, -1F, -4F, 4, 4, 2), PartPose.offset(0F, 17F, -4F));
        root.addOrReplaceChild("HeadC", CubeListBuilder.create().texOffs(29, 27).addBox(-1.5F, 0F, -6F, 3, 3, 2), PartPose.offset(0F, 17F, -4F));
        root.addOrReplaceChild("Eyes", CubeListBuilder.create().texOffs(0, 36).addBox(-2F, 0.5F, -5.5F, 4, 1, 1), PartPose.offset(0F, 17F, -4F));
        root.addOrReplaceChild("Lplate", CubeListBuilder.create().texOffs(0, 40).addBox(1F, -3F, -1F, 2, 6, 6), PartPose.offsetAndRotation(0F, 17F, -4F, 0F, 0.5235988F, 0F));
        root.addOrReplaceChild("Rplate", CubeListBuilder.create().texOffs(47, 40).addBox(-3F, -3F, -1F, 2, 6, 6), PartPose.offsetAndRotation(0F, 17F, -4F, 0F, -0.5235988F, 0F));
        root.addOrReplaceChild("TplateA", CubeListBuilder.create().texOffs(23, 54).addBox(-1.5F, -3.9F, -0.5F, 3, 2, 7), PartPose.offsetAndRotation(0F, 17F, -4F, 0.6981317F, 0F, 0F));
        root.addOrReplaceChild("TplateB", CubeListBuilder.create().texOffs(0, 60).addBox(-2.5F, -3F, 0.5F, 5, 2, 5), PartPose.offsetAndRotation(0F, 17F, -4F, 0.6981317F, 0F, 0F));
        root.addOrReplaceChild("TplateC", CubeListBuilder.create().texOffs(0, 53).addBox(-3.5F, -1.9F, 2F, 7, 2, 3), PartPose.offsetAndRotation(0F, 17F, -4F, 0.6981317F, 0F, 0F));
        root.addOrReplaceChild("NoseA", CubeListBuilder.create().texOffs(35, 65).addBox(-1F, -5.3F, -6.5F, 2, 6, 2), PartPose.offsetAndRotation(0F, 17F, -4F, 0.3490659F, 0F, 0F));
        root.addOrReplaceChild("NoseB", CubeListBuilder.create().texOffs(36, 69).addBox(-0.5F, -3.5F, -8.2F, 1, 2, 2), PartPose.offsetAndRotation(0F, 17F, -4F, -0.1396263F, 0F, 0F));
        root.addOrReplaceChild("NoseC", CubeListBuilder.create().texOffs(32, 22).addBox(-0.5F, -6.5F, -8.2F, 1, 3, 1), PartPose.offsetAndRotation(0F, 17F, -4F, -0.1396263F, 0F, 0F));
        root.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(21, 66).addBox(-2F, -4F, 2F, 4, 6, 2), PartPose.offset(0F, 17F, -4F));

        return LayerDefinition.create(mesh, 64, 128);
    }
}
