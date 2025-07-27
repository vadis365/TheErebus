package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.block.model.BambooExtenderModel;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class BambooExtenderItemRenderer extends BlockEntityWithoutLevelRenderer {

	private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/tiles/bamboo_extender.png");
	private final BambooExtenderModel model;

    public BambooExtenderItemRenderer(BlockEntityRenderDispatcher renderer, EntityModelSet modelSet) {
        super(renderer, modelSet);
        EntityModelSet set = Minecraft.getInstance().getEntityModels();
        model = new BambooExtenderModel(set.bakeLayer(ModBlockEntityRendering.BAMBOO_EXTENDER));
    }

    @Override
    public void renderByItem(@Nonnull ItemStack itemStackIn, @Nonnull ItemDisplayContext transformType, PoseStack stack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        VertexConsumer consumer = buffer.getBuffer(RenderType.entitySolid(TEXTURE));
        stack.pushPose();
        stack.translate(0.5D, 1.5D, 0.5D);
        stack.scale(-1, -1, 1);
        stack.scale(1F, 1F, 1F);
        stack.rotateAround(Axis.YN.rotationDegrees(90), 0, 1, 0);
        model.renderToBuffer(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
        stack.popPose();
    }
}
