package erebus.core.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public interface IHighlightedBlock
{
    AxisAlignedBB[] getBoxes(World world, int x, int y, int z, EntityPlayer player);
}
