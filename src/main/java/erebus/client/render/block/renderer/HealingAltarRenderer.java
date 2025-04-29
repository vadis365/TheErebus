package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import erebus.Erebus;
import erebus.block.entity.HealingAltarBlockEntity;
import erebus.client.render.block.model.HealingAltarModel;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HealingAltarRenderer implements BlockEntityRenderer<HealingAltarBlockEntity> {
	private static final ResourceLocation[] TEXTURE = new ResourceLocation[] {
			Erebus.prefix("textures/special/tiles/altar_healing_1.png"),
			Erebus.prefix("textures/special/tiles/altar_healing_2.png"),
			Erebus.prefix("textures/special/tiles/altar_healing_3.png"),
			Erebus.prefix("textures/special/tiles/altar_healing_4.png"),
			Erebus.prefix("textures/special/tiles/altar_healing_5.png")
			};

	private final HealingAltarModel model;
	
	public HealingAltarRenderer(Context context) {
		model = new HealingAltarModel(context.bakeLayer(ModBlockEntityRendering.ALTAR_HEALING));
	}

	@Override
    public void render(HealingAltarBlockEntity tile, float partialTick, PoseStack stack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
		if(tile == null || !tile.hasLevel())
			return;
		VertexConsumer consumer = buffer.getBuffer(RenderType.entitySolid(getAltarTexture(tile)));
		stack.pushPose();
		stack.translate(0.5D, 0.75D, 0.5D);
		stack.scale(0.5F, -0.5F, -0.5F);
		stack.mulPose(Axis.YP.rotationDegrees(180F));
		model.renderWithTile(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF, tile, partialTick);
		stack.popPose();
	}

	protected ResourceLocation getAltarTexture(HealingAltarBlockEntity tile) {
		if (tile.animationTicks <= 4)
			return TEXTURE[0];
		else if (tile.animationTicks > 4 && tile.animationTicks <= 8)
			return TEXTURE[1];
		else if (tile.animationTicks > 8 && tile.animationTicks <= 12)
			return TEXTURE[2];
		else if (tile.animationTicks > 12 && tile.animationTicks <= 16)
			return TEXTURE[3];
		else if (tile.animationTicks > 16 && tile.animationTicks <= 20)
			return TEXTURE[4];
		else
			return TEXTURE[0];
	}
}