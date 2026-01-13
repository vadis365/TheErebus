package erebus.client.render.block.model;

import erebus.client.render.block.state.LiquifierBlockEntityRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class LiquifierModel extends Model<LiquifierBlockEntityRenderState> {

    public LiquifierModel(ModelPart root) {
		super(root, RenderTypes::entityTranslucent);
        root.getChild("tank");
        root.getChild("lid_mid");
        root.getChild("lid_bottom");
        root.getChild("feed_pipe");
    }

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("tank", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -3.0F, -8.0F, 16.0F, 11.0F, 16.0F, new CubeDeformation(0.001F))
                .texOffs(48, 0).addBox(-7.0F, -6.0F, -7.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(110, 0).addBox(-7.0F, -6.0F, 5.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(102, 0).addBox(5.0F, -6.0F, -7.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(118, 0).addBox(5.0F, -6.0F, 5.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(48, 14).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        partdefinition.addOrReplaceChild("lid_mid", CubeListBuilder.create().texOffs(48, 0).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        partdefinition.addOrReplaceChild("lid_bottom", CubeListBuilder.create().texOffs(84, 0).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition feed_pipe = partdefinition.addOrReplaceChild("feed_pipe", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        feed_pipe.addOrReplaceChild("paddle_left", CubeListBuilder.create().texOffs(0, 8).addBox(-6.5F, 0.0F, -0.5F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        feed_pipe.addOrReplaceChild("crossbar_left", CubeListBuilder.create().texOffs(48, 5).addBox(-6.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        feed_pipe.addOrReplaceChild("crossbar_right", CubeListBuilder.create().texOffs(108, 5).addBox(2.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        feed_pipe.addOrReplaceChild("paddle_right", CubeListBuilder.create().texOffs(119, 7).addBox(3.5F, 0.0F, -0.5F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
	}
}
