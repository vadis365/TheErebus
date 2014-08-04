package erebus.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntityPortal;
import erebus.world.teleporter.TeleporterHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockErebusPortal extends BlockContainer
{
    public BlockErebusPortal()
    {
		super(Material.portal);
		setTickRandomly(true);
        setBlockTextureName("erebus:erebusPortal");
        setHardness(0x1.fffffeP+127f); //Pretty hard
	}

    public void makePortal(World w, int x, int y, int z)
    {
        if (isPatternValid(w, x, y, z))
        {
            w.setBlock(x, y, z, this);
            w.setBlock(x, y + 1, z, this);
        }
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

    public static int isThisAThing(int thing)
    {
        return thing & 3;
    }



    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess w, int x, int y, int z, int m)
    {
        int thing = 0;

        if (w.getBlock(x, y, z) == this)
        {
            thing = isThisAThing(w.getBlockMetadata(x, y, z));

            if (thing == 0) return false;

            if (thing == 2 && m != 5 && m != 4) return false;

            if (thing == 1 && m != 3 && m != 2) return false;
        }

        boolean flag = w.getBlock(x - 1, y, z) == this && w.getBlock(x - 2, y, z) != this;
        boolean flag1 = w.getBlock(x + 1, y, z) == this && w.getBlock(x + 2, y, z) != this;
        boolean flag2 = w.getBlock(x, y, z - 1) == this && w.getBlock(x, y, z - 2) != this;
        boolean flag3 = w.getBlock(x, y, z + 1) == this && w.getBlock(x, y, z + 2) != this;
        boolean flag4 = flag || flag1 || thing == 1;
        boolean flag5 = flag2 || flag3 || thing == 2;
        return flag4 && m == 4 || (flag4 && m == 5 || (flag5 && m == 2 || flag5 && m == 3));
    }

    public void onNeighborBlockChange(World w, int x, int y, int z, Block b)
    {
        if (b == this) w.setBlockToAir(x, y, z);
        if (!isPatternValid(w, x, y, z)) w.setBlockToAir(x, y, x);
    }

    public int quantityDropped(Random rand)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
        if (entity.ridingEntity == null && entity.riddenByEntity == null && entity.timeUntilPortal <= 0)
        {
            if (entity.dimension == 0)TeleporterHandler.transferToErebus(entity);
            else TeleporterHandler.transferToOverworld(entity);
        }
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public TileEntity createNewTileEntity(World w, int m)
    {
        return new TileEntityPortal();
    }
}