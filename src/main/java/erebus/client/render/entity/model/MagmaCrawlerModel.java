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
    public ModelPart root;
    private final ModelPart Thx, ThxTop, ThxS, ThxBottom, Eyeball, ThxButt, ThxFront;
    private final ModelPart LBLA, LBLB, LBLC, LBLD;
    private final ModelPart LMLA, LMLB, LMLC, LMLD;
    private final ModelPart LFLA, LFLB, LFLC, LFLD;
    private final ModelPart RFLA, RFLB, RFLC, RFLD;
    private final ModelPart RMLA, RMLB, RMLC, RMLD;
    private final ModelPart RBLA, RBLB, RBLC, RBLD;

    public MagmaCrawlerModel(ModelPart root) {
        super(root);
        this.root = root;
        this.Thx = root.getChild("Thx");
        this.ThxTop = root.getChild("ThxTop");
        this.ThxS = root.getChild("ThxS");
        this.ThxBottom = root.getChild("ThxBottom");
        this.Eyeball = root.getChild("Eyeball");
        this.ThxButt = root.getChild("ThxButt");
        this.ThxFront = root.getChild("ThxFront");

        this.LBLA = root.getChild("LBLA");
        this.LBLB = LBLA.getChild("LBLB");
        this.LBLC = LBLA.getChild("LBLC");
        this.LBLD = LBLA.getChild("LBLD");
        this.LMLA = root.getChild("LMLA");
        this.LMLB = LMLA.getChild("LMLB");
        this.LMLC = LMLA.getChild("LMLC");
        this.LMLD = LMLA.getChild("LMLD");
        this.LFLA = root.getChild("LFLA");
        this.LFLB = LFLA.getChild("LFLB");
        this.LFLC = LFLA.getChild("LFLC");
        this.LFLD = LFLA.getChild("LFLD");
        this.RFLA = root.getChild("RFLA");
        this.RFLB = RFLA.getChild("RFLB");
        this.RFLC = RFLA.getChild("RFLC");
        this.RFLD = RFLA.getChild("RFLD");
        this.RMLA = root.getChild("RMLA");
        this.RMLB = RMLA.getChild("RMLB");
        this.RMLC = RMLA.getChild("RMLC");
        this.RMLD = RMLA.getChild("RMLD");
        this.RBLA = root.getChild("RBLA");
        this.RBLB = RBLA.getChild("RBLB");
        this.RBLC = RBLA.getChild("RBLC");
        this.RBLD = RBLA.getChild("RBLD");
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
