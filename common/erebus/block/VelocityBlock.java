package erebus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class VelocityBlock extends Block {

	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	public VelocityBlock(int id) {
		super(id, Material.rock);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 0.875F, z + 1);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityPlayer) {
			double speed = 0.5D;
			int meta = world.getBlockMetadata(x, y, z);
			int[] factorX = { 0, 1, 0, -1 };
			int[] factorZ = { -1, 0, 1, 0 };

			if (entity != null && entity.posY > y + 0.5D) {
				if (factorX[meta] == 0 && Math.abs(x + 0.5D - entity.posX) < 0.5D && Math.abs(x + 0.5D - entity.posX) > 0.1D)
					entity.motionX += Math.signum(x + 0.5D - entity.posX) * Math.min(speed, Math.abs(x + 0.5D - entity.posX)) / 1.2D;

				if (factorZ[meta] == 0 && Math.abs(z + 0.5D - entity.posZ) < 0.5D && Math.abs(z + 0.5D - entity.posZ) > 0.1D)
					entity.motionZ += Math.signum(z + 0.5D - entity.posZ) * Math.min(speed, Math.abs(z + 0.5D - entity.posZ)) / 1.2D;

				entity.motionX += factorX[meta] * speed;
				entity.motionZ += factorZ[meta] * speed;
			}
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack is) {
		int angle = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		if (angle == 0)
			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		if (angle == 1)
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		if (angle == 2)
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		if (angle == 3)
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		icons = new Icon[4];

		for (int i = 0; i < icons.length; i++)
			icons[i] = reg.registerIcon("erebus:block_speed_" + i);
	}
}