package erebus.block;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockErebusPortal extends BlockBreakable
{
    public BlockErebusPortal()
    {
		super("erebus:erebusPortal", Material.portal, false);
		setTickRandomly(true);
	}

    public void makePortal(World w, int x, int y, int z)
    {
        if (!w.isRemote) System.out.println(w.getBlockMetadata(x, y - 1, z));
    }

    public boolean isPatternValid(World w, int x, int y, int z)
    {
        { //Layer 0
            if (w.getBlock(x, y - 1, z) != Blocks.stonebrick || w.getBlockMetadata(x, y - 1, z) != 3) return false;
        }
        { //Layer 1
            if (w.getBlock(x - 1, y, z - 1) != Blocks.stonebrick || w.getBlockMetadata(x - 1, y, z - 1) != 0) return false;
            if (w.getBlock(x - 1, y, z + 1) != Blocks.stonebrick || w.getBlockMetadata(x - 1, y, z + 1) != 0) return false;
            if (w.getBlock(x + 1, y, z - 1) != Blocks.stonebrick || w.getBlockMetadata(x + 1, y, z - 1) != 0) return false;
            if (w.getBlock(x + 1, y, z + 1) != Blocks.stonebrick || w.getBlockMetadata(x + 1, y, z + 1) != 0) return false;

            if (w.getBlock(x + 2, y, z - 1) != Blocks.brick_stairs || w.getBlockMetadata(x + 2, y, z - 1) != 1) return false;
        }
        { //Layer 2
            if (w.getBlock(x - 1, y + 1, z + 1) != Blocks.stonebrick || w.getBlockMetadata(x - 1, y + 1, z + 1) != 0) return false;
            if (w.getBlock(x - 1, y + 1, z - 1) != Blocks.stonebrick || w.getBlockMetadata(x - 1, y + 1, z - 1) != 0) return false;
            if (w.getBlock(x + 1, y + 1, z + 1) != Blocks.stonebrick || w.getBlockMetadata(x + 1, y + 1, z + 1) != 0) return false;
            if (w.getBlock(x + 1, y + 1, z - 1) != Blocks.stonebrick || w.getBlockMetadata(x + 1, y + 1, z - 1) != 0) return false;
        }
        { //Layer 3
            if (w.getBlock(x, y + 2, z) != Blocks.glass) return false;
            if (w.getBlock(x - 1, y + 2, z) != Blocks.stone_slab || w.getBlockMetadata(x - 1, y + 2, z) != 5) return false;
            if (w.getBlock(x - 1, y + 2, z - 1) != Blocks.stone_slab || w.getBlockMetadata(x - 1, y + 2, z - 1) != 5) return false;
            if (w.getBlock(x - 1, y + 2, z + 1) != Blocks.stone_slab || w.getBlockMetadata(x - 1, y + 2, z + 1) != 5) return false;
            if (w.getBlock(x, y + 2, z - 1) != Blocks.stone_slab || w.getBlockMetadata(x, y + 2, z - 1) != 5) return false;
            if (w.getBlock(x, y + 2, z + 1) != Blocks.stone_slab || w.getBlockMetadata(x, y + 2, z + 1) != 5) return false;
            if (w.getBlock(x + 1, y + 2, z) != Blocks.stone_slab || w.getBlockMetadata(x - 1, y + 2, z) != 5) return false;
            if (w.getBlock(x + 1, y + 2, z - 1) != Blocks.stone_slab || w.getBlockMetadata(x + 1, y + 2, z - 1) != 5) return false;
            if (w.getBlock(x + 1, y + 2, z + 1) != Blocks.stone_slab || w.getBlockMetadata(x + 1, y + 2, z + 1) != 5) return false;
        }
        return true;
    }
}