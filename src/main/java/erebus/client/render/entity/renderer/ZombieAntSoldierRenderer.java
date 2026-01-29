package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.SoldierAntModel;
import erebus.entity.ZombieAntSoldier;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class ZombieAntSoldierRenderer extends MobRenderer<ZombieAntSoldier, SoldierAntModel<ZombieAntSoldier>> {

	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/zombie_ant_soldier.png");

	public ZombieAntSoldierRenderer(EntityRendererProvider.Context context) {
		super(context, new SoldierAntModel<>(context.bakeLayer(ModEntityRendering.ZOMBIE_ANT_SOLDIER)), 1.5F);
	}

	@Override
	protected void scale(ZombieAntSoldier ant, PoseStack matrix, float partialTickTime) {
		matrix.scale(1.125F, 1.125F, 1.125F);
	}

	@Override
	public Identifier getTextureLocation(ZombieAntSoldier ant) {
		return TEXTURE;
	}
}
