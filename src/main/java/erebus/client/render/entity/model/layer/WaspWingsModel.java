package erebus.client.render.entity.model.layer;

import erebus.client.render.entity.renderer.state.WaspRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class WaspWingsModel extends EntityModel<WaspRenderState> {
    public ModelPart RWingMid;
    public ModelPart LWingMid;
    public ModelPart RWingBack;
    public ModelPart RWingFront;
    public ModelPart LWingBack;
    public ModelPart LWingFront;

    public WaspWingsModel(ModelPart root) {
        super(root);
        this.RWingMid = root.getChild("RWingMid");
        this.LWingMid = root.getChild("LWingMid");
        this.RWingBack = root.getChild("RWingMid").getChild("RWingBack");
        this.RWingFront = root.getChild("RWingMid").getChild("RWingFront");
        this.LWingBack = root.getChild("LWingMid").getChild("LWingBack");
        this.LWingFront = root.getChild("LWingMid").getChild("LWingFront");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition RWingMid = root.addOrReplaceChild("RWingMid", CubeListBuilder.create().texOffs(16, 44).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 13.0F, 4.0F, 0.0F, 0.1745F, 0.0F));
        RWingMid.addOrReplaceChild("RWingFront", CubeListBuilder.create().texOffs(36, 28).addBox(1.5F, -0.5F, 6.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        RWingMid.addOrReplaceChild("RWingBack", CubeListBuilder.create().texOffs(36, 28).addBox(-2.5F, -0.5F, 6.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition LWingMid = root.addOrReplaceChild("LWingMid", CubeListBuilder.create().texOffs(16, 44).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 13.0F, 4.0F, 0.0F, -0.1745F, 0.0F));
        LWingMid.addOrReplaceChild("LWingBack", CubeListBuilder.create().texOffs(36, 28).addBox(1.5F, -0.5F, 6.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        LWingMid.addOrReplaceChild("LWingFront", CubeListBuilder.create().texOffs(36, 28).addBox(-2.5F, -0.5F, 6.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 128);
    }

    @Override
    public void setupAnim(WaspRenderState state) {
        super.setupAnim(state);
        float smoothedTicks = state.animationTicks + (state.animationTicks - state.prevAnimationTicks) * state.partialTick;
        float flap = Mth.sin(smoothedTicks * 1.2F) * 0.5F;

        if(state.isFlying) {
            RWingMid.yRot = 1.5F;
            LWingMid.yRot = -1.5F;
            RWingMid.xRot = flap;
            LWingMid.xRot = flap;
        } else  {
            RWingMid.yRot = 0.25F;
            LWingMid.yRot = -0.25F;
            RWingMid.xRot = 0F;
            LWingMid.xRot = 0F;
        }
    }
}
