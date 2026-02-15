package erebus.client.render.block.model.altar.healing;

import erebus.client.render.block.renderer.state.HealingAltarBlockEntityRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class HealingAltarRoseModel extends Model<HealingAltarBlockEntityRenderState> {

    public HealingAltarRoseModel(ModelPart root) {
        super(root, RenderTypes::entitySolid);
        root.getChild("ROutPetal1");
        root.getChild("ROutPetal2");
        root.getChild("ROutPetal3");
        root.getChild("ROutPelal4");
        root.getChild("LOutPetal1");
        root.getChild("LOutPetal2");
        root.getChild("LOutPetal3");
        root.getChild("LOutPetal4");
        root.getChild("BOutPetal1");
        root.getChild("BOutPetal2");
        root.getChild("BOutPetal3");
        root.getChild("BOutPetal4");
        root.getChild("RInPetal1");
        root.getChild("RInPetal2");
        root.getChild("RInPetal3");
        root.getChild("RInPetal4");
        root.getChild("LInPetal1");
        root.getChild("LInPetal2");
        root.getChild("LinPetal3");
        root.getChild("LInPetal4");
        root.getChild("FInPetal1");
        root.getChild("FInPetal2");
        root.getChild("FInPetal3");
        root.getChild("FInPetal4");
        root.getChild("RLeafBack");
        root.getChild("RLeafMain");
        root.getChild("RLeafFront");
        root.getChild("RLeafTop");
        root.getChild("RLeafEnd");
        root.getChild("LLeafBack");
        root.getChild("LLeafMain");
        root.getChild("LLeafFront");
        root.getChild("LLeafTop");
        root.getChild("LLeafEnd");
        root.getChild("Stem");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("ROutPetal1", CubeListBuilder.create().texOffs(0, 37).addBox(-2.0F, 0.0F, 5.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.7854F, -2.0944F, 0.0F));
        part.addOrReplaceChild("ROutPetal2", CubeListBuilder.create().texOffs(20, 43).addBox(-3.0F, 0.0F, 4.5F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, -2.0944F, 0.0F));
        part.addOrReplaceChild("ROutPetal3", CubeListBuilder.create().texOffs(20, 51).addBox(-4.0F, -4.0F, 4.5F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, -2.0944F, 0.0F));
        part.addOrReplaceChild("ROutPelal4", CubeListBuilder.create().texOffs(20, 37).addBox(-3.0F, -7.0F, 5.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.0F, -2.0944F, 0.0F));
        part.addOrReplaceChild("LOutPetal1", CubeListBuilder.create().texOffs(0, 37).addBox(-2.0F, 0.0F, 5.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.7854F, 2.0944F, 0.0F));
        part.addOrReplaceChild("LOutPetal2", CubeListBuilder.create().texOffs(20, 43).addBox(-3.0F, 0.0F, 4.5F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, 2.0944F, 0.0F));
        part.addOrReplaceChild("LOutPetal3", CubeListBuilder.create().texOffs(20, 51).addBox(-4.0F, -4.0F, 4.5F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, 2.0944F, 0.0F));
        part.addOrReplaceChild("LOutPetal4", CubeListBuilder.create().texOffs(20, 37).addBox(-3.0F, -7.0F, 5.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.0F, 2.0944F, 0.0F));
        part.addOrReplaceChild("BOutPetal1", CubeListBuilder.create().texOffs(0, 37).addBox(-2.0F, 0.0F, 5.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
        part.addOrReplaceChild("BOutPetal2", CubeListBuilder.create().texOffs(20, 43).addBox(-3.0F, 0.0F, 4.5F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, 0.0F, 0.0F));
        part.addOrReplaceChild("BOutPetal3", CubeListBuilder.create().texOffs(20, 51).addBox(-4.0F, -4.0F, 4.5F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, 0.0F, 0.0F));
        part.addOrReplaceChild("BOutPetal4", CubeListBuilder.create().texOffs(20, 37).addBox(-3.0F, -7.0F, 5.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));
        part.addOrReplaceChild("RInPetal1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.5F, 1.0F, 4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.7854F, -1.0472F, 0.0F));
        part.addOrReplaceChild("RInPetal2", CubeListBuilder.create().texOffs(35, 43).addBox(-3.0F, 0.0F, 0.5F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, -1.0472F, 0.0F));
        part.addOrReplaceChild("RInPetal3", CubeListBuilder.create().texOffs(39, 51).addBox(-3.5F, -4.0F, 1.5F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, -1.0472F, 0.0F));
        part.addOrReplaceChild("RInPetal4", CubeListBuilder.create().texOffs(20, 37).addBox(-3.0F, -7.0F, 3.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.0F, -1.0472F, 0.0F));
        part.addOrReplaceChild("LInPetal1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.5F, 1.0F, 4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.7854F, 1.0472F, 0.0F));
        part.addOrReplaceChild("LInPetal2", CubeListBuilder.create().texOffs(35, 43).addBox(-3.0F, 0.0F, 0.5F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, 1.0472F, 0.0F));
        part.addOrReplaceChild("LinPetal3", CubeListBuilder.create().texOffs(39, 51).addBox(-3.5F, -4.0F, 1.5F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, 1.0472F, 0.0F));
        part.addOrReplaceChild("LInPetal4", CubeListBuilder.create().texOffs(20, 37).addBox(-3.0F, -7.0F, 3.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.0F, 1.0472F, 0.0F));
        part.addOrReplaceChild("FInPetal1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.5F, 1.0F, 4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.7854F, -3.1416F, 0.0F));
        part.addOrReplaceChild("FInPetal2", CubeListBuilder.create().texOffs(35, 43).addBox(-3.0F, 0.0F, 0.5F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, 3.1416F, 0.0F));
        part.addOrReplaceChild("FInPetal3", CubeListBuilder.create().texOffs(39, 51).addBox(-3.5F, -4.0F, 1.5F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, 3.1416F, 0.0F));
        part.addOrReplaceChild("FInPetal4", CubeListBuilder.create().texOffs(20, 37).addBox(-3.0F, -7.0F, 3.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
        part.addOrReplaceChild("RLeafBack", CubeListBuilder.create().texOffs(8, 43).addBox(-2.0F, 11.0F, -9.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.3491F, 1.5708F, 0.0F));
        part.addOrReplaceChild("RLeafMain", CubeListBuilder.create().texOffs(13, 37).addBox(-1.0F, 7.5F, -9.5F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.3491F, 1.5708F, 0.0F));
        part.addOrReplaceChild("RLeafFront", CubeListBuilder.create().texOffs(8, 54).addBox(1.0F, 11.0F, -9.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.3491F, 1.5708F, 0.0F));
        part.addOrReplaceChild("RLeafTop", CubeListBuilder.create().texOffs(13, 53).addBox(-1.0F, 12.0F, -1.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.4363F, 1.5708F, 0.0F));
        part.addOrReplaceChild("RLeafEnd", CubeListBuilder.create().texOffs(13, 58).addBox(-0.5F, 15.0F, -1.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.4363F, 1.5708F, 0.0F));
        part.addOrReplaceChild("LLeafBack", CubeListBuilder.create().texOffs(8, 43).addBox(1.0F, 11.0F, -9.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.3491F, -1.5708F, 0.0F));
        part.addOrReplaceChild("LLeafMain", CubeListBuilder.create().texOffs(13, 37).addBox(-1.0F, 7.5F, -9.5F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.3491F, -1.5708F, 0.0F));
        part.addOrReplaceChild("LLeafFront", CubeListBuilder.create().texOffs(8, 54).addBox(-2.0F, 11.0F, -9.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.3491F, -1.5708F, 0.0F));
        part.addOrReplaceChild("LLeafTop", CubeListBuilder.create().texOffs(13, 53).addBox(-1.0F, 12.0F, 0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.4363F, -4.7124F, 0.0F));
        part.addOrReplaceChild("LLeafEnd", CubeListBuilder.create().texOffs(13, 58).addBox(-0.5F, 15.0F, 0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.4363F, -4.7124F, 0.0F));
        part.addOrReplaceChild("Stem", CubeListBuilder.create().texOffs(58, 40).addBox(-1.0F, 6.0F, -1.0F, 2.0F, 18.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        return LayerDefinition.create(mesh, 256, 64);
    }
}
