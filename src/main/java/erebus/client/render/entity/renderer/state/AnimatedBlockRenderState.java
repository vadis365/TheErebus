package erebus.client.render.entity.renderer.state;

import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.level.block.state.BlockState;

public class AnimatedBlockRenderState extends ClimbingRenderState {

    public ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
    public BlockState blockState;
    public TextureAtlasSprite sprite;
}
