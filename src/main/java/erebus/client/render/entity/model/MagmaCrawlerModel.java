package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.MagmaCrawlerRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class MagmaCrawlerModel extends EntityModel<MagmaCrawlerRenderState> {

    public MagmaCrawlerModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("Thx", CubeListBuilder.create().texOffs(14, 13).addBox(-3.5F, -3.5F, 0.0F, 7, 7, 9), PartPose.offset(0.0F, 17.0F, -4.0F));
        root.addOrReplaceChild("ThxTop", CubeListBuilder.create().texOffs(21, 30).addBox(-2.5F, -4.5F, 1.0F, 5, 1, 7), PartPose.offset(0.0F, 17.0F, -4.0F));
        root.addOrReplaceChild("ThxS", CubeListBuilder.create().texOffs(15, 39).addBox(-4.5F, -2.5F, 1.0F, 9, 5, 7), PartPose.offset(0.0F, 17.0F, -4.0F));
        root.addOrReplaceChild("ThxBottom", CubeListBuilder.create().texOffs(21, 30).addBox(-2.5F, 0.0F, 1.0F, 5, 1, 7), PartPose.offset(0.0F, 20.0F, -4.0F));
        root.addOrReplaceChild("Eyeball", CubeListBuilder.create().texOffs(27, 52).addBox(-1.5F, -1.5F, 0.0F, 3, 3, 1), PartPose.offset(0.0F, 17.0F, -6.0F));
        root.addOrReplaceChild("ThxButt", CubeListBuilder.create().texOffs(14, 30).addBox(-2.5F, -2.5F, 0.0F, 5, 5, 1), PartPose.offset(0.0F, 17.0F, 5.0F));
        root.addOrReplaceChild("ThxFront", CubeListBuilder.create().texOffs(14, 30).addBox(-2.5F, -2.5F, 0.0F, 5, 5, 1), PartPose.offset(0.0F, 17.0F, -5.0F));

        PartDefinition LBLA = root.addOrReplaceChild("LBLA", CubeListBuilder.create().texOffs(0, 95).addBox(0.0F, -1.0F, -1.0F, 4, 2, 2), PartPose.offsetAndRotation(4.0F, 17.0F, 8.0F, 0.25F, -0.7F, -0.5F));
        LBLA.addOrReplaceChild("LBLB", CubeListBuilder.create().texOffs(0, 88).addBox(3.0F, 0.0F, -1.0F, 2, 4, 2), PartPose.ZERO);
        LBLA.addOrReplaceChild("LBLC", CubeListBuilder.create().texOffs(0, 82).addBox(1.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0.25F, 0.1F, -0.45F));
        LBLA.addOrReplaceChild("LBLD", CubeListBuilder.create().texOffs(0, 76).addBox(0.5F, 8.0F, -0.5F, 1, 4, 1), PartPose.rotation(0.39F, 0.0F, -0.77F));

        PartDefinition LMLA = root.addOrReplaceChild("LMLA", CubeListBuilder.create().texOffs(0, 95).addBox(0.0F, -1.0F, -1.0F, 4, 2, 2), PartPose.offsetAndRotation(4.0F, 17.0F, 5.0F, 0.0F, 0.0F, -0.3490659F));
        LMLA.addOrReplaceChild("LMLB", CubeListBuilder.create().texOffs(0, 88).addBox(3.0F, 0.0F, -1.0F, 2, 4, 2), PartPose.ZERO);
        LMLA.addOrReplaceChild("LMLC", CubeListBuilder.create().texOffs(0, 82).addBox(1.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0.0F, 0.0F, -0.3490658F));
        LMLA.addOrReplaceChild("LMLD", CubeListBuilder.create().texOffs(0, 76).addBox(0.5F, 8.0F, -0.5F, 1, 4, 1), PartPose.rotation(0.0F, 0.0F, -0.5235987F));

        PartDefinition LFLA = root.addOrReplaceChild("LFLA", CubeListBuilder.create().texOffs(0, 95).addBox(0.0F, -1.0F, -1.0F, 4, 2, 2), PartPose.offsetAndRotation(4.0F, 17.0F, 2.0F, -0.25F, 0.7F, -0.5F));
        LFLA.addOrReplaceChild("LFLB", CubeListBuilder.create().texOffs(0, 88).addBox(3.0F, 0.0F, -1.0F, 2, 4, 2), PartPose.ZERO);
        LFLA.addOrReplaceChild("LFLC", CubeListBuilder.create().texOffs(0, 82).addBox(1.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(-0.25F, -0.1F, -0.45F));
        LFLA.addOrReplaceChild("LFLD", CubeListBuilder.create().texOffs(0, 76).addBox(0.5F, 8.0F, -0.5F, 1, 4, 1), PartPose.rotation(-0.39F, 0.0F, -0.77F));

        PartDefinition RFLA = root.addOrReplaceChild("RFLA", CubeListBuilder.create().texOffs(0, 95).addBox(-4.0F, -1.0F, -1.0F, 4, 2, 2), PartPose.offsetAndRotation(-4.0F, 17.0F, 2.0F, -0.25F, -0.7F, 0.5F));
        RFLA.addOrReplaceChild("RFLB", CubeListBuilder.create().texOffs(0, 88).addBox(-5.0F, 0.0F, -1.0F, 2, 4, 2), PartPose.ZERO);
        RFLA.addOrReplaceChild("RFLC", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(-0.25F, 0.1F, 0.45F));
        RFLA.addOrReplaceChild("RFLD", CubeListBuilder.create().texOffs(0, 76).addBox(-1.5F, 8.0F, -0.5F, 1, 4, 1), PartPose.rotation(-0.39F, 0.0F, 0.77F));

        PartDefinition RMLA = root.addOrReplaceChild("RMLA", CubeListBuilder.create().texOffs(0, 95).addBox(-4.0F, -1.0F, -1.0F, 4, 2, 2), PartPose.offsetAndRotation(-4.0F, 17.0F, 5.0F, 0.0F, 0.0F, 0.3490659F));
        RMLA.addOrReplaceChild("RMLB", CubeListBuilder.create().texOffs(0, 88).addBox(-5.0F, 0.0F, -1.0F, 2, 4, 2), PartPose.ZERO);
        RMLA.addOrReplaceChild("RMLC", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0.0F, 0.0F, 0.3490658F));
        RMLA.addOrReplaceChild("RMLD", CubeListBuilder.create().texOffs(0, 76).addBox(-1.5F, 8.0F, -0.5F, 1, 4, 1), PartPose.rotation(0.0F, 0.0F, 0.5235987F));

        PartDefinition RBLA = root.addOrReplaceChild("RBLA", CubeListBuilder.create().texOffs(0, 95).addBox(-4.0F, -1.0F, -1.0F, 4, 2, 2), PartPose.offsetAndRotation(-4.0F, 17.0F, 8.0F, 0.25F, 0.7F, 0.5F));
        RBLA.addOrReplaceChild("RBLB", CubeListBuilder.create().texOffs(0, 88).addBox(-5.0F, 0.0F, -1.0F, 2, 4, 2), PartPose.ZERO);
        RBLA.addOrReplaceChild("RBLC", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1), PartPose.rotation(0.25F, -0.1F, 0.45F));
        RBLA.addOrReplaceChild("RBLD", CubeListBuilder.create().texOffs(0, 76).addBox(-1.5F, 8.0F, -0.5F, 1, 4, 1), PartPose.rotation(0.39F, 0.0F, 0.77F));

        return LayerDefinition.create(mesh, 64, 128);
    }
}
