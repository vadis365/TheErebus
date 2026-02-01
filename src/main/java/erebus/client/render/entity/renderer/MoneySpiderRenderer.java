package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.MoneySpiderModel;
import erebus.client.render.entity.renderer.state.MoneySpiderRenderState;
import erebus.entity.MoneySpider;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class MoneySpiderRenderer extends MobRenderer<MoneySpider, MoneySpiderRenderState, MoneySpiderModel> {

	private static final Identifier[] TEXTURES = new Identifier[] {
			Erebus.prefix("textures/entity/money_spider.png"),
			Erebus.prefix("textures/entity/money_spider_euro.png"),
			Erebus.prefix("textures/entity/money_spider_pound.png") };

	public MoneySpiderRenderer(EntityRendererProvider.Context context) {
		super(context, new MoneySpiderModel(context.bakeLayer(ModEntityRendering.MONEY_SPIDER)), 0.15F);
	}

	@Override
	public MoneySpiderRenderState createRenderState() {
		return new MoneySpiderRenderState();
	}

	@Override
	public void extractRenderState(MoneySpider entity, MoneySpiderRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.skin = entity.getSkin();
	}

	@Override
	protected void scale(MoneySpiderRenderState state, PoseStack pose) {
		pose.scale(0.3F, 0.3F, 0.3F);
	}

	@Override
	public @NonNull Identifier getTextureLocation(MoneySpiderRenderState state) {
		return TEXTURES[state.skin];
	}
}
