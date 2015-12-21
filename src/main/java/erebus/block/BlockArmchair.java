package erebus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import erebus.Erebus;
import erebus.core.handler.configs.ConfigHandler;

public class BlockArmchair extends Block {

    public BlockArmchair() {
        super(Material.wood);
        setBlockName("erebus.armchair");
        setBlockTextureName("erebus:anthillBlock");
        setHardness(2.0F);
        setResistance(10.0F);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    	if(world.isRemote)
    		return false;
        if(player.isSneaking()) {
            return false;
        } else {
        	if (ConfigHandler.INSTANCE.allowRespawning) {
        		player.getEntityData().setInteger("armchairX", (int) x);
        		player.getEntityData().setInteger("armchairY", (int) y);
        		player.getEntityData().setInteger("armchairZ", (int) z);
        		player.getEntityData().setBoolean("armchairSpawn", true);
        		Erebus.proxy.getClientPlayer().addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("armchair.spawnSet")));
        	}
        }
        return true;
    }
}