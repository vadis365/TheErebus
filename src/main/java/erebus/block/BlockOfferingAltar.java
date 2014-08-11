package erebus.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.tileentity.TileEntityOfferingAltar;

public class BlockOfferingAltar extends BlockContainer {

	public BlockOfferingAltar() {
		super(Material.rock);
		setBlockName("offeringAltar");
		setCreativeTab(ModTabs.blocks);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World w, int m) {
		return new TileEntityOfferingAltar();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int h, float t, float u, float v) {
		TileEntityOfferingAltar tile = TileEntityOfferingAltar.instance(world, x, y, z);
		if (!player.isSneaking() && player.getCurrentEquippedItem() != null && tile.canAddItem()) {
			player.getCurrentEquippedItem().stackSize--;
			tile.addItem(player.getCurrentEquippedItem().getItem());
			return true;
		} else if (player.isSneaking() && tile.hasItems()) {
			if (!world.isRemote)
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, tile.getLatestItem()));
			return true;
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}
}