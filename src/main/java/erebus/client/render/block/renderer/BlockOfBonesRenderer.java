package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.BlockOfBonesBlock;
import erebus.block.entity.BlockOfBonesBlockEntity;
import erebus.client.render.block.model.BlockOfBonesModel;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.ClientHooks;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class BlockOfBonesRenderer implements BlockEntityRenderer<BlockOfBonesBlockEntity> {

    private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/tiles/bone_block.png");
    private final BlockOfBonesModel model;
    private final BlockEntityRenderDispatcher dispatcher;

    public BlockOfBonesRenderer(Context context) {
        model = new BlockOfBonesModel(context.bakeLayer(ModBlockEntityRendering.BLOCK_OF_BONES));
        dispatcher = context.getBlockEntityRenderDispatcher();
    }

    @Override
    public void render(BlockOfBonesBlockEntity entity, float partialTick, PoseStack pose, MultiBufferSource source, int light, int overlay) {
        BlockState state = entity.getBlockState();
        Direction direction = state.getValue(BlockOfBonesBlock.FACING);
        VertexConsumer consumer = source.getBuffer(RenderType.entitySolid(TEXTURE));

        pose.pushPose();
        pose.translate(0.5D, 1.5F, 0.5D);
        pose.scale(1, -1, -1);

        switch (direction) {
            case NORTH:
                pose.rotateAround(Axis.YP.rotationDegrees(180), 0, 1, 0);
                break;
            case SOUTH:
                pose.rotateAround(Axis.YP.rotationDegrees(0), 0, 1, 0);
                break;
            case WEST:
                pose.rotateAround(Axis.YP.rotationDegrees(90), 0, 1, 0);
                break;
            case EAST:
                pose.rotateAround(Axis.YN.rotationDegrees(90), 0, 1, 0);
                break;
        }

        model.renderToBuffer(pose, consumer, light, overlay, 0xFFFFFFFF);
        pose.popPose();

        //renderNameTag(entity, Component.translatable("block.erebus.block_o_bones"), pose, source, light, partialTick);
    }

    @Override
    public boolean shouldRender(BlockOfBonesBlockEntity blockEntity, Vec3 cameraPos) {
        return true;
    }

    private void renderNameTag(BlockOfBonesBlockEntity entity, Component displayName, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float partialTick) {
        double distance = this.dispatcher.camera.getEntity().distanceToSqr(entity.getBlockPos().getCenter());
        if (ClientHooks.isNameplateInRenderDistance(dispatcher.camera.getEntity(), distance)) {
            Vec3 vec3 = dispatcher.camera.getBlockPosition().getCenter();
            poseStack.pushPose();
            poseStack.translate(vec3.x, vec3.y + (double) 0.5F, vec3.z);
            poseStack.mulPose(dispatcher.camera.rotation());
            poseStack.scale(0.025F, -0.025F, 0.025F);
            Matrix4f matrix4f = poseStack.last().pose();
            float f = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
            int j = (int) (f * 255.0F) << 24;
            Font font = Minecraft.getInstance().font;
            float f1 = (float) (-font.width(displayName) / 2);
            font.drawInBatch(displayName, f1, 0, 553648127, false, matrix4f, bufferSource, Font.DisplayMode.NORMAL, j, packedLight);

            poseStack.popPose();
        }
    }
}
