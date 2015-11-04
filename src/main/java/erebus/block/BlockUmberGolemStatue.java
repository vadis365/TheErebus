package erebus.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.entity.EntityUmberGolem;
import erebus.tileentity.TileEntityUmberGolemStatue;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockUmberGolemStatue extends BlockContainer {

	public BlockUmberGolemStatue() {
		super(Material.rock);
		setHardness(2.0F);
		setBlockName("erebus.umberGolemStatue");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return Blocks.stone.getIcon(side, 0);
	}

	@Override
	public int getRenderType() {
		return -1;
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
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityUmberGolemStatue();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (player.getCurrentEquippedItem() != null)
			if (player.getCurrentEquippedItem().getItem() == ModItems.wandOfAnimation) {
				player.getCurrentEquippedItem().damageItem(1, player);
				if (!world.isRemote) {
					EntityUmberGolem entityUmberGolem;
					entityUmberGolem = new EntityUmberGolem(world);
					world.setBlockToAir(x, y, z);
					world.playSoundEffect(x, y, z, "erebus:altaroffering", 0.2F, 1.0F);
					entityUmberGolem.setPositionAndRotation(x + 0.5D, y, z + 0.5D, MathHelper.wrapAngleTo180_float(player.rotationYaw * 360.0F), 0.0F);
					world.spawnEntityInWorld(entityUmberGolem);
				}
				return true;
			}
		return false;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack is) {
		byte b0 = 0;
		int l1 = MathHelper.floor_double(entityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		if (l1 == 0)
			b0 = 2;
		if (l1 == 1)
			b0 = 5;
		if (l1 == 2)
			b0 = 3;
		if (l1 == 3)
			b0 = 4;
		world.setBlockMetadataWithNotify(x, y, z, b0, 3);
	}
}