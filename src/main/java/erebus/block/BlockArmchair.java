package erebus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BlockArmchair extends Block{

    public BlockArmchair() {
        super(Material.wood);
        setBlockName("erebus.armchair");
        setHardness(2.0F);
        setResistance(10.0F);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if(player.isSneaking()) {
            return false;
        } else {
            player.setSpawnChunk(new ChunkCoordinates(x, y, z), false);
            StatCollector.translateToLocal("armchair.spawnSet");
        }
        return false;
    }
}
