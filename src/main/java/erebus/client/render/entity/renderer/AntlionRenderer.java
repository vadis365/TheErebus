package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.AntlionModel;
import erebus.client.render.entity.renderer.state.AntlionRenderState;
import erebus.entity.Antlion;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class AntlionRenderer extends MobRenderer<Antlion, AntlionRenderState, AntlionModel> {

	public AntlionRenderer(EntityRendererProvider.Context context) {
		super(context, new AntlionModel(context.bakeLayer(ModEntityRendering.ANTLION)), 0.75F);
		this.model = new AntlionModel(context.bakeLayer(ModEntityRendering.ANTLION));
	}

	@Override
	protected void scale(AntlionRenderState state, PoseStack pose) {
		pose.scale(0.75F, 0.75F, 0.75F);
	}

	@Override
	public AntlionRenderState createRenderState() {
		return new AntlionRenderState();
	}

	@Override
	public @NonNull Identifier getTextureLocation(AntlionRenderState state) {
		return Erebus.prefix("textures/entity/antlion.png");
	}
}
