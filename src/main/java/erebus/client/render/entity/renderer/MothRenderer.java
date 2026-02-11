package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.MothModel;
import erebus.client.render.entity.renderer.state.MothRenderState;
import erebus.entity.Moth;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jspecify.annotations.NonNull;

public class MothRenderer extends MobRenderer<Moth, MothRenderState, MothModel> {
	public static final Identifier TEXTURE_1 = Erebus.prefix("textures/entity/moth_1.png");
	public static final Identifier TEXTURE_2 = Erebus.prefix("textures/entity/moth_2.png");
	public static final Identifier TEXTURE_3 = Erebus.prefix("textures/entity/moth_3.png");

	public MothRenderer(EntityRendererProvider.Context context) {
        super(context, new MothModel(context.bakeLayer(ModEntityRendering.MOTH)), 0.3F);
	}

	@Override
	public MothRenderState createRenderState() {
		return new MothRenderState();
	}

	@Override
	public void extractRenderState(Moth entity, MothRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.skin = entity.getSkin();

		float smoothedTicks = entity.tickCount + (entity.tickCount - (entity.tickCount - 1)) * partialTicks;
		state.flap = Mth.sin((smoothedTicks) * 1.2F) * 0.5F;
	}

	@Override
	protected void scale(MothRenderState state, PoseStack pose) {
		pose.scale(0.5F, 0.5F, 0.5F);
	}

	@Override
	public @NonNull Identifier getTextureLocation(MothRenderState state) {
        return switch (state.skin) {
            case 1 -> TEXTURE_2;
            case 2 -> TEXTURE_3;
            default -> TEXTURE_1;
        };
    }
}
