package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.entity.model.GrasshopperModel;
import erebus.entity.Grasshopper;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class GrasshopperRenderer extends  MobRenderer<Grasshopper, GrasshopperModel<Grasshopper>> {
	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/grasshopper.png");

	public GrasshopperRenderer(EntityRendererProvider.Context context) {
		super(context, new GrasshopperModel<>(context.bakeLayer(ModEntityRendering.GRASSHOPPER)), 0.5F);
	}
	
	@Override
	protected void scale(Grasshopper grasshopper, PoseStack stack,  float partialTickTime ) {
		float jumpAngle = Mth.sin(grasshopper.getJumpCompletion(partialTickTime) * (float) Math.PI);
		stack.mulPose(Axis.XP.rotation(-jumpAngle * 10.0F * (float) (Math.PI / 180.0)));
	}

	@Override
	public ResourceLocation getTextureLocation(Grasshopper grasshopper) {
		return TEXTURE;
	}
}
