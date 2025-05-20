package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.entity.OfferingAltarBlockEntity;
import erebus.client.particle.ClientParticles;
import erebus.client.render.block.model.OfferingAltarModel;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OfferingAltarRenderer implements BlockEntityRenderer<OfferingAltarBlockEntity> {
	private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/tiles/offering_altar.png");
	private final OfferingAltarModel model;
	private final ItemRenderer itemRenderer;

	public OfferingAltarRenderer(Context context) {
		model = new OfferingAltarModel(context.bakeLayer(ModBlockEntityRendering.OFFERING_ALTAR));
		itemRenderer = context.getItemRenderer();
	}

	@Override
    public void render(OfferingAltarBlockEntity tile, float partialTick, PoseStack stack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
		if(tile == null || !tile.hasLevel())
			return;
		VertexConsumer consumer = buffer.getBuffer(RenderType.entitySolid(TEXTURE));

		stack.pushPose();
		stack.translate(0.5D, 1.5D, 0.5D);
		stack.scale(-1, -1, 1);
		model.renderToBuffer(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		stack.popPose();

		stack.pushPose();
		stack.translate(0.5D, 0.75D, 0.5D);
		renderItems(tile, partialTick, stack, buffer, packedLight, packedOverlay, 0D, 0D, 0D);
		stack.popPose();
	}

	@SuppressWarnings("resource")
	private void renderItems(OfferingAltarBlockEntity tile, float partialTick, PoseStack stack, MultiBufferSource buffer, int packedLight, int packedOverlay, double x, double y, double z) {
		float angle = tile.time + (tile.time - tile.prevTime) * partialTick;
		float renderRotation = tile.rotation + (tile.rotation - tile.prevRotation) * partialTick;
		if (tile.getItems().get(3).isEmpty()) {
			stack.translate(0F, 0.75, 0F);
			for (int i = 0; i < 3; i++) {
				ItemStack item = tile.getItems().get(i);
				if (!item.isEmpty()) {
					stack.pushPose();
					stack.mulPose(Axis.YP.rotationDegrees((float)120 * (i + 1) + renderRotation));
					stack.translate(Math.cos(Math.toRadians(angle)), 0, 0);
					stack.scale(0.5F, 0.5F, 0.5F);
					stack.pushPose();
					stack.mulPose(Axis.XN.rotationDegrees((float)120 * (i + 1) + renderRotation + angle));
					stack.mulPose(Axis.YN.rotationDegrees((float)120 * (i + 1) + renderRotation * 2F + angle));
					stack.mulPose(Axis.ZN.rotationDegrees((float)120 * (i + 1) + renderRotation + angle));
			        itemRenderer.renderStatic(item, ItemDisplayContext.FIXED, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, stack, buffer, tile.getLevel(), 1);
					stack.popPose();
					stack.popPose();
					double a = -Math.toRadians((float)120 * (i + 1) + renderRotation -90);
					double offSetX = -Math.sin(a) * Math.cos(Math.toRadians(angle));
					double offSetZ = Math.cos(a) * Math.cos(Math.toRadians(angle));
					if(tile.getLevel().getGameTime()%2 == 0)
						ClientParticles.spawnCustomParticle(getParticleType(item.getItem()), tile.getBlockPos().getX() + 0.5F - offSetX , tile.getBlockPos().getY() + 1.5F + (tile.getLevel().random.nextFloat() - tile.getLevel().random.nextFloat()) *0.1F, tile.getBlockPos().getZ() + 0.5F - offSetZ, 0.0D, 0.0D, 0.0D);
				}
			}
		} else {
			stack.pushPose();
			stack.translate(0F, 0.75F, 0);
			stack.mulPose(Axis.YP.rotationDegrees((float)tile.getLevel().getGameTime()));
			stack.scale(0.5F, 0.5F, 0.5F);
			itemRenderer.renderStatic(tile.getItemForRendering(3), ItemDisplayContext.FIXED, packedLight, OverlayTexture.NO_OVERLAY, stack, buffer, tile.getLevel(), 1);
			stack.popPose();
		}
	}

	@SuppressWarnings("deprecation")
	public String getParticleType (Item itemIn) {
		if(itemIn == Item.byBlock(Blocks.OBSIDIAN))
			return "swampflame_green";
		if(itemIn == Items.DIAMOND)
			return "swampflame_green";
		if(itemIn == Items.EMERALD)
			return "swampflame_green";
		return "flame";
	}
}