package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.item.ItemMaterials;

public class BlockForceLock extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon front;

	public BlockForceLock() {
		super(Material.rock);
		setBlockUnbreakable();
		setResistance(6000000.0F);
		setStepSound(soundTypeStone);
		//setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.forceLock");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return meta == 0 && side == 4 ? front : meta == 1 && side == 2 ? front : meta == 2 && side == 5 ? front : meta == 3 && side == 3 ? front : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("erebus:forceField");
		front = reg.registerIcon("erebus:force_lock");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		return world.getBlock(x, y, z) == ModBlocks.forceField ? false : super.shouldSideBeRendered(world, x, y, z, side);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderBlockPass() {
		return 1;
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getCurrentEquippedItem();
		if (stack != null && stack.getItem() == ModItems.materials && stack.getItemDamage() == ItemMaterials.DATA.FORCE_KEY.ordinal()) {
			if (!player.capabilities.isCreativeMode)
				stack.stackSize--;
			if (!world.isRemote) {
				int meta = world.getBlockMetadata(x, y, z);
				if (meta == 0 || meta == 2) {
					if(world.getBlock(x, y, z + 1) == ModBlocks.forceField)
						Utils.breakBlockWithParticles(world, x, y, z + 1, 0);
					if(world.getBlock(x, y, z - 1) == ModBlocks.forceField)
						Utils.breakBlockWithParticles(world, x, y, z - 1, 0);
				}
				if (meta == 1 || meta == 3) {
					if(world.getBlock(x + 1, y, z) == ModBlocks.forceField)
						Utils.breakBlockWithParticles(world, x + 1, y, z, 0);
					if(world.getBlock(x - 1, y, z) == ModBlocks.forceField)
						Utils.breakBlockWithParticles(world, x - 1, y, z, 0);
				}
				Utils.breakBlockWithParticles(world, x, y, z, 0);
			}
			return true;
		}
		return false;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack is) {
		int meta = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 1.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, meta, 2);
		System.out.println("Meta: "+ meta);
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		Random random = world.rand;
		double pixel = 0.0625D;
		if (rand.nextInt(5) == 0)
			for (int l = 0; l < 6; ++l) {
				double particleX = x + random.nextFloat();
				double particleY = y + random.nextFloat();
				double particleZ = z + random.nextFloat();

				if (l == 0 && !world.getBlock(x, y + 1, z).isOpaqueCube())
					particleY = y + 1 + pixel;

				if (l == 1 && !world.getBlock(x, y - 1, z).isOpaqueCube())
					particleY = y - pixel;

				if (l == 2 && !world.getBlock(x, y, z + 1).isOpaqueCube())
					particleZ = z + 1 + pixel;

				if (l == 3 && !world.getBlock(x, y, z - 1).isOpaqueCube())
					particleZ = z - pixel;

				if (l == 4 && !world.getBlock(x + 1, y, z).isOpaqueCube())
					particleX = x + 1 + pixel;

				if (l == 5 && !world.getBlock(x - 1, y, z).isOpaqueCube())
					particleX = x - pixel;

				if (particleX < x || particleX > x + 1 || particleY < 0.0D || particleY > y + 1 || particleZ < z || particleZ > z + 1)
					Erebus.proxy.spawnCustomParticle("sparks", world, particleX, particleY, particleZ, 0, 0, 0);
			}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		double twoPixels = 0.125D;
		return AxisAlignedBB.getBoundingBox(x + twoPixels, y, z + twoPixels, x + 1 - twoPixels, y + 1, z + 1 - twoPixels);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			int Knockback = 1;
			entity.attackEntityFrom(DamageSource.cactus, 1);
			entity.addVelocity(MathHelper.sin(entity.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.1F, 0.08D, -MathHelper.cos(entity.rotationYaw * 3.141593F / 180.0F) * Knockback * 0.1F);
			entity.worldObj.playSoundAtEntity(entity, "erebus:glowwormhurt", 1.0F, 1.0F);
		}
	}
}