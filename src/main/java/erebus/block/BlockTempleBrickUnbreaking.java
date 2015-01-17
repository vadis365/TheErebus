package erebus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.item.Materials;

public class BlockTempleBrickUnbreaking extends Block {
	
	private IIcon templeBrick, templeBrickJade, templeBrickExo, templeBrickCream, templeBrickEye, templeBrickString;
	private IIcon templeBrickJade1, templeBrickExo1, templeBrickCream1, templeBrickEye1, templeBrickString1;
	
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
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("erebus:templeBrick");
		templeBrickJade = reg.registerIcon("erebus:templeBrickJade");
		templeBrickExo = reg.registerIcon("erebus:templeBrickExo");
		templeBrickCream = reg.registerIcon("erebus:templeBrickCream");
		templeBrickEye = reg.registerIcon("erebus:templeBrickEye");
		templeBrickString = reg.registerIcon("erebus:templeBrickString");
		templeBrickJade1 = reg.registerIcon("erebus:templeBrickJade1");
		templeBrickExo1 = reg.registerIcon("erebus:templeBrickExo1");
		templeBrickCream1 = reg.registerIcon("erebus:templeBrickCream1");
		templeBrickEye1 = reg.registerIcon("erebus:templeBrickEye1");
		templeBrickString1 = reg.registerIcon("erebus:templeBrickString1");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side == 1)
			switch(meta) {
			case 0:
				return blockIcon;
			case 1:
				return templeBrickJade;
			case 2:
				return templeBrickExo;
			case 3:
				return templeBrickCream;
			case 4:
				return templeBrickEye;
			case 5:
				return templeBrickString;
			case 6:
				return templeBrickJade1;
			case 7:
				return templeBrickExo1;
			case 8:
				return templeBrickCream1;
			case 9:
				return templeBrickEye1;
			case 10:
				return templeBrickString1;
			default:
				return blockIcon;
		}
			return blockIcon;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		if (player.isSneaking())
			return false;

		ItemStack stack = player.getCurrentEquippedItem();
		int meta = world.getBlockMetadata(x, y, z);
		if (stack != null) {
			switch (meta) {
			case 0:
				return true;
			case 1:
				if (stack.getItem() == ModItems.materials && stack.getItemDamage() == Materials.DATA.jade.ordinal()) {
					world.setBlockMetadataWithNotify(x, y, z, 6, 3);
					if (!player.capabilities.isCreativeMode)
						stack.stackSize--;
				}
				return true;
			case 2:
				if (stack.getItem() == ModItems.materials &&  stack.getItemDamage() == Materials.DATA.plateExo.ordinal()) {
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
				if (stack.getItem() == ModItems.materials &&  stack.getItemDamage() == Materials.DATA.magmaCrawlerEye.ordinal()) {
					world.setBlockMetadataWithNotify(x, y, z, 9, 3);
					if (!player.capabilities.isCreativeMode)
						stack.stackSize--;
				}
				return true;
			case 5:
				if (stack.getItem() == Items.string) {
					world.setBlockMetadataWithNotify(x, y, z, 10, 3); //meta 10 for forcefield breaking
					if (!player.capabilities.isCreativeMode)
						stack.stackSize--;
				}
				return true;
			}
		}
		return true;
	}
	
}