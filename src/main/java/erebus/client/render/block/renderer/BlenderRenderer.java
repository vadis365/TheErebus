package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.BlenderBlock;
import erebus.block.entity.BlenderBlockEntity;
import erebus.client.render.block.model.BlenderModel;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlenderRenderer implements BlockEntityRenderer<BlenderBlockEntity> {

    private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/tiles/blender.png");
    private final BlenderModel model;

    public BlenderRenderer(Context context) {
        model = new BlenderModel(context.bakeLayer(ModBlockEntityRendering.BLENDER));
    }

    @Override
    public void render(BlenderBlockEntity entity, float partialTick, PoseStack pose, MultiBufferSource source, int light, int overlay) {
        BlockState state = entity.getBlockState();
        Direction direction = state.getValue(BlenderBlock.FACING);
        VertexConsumer consumer = source.getBuffer(model.renderType(TEXTURE));

        pose.pushPose();
        pose.translate(0.5F, 1.5F, 0.5F);
        pose.scale(1, -1, -1);
        pose.scale(0.89F, 1, 0.89F);

        switch (direction) {
            case NORTH -> pose.rotateAround(Axis.YP.rotationDegrees(90), 0, 1, 0);
            case SOUTH -> pose.rotateAround(Axis.YP.rotationDegrees(270), 0, 1, 0);
            case WEST -> pose.rotateAround(Axis.YP.rotationDegrees(0), 0, 1, 0);
            case EAST -> pose.rotateAround(Axis.YP.rotationDegrees(180), 0, 1, 0);
        }

        model.renderToBuffer(pose, consumer, light, overlay, 0xFFFFFFFF);
        pose.popPose();
    }
}
