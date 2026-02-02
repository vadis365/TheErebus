package erebus.client.render.item.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Unit;

public class WaspDaggerModel extends Model<Unit> {
    public final ModelPart point;
    public final ModelPart blade;
    public final ModelPart tang;
    public final ModelPart hilt;
    public final ModelPart pommel;
    public final ModelPart jewel;

    public WaspDaggerModel(ModelPart root) {
        super(root, RenderTypes::entitySolid);
        point = root.getChild("Point");
        blade = root.getChild("Blade");
        tang = root.getChild("Tang");
        hilt = root.getChild("Hilt");
        pommel = root.getChild("Pommel");
        jewel = root.getChild("Jewel");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild(
                "Point",
                CubeListBuilder.create()
                        .texOffs(23, 0)
                        .addBox(
                                -1.5F,
                                -1.5F,
                                -0.5F,
                                3,
                                3,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(0, -12, 0, 0, 0, 0.7853982F)
        );

        partdefinition.addOrReplaceChild(
                "Blade",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(
                                -2,
                                -36,
                                -0.5F,
                                4,
                                16,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 24, 0)
        );

        partdefinition.addOrReplaceChild(
                "Tang",
                CubeListBuilder.create()
                        .texOffs(10, 46)
                        .addBox(
                                -4,
                                -20,
                                -1.5F,
                                4,
                                16,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 24, 0)
        );

        partdefinition.addOrReplaceChild(
                "Hilt",
                CubeListBuilder.create()
                        .texOffs(0, 49)
                        .addBox(
                                -1.5F,
                                -18,
                                -1,
                                3,
                                13,
                                2,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 24, 0)
        );

        partdefinition.addOrReplaceChild(
                "Pommel",
                CubeListBuilder.create()
                        .texOffs(13, 56)
                        .addBox(
                                -5,
                                -5,
                                -2,
                                4,
                                4,
                                4,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(0, 24, 0, 0, 0, 0.7853982F)
        );

        partdefinition.addOrReplaceChild(
                "Jewel",
                CubeListBuilder.create()
                        .texOffs(13, 56)
                        .addBox(
                                -5,
                                -5,
                                -2,
                                4,
                                4,
                                4,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(0, 10, 0, 0, 0, 0.7853982F)
        );

        return LayerDefinition.create(meshdefinition, 32, 64);
    }
}
