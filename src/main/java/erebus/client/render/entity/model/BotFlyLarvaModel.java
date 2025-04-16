package erebus.client.render.entity.model;

import java.util.Arrays;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BotFlyLarvaModel<T extends Entity> extends HierarchicalModel<T> {

	private final ModelPart[] larvaBodyParts = new ModelPart[7];
	private final ModelPart root;
	private static final int[][] LAVA_BOX_LENGTH = new int[][] { { 3, 2, 2 }, { 4, 3, 2 }, { 6, 4, 3 }, { 3, 3, 3 }, { 2, 2, 3 }, { 2, 1, 2 }, { 1, 1, 2 } };
	private static final int[][] LAVA_TEXTURE_POS = new int[][] { { 0, 0 }, { 0, 4 }, { 0, 9 }, { 0, 16 }, { 0, 22 }, { 11, 0 }, { 13, 4 } };

	public BotFlyLarvaModel(ModelPart root) {
		this.root = root;
		Arrays.setAll(this.larvaBodyParts, parts -> root.getChild(getSegmentName(parts)));
	}

    private static String getSegmentName(int index) {
        return "segment" + index;
    }
    
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        float[] aFloat = new float[7];
		float f = -3.5F;

		for (int i = 0; i < 7; ++i) {
			partdefinition.addOrReplaceChild(
	                getSegmentName(i),
	                CubeListBuilder.create()
	                    .texOffs(LAVA_TEXTURE_POS[i][0], LAVA_TEXTURE_POS[i][1])
	                    .addBox(LAVA_BOX_LENGTH[i][0] * -0.5F, 0.0F, LAVA_BOX_LENGTH[i][2] * -0.5F,
	                    		LAVA_BOX_LENGTH[i][0], LAVA_BOX_LENGTH[i][1], LAVA_BOX_LENGTH[i][2]),
	                    PartPose.offset(0.0F, 24 - LAVA_BOX_LENGTH[i][1], f));
			aFloat[i] = f;

			if (i < 6)
				f += (LAVA_BOX_LENGTH[i][2] + LAVA_BOX_LENGTH[i + 1][2]) * 0.5F;
		}

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

    @Override
    public ModelPart root() {
        return this.root;
    }

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		for (int i = 0; i < larvaBodyParts.length; ++i)
			larvaBodyParts[i].render(stack, consumer, light, overlay, colour);
	}

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		for (int i = 0; i < larvaBodyParts.length; ++i) {
			larvaBodyParts[i].yRot = Mth.cos(ageInTicks * 0.9F + (float)i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.05F * (1 + Math.abs((float)i - 2));
			larvaBodyParts[i].x = Mth.sin(ageInTicks * 0.9F + (float)i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.2F * Math.abs((float)i - 2);
		}
	}
}
