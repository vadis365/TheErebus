package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.StagBeetleRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.util.Mth;

public class StagBeetleModel extends EntityModel<StagBeetleRenderState> {
    private final ModelPart legLB1, legLM1, legLF1, legRB1, legRM1, legRF1, headMid, mandibleR1, mandibleL1;

    public StagBeetleModel(ModelPart root) {
        super(root);
        legLB1 = root.getChild("legLB1");
        legLM1 = root.getChild("legLM1");
        legLF1 = root.getChild("legLF1");
        legRB1 = root.getChild("legRB1");
        legRM1 = root.getChild("legRM1");
        legRF1 = root.getChild("legRF1");
        headMid = root.getChild("headMid");
        mandibleR1 = root.getChild("headMid").getChild("headR").getChild("mandibleR1");
        mandibleL1 = root.getChild("headMid").getChild("headL").getChild("mandibleL1");
    }

    public static LayerDefinition createBodyLayer() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();
        var abdomenRTop = root.addOrReplaceChild("abdomenRTop", CubeListBuilder.create().texOffs(74, 52).addBox(-8.0F, -5.0F, 0.5F, 7, 1, 17), PartPose.offsetAndRotation(0.0F, 15.0F, 6.0F, 0.0F, -0.0F, -0.17453292012214658F));
        var legLB1 = root.addOrReplaceChild("legLB1", CubeListBuilder.create().texOffs(0, 38).addBox(-0.5F, -2.0F, -2.0F, 10, 4, 4), PartPose.offsetAndRotation(9.0F, 16.0F, 16.5F, -0.17453292519943295F, -0.5235987755982988F, 0.3490658503988659F));
        var abdomenBellyBack = root.addOrReplaceChild("abdomenBellyBack", CubeListBuilder.create().texOffs(47, 121).addBox(-6.0F, 2.0F, 15.5F, 12, 2, 5), PartPose.offsetAndRotation(0.0F, 15.0F, 6.0F, 0F, 0F, 0F));
        var abdomenBelly = root.addOrReplaceChild("abdomenBelly", CubeListBuilder.create().texOffs(35, 103).addBox(-7.0F, 2.0F, 0.5F, 14, 2, 15), PartPose.offsetAndRotation(0.0F, 15.0F, 6.0F, 0F, 0F, 0F));
        var abdomenL = root.addOrReplaceChild("abdomenL", CubeListBuilder.create().texOffs(15, 106).addBox(9.0F, -3.0F, 1.5F, 1, 4, 15), PartPose.offsetAndRotation(0.0F, 15.0F, 6.0F, 0.0F, -0.0F, 0.17453292012214658F));
        var thoraxBack = root.addOrReplaceChild("thoraxBack", CubeListBuilder.create().texOffs(47, 61).addBox(-7.5F, -2.5F, -2.5F, 15, 5, 2), PartPose.offsetAndRotation(0.0F, 15.0F, 6.0F, 0F, 0F, 0F));
        var abdomenRMid = root.addOrReplaceChild("abdomenRMid", CubeListBuilder.create().texOffs(72, 77).addBox(-9.0F, -4.0F, -0.5F, 9, 6, 19), PartPose.offsetAndRotation(0.0F, 15.0F, 6.0F, 0.0F, -0.0F, -0.17453292012214658F));
        var legLF1 = root.addOrReplaceChild("legLF1", CubeListBuilder.create().texOffs(0, 12).addBox(-0.5F, -1.5F, -1.5F, 8, 3, 3), PartPose.offsetAndRotation(5.0F, 15.0F, 1.5F, 0.17453292519943295F, 0.3490658503988659F, 0.3490658503988659F));
        var legRM1 = root.addOrReplaceChild("legRM1", CubeListBuilder.create().texOffs(104, 19).addBox(0.0F, -1.5F, -1.5F, 9, 3, 3), PartPose.offsetAndRotation(-6.0F, 15.0F, 5.5F, 0.0F, 0.0F, 2.792526803190927F));
        var legLM1 = root.addOrReplaceChild("legLM1", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, -1.5F, -1.5F, 9, 3, 3), PartPose.offsetAndRotation(6.0F, 15.0F, 5.5F, 0.0F, 0.0F, 0.3490658503988659F));
        var bumL2 = root.addOrReplaceChild("bumL2", CubeListBuilder.create().texOffs(0, 117).addBox(0.5F, -3.0F, 21.5F, 6, 4, 1), PartPose.offsetAndRotation(0.0F, 15.0F, 6.0F, 0.0F, -0.0F, 0.17453292012214658F));
        var headMid = root.addOrReplaceChild("headMid", CubeListBuilder.create().texOffs(54, 30).addBox(-2.0F, -2.0F, -7.0F, 4, 5, 6), PartPose.offsetAndRotation(0.0F, 13.0F, -0.5F, -0.17453292519943295F, 0.0F, 0.0F));
        var thoraxMain = root.addOrReplaceChild("thoraxMain", CubeListBuilder.create().texOffs(47, 49).addBox(-5.5F, -1.5F, 0.0F, 11, 5, 6), PartPose.offsetAndRotation(0.0F, 13.0F, -1.5F, -0.17453292012214658F, -0.0F, 0.0F));
        var legRF1 = root.addOrReplaceChild("legRF1", CubeListBuilder.create().texOffs(106, 12).addBox(-0.5F, -1.5F, -1.5F, 8, 3, 3), PartPose.offsetAndRotation(-5.0F, 15.0F, 1.5F, -0.17453292519943295F, 2.792526803190927F, -0.3490658503988659F));
        var abdomenLMid = root.addOrReplaceChild("abdomenLMid", CubeListBuilder.create().texOffs(0, 77).addBox(0.0F, -4.0F, -0.5F, 9, 6, 19), PartPose.offsetAndRotation(0.0F, 15.0F, 6.0F, 0.0F, -0.0F, 0.17453292012214658F));
        var legRB1 = root.addOrReplaceChild("legRB1", CubeListBuilder.create().texOffs(100, 38).addBox(-0.5F, -2.0F, -2.0F, 10, 4, 4), PartPose.offsetAndRotation(-9.0F, 16.0F, 16.5F, -2.96705972839036F, -0.5235987755982988F, 2.792526803190927F));
        var abdomenLTopRear = root.addOrReplaceChild("abdomenLTopRear", CubeListBuilder.create().texOffs(22, 71).addBox(1.5F, -5.0F, 17.5F, 5, 1, 3), PartPose.offsetAndRotation(0.0F, 15.0F, 6.0F, 0.0F, -0.0F, 0.17453292012214658F));
        var legLM2 = legLM1.addOrReplaceChild("legLM2", CubeListBuilder.create().texOffs(0, 26).addBox(-0.5F, -1.5F, -1.5F, 8, 3, 3), PartPose.offsetAndRotation(8.5F, 0.0F, 0.0F, 0.17453292519943295F, -0.4363323129985824F, 0.0F));
        var bumL = root.addOrReplaceChild("bumL", CubeListBuilder.create().texOffs(0, 107).addBox(0.0F, -4.0F, 18.5F, 8, 6, 3), PartPose.offsetAndRotation(0.0F, 15.0F, 6.0F, 0.0F, -0.0F, 0.17453292012214658F));
        var spine = root.addOrReplaceChild("spine", CubeListBuilder.create().texOffs(40, 69).addBox(-1.5F, -3.5F, -0.5F, 3, 5, 21), PartPose.offsetAndRotation(0.0F, 15.0F, 6.0F, 0F, 0F, 0F));
        var bumR = root.addOrReplaceChild("bumR", CubeListBuilder.create().texOffs(106, 107).addBox(-8.0F, -4.0F, 18.5F, 8, 6, 3), PartPose.offsetAndRotation(0.0F, 15.0F, 6.0F, 0.0F, -0.0F, -0.17453292012214658F));
        var abdomenLTop = root.addOrReplaceChild("abdomenLTop", CubeListBuilder.create().texOffs(6, 52).addBox(1.0F, -5.0F, 0.5F, 7, 1, 17), PartPose.offsetAndRotation(0.0F, 15.0F, 6.0F, 0.0F, -0.0F, 0.17453292012214658F));
        var abdomenR = root.addOrReplaceChild("abdomenR", CubeListBuilder.create().texOffs(81, 106).addBox(-10.0F, -3.0F, 1.5F, 1, 4, 15), PartPose.offsetAndRotation(0.0F, 15.0F, 6.0F, 0.0F, -0.0F, -0.15707963705062866F));
        var legRB2 = legRB1.addOrReplaceChild("legRB2", CubeListBuilder.create().texOffs(106, 47).addBox(0.0F, -1.5F, -1.5F, 8, 3, 3), PartPose.offsetAndRotation(8.0F, -0.2F, 0.5F, 0.3490658503988659F, 0.8726646259971648F, 0.3490658503988659F));
        var abdomenRTopRear = root.addOrReplaceChild("abdomenRTopRear", CubeListBuilder.create().texOffs(90, 71).addBox(-6.5F, -5.0F, 17.5F, 5, 1, 3), PartPose.offsetAndRotation(0.0F, 15.0F, 6.0F, 0.0F, -0.0F, -0.17453292012214658F));
        var legLB2 = legLB1.addOrReplaceChild("legLB2", CubeListBuilder.create().texOffs(0, 47).addBox(0.0F, -1.5F, -1.5F, 8, 3, 3), PartPose.offsetAndRotation(8.0F, -0.2F, -0.5F, -0.3490658503988659F, -0.8726646259971648F, 0.3490658503988659F));
        var bumR2 = root.addOrReplaceChild("bumR2", CubeListBuilder.create().texOffs(114, 117).addBox(-6.5F, -3.0F, 21.5F, 6, 4, 1), PartPose.offsetAndRotation(0.0F, 15.0F, 6.0F, 0.0F, -0.0F, -0.17453292012214658F));
        var headL = headMid.addOrReplaceChild("headL", CubeListBuilder.create().texOffs(29, 35).addBox(1.5F, -2.5F, -8.0F, 6, 6, 7), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.17453292519943295F));
        var legLF2 = legLF1.addOrReplaceChild("legLF2", CubeListBuilder.create().texOffs(0, 5).addBox(0.0F, -1.5F, -1.5F, 8, 3, 3), PartPose.offsetAndRotation(7.0F, 0.0F, 0.5F, 0.5235987755982988F, 1.0471975511965976F, 0.3490658503988659F));
        var legLF3 = legLF2.addOrReplaceChild("legLF3", CubeListBuilder.create().texOffs(0, 0).addBox(0.5F, -1.1F, -1.0F, 8, 2, 2), PartPose.offsetAndRotation(7.5F, 0.0F, -0.5F, 0.0F, -0.5235987755982988F, 0.0F));
        var eyeL = headL.addOrReplaceChild("eyeL", CubeListBuilder.create().texOffs(29, 35).addBox(7.5F, -2.0F, -6.0F, 1, 2, 2), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0F, 0F, 0F));
        var legLM3 = legLM2.addOrReplaceChild("legLM3", CubeListBuilder.create().texOffs(0, 33).addBox(-0.5F, -1.0F, -1.0F, 9, 2, 2), PartPose.offsetAndRotation(7.5F, 0.0F, 0.0F, 0.0F, -0.2617993877991494F, 0.17453292519943295F));
        var legRM2 = legRM1.addOrReplaceChild("legRM2", CubeListBuilder.create().texOffs(106, 26).addBox(-0.5F, -1.5F, -1.5F, 8, 3, 3), PartPose.offsetAndRotation(8.5F, 0.0F, 0.0F, 2.96705972839036F, -0.4363323129985824F, 0.0F));
        var legLB3 = legLB2.addOrReplaceChild("legLB3", CubeListBuilder.create().texOffs(0, 54).addBox(-0.5F, -1.0F, -1.0F, 8, 2, 2), PartPose.offsetAndRotation(7.5F, 0.0F, 0.0F, 0.0F, 0.6981317007977318F, -0.17453292519943295F));
        var headR = headMid.addOrReplaceChild("headR", CubeListBuilder.create().texOffs(73, 35).addBox(-7.5F, -2.5F, -8.0F, 6, 6, 7), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0F, -0.17453292519943295F));
        var legRF2 = legRF1.addOrReplaceChild("legRF2", CubeListBuilder.create().texOffs(106, 5).addBox(0.0F, -1.5F, -1.5F, 8, 3, 3), PartPose.offsetAndRotation(7.0F, 0.0F, -0.5F, -0.5235987755982988F, -1.0471975511965976F, 0.3490658503988659F));
        var eyeR = headR.addOrReplaceChild("eyeR", CubeListBuilder.create().texOffs(93, 35).addBox(-8.5F, -2.0F, -6.0F, 1, 2, 2), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0F, 0F, 0F));
        var legRF3 = legRF2.addOrReplaceChild("legRF3", CubeListBuilder.create().texOffs(108, 0).addBox(0.5F, -1.0F, -1.0F, 8, 2, 2), PartPose.offsetAndRotation(7.5F, 0.0F, 0.5F, 0.0F, 0.5235987755982988F, 0.0F));
        var mandibleL1 = headL.addOrReplaceChild("mandibleL1", CubeListBuilder.create().texOffs(29, 25).addBox(-1.5F, -1.0F, -6.0F, 3, 2, 7), PartPose.offsetAndRotation(4.0F, 1.0F, -7.5F, 0.17453292519943295F, -0.7853981633974483F, -0.17453292519943295F));
        var legRB3 = legRB2.addOrReplaceChild("legRB3", CubeListBuilder.create().texOffs(108, 54).addBox(-0.5F, -1.0F, -1.0F, 8, 2, 2), PartPose.offsetAndRotation(7.5F, 0.0F, 0.0F, 0.0F, -0.6981317007977318F, -0.17453292519943295F));
        var mandibleR1 = headR.addOrReplaceChild("mandibleR1", CubeListBuilder.create().texOffs(79, 25).addBox(-1.5F, -1.0F, -6.0F, 3, 2, 7), PartPose.offsetAndRotation(-4.0F, 1.0F, -7.5F, 0.17453292519943295F, 0.7853981633974483F, 0.17453292519943295F));
        var legRM3 = legRM2.addOrReplaceChild("legRM3", CubeListBuilder.create().texOffs(106, 33).addBox(-0.5F, -1.0F, -1.0F, 9, 2, 2), PartPose.offsetAndRotation(7.5F, 0.0F, 0.0F, 0.0F, 0.2617993877991494F, 0.17453292519943295F));
        var mandibleL2 = mandibleL1.addOrReplaceChild("mandibleL2", CubeListBuilder.create().texOffs(29, 14).addBox(2.5F, -1.5F, -10.0F, 3, 3, 7), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7853981633974483F, 0.0F));
        var mandibleR2 = mandibleR1.addOrReplaceChild("mandibleR2", CubeListBuilder.create().texOffs(79, 14).addBox(-5.5F, -1.5F, -10.0F, 3, 3, 7), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7853981633974483F, 0.0F));
        var mandibleL3 = mandibleL2.addOrReplaceChild("mandibleL3", CubeListBuilder.create().texOffs(43, 16).addBox(1.5F, -1.0F, -8.0F, 1, 2, 2), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0F, 0F, 0F));
        var mandibleL4 = mandibleL3.addOrReplaceChild("mandibleL4", CubeListBuilder.create().texOffs(50, 18).addBox(-0.5F, -0.5F, -7.5F, 2, 1, 1), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0F, 0F, 0F));
        var mandibleR3 = mandibleR2.addOrReplaceChild("mandibleR3", CubeListBuilder.create().texOffs(79, 16).addBox(-2.5F, -1.0F, -8.0F, 1, 2, 2), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0F, 0F, 0F));
        var mandibleL5 = mandibleL4.addOrReplaceChild("mandibleL5", CubeListBuilder.create().texOffs(29, 6).addBox(5.5F, -1.0F, -13.0F, 2, 2, 5), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3490658503988659F, 0.0F));
        var mandibleR4 = mandibleR3.addOrReplaceChild("mandibleR4", CubeListBuilder.create().texOffs(72, 18).addBox(-1.5F, -0.5F, -7.5F, 2, 1, 1), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0F, 0F, 0F));
        var mandibleR5 = mandibleR4.addOrReplaceChild("mandibleR5", CubeListBuilder.create().texOffs(85, 6).addBox(-7.5F, -1.0F, -13.0F, 2, 2, 5), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3490658503988659F, 0.0F));
        var mandibleR6 = mandibleR5.addOrReplaceChild("mandibleR6", CubeListBuilder.create().texOffs(81, 6).addBox(-5.5F, -0.5F, -11.5F, 3, 1, 1), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0F, 0F, 0F));
        var mandibleL6 = mandibleL5.addOrReplaceChild("mandibleL6", CubeListBuilder.create().texOffs(39, 6).addBox(2.5F, -0.5F, -11.5F, 3, 1, 1), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0F, 0F, 0F));
        var mandibleR7 = mandibleR6.addOrReplaceChild("mandibleR7", CubeListBuilder.create().texOffs(89, 0).addBox(-2.0F, -0.5F, -18.0F, 1, 1, 4), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3490658503988659F, 0.0F));
        var mandibleL7 = mandibleL6.addOrReplaceChild("mandibleL7", CubeListBuilder.create().texOffs(29, 0).addBox(1.0F, -0.5F, -18.0F, 1, 1, 4), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3490658503988659F, 0.0F));
        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(StagBeetleRenderState state) {
        super.setupAnim(state);
        float legs = Mth.cos(state.walkAnimationPos * 0.75F) * 0.3F * state.walkAnimationSpeed;
        legLB1.yRot = legs - 0.3490659F;
        legLM1.yRot = -legs;
        legLF1.yRot = legs + 0.3490659F;
        legRB1.yRot = -legs - 0.3490659F;
        legRM1.yRot = legs;
        legRF1.yRot = legs + 3.142F - 0.3490659F;
        float ticks = state.jawTicks;
        headMid.xRot = -0.175F;
        if (state.digging) {
            if (state.headPosition == 0) headMid.xRot += 0.085F * ticks;
            else if (state.headPosition == 2) headMid.xRot -= 0.17F * ticks;
            float jaws = ticks < 5 ? 1.2F + 0.025F * ticks : 1.45F - 0.175F * ticks;
            mandibleR1.yRot = jaws;
            mandibleL1.yRot = -jaws;
        } else {
            mandibleR1.yRot = legs * 0.2F * state.walkAnimationSpeed + 0.8F;
            mandibleL1.yRot = -legs * 0.2F * state.walkAnimationSpeed - 0.8F;
        }
    }
}
