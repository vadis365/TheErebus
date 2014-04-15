package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import erebus.entity.EntityBeetleLarva;

public class BlockHollowLog extends Block {

	@SideOnly(Side.CLIENT)
	private Icon iconTop, iconSide, iconMoss;

	public BlockHollowLog(int id) {
		super(id, Material.wood);
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return side == 0 || side == 1 ? iconTop : (side == 2 || side == 3) && meta == 1 || (side == 4 || side == 5) && meta == 0 ? iconSide : iconMoss;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		iconTop = reg.registerIcon("erebus:hollowLogTop");
		iconSide = reg.registerIcon("erebus:hollowLogSide");
		iconMoss = reg.registerIcon("erebus:hollowLogMoss");
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public int getRenderType() {
		return BlockRenderIDs.HOLLOW_LOG.id();
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int s, AxisAlignedBB box, List list, Entity entity) {
		float pixel = 0.0625F; // 1 pixel
		// bottom
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, pixel, 1.0F);
		super.addCollisionBoxesToList(world, x, y, s, box, list, entity);

		// top
		setBlockBounds(0.0F, 1.0F - pixel, 0.0F, 1.0F, 1.0F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, s, box, list, entity);

		// east
		setBlockBounds(1.0F - pixel, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, s, box, list, entity);

		// south
		setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F + pixel, 1.0F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, s, box, list, entity);

		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack is) {
		int l = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		world.setBlockMetadataWithNotify(x, y, z, l == 0 || l == 2 ? 0 : 1, 2);
	}
	
// Debug placeholder code until woodlouse mob is added
	@Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
        if (!world.isRemote) {
        	if(world.rand.nextInt(2)==0) { // will be 1:20 not 1:2
            EntityBeetleLarva entity = new EntityBeetleLarva(world);
            entity.setLocationAndAngles((double)x + 0.5D, (double)y, (double)z + 0.5D, 0.0F, 0.0F);
            world.spawnEntityInWorld(entity);
            }
        }
        super.onBlockDestroyedByPlayer(world, x, y, z, meta);
    }

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return Item.stick.itemID;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1 + rand.nextInt(4);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		return true;
	}
}