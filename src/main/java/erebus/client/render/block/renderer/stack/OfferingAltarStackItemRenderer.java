package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.Erebus;
import erebus.client.render.block.model.OfferingAltarModel;
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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class OfferingAltarStackItemRenderer extends BlockEntityWithoutLevelRenderer {
	private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/tiles/offering_altar.png");
	private final OfferingAltarModel model;

	public OfferingAltarStackItemRenderer(BlockEntityRenderDispatcher renderer, EntityModelSet modelSet) {
		super(renderer, modelSet);
		EntityModelSet EntityModelSetThatIsntNULL = Minecraft.getInstance().getEntityModels();
		model = new OfferingAltarModel(EntityModelSetThatIsntNULL.bakeLayer(ModBlockEntityRendering.OFFERING_ALTAR));
	}

	@Override
	public void renderByItem(@Nonnull ItemStack itemStackIn, @Nonnull ItemDisplayContext transformType, PoseStack stack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
		VertexConsumer consumer = buffer.getBuffer(RenderType.entitySolid(TEXTURE));
		stack.pushPose();
		stack.translate(0.5D, 1.5D, 0.5D);
		stack.scale(-1, -1, 1);
		model.renderToBuffer(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		stack.popPose();
	}

}
