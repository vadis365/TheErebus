package erebus.client.render.item.renderer;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class ErebusItemRenderer<R extends BlockEntityWithoutLevelRenderer> implements IClientItemExtensions {

    private final R renderer;

    public ErebusItemRenderer(R renderer) {
        this.renderer = renderer;
    }

    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return renderer;
    }
}
