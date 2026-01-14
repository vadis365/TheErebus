package erebus.client.render.block.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;

public class BlockOfBonesBlockEntityRenderState extends BlockEntityRenderState {
    public Direction facing;
    public Vec3 nametagAttachment;
    public Component nametag;
    public double distanceToCameraSq;
}
