package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.GlowWormRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class GlowWormModel extends EntityModel<GlowWormRenderState> {
    public ModelPart root;
    private final ModelPart Head1, Head2, LMandible, RMandible, AntL, AntR, Eyes;
    private final ModelPart ThxL1, ThxR1, ThxL2, ThxR2, ThxL3, ThxR3, ThxL4, ThxR4, ThxL5, ThxR5;
    private final ModelPart ThxL6, ThxR6, ThxL7, ThxR7, ThxL8, ThxR8, ThxL9, ThxR9, ThxL10, ThxR10, Thx11;
    private final ModelPart LBL1, LBL2, LBL3, LBL4;
    private final ModelPart LML1, LML2, LML3, LML4;
    private final ModelPart LFL1, LFL2, LFL3, LFL4;
    private final ModelPart RFL1, RFL2, RFL3, RFL4;
    private final ModelPart RML1, RML2, RML3, RML4;
    private final ModelPart RBL1, RBL2, RBL3, RBL4;

    public GlowWormModel(ModelPart root) {
        super(root);
        this.root = root;
        this.Head1 = root.getChild("Head1");
        this.Head2 = Head1.getChild("Head2");
        this.LMandible = Head1.getChild("LMandible");
        this.RMandible = Head1.getChild("RMandible");
        this.AntL = Head1.getChild("AntL");
        this.AntR = Head1.getChild("AntR");
        this.Eyes = Head1.getChild("Eyes");

        this.ThxL1 = root.getChild("ThxL1");
        this.ThxR1 = root.getChild("ThxR1");
        this.ThxL2 = root.getChild("ThxL2");
        this.ThxR2 = root.getChild("ThxR2");
        this.ThxL3 = root.getChild("ThxL3");
        this.ThxR3 = root.getChild("ThxR3");
        this.ThxL4 = root.getChild("ThxL4");
        this.ThxR4 = root.getChild("ThxR4");
        this.ThxL5 = root.getChild("ThxL5");
        this.ThxR5 = root.getChild("ThxR5");
        this.ThxL6 = root.getChild("ThxL6");
        this.ThxR6 = root.getChild("ThxR6");
        this.ThxL7 = root.getChild("ThxL7");
        this.ThxR7 = root.getChild("ThxR7");
        this.ThxL8 = root.getChild("ThxL8");
        this.ThxR8 = root.getChild("ThxR8");
        this.ThxL9 = root.getChild("ThxL9");
        this.ThxR9 = root.getChild("ThxR9");
        this.ThxL10 = root.getChild("ThxL10");
        this.ThxR10 = root.getChild("ThxR10");
        this.Thx11 = root.getChild("Thx11");

        this.LBL1 = root.getChild("LBL1");
        this.LBL2 = LBL1.getChild("LBL2");
        this.LBL3 = LBL1.getChild("LBL3");
        this.LBL4 = LBL1.getChild("LBL4");
        this.LML1 = root.getChild("LML1");
        this.LML2 = LML1.getChild("LML2");
        this.LML3 = LML1.getChild("LML3");
        this.LML4 = LML1.getChild("LML4");
        this.LFL1 = root.getChild("LFL1");
        this.LFL2 = LFL1.getChild("LFL2");
        this.LFL3 = LFL1.getChild("LFL3");
        this.LFL4 = LFL1.getChild("LFL4");
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
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition Head1 = root.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(21, 0).addBox(-2.5F, -1.5F, -4.5F, 5, 4, 5), PartPose.offsetAndRotation(0F, 16F, -11F, 0.1745329F, 0F, 0F));
        Head1.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(41, 0).addBox(-2F, -1F, -5.5F, 4, 3, 1), PartPose.ZERO);
        Head1.addOrReplaceChild("LMandible", CubeListBuilder.create().texOffs(52, 0).addBox(0.5F, 1F, -8.5F, 1, 1, 3), PartPose.ZERO);
        Head1.addOrReplaceChild("RMandible", CubeListBuilder.create().texOffs(52, 0).addBox(-1.5F, 1F, -8.5F, 1, 1, 3), PartPose.ZERO);
        Head1.addOrReplaceChild("AntL", CubeListBuilder.create().texOffs(43, 80).addBox(4F, 0.5F, -2F, 9, 1, 1), PartPose.rotation(-0.1745329F, 0.8726646F, 0F)); // Adjusted for Head1 rotation
        Head1.addOrReplaceChild("AntR", CubeListBuilder.create().texOffs(43, 80).addBox(-13F, 0.5F, -2F, 9, 1, 1), PartPose.rotation(-0.1745329F, -0.8726646F, 0F)); // Adjusted for Head1 rotation
        Head1.addOrReplaceChild("Eyes", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -1F, -4F, 7, 2, 2), PartPose.ZERO);

        root.addOrReplaceChild("ThxL1", CubeListBuilder.create().texOffs(0, 5).addBox(-0.6F, -3.5F, -3F, 5, 1, 2), PartPose.offsetAndRotation(0F, 16F, -10F, 0.1745329F, -0.0261799F, 0.1745329F));
        root.addOrReplaceChild("ThxR1", CubeListBuilder.create().texOffs(45, 5).addBox(-4.4F, -3.5F, -3F, 5, 1, 2), PartPose.offsetAndRotation(0F, 16F, -10F, 0.1745329F, 0.0261799F, -0.1745329F));
        root.addOrReplaceChild("ThxL2", CubeListBuilder.create().texOffs(7, 10).addBox(-0.7F, -4F, -1F, 7, 6, 5), PartPose.offsetAndRotation(0F, 16F, -10F, 0.1745329F, -0.0261799F, 0.1745329F));
        root.addOrReplaceChild("ThxR2", CubeListBuilder.create().texOffs(32, 10).addBox(-6.3F, -4F, -1F, 7, 6, 5), PartPose.offsetAndRotation(0F, 16F, -10F, 0.1745329F, 0.0261799F, -0.1745329F));
        root.addOrReplaceChild("ThxL3", CubeListBuilder.create().texOffs(13, 22).addBox(-0.7F, -3.5F, 4F, 5, 7, 4), PartPose.offsetAndRotation(0F, 16F, -10F, 0.1745329F, -0.0261799F, 0.1745329F));
        root.addOrReplaceChild("ThxR3", CubeListBuilder.create().texOffs(32, 22).addBox(-4.3F, -3.5F, 4F, 5, 7, 4), PartPose.offsetAndRotation(0F, 16F, -10F, 0.1745329F, 0.0261799F, -0.1745329F));
        root.addOrReplaceChild("ThxL4", CubeListBuilder.create().texOffs(7, 34).addBox(-0.6F, -3F, 8F, 7, 8, 5), PartPose.offsetAndRotation(0F, 16F, -10F, 0.1745329F, -0.0261799F, 0.1745329F));
        root.addOrReplaceChild("ThxR4", CubeListBuilder.create().texOffs(32, 34).addBox(-6.4F, -3F, 8F, 7, 8, 5), PartPose.offsetAndRotation(0F, 16F, -10F, 0.1745329F, 0.0261799F, -0.1745329F));
        root.addOrReplaceChild("ThxL5", CubeListBuilder.create().texOffs(5, 48).addBox(-0.5F, -2.5F, 13F, 8, 9, 5), PartPose.offsetAndRotation(0F, 16F, -10F, 0.1745329F, -0.0261799F, 0.1745329F));
        root.addOrReplaceChild("ThxR5", CubeListBuilder.create().texOffs(32, 48).addBox(-7.5F, -2.5F, 13F, 8, 9, 5), PartPose.offsetAndRotation(0F, 16F, -10F, 0.1745329F, 0.0261799F, -0.1745329F));
        root.addOrReplaceChild("ThxL6", CubeListBuilder.create().texOffs(1, 63).addBox(-0.5F, -2F, 18F, 9, 10, 6), PartPose.offsetAndRotation(0F, 16F, -10F, 0.1745329F, -0.0261799F, 0.1745329F));
        root.addOrReplaceChild("ThxR6", CubeListBuilder.create().texOffs(32, 63).addBox(-8.5F, -2F, 18F, 9, 10, 6), PartPose.offsetAndRotation(0F, 16F, -10F, 0.1745329F, 0.0261799F, -0.1745329F));
        root.addOrReplaceChild("ThxL7", CubeListBuilder.create().texOffs(1, 63).addBox(-1F, -5.5F, 23.5F, 9, 10, 6), PartPose.offsetAndRotation(0F, 16F, -10F, 0F, 0F, 0.1745329F));
        root.addOrReplaceChild("ThxR7", CubeListBuilder.create().texOffs(32, 63).addBox(-8F, -5.5F, 23.5F, 9, 10, 6), PartPose.offsetAndRotation(0F, 16F, -10F, 0F, 0F, -0.1745329F));
        root.addOrReplaceChild("ThxL8", CubeListBuilder.create().texOffs(5, 48).addBox(-1.6F, -10F, 28F, 8, 9, 5), PartPose.offsetAndRotation(0F, 16F, -10F, -0.1745329F, 0.0261799F, 0.1745329F));
        root.addOrReplaceChild("ThxR8", CubeListBuilder.create().texOffs(32, 48).addBox(-6.4F, -10F, 28F, 8, 9, 5), PartPose.offsetAndRotation(0F, 16F, -10F, -0.1745329F, -0.0261799F, -0.1745329F));
        root.addOrReplaceChild("ThxL9", CubeListBuilder.create().texOffs(7, 100).addBox(-1.5F, -9.5F, 33F, 7, 8, 5), PartPose.offsetAndRotation(0F, 16F, -10F, -0.1745329F, 0.0261799F, 0.1745329F));
        root.addOrReplaceChild("ThxR9", CubeListBuilder.create().texOffs(32, 100).addBox(-5.5F, -9.5F, 33F, 7, 8, 5), PartPose.offsetAndRotation(0F, 16F, -10F, -0.1745329F, -0.0261799F, -0.1745329F));
        root.addOrReplaceChild("ThxL10", CubeListBuilder.create().texOffs(13, 83).addBox(-1.5F, -9F, 38F, 5, 7, 4), PartPose.offsetAndRotation(0F, 16F, -10F, -0.1745329F, 0.0261799F, 0.1745329F));
        root.addOrReplaceChild("ThxR10", CubeListBuilder.create().texOffs(32, 83).addBox(-3.5F, -9F, 38F, 5, 7, 4), PartPose.offsetAndRotation(0F, 16F, -10F, -0.1745329F, -0.0261799F, -0.1745329F));
        root.addOrReplaceChild("Thx11", CubeListBuilder.create().texOffs(19, 114).addBox(-3.5F, -7F, 42F, 7, 3, 3), PartPose.offsetAndRotation(0F, 16F, -10F, -0.1745329F, 0F, 0F));

        PartDefinition LBL1 = root.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(42, 95).addBox(-1F, -1F, -1F, 7, 2, 2), PartPose.offsetAndRotation(6F, 18F, 6F, 0F, 0F, -0.3490659F));
        LBL1.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 88).addBox(5F, 0F, -1F, 2, 4, 2), PartPose.ZERO);
        LBL1.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(0, 82).addBox(3.5F, 5.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, -0.3490658F));
        LBL1.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(7, 82).addBox(2.5F, 9F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, -0.5235987F));

        PartDefinition LML1 = root.addOrReplaceChild("LML1", CubeListBuilder.create().texOffs(0, 95).addBox(-1F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(5F, 17F, -1F, 0F, 0F, -0.3490659F));
        LML1.addOrReplaceChild("LML2", CubeListBuilder.create().texOffs(0, 88).addBox(3F, 0F, -1F, 2, 4, 2), PartPose.ZERO);
        LML1.addOrReplaceChild("LML3", CubeListBuilder.create().texOffs(0, 82).addBox(1.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, -0.3490658F));
        LML1.addOrReplaceChild("LML4", CubeListBuilder.create().texOffs(7, 82).addBox(0.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, -0.5235987F));

        PartDefinition LFL1 = root.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(4F, 17F, -8F, 0F, 0F, -0.3490659F));
        LFL1.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 88).addBox(3F, 0F, -1F, 2, 4, 2), PartPose.ZERO);
        LFL1.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(0, 82).addBox(1.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, -0.3490658F));
        LFL1.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(7, 82).addBox(0.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, -0.5235987F));

        PartDefinition RFL1 = root.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 95).addBox(-4F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(-4F, 17F, -8F, 0F, 0F, 0.3490659F));
        RFL1.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 88).addBox(-5F, 0F, -1F, 2, 4, 2), PartPose.ZERO);
        RFL1.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, 0.3490658F));
        RFL1.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(7, 82).addBox(-1.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, 0.5235987F));

        PartDefinition RML1 = root.addOrReplaceChild("RML1", CubeListBuilder.create().texOffs(0, 95).addBox(-4F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(-5F, 17F, -1F, 0F, 0F, 0.3490659F));
        RML1.addOrReplaceChild("RML2", CubeListBuilder.create().texOffs(0, 88).addBox(-5F, 0F, -1F, 2, 4, 2), PartPose.ZERO);
        RML1.addOrReplaceChild("RML3", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, 0.3490658F));
        RML1.addOrReplaceChild("RML4", CubeListBuilder.create().texOffs(7, 82).addBox(-1.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, 0.5235987F));

        PartDefinition RBL1 = root.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(42, 95).addBox(-6F, -1F, -1F, 7, 2, 2), PartPose.offsetAndRotation(-6F, 18F, 6F, 0F, 0F, 0.3490659F));
        RBL1.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 88).addBox(-7F, 0F, -1F, 2, 4, 2), PartPose.ZERO);
        RBL1.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(0, 82).addBox(-5.5F, 5.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, 0.3490658F));
        RBL1.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(7, 82).addBox(-3.5F, 9F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, 0.5235987F));

        return LayerDefinition.create(mesh, 64, 128);
    }
}
