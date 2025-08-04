package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import erebus.Erebus;
import erebus.client.render.entity.model.BedBugModel;
import erebus.entity.BedBug;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BedBugRenderer extends MobRenderer<BedBug, BedBugModel<BedBug>> {

	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/bed_bug.png");

	public BedBugRenderer(EntityRendererProvider.Context context) {
		super(context, new BedBugModel<>(context.bakeLayer(ModEntityRendering.BED_BUG)), 0.5F);
	}

	@Override
	protected void scale(BedBug bed_bug, PoseStack matrix, float partialTickTime) {
		matrix.scale(0.5F, 0.5F, 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(BedBug bed_bug) {
		return TEXTURE;
	}
}