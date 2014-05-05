package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityMucusBombPrimed;

public class BlockMucusBomb extends Block {
	@SideOnly(Side.CLIENT)
	private Icon topIcon;
	@SideOnly(Side.CLIENT)
	private Icon bottomIcon;

	public BlockMucusBomb(int par1) {
		super(par1, Material.tnt);
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 0 ? this.bottomIcon : (side == 1 ? this.topIcon: this.blockIcon);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);

		if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
			this.onBlockDestroyedByPlayer(world, x, y, z, 1);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int id) {
		if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
			onBlockDestroyedByPlayer(world, x, y, z, 1);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
		if (!world.isRemote) {
			EntityMucusBombPrimed entitymucusbombprimed = new EntityMucusBombPrimed(world, (double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), explosion.getExplosivePlacedBy());
			entitymucusbombprimed.fuse = world.rand.nextInt(entitymucusbombprimed.fuse / 4)+ entitymucusbombprimed.fuse/ 8;
			world.spawnEntityInWorld(entitymucusbombprimed);
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		primeTnt(world, x, y, z, meta, (EntityLivingBase) null);
	}

	public void primeTnt(World world, int x, int y, int z, int meta, EntityLivingBase entity) {
		if (!world.isRemote) {
			if ((meta & 1) == 1) {
				EntityMucusBombPrimed entitymucusbombprimed = new EntityMucusBombPrimed(world, (double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), entity);
				world.spawnEntityInWorld(entitymucusbombprimed );
				world.playSoundAtEntity(entitymucusbombprimed , "random.fuse", 1.0F, 1.0F);
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
	
		if (player.getCurrentEquippedItem() != null&& player.getCurrentEquippedItem().itemID == Item.flintAndSteel.itemID) {
			primeTnt(world, x, y, z, 1, player);
			world.setBlockToAir(x, y, z);
			player.getCurrentEquippedItem().damageItem(1, player);
			return true;
		} else {
			return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
		}
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
	public void registerIcons(IconRegister par1IconRegister) {
		blockIcon = par1IconRegister.registerIcon("erebus:mucusBombSides");
		topIcon = par1IconRegister.registerIcon("erebus:mucusBombTopOff");
		this.bottomIcon = par1IconRegister.registerIcon("erebus:mucusBombBottom");
	}
}
