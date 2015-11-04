package erebus.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.entity.EntityMucusBombPrimed;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockMucusBomb extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon topIcon, bottomIcon;

	public BlockMucusBomb() {
		super(Material.tnt);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.mucusBomb");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 0 ? bottomIcon : side == 1 ? topIcon : blockIcon;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
			onBlockDestroyedByPlayer(world, x, y, z, 1);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block id) {
		if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
			onBlockDestroyedByPlayer(world, x, y, z, 1);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
		if (!world.isRemote) {
			EntityMucusBombPrimed primed = new EntityMucusBombPrimed(world, x + 0.5F, y + 0.5F, z + 0.5F);
			primed.fuse = world.rand.nextInt(primed.fuse / 4) + primed.fuse / 8;
			world.spawnEntityInWorld(primed);
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		primeTnt(world, x, y, z, meta, (EntityLivingBase) null);
	}

	public void primeTnt(World world, int x, int y, int z, int meta, EntityLivingBase entity) {
		if (!world.isRemote)
			if ((meta & 1) == 1) {
				EntityMucusBombPrimed primed = new EntityMucusBombPrimed(world, x + 0.5F, y + 0.5F, z + 0.5F);
				world.spawnEntityInWorld(primed);
				world.playSoundAtEntity(primed, "random.fuse", 1.0F, 1.0F);
			}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.flint_and_steel) {
			primeTnt(world, x, y, z, 1, player);
			world.setBlockToAir(x, y, z);
			player.getCurrentEquippedItem().damageItem(1, player);
			return true;
		} else
			return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityArrow && !world.isRemote) {
			EntityArrow entityarrow = (EntityArrow) entity;

			if (entityarrow.isBurning()) {
				primeTnt(world, x, y, z, 1, entityarrow.shootingEntity instanceof EntityLivingBase ? (EntityLivingBase) entityarrow.shootingEntity : null);
				world.setBlockToAir(x, y, z);
			}
		}
	}

	@Override
	public boolean canDropFromExplosion(Explosion explosion) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("erebus:mucusBombSides");
		topIcon = reg.registerIcon("erebus:mucusBombTopOff");
		bottomIcon = reg.registerIcon("erebus:mucusBombBottom");
	}
}