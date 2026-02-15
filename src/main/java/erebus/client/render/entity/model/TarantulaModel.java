package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.TarantulaRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class TarantulaModel extends EntityModel<TarantulaRenderState> {
    public ModelPart root;
    private final ModelPart Body, RearEnd, Head;
    private final ModelPart Left_Fang, Right_Fang, Left_Pincerthing, Right_Pincerthing;
    private final ModelPart Left_Spinneret, Right_Spinneret;
    private final ModelPart Leg1, LegE1, Leg2, LegE2, Leg3, LegE3, Leg4, LegE4;
    private final ModelPart Leg1F, LegEF1, Leg2F, LegEF2, Leg3F, LegEF3, Leg4F, LegEF4;

    // Normal Legs
    private final float Pair1Z = 1.4F;
    private final float Pair2Z = 1F;
    private final float Pair3Z = 1.2F;
    private final float Pair4Z = 1F;

    private final float Pair1Y = 0.8F;
    private final float Pair2Y = 0.5F;
    private final float Pair3Y = 0.3F;
    private final float Pair4Y = 0.6F;

    // Extension Legs
    private final float PairE1Z = 0.3F;
    private final float PairE2Z = 1.3F;
    private final float PairE3Z = 1.8F;
    private final float PairE4Z = 1.2F;

    private final float PairE1Y = 1.4F;
    private final float PairE2Y = 0.5F;
    private final float PairE3Y = 0.3F;
    private final float PairE4Y = 0.6F;

    private final float legSpeed = 1.1F;

    public TarantulaModel(ModelPart root) {
        super(root);
        this.root = root;
        this.Body = root.getChild("Body");
        this.RearEnd = root.getChild("RearEnd");
        this.Head = root.getChild("Head");
        this.Left_Fang = Head.getChild("Left_Fang");
        this.Right_Fang = Head.getChild("Right_Fang");
        this.Left_Pincerthing = Head.getChild("Left_Pincerthing");
        this.Right_Pincerthing = Head.getChild("Right_Pincerthing");
        this.Left_Spinneret = root.getChild("Left_Spinneret");
        this.Right_Spinneret = root.getChild("Right_Spinneret");

        this.Leg1 = root.getChild("Leg1");
        this.LegE1 = Leg1.getChild("LegE1");
        this.Leg2 = root.getChild("Leg2");
        this.LegE2 = Leg2.getChild("LegE2");
        this.Leg3 = root.getChild("Leg3");
        this.LegE3 = Leg3.getChild("LegE3");
        this.Leg4 = root.getChild("Leg4");
        this.LegE4 = Leg4.getChild("LegE4");

        this.Leg1F = root.getChild("Leg1F");
        this.LegEF1 = Leg1F.getChild("LegEF1");
        this.Leg2F = root.getChild("Leg2F");
        this.LegEF2 = Leg2F.getChild("LegEF2");
        this.Leg3F = root.getChild("Leg3F");
        this.LegEF3 = Leg3F.getChild("LegEF3");
        this.Leg4F = root.getChild("Leg4F");
        this.LegEF4 = Leg4F.getChild("LegEF4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(63, 6).addBox(-7.0F, -6.0F, -7.0F, 14, 8, 13), PartPose.offset(0.0F, 21.0F, 0.0F));
        root.addOrReplaceChild("RearEnd", CubeListBuilder.create().texOffs(38, 31).addBox(-10.0F, -6.0F, -2.0F, 20, 10, 23), PartPose.offset(0.0F, 19.0F, 8.0F));

        PartDefinition Head = root.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 48).addBox(-5.5F, -3.0F, -2.0F, 11, 6, 3), PartPose.offset(0.0F, 20.0F, -8.0F));
        Head.addOrReplaceChild("Right_Fang", CubeListBuilder.create().texOffs(0, 58).addBox(-1.0F, 0.0F, -2.0F, 2, 4, 2), PartPose.offset(1.0F, -1.0F, -2.0F));
        Head.addOrReplaceChild("Left_Fang", CubeListBuilder.create().texOffs(0, 58).addBox(-1.0F, 0.0F, -2.0F, 2, 4, 2), PartPose.offset(-1.0F, -1.0F, -2.0F));
        Head.addOrReplaceChild("Right_Pincerthing", CubeListBuilder.create().texOffs(10, 57).addBox(-1.0F, -1.0F, -5.0F, 2, 2, 5), PartPose.offset(3.0F, 0.0F, -2.0F));
        Head.addOrReplaceChild("Left_Pincerthing", CubeListBuilder.create().texOffs(10, 57).addBox(-1.0F, -1.0F, -5.0F, 2, 2, 5), PartPose.offset(-3.0F, 0.0F, -2.0F));

        root.addOrReplaceChild("Left_Spinneret", CubeListBuilder.create().texOffs(24, 57).addBox(-1.0F, -1.0F, 0.0F, 2, 2, 5), PartPose.offset(-2.0F, 18.0F, 29.0F));
        root.addOrReplaceChild("Right_Spinneret", CubeListBuilder.create().texOffs(24, 57).addBox(-1.0F, -1.0F, 0.0F, 2, 2, 5), PartPose.offset(2.0F, 18.0F, 29.0F));

        PartDefinition Leg1 = root.addOrReplaceChild("Leg1", CubeListBuilder.create().texOffs(30, 0).addBox(0.0F, -1.0F, -1.0F, 7, 2, 2), PartPose.offset(5.0F, 21.0F, -7.0F));
        Leg1.addOrReplaceChild("LegE1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 13, 2, 2), PartPose.offset(7.0F, 0.0F, 0.0F));

        PartDefinition Leg2 = root.addOrReplaceChild("Leg2", CubeListBuilder.create().texOffs(30, 0).addBox(0.0F, -1.0F, -1.0F, 7, 2, 2), PartPose.offset(7.0F, 21.0F, -3.0F));
        Leg2.addOrReplaceChild("LegE2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 13, 2, 2), PartPose.offset(7.0F, 0.0F, 0.0F));

        PartDefinition Leg3 = root.addOrReplaceChild("Leg3", CubeListBuilder.create().texOffs(30, 0).addBox(0.0F, -1.0F, -1.0F, 7, 2, 2), PartPose.offset(7.0F, 21.0F, 1.0F));
        Leg3.addOrReplaceChild("LegE3", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 13, 2, 2), PartPose.offset(7.0F, 0.0F, 0.0F));

        PartDefinition Leg4 = root.addOrReplaceChild("Leg4", CubeListBuilder.create().texOffs(30, 0).addBox(0.0F, -1.0F, -1.0F, 7, 2, 2), PartPose.offset(7.0F, 21.0F, 5.0F));
        Leg4.addOrReplaceChild("LegE4", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 13, 2, 2), PartPose.offset(7.0F, 0.0F, 0.0F));

        PartDefinition Leg1F = root.addOrReplaceChild("Leg1F", CubeListBuilder.create().texOffs(30, 4).addBox(-7.0F, -1.0F, -1.0F, 7, 2, 2), PartPose.offset(-5.0F, 21.0F, -7.0F));
        Leg1F.addOrReplaceChild("LegEF1", CubeListBuilder.create().texOffs(0, 4).addBox(-12.0F, -1.0F, -1.0F, 13, 2, 2), PartPose.offset(-8.0F, 0.0F, 0.0F));

        PartDefinition Leg2F = root.addOrReplaceChild("Leg2F", CubeListBuilder.create().texOffs(30, 4).addBox(-7.0F, -1.0F, -1.0F, 7, 2, 2), PartPose.offset(-7.0F, 21.0F, -3.0F));
        Leg2F.addOrReplaceChild("LegEF2", CubeListBuilder.create().texOffs(0, 4).addBox(-13.0F, -1.0F, -1.0F, 13, 2, 2), PartPose.offset(-7.0F, 0.0F, 0.0F));

        PartDefinition Leg3F = root.addOrReplaceChild("Leg3F", CubeListBuilder.create().texOffs(30, 4).addBox(-7.0F, -1.0F, -1.0F, 7, 2, 2), PartPose.offset(-7.0F, 21.0F, 1.0F));
        Leg3F.addOrReplaceChild("LegEF3", CubeListBuilder.create().texOffs(0, 4).addBox(-13.0F, -1.0F, -1.0F, 13, 2, 2), PartPose.offset(-7.0F, 0.0F, 0.0F));

        PartDefinition Leg4F = root.addOrReplaceChild("Leg4F", CubeListBuilder.create().texOffs(30, 4).addBox(-7.0F, -1.0F, -1.0F, 7, 2, 2), PartPose.offset(-7.0F, 21.0F, 5.0F));
        Leg4F.addOrReplaceChild("LegEF4", CubeListBuilder.create().texOffs(0, 4).addBox(-13.0F, -1.0F, -1.0F, 13, 2, 2), PartPose.offset(-7.0F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 64);
    }

    @Override
    public void setupAnim(TarantulaRenderState state) {
        super.setupAnim(state);

        Head.yRot = state.yRot / (180F / (float) Math.PI);
        Head.xRot = state.xRot / (180F / (float) Math.PI);

        float varY = Mth.sin(state.walkAnimationPos * legSpeed) * 1.4F * state.walkAnimationSpeed;
        float varZ = Mth.abs(Mth.cos(state.walkAnimationPos * legSpeed)) * 0.8F * state.walkAnimationSpeed;

        float varEY = 0;
        float varEZ = 0;

        Leg1.zRot = -Pair1Z + varZ;
        Leg2.zRot = -Pair2Z + varZ;
        Leg3.zRot = -Pair3Z + varZ;
        Leg4.zRot = -Pair4Z + varZ;

        Leg1F.zRot = Pair1Z - varZ;
        Leg2F.zRot = Pair2Z - varZ;
        Leg3F.zRot = Pair3Z - varZ;
        Leg4F.zRot = Pair4Z - varZ;

        Leg1.yRot = Pair1Y + varY;
        Leg2.yRot = Pair2Y - varY;
        Leg3.yRot = -Pair3Y + varY;
        Leg4.yRot = -Pair4Y - varY;

        Leg1F.yRot = -Pair1Y + varY;
        Leg2F.yRot = -Pair2Y - varY;
        Leg3F.yRot = Pair3Y + varY;
        Leg4F.yRot = Pair4Y - varY;

        LegE1.zRot = PairE1Z + varEZ;
        LegE2.zRot = PairE2Z + varEZ;
        LegE3.zRot = PairE3Z + varEZ;
        LegE4.zRot = PairE4Z + varEZ;

        LegEF1.zRot = -PairE1Z + varEZ;
        LegEF2.zRot = -PairE2Z + varEZ;
        LegEF3.zRot = -PairE3Z + varEZ;
        LegEF4.zRot = -PairE4Z + varEZ;

        LegE1.yRot = PairE1Y + varEY;
        LegE2.yRot = PairE2Y + varEY;
        LegE3.yRot = -PairE3Y + varEY;
        LegE4.yRot = -PairE4Y + varEY;

        LegEF1.yRot = -PairE1Y + varEY;
        LegEF2.yRot = -PairE2Y + varEY;
        LegEF3.yRot = PairE3Y + varEY;
        LegEF4.yRot = PairE4Y + varEY;
    }
}
