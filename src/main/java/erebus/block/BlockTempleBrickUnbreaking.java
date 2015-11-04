package erebus.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.item.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockTempleBrickUnbreaking extends Block {

	public static final String[] iconPaths = new String[] { "templeBrick", "templeBrickJade", "templeBrickExo", "templeBrickCream", "templeBrickEye", "templeBrickString", "templeBrickJade1", "templeBrickExo1", "templeBrickCream1", "templeBrickEye1", "templeBrickString1" };

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockTempleBrickUnbreaking() {
		super(Material.rock);
		setCreativeTab(ModTabs.blocks);
		setStepSound(Block.soundTypeStone);
		setBlockTextureName("erebus:templeBrick");
		setBlockName("erebus.templeBrickUnbreaking");
		setBlockUnbreakable();
		setResistance(6000000.0F);
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons = new IIcon[iconPaths.length];
		int i = 0;
		for (String path : iconPaths)
			icons[i++] = reg.registerIcon("erebus:" + path);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icons[Math.max(0, Math.min(icons.length - 1, side == 1 ? meta : 0))];
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		if (player.isSneaking())
			return false;

		ItemStack stack = player.getCurrentEquippedItem();
		int meta = world.getBlockMetadata(x, y, z);
		if (stack != null)
			switch (meta) {
				case 0:
					return true;
				case 1:
					if (stack.getItem() == ModItems.materials && stack.getItemDamage() == ItemMaterials.DATA.jade.ordinal()) {
						world.setBlockMetadataWithNotify(x, y, z, 6, 3);
						if (!player.capabilities.isCreativeMode)
							stack.stackSize--;
					}
					return true;
				case 2:
					if (stack.getItem() == ModItems.materials && stack.getItemDamage() == ItemMaterials.DATA.plateExo.ordinal()) {
						world.setBlockMetadataWithNotify(x, y, z, 7, 3);
						if (!player.capabilities.isCreativeMode)
							stack.stackSize--;
					}
					return true;
				case 3:
					if (stack.getItem() == Items.magma_cream) {
						world.setBlockMetadataWithNotify(x, y, z, 8, 3);
						if (!player.capabilities.isCreativeMode)
							stack.stackSize--;
					}
					return true;
				case 4:
					if (stack.getItem() == ModItems.materials && stack.getItemDamage() == ItemMaterials.DATA.magmaCrawlerEye.ordinal()) {
						world.setBlockMetadataWithNotify(x, y, z, 9, 3);
						if (!player.capabilities.isCreativeMode)
							stack.stackSize--;
					}
					return true;
				case 5:
					if (stack.getItem() == Items.string) {
						world.setBlockMetadataWithNotify(x, y, z, 10, 3); // meta 10 for forcefield breaking
						if (!player.capabilities.isCreativeMode)
							stack.stackSize--;
					}
					return true;
			}
		return true;
	}
}