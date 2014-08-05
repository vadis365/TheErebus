package erebus.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.core.helper.IHighlightedBlock;
import erebus.core.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockGaeanKeystone extends Block implements IHighlightedBlock
{
    @SideOnly(Side.CLIENT)
    public IIcon icons[];
    public AxisAlignedBB[][] boxes =
            {
                    {
                            AxisAlignedBB.getBoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.8125f, 1.0f)
                    },
                    {
                            AxisAlignedBB.getBoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.8125f, 1.0f),

                            AxisAlignedBB.getBoundingBox(0.3125f - 0.0625f, 0.8125f, 0.3125f - 0.0625f, 0.6875f + 0.0625f, 1.0f, 0.6875f + 0.0625f)
                    }
            };

    public BlockGaeanKeystone()
    {
        super(Material.rock);
        setBlockName("gaeanKeystone");
        setBlockTextureName("erebus:gaeanKeystone");
        setHardness(3.0f);
        setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.8125f, 1.0f);
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public Item getItemDropped(int i, Random r, int h)
    {
        return ModItems.gaeanGem;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister r)
    {
        icons = new IIcon[4];
        icons[0] = r.registerIcon("erebus:keystone_top");
        icons[1] = r.registerIcon("erebus:keystone_sides");
        icons[2] = r.registerIcon("erebus:keystone_bottom");
        icons[3] = r.registerIcon("erebus:gaeanEye");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? icons[0] : (side == 0 ? icons[2] : icons[1]);
    }

    public static boolean isGemActive(int metadata)
    {
        return metadata > 0;
    }

    public IIcon getEyeIcon()
    {
        return icons[3];
    }

    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB box, List list, Entity entity)
    {
        AxisAlignedBB[] aabbs = boxes[isGemActive(world.getBlockMetadata(x, y, z)) ? 1 : 0];
        for (AxisAlignedBB aabb : aabbs)
        {
            AxisAlignedBB aabbTmp = aabb.getOffsetBoundingBox(x, y, z);
            if (box.intersectsWith(aabbTmp)) list.add(aabbTmp);
        }
    }

    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 origin, Vec3 direction)
    {
        AxisAlignedBB[] aabbs = boxes[isGemActive(world.getBlockMetadata(x, y, z)) ? 1 : 0];
        MovingObjectPosition closest = null;
        for (AxisAlignedBB aabb : aabbs)
        {
            MovingObjectPosition mop = aabb.getOffsetBoundingBox(x, y, z).calculateIntercept(origin, direction);
            if (mop != null)
            {
                if (closest != null && mop.hitVec.distanceTo(origin) < closest.hitVec.distanceTo(origin)) closest = mop;
                else closest = mop;
            }
        }
        if (closest != null)
        {
            closest.blockX = x;
            closest.blockY = y;
            closest.blockZ = z;
        }
        return closest;
    }

    public AxisAlignedBB[] getBoxes(World world, int x, int y, int z, EntityPlayer player)
    {
        return boxes[isGemActive(world.getBlockMetadata(x, y, z)) ? 1 : 0];
    }

    public int getRenderType()
    {
        return ClientProxy.BlockRenderIDs.KEYSTONE.id();
    }

    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int h, float k, float i, float f)
    {
        if (p.getCurrentEquippedItem() != null && p.getCurrentEquippedItem().getItem() == ModItems.portalActivator)
        {
            w.setBlockMetadataWithNotify(x, y, z, 1, 2);
            if (!ModBlocks.portalErebus.makePortal(w, x, y - 2, z))
            {
                w.setBlockMetadataWithNotify(x, y, z, 0, 2);
                return false;
            }
            if (!p.capabilities.isCreativeMode) p.inventory.mainInventory[p.inventory.currentItem] = null;
            return true;
        }
        else return false;
    }
}
