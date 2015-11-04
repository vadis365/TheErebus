package erebus.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class VelocityBlock extends Block {
	@SideOnly(Side.CLIENT)
	protected IIcon top, rest;

	public VelocityBlock() {
		super(Material.rock);
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.velocityBlock");
		setBlockTextureName("erebus:velocityBlock");
	}

	@Override
	public int getRenderType() {
		return BlockRenderIDs.VELOCITY_BLOCK.id();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 0.875F, z + 1);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity.isSneaking())
			return;

		if (entity instanceof EntityLiving)
			((EntityLiving) entity).func_110163_bv();
		else if (entity instanceof EntityItem)
			((EntityItem) entity).age = 0;

		double speed = speed();
		int meta = world.getBlockMetadata(x, y, z);
		int[] factorX = { 0, 1, 0, -1 };
		int[] factorZ = { -1, 0, 1, 0 };

		if (entity.posY > y + 0.5D) {
			if (factorX[meta] == 0 && Math.abs(x + 0.5D - entity.posX) < 0.5D && Math.abs(x + 0.5D - entity.posX) > 0.1D)
				entity.motionX += Math.signum(x + 0.5D - entity.posX) * Math.min(speed, Math.abs(x + 0.5D - entity.posX)) / 1.2D;

			if (factorZ[meta] == 0 && Math.abs(z + 0.5D - entity.posZ) < 0.5D && Math.abs(z + 0.5D - entity.posZ) > 0.1D)
				entity.motionZ += Math.signum(z + 0.5D - entity.posZ) * Math.min(speed, Math.abs(z + 0.5D - entity.posZ)) / 1.2D;

			entity.motionX += factorX[meta] * speed;
			entity.motionZ += factorZ[meta] * speed;
		}
	}

	protected double speed() {
		return 0.5D;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack is) {
		int angle = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, angle, 2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? top : rest;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		top = reg.registerIcon(getTextureName() + "_top");
		rest = reg.registerIcon(getTextureName() + "_rest");
	}
}