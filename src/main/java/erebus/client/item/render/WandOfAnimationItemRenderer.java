package erebus.client.item.render;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;

import erebus.Erebus;
import erebus.client.item.model.WandOfAnimationItemModel;
import erebus.registries.ModItemRendering;
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

@OnlyIn(Dist.CLIENT)
public class WandOfAnimationItemRenderer extends BlockEntityWithoutLevelRenderer {

	private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/items/wand_of_animation.png");

	private final WandOfAnimationItemModel wand_model;

	public WandOfAnimationItemRenderer(BlockEntityRenderDispatcher renderer, EntityModelSet modelSet) {
		super(renderer, modelSet);
		EntityModelSet EntityModelSetThatIsntNULL = Minecraft.getInstance().getEntityModels();
		wand_model = new WandOfAnimationItemModel(EntityModelSetThatIsntNULL.bakeLayer(ModItemRendering.WAND_OF_ANIMATION));
	}

	@Override
	public void renderByItem(ItemStack stack, @Nonnull ItemDisplayContext transformType, PoseStack matrixStack, MultiBufferSource bufferIn, int combinedLight, int combinedOverlayIn) {
		matrixStack.pushPose();
		//matrixStack.translate(0.5D, 1.5D, 0.5D);
		matrixStack.scale(0.9999F, 0.9999F, 0.9999F);
		wand_model.renderToBuffer(matrixStack, bufferIn.getBuffer(RenderType.entitySmoothCutout(TEXTURE)), combinedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		matrixStack.popPose();
	}

}
