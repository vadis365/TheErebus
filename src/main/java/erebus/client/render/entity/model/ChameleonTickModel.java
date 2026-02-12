package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.ChameleonTickRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ChameleonTickModel extends EntityModel<ChameleonTickRenderState> {
    public ModelPart root;
    private final ModelPart LBL1, LBL2, LBL3, LBL4;
    private final ModelPart LML1, LML2, LML3, LML4;
    private final ModelPart LFL1, LFL2, LFL3, LFL4;
    private final ModelPart RBL1, RBL2, RBL3, RBL4;
    private final ModelPart RML1, RML2, RML3, RML4;
    private final ModelPart RFL1, RFL2, RFL3, RFL4;
    private final ModelPart Back1, Back2;
    private final ModelPart HeadBack, HeadFront, Rmand, Lmand;

    public ChameleonTickModel(ModelPart root) {
        super(root);
        this.root = root;
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
        this.RBL1 = root.getChild("RBL1");
        this.RBL2 = RBL1.getChild("RBL2");
        this.RBL3 = RBL1.getChild("RBL3");
        this.RBL4 = RBL1.getChild("RBL4");
        this.RML1 = root.getChild("RML1");
        this.RML2 = RML1.getChild("RML2");
        this.RML3 = RML1.getChild("RML3");
        this.RML4 = RML1.getChild("RML4");
        this.RFL1 = root.getChild("RFL1");
        this.RFL2 = RFL1.getChild("RFL2");
        this.RFL3 = RFL1.getChild("RFL3");
        this.RFL4 = RFL1.getChild("RFL4");
        this.Back1 = root.getChild("Back1");
        this.Back2 = root.getChild("Back2");
        this.HeadBack = root.getChild("HeadBack");
        this.HeadFront = HeadBack.getChild("HeadFront");
        this.Rmand = HeadBack.getChild("Rmand");
        this.Lmand = HeadBack.getChild("Lmand");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition LBL1 = root.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(0, 16).addBox(1.0F, -1.0F, -1.5F, 5, 3, 3), PartPose.offsetAndRotation(6.0F, 18.0F, 6.0F, 0.0F, -0.3490659F, -0.3490659F));
        LBL1.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 16).addBox(5.0F, 0.0F, -1.0F, 2, 4, 2), PartPose.ZERO);
        LBL1.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(0, 16).addBox(3.5F, 5.5F, -0.5F, 2, 4, 1), PartPose.rotation(0.0F, 0.0F, -0.3490658F)); // -0.6981317 - (-0.3490659) = -0.3490658
        LBL1.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(0, 16).addBox(2.5F, 9.0F, -0.5F, 1, 4, 1), PartPose.rotation(0.0F, 0.0F, -0.5235987F)); // -0.8726646 - (-0.3490659) = -0.5235987

        PartDefinition LML1 = root.addOrReplaceChild("LML1", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -1.0F, -1.5F, 5, 3, 3), PartPose.offsetAndRotation(8.0F, 17.0F, 0.0F, 0.0F, 0.0F, -0.3490659F));
        LML1.addOrReplaceChild("LML2", CubeListBuilder.create().texOffs(0, 16).addBox(3.0F, 0.0F, -1.0F, 2, 4, 2), PartPose.ZERO);
        LML1.addOrReplaceChild("LML3", CubeListBuilder.create().texOffs(0, 16).addBox(1.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0.0F, 0.0F, -0.3490658F));
        LML1.addOrReplaceChild("LML4", CubeListBuilder.create().texOffs(0, 16).addBox(0.5F, 8.0F, -0.5F, 1, 4, 1), PartPose.rotation(0.0F, 0.0F, -0.5235987F));

        PartDefinition LFL1 = root.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -1.0F, -1.5F, 5, 3, 3), PartPose.offsetAndRotation(8.0F, 17.0F, -6.0F, 0.0F, 0.3490659F, -0.3490659F));
        LFL1.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 16).addBox(3.0F, 0.0F, -1.0F, 2, 4, 2), PartPose.ZERO);
        LFL1.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(0, 16).addBox(1.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0.0F, 0.0F, -0.3490658F));
        LFL1.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(0, 16).addBox(0.5F, 8.0F, -0.5F, 1, 4, 1), PartPose.rotation(0.0F, 0.0F, -0.5235987F));

        PartDefinition RBL1 = root.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(0, 16).addBox(-6.0F, -1.0F, -1.5F, 5, 3, 3), PartPose.offsetAndRotation(-6.0F, 18.0F, 6.0F, 0.0F, 0.3490659F, 0.3490659F));
        RBL1.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 16).addBox(-7.0F, 0.0F, -1.0F, 2, 4, 2), PartPose.ZERO);
        RBL1.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(0, 16).addBox(-5.5F, 5.5F, -0.5F, 2, 4, 1), PartPose.rotation(0.0F, 0.0F, 0.3490658F)); // 0.6981317 - 0.3490659 = 0.3490658
        RBL1.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(0, 16).addBox(-3.5F, 9.0F, -0.5F, 1, 4, 1), PartPose.rotation(0.0F, 0.0F, 0.5235987F)); // 0.8726646 - 0.3490659 = 0.5235987

        PartDefinition RML1 = root.addOrReplaceChild("RML1", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -1.0F, -1.5F, 5, 3, 3), PartPose.offsetAndRotation(-8.0F, 17.0F, 0.0F, 0.0F, 0.0F, 0.3490659F));
        RML1.addOrReplaceChild("RML2", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, 0.0F, -1.0F, 2, 4, 2), PartPose.ZERO);
        RML1.addOrReplaceChild("RML3", CubeListBuilder.create().texOffs(0, 16).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0.0F, 0.0F, 0.3490658F));
        RML1.addOrReplaceChild("RML4", CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, 8.0F, -0.5F, 1, 4, 1), PartPose.rotation(0.0F, 0.0F, 0.5235987F));

        PartDefinition RFL1 = root.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -1.0F, -1.5F, 5, 3, 3), PartPose.offsetAndRotation(-8.0F, 17.0F, -6.0F, 0.0F, -0.3490659F, 0.3490659F));
        RFL1.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, 0.0F, -1.0F, 2, 4, 2), PartPose.ZERO);
        RFL1.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(0, 16).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0.0F, 0.0F, 0.3490658F));
        RFL1.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, 8.0F, -0.5F, 1, 4, 1), PartPose.rotation(0.0F, 0.0F, 0.5235987F));

        root.addOrReplaceChild("Back1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -5.0F, 8.0F, 12, 11, 5).mirror(), PartPose.offset(0.0F, 15.0F, 0.0F));
        root.addOrReplaceChild("Back2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -3.0F, 13.0F, 8, 7, 3).mirror(), PartPose.offset(0.0F, 15.0F, 0.0F));

        PartDefinition HeadBack = root.addOrReplaceChild("HeadBack", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -5.0F, -4.0F, 11, 10, 4).mirror(), PartPose.offset(0.0F, 16.0F, -8.0F));
        HeadBack.addOrReplaceChild("HeadFront", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -2.0F, -10.0F, 8, 5, 6).mirror(), PartPose.ZERO);
        HeadBack.addOrReplaceChild("Rmand", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -15.0F, 2, 2, 6).mirror(), PartPose.ZERO);
        HeadBack.addOrReplaceChild("Lmand", CubeListBuilder.create().texOffs(-1, 0).addBox(1.0F, 0.0F, -15.0F, 2, 2, 6).mirror(), PartPose.ZERO);

        return LayerDefinition.create(mesh, 16, 16);
    }
}
