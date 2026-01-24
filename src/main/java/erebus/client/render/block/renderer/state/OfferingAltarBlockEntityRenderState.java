package erebus.client.render.block.renderer.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

public class OfferingAltarBlockEntityRenderState extends BlockEntityRenderState {
    public float partialTick;
    public int time;
    public int prevTime;
    public int rotation;
    public int prevRotation;

    public ItemStackRenderState[] itemStackRenderStates = new ItemStackRenderState[4];
    public ItemStack[] stacks = new ItemStack[4];

    public boolean canCraft;
    public boolean shouldSpawnParticles;

    public RandomSource random;
}
