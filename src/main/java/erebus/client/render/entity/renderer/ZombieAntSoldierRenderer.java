package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.SoldierAntModel;
import erebus.client.render.entity.renderer.state.SoldierAntRenderState;
import erebus.entity.ZombieAntSoldier;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class ZombieAntSoldierRenderer extends MobRenderer<ZombieAntSoldier, SoldierAntRenderState, SoldierAntModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/zombie_ant_soldier.png");

	public ZombieAntSoldierRenderer(EntityRendererProvider.Context context) {
		super(context, new SoldierAntModel(context.bakeLayer(ModEntityRendering.ZOMBIE_ANT_SOLDIER)), 1.5F);
	}

	@Override
	public SoldierAntRenderState createRenderState() {
		return new SoldierAntRenderState();
	}

	@Override
	public void extractRenderState(ZombieAntSoldier entity, SoldierAntRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	protected void scale(SoldierAntRenderState state, PoseStack matrix) {
		matrix.scale(1.125F, 1.125F, 1.125F);
	}

	@Override
	public @NonNull Identifier getTextureLocation(SoldierAntRenderState state) {
		return TEXTURE;
	}
}
