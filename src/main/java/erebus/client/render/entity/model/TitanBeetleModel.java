package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.TitanBeetleRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class TitanBeetleModel extends EntityModel<TitanBeetleRenderState> {
    public ModelPart root;
    private final ModelPart Thx, ThxS;
    private final ModelPart Ab, AbSide, AbBack;
    private final ModelPart HeadMain, Eyes, HeadTop, HeadFront, Neck;
    private final ModelPart LMandible1, LMandible2, RMandible1, RMandible2;
    private final ModelPart AntLE, AntRE, AntLB, AntRB;
    private final ModelPart LBL1, LBL2, LBL3, LBL4;
    private final ModelPart LML1, LML2, LML3, LML4;
    private final ModelPart LFL1, LFL2, LFL3, LFL4;
    private final ModelPart RFL1, RFL2, RFL3, RFL4;
    private final ModelPart RML1, RML2, RML3, RML4;
    private final ModelPart RBL1, RBL2, RBL3, RBL4;
    private final ModelPart Lid, Body, Lock;

    public TitanBeetleModel(ModelPart root) {
        super(root);
        this.root = root;
        this.Thx = root.getChild("Thx");
        this.ThxS = root.getChild("ThxS");
        this.Ab = root.getChild("Ab");
        this.AbSide = root.getChild("AbSide");
        this.AbBack = root.getChild("AbBack");
        this.Neck = root.getChild("Neck");

        this.HeadMain = root.getChild("HeadMain");
        this.Eyes = HeadMain.getChild("Eyes");
        this.HeadTop = HeadMain.getChild("HeadTop");
        this.HeadFront = HeadMain.getChild("HeadFront");
        this.LMandible1 = HeadMain.getChild("LMandible1");
        this.LMandible2 = HeadMain.getChild("LMandible2");
        this.RMandible1 = HeadMain.getChild("RMandible1");
        this.RMandible2 = HeadMain.getChild("RMandible2");
        this.AntLE = HeadMain.getChild("AntLE");
        this.AntRE = HeadMain.getChild("AntRE");
        this.AntLB = HeadMain.getChild("AntLB");
        this.AntRB = HeadMain.getChild("AntRB");

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

        this.Lid = root.getChild("Lid");
        this.Body = root.getChild("Body");
        this.Lock = root.getChild("Lock");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("Thx", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -3F, -1F, 9, 6, 7), PartPose.offset(0F, 16F, -7F));
        root.addOrReplaceChild("ThxS", CubeListBuilder.create().texOffs(0, 14).addBox(-6F, -2F, 0F, 12, 4, 4), PartPose.offset(0F, 16F, -7F));
        root.addOrReplaceChild("Ab", CubeListBuilder.create().texOffs(0, 24).addBox(-5.5F, -4.5F, -2F, 11, 7, 21), PartPose.offsetAndRotation(0F, 17F, 0F, -0.0872665F, 0F, 0F));
        root.addOrReplaceChild("AbSide", CubeListBuilder.create().texOffs(33, 0).addBox(-7F, -2F, 1F, 14, 4, 19), PartPose.offsetAndRotation(0F, 16F, -2F, -0.0872665F, 0F, 0F));
        root.addOrReplaceChild("AbBack", CubeListBuilder.create().texOffs(0, 24).addBox(-3.5F, -2F, 21F, 7, 4, 2), PartPose.offsetAndRotation(0F, 16F, -2F, -0.0872665F, 0F, 0F));
        root.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(65, 58).addBox(-2F, -2F, -8.5F, 4, 3, 2), PartPose.offsetAndRotation(0F, 16F, 0F, 0.1745329F, 0F, 0F));

        PartDefinition HeadMain = root.addOrReplaceChild("HeadMain", CubeListBuilder.create().texOffs(65, 38).addBox(-3.5F, -1.5F, -6F, 7, 4, 6), PartPose.offsetAndRotation(0F, 16F, -8F, 0.1745329F, 0F, 0F));
        HeadMain.addOrReplaceChild("Eyes", CubeListBuilder.create().texOffs(65, 49).addBox(-4F, -2F, -4F, 8, 3, 3), PartPose.ZERO);
        HeadMain.addOrReplaceChild("HeadTop", CubeListBuilder.create().texOffs(65, 30).addBox(-1.5F, -2.5F, -6F, 3, 1, 6), PartPose.ZERO);
        HeadMain.addOrReplaceChild("HeadFront", CubeListBuilder.create().texOffs(65, 24).addBox(-2.5F, -1F, -7.5F, 5, 3, 2), PartPose.ZERO);
        HeadMain.addOrReplaceChild("LMandible1", CubeListBuilder.create().texOffs(92, 33).addBox(-2F, 0F, -10.5F, 2, 1, 4), PartPose.rotation(0F, -0.3490659F, 0F));
        HeadMain.addOrReplaceChild("LMandible2", CubeListBuilder.create().texOffs(94, 30).addBox(-3F, 0F, -11.5F, 3, 1, 1), PartPose.rotation(0F, -0.3490659F, 0F));
        HeadMain.addOrReplaceChild("RMandible1", CubeListBuilder.create().texOffs(105, 33).addBox(0F, 0F, -10.5F, 2, 1, 4), PartPose.rotation(0F, 0.3316126F, 0F));
        HeadMain.addOrReplaceChild("RMandible2", CubeListBuilder.create().texOffs(107, 30).addBox(0F, 0F, -11.5F, 3, 1, 1), PartPose.rotation(0F, 0.3316126F, 0F));
        HeadMain.addOrReplaceChild("AntLE", CubeListBuilder.create().texOffs(95, 24).addBox(-1F, 0.5F, -11F, 9, 1, 1), PartPose.rotation(0F, -0.6981317F, 0F));
        HeadMain.addOrReplaceChild("AntLB", CubeListBuilder.create().texOffs(80, 24).addBox(5F, 0.5F, -2F, 6, 1, 1), PartPose.rotation(0F, 0.8726646F, 0F));
        HeadMain.addOrReplaceChild("AntRE", CubeListBuilder.create().texOffs(95, 27).addBox(-8F, 0.5F, -11F, 9, 1, 1), PartPose.rotation(0F, 0.6981317F, 0F));
        HeadMain.addOrReplaceChild("AntRB", CubeListBuilder.create().texOffs(80, 27).addBox(-11F, 0.5F, -2F, 6, 1, 1), PartPose.rotation(0F, -0.8726646F, 0F));

        float correction = 0.3490659F;

        PartDefinition LBL1 = root.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(42, 53).addBox(-1F, -1F, -1F, 7, 2, 2), PartPose.offsetAndRotation(7F, 17F, 7F, 0F, -0.6981317F, -0.3490659F - correction));
        LBL1.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 53).addBox(5F, 0F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, -0.3490659F + correction + (0.3490659F + correction))); // relative to parent's Z-rot
        LBL1.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(9, 53).addBox(3.5F, 5.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, -0.6981317F + correction + (0.3490659F + correction)));
        LBL1.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(16, 53).addBox(2.5F, 9F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, -0.8726646F + correction + (0.3490659F + correction)));

        PartDefinition LML1 = root.addOrReplaceChild("LML1", CubeListBuilder.create().texOffs(0, 60).addBox(0F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(6F, 17F, 1F, 0F, 0F, -0.3490659F - correction));
        LML1.addOrReplaceChild("LML2", CubeListBuilder.create().texOffs(0, 53).addBox(3F, 0F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, -0.3490659F + correction + (0.3490659F + correction)));
        LML1.addOrReplaceChild("LML3", CubeListBuilder.create().texOffs(9, 53).addBox(1.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, -0.6981317F + correction + (0.3490659F + correction)));
        LML1.addOrReplaceChild("LML4", CubeListBuilder.create().texOffs(16, 53).addBox(0.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, -0.8726646F + correction + (0.3490659F + correction)));

        PartDefinition LFL1 = root.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 60).addBox(0F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(5F, 17F, -5F, 0F, 0.6981317F, -0.3490659F - correction));
        LFL1.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 53).addBox(3F, 0F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, -0.3490659F + correction + (0.3490659F + correction)));
        LFL1.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(9, 53).addBox(1.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, -0.6981317F + correction + (0.3490659F + correction)));
        LFL1.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(16, 53).addBox(0.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, -0.8726646F + correction + (0.3490659F + correction)));

        PartDefinition RFL1 = root.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 60).addBox(-4F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(-5F, 17F, -5F, 0F, -0.6981317F, 0.3490659F + correction));
        RFL1.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 53).addBox(-5F, 0F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, 0.3490659F - correction - (0.3490659F + correction)));
        RFL1.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(9, 53).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, 0.6981317F - correction - (0.3490659F + correction)));
        RFL1.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(16, 53).addBox(-1.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, 0.8726646F - correction - (0.3490659F + correction)));

        PartDefinition RML1 = root.addOrReplaceChild("RML1", CubeListBuilder.create().texOffs(0, 60).addBox(-4F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(-6F, 17F, 1F, 0F, 0F, 0.3490659F + correction));
        RML1.addOrReplaceChild("RML2", CubeListBuilder.create().texOffs(0, 53).addBox(-5F, 0F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, 0.3490659F - correction - (0.3490659F + correction)));
        RML1.addOrReplaceChild("RML3", CubeListBuilder.create().texOffs(9, 53).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, 0.6981317F - correction - (0.3490659F + correction)));
        RML1.addOrReplaceChild("RML4", CubeListBuilder.create().texOffs(16, 53).addBox(-1.5F, 8F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, 0.8726646F - correction - (0.3490659F + correction)));

        PartDefinition RBL1 = root.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(42, 53).addBox(-6F, -1F, -1F, 7, 2, 2), PartPose.offsetAndRotation(-7F, 17F, 7F, 0F, 0.6981317F, 0.3490659F + correction));
        RBL1.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 53).addBox(-7F, 0F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, 0.3490659F - correction - (0.3490659F + correction)));
        RBL1.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(9, 53).addBox(-5.5F, 5.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, 0.6981317F - correction - (0.3490659F + correction)));
        RBL1.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(16, 53).addBox(-3.5F, 9F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, 0.8726646F - correction - (0.3490659F + correction)));

        root.addOrReplaceChild("Lid", CubeListBuilder.create().texOffs(0, 65).addBox(-7F, -4F, -14F, 14, 5, 14), PartPose.offsetAndRotation(0F, 3F, 4F, 0.0872665F, 3.141593F, 0F));
        root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 85).addBox(-7F, 0F, -14F, 14, 10, 14), PartPose.offsetAndRotation(0F, 3F, 4F, 0.0872665F, 3.141593F, 0F));
        root.addOrReplaceChild("Lock", CubeListBuilder.create().texOffs(0, 110).addBox(-1F, -1F, -15F, 2, 4, 1), PartPose.offsetAndRotation(0F, 3F, 4F, 0.0872665F, 3.141593F, 0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(TitanBeetleRenderState state) {
        super.setupAnim(state);
        Lid.xRot = 0.0872665F - (state.smoothedTicks * Mth.HALF_PI);
        Lock.xRot = 0.0872665F - (state.smoothedTicks * Mth.HALF_PI);

        float correction = 0.3490659F;
        float legMovement = Mth.cos(state.walkAnimationPos) * 0.5F * state.walkAnimationSpeed;
        HeadMain.yRot = state.yRot / (180F / Mth.PI);

        LBL1.xRot = -legMovement + correction;
        LML1.xRot = legMovement;
        LFL1.xRot = -legMovement - correction;

        RBL1.xRot = -legMovement + correction;
        RML1.xRot = legMovement;
        RFL1.xRot = -legMovement - correction;
    }
}
