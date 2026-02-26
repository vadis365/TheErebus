package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.entity.OfferingAltarBlockEntity;
import erebus.client.particle.ClientParticles;
import erebus.client.render.block.model.OfferingAltarModel;
import erebus.client.render.block.renderer.state.OfferingAltarBlockEntityRenderState;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.SpriteGetter;
import net.minecraft.client.resources.model.SpriteId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class OfferingAltarRenderer implements BlockEntityRenderer<OfferingAltarBlockEntity, OfferingAltarBlockEntityRenderState> {
	private final Identifier TEXTURE = Erebus.prefix("offering_altar");
	private final OfferingAltarModel model;
	private final ItemModelResolver itemModelResolver;
	private final SpriteGetter sprites;

	public OfferingAltarRenderer(Context context) {
		model = new OfferingAltarModel(context.bakeLayer(ModBlockEntityRendering.OFFERING_ALTAR));
		itemModelResolver = context.itemModelResolver();
		sprites = context.sprites();
	}

	@Override
	public void extractRenderState(OfferingAltarBlockEntity blockEntity, OfferingAltarBlockEntityRenderState state, float partialTicks, @NonNull Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
		state.partialTick = partialTicks;
		state.time = blockEntity.time;
		state.prevTime = blockEntity.prevTime;
		state.rotation = blockEntity.rotation;
		state.prevRotation = blockEntity.prevRotation;
		state.random = blockEntity.getLevel().getRandom();
		state.blockPos = blockEntity.getBlockPos();

		for(int c = 0; c < 4; c++ ) {
			ItemStack stack = blockEntity.getItem(c);
			state.stacks[c] = stack;
			if(!stack.isEmpty()) {
				itemModelResolver.updateForTopItem(
						state.itemStackRenderStates[c],
						stack,
						ItemDisplayContext.FIXED,
						blockEntity.getLevel(),
						null,
						0
				);
			}
		}

		state.canCraft = blockEntity.getItem(3).isEmpty();
		state.shouldSpawnParticles = blockEntity.getLevel().getGameTime() % 2 == 0;
	}

	@Override
	public OfferingAltarBlockEntityRenderState createRenderState() {
		return new OfferingAltarBlockEntityRenderState();
	}

	@Override
	public void submit(OfferingAltarBlockEntityRenderState renderState, PoseStack pose, SubmitNodeCollector submit, @NonNull CameraRenderState camera) {
		SpriteId sprite = Sheets.BLOCKS_MAPPER.apply(TEXTURE);

		pose.pushPose();
		pose.translate(0.5D, 1.5D, 0.5D);
		pose.scale(-1, -1, 1);
		submit.submitModel(
				model,
				renderState,
				pose,
				sprite.renderType(RenderTypes::entitySolid),
				renderState.lightCoords,
				OverlayTexture.NO_OVERLAY,
				-1,
				sprites.get(sprite),
				0,
				renderState.breakProgress
		);
		pose.popPose();

		pose.pushPose();
		pose.translate(0.5D, 0.75D, 0.5D);
		renderItems(renderState, pose, submit);
		pose.popPose();
	}

	private void renderItems(OfferingAltarBlockEntityRenderState state, PoseStack pose, SubmitNodeCollector submit) {
		float angle = state.time + (state.time - state.prevTime) * state.partialTick;
		float renderRotation = state.rotation + (state.rotation - state.prevRotation) * state.partialTick;
		if (state.canCraft) {
			for (int c = 0; c < 3; c++) {
				ItemStack item = state.stacks[c];
				if (item != null && !item.isEmpty()) {
					pose.pushPose();
					pose.translate(0F, 0.75, 0F);
					pose.mulPose(Axis.YP.rotationDegrees((float) 120 * (c + 1) + renderRotation));
					pose.translate(Math.cos(Math.toRadians(angle)), 0, 0);
					pose.scale(0.5F, 0.5F, 0.5F);
					pose.mulPose(Axis.XN.rotationDegrees((float) 120 * (c + 1) + renderRotation + angle));
					pose.mulPose(Axis.YN.rotationDegrees((float) 120 * (c + 1) + renderRotation * 2F + angle));
					pose.mulPose(Axis.ZN.rotationDegrees((float) 120 * (c + 1) + renderRotation + angle));
					state.itemStackRenderStates[c].submit(pose, submit, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
					pose.popPose();

					double a = -Math.toRadians((float) 120 * (c + 1) + renderRotation - 90);
					double offSetX = -Math.sin(a) * Math.cos(Math.toRadians(angle));
					double offSetZ = Math.cos(a) * Math.cos(Math.toRadians(angle));
					if (state.shouldSpawnParticles)
						ClientParticles.spawnParticles(getParticleType(item), state.blockPos.getX() + 0.5F - offSetX, state.blockPos.getY() + 1.5F + (state.random.nextFloat() - state.random.nextFloat()) * 0.1F, state.blockPos.getZ() + 0.5F - offSetZ, 0.0D, 0.0D, 0.0D);
				}
			}
		} else {
			if (state.stacks[3] != null && !state.stacks[3].isEmpty()) {
				pose.pushPose();
				pose.translate(0F, 1.5F, 0);
				pose.mulPose(Axis.YP.rotationDegrees(angle));
				pose.scale(0.5F, 0.5F, 0.5F);
				state.itemStackRenderStates[3].submit(pose, submit, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
				pose.popPose();
			}
		}
	}

	public ClientParticles.ParticleType getParticleType (ItemStack stack) {
		if(stack.is(Blocks.OBSIDIAN.asItem()))
			return ClientParticles.ParticleType.SWAMPFLAME_GREEN;
		if(stack.is(Items.DIAMOND))
			return ClientParticles.ParticleType.SONIC_BLUE;
		if(stack.is(Items.EMERALD))
			return ClientParticles.ParticleType.SWAMPFLAME_GREEN;
		return ClientParticles.ParticleType.FLAME;
	}
}
