package erebus.client.render.block.model.altar.experience;

import erebus.client.render.block.renderer.state.ExperienceAltarBlockEntityRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class ExperienceAltarGlassModel extends Model<ExperienceAltarBlockEntityRenderState> {

    public ExperienceAltarGlassModel(ModelPart root) {
		super(root, RenderTypes::entitySolid);
        root.getChild("GlassTop");
        root.getChild("GlassBot");
        root.getChild("GlassMid");
        root.getChild("BPlate");
        root.getChild("TPlate");
        root.getChild("RFSupport");
        root.getChild("RBSupport");
        root.getChild("LFSupport");
        root.getChild("LBSupport");
    }

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("GlassTop", CubeListBuilder.create().texOffs(0, 37).addBox(-3.5F, 8.0F, -3.5F, 7.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));
        part.addOrReplaceChild("GlassBot", CubeListBuilder.create().texOffs(0, 51).addBox(-3.5F, 16.0F, -3.5F, 7.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));
        part.addOrReplaceChild("GlassMid", CubeListBuilder.create().texOffs(29, 37).addBox(-1.5F, 14.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));
        part.addOrReplaceChild("BPlate", CubeListBuilder.create().texOffs(42, 37).addBox(-7.0F, 22.0F, -7.0F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));
        part.addOrReplaceChild("TPlate", CubeListBuilder.create().texOffs(42, 37).addBox(-7.0F, 6.0F, -7.0F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));
        part.addOrReplaceChild("RFSupport", CubeListBuilder.create().texOffs(99, 37).addBox(-6.0F, 5.0F, -6.0F, 2.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));
        part.addOrReplaceChild("RBSupport", CubeListBuilder.create().texOffs(99, 37).addBox(-6.0F, 5.0F, 4.0F, 2.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));
        part.addOrReplaceChild("LFSupport", CubeListBuilder.create().texOffs(99, 37).addBox(4.0F, 5.0F, -6.0F, 2.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));
        part.addOrReplaceChild("LBSupport", CubeListBuilder.create().texOffs(99, 37).addBox(4.0F, 5.0F, 4.0F, 2.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

        return LayerDefinition.create(mesh, 256, 64);
	}
}
