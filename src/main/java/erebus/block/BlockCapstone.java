package erebus.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.entity.effect.EntityErebusLightningBolt;
import erebus.item.ItemDungeonIdols.IDOL;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCapstone extends Block {
	private IIcon capstoneMud, capstoneMud1, capstoneIron, capstoneIron1, capstoneGold, capstoneGold1, capstoneJade, capstoneJade1;

	public BlockCapstone() {
		super(Material.rock);
		setStepSound(Block.soundTypeStone);
		setBlockUnbreakable();
		setResistance(6000000.0F);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.capstone");
		setBlockTextureName("erebus:capstone");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("erebus:capstone");
		capstoneMud = reg.registerIcon("erebus:capstoneMud");
		capstoneIron = reg.registerIcon("erebus:capstoneIron");
		capstoneGold = reg.registerIcon("erebus:capstoneGold");
		capstoneJade = reg.registerIcon("erebus:capstoneJade");
		capstoneMud1 = reg.registerIcon("erebus:capstoneMud1");
		capstoneIron1 = reg.registerIcon("erebus:capstoneIron1");
		capstoneGold1 = reg.registerIcon("erebus:capstoneGold1");
		capstoneJade1 = reg.registerIcon("erebus:capstoneJade1");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side == 1)
			switch (meta) {
				case 0:
					return blockIcon;
				case 1:
					return capstoneMud;
				case 2:
					return capstoneIron;
				case 3:
					return capstoneGold;
				case 4:
					return capstoneJade;
				case 5:
					return capstoneMud1;
				case 6:
					return capstoneIron1;
				case 7:
					return capstoneGold1;
				case 8:
					return capstoneJade1;
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
					if (stack.getItem() == ModItems.idols && stack.getItemDamage() == IDOL.Mud.ordinal()) {
						world.setBlockMetadataWithNotify(x, y, z, 5, 3);
						if (!player.capabilities.isCreativeMode)
							stack.stackSize--;
					}
					return true;
				case 2:
					if (stack.getItem() == ModItems.idols && stack.getItemDamage() == IDOL.Iron.ordinal()) {
						world.setBlockMetadataWithNotify(x, y, z, 6, 3);
						if (!player.capabilities.isCreativeMode)
							stack.stackSize--;
					}
					return true;
				case 3:
					if (stack.getItem() == ModItems.idols && stack.getItemDamage() == IDOL.Gold.ordinal()) {
						world.setBlockMetadataWithNotify(x, y, z, 7, 3);
						if (!player.capabilities.isCreativeMode)
							stack.stackSize--;
					}
					return true;
				case 4:
					if (stack.getItem() == ModItems.idols && stack.getItemDamage() == IDOL.Jade.ordinal()) {
						world.setBlockMetadataWithNotify(x, y, z, 8, 3);
						if (!player.capabilities.isCreativeMode)
							stack.stackSize--;
					}
					return true;
			}
		}
		return true;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 5)
			if (world.getBlockMetadata(x + 1, y, z) == 6)
				if (world.getBlockMetadata(x, y, z + 1) == 7)
					if (world.getBlockMetadata(x + 1, y, z + 1) == 8)
						openPyramid(world, x, y, z, 1, 1);
		if (meta == 6)
			if (world.getBlockMetadata(x - 1, y, z) == 5)
				if (world.getBlockMetadata(x, y, z + 1) == 8)
					if (world.getBlockMetadata(x - 1, y, z + 1) == 7)
						openPyramid(world, x, y, z, -1, 1);

		if (meta == 7)
			if (world.getBlockMetadata(x + 1, y, z) == 8)
				if (world.getBlockMetadata(x, y, z - 1) == 5)
					if (world.getBlockMetadata(x + 1, y, z - 1) == 6)
						openPyramid(world, x, y, z, 1, -1);

		if (meta == 8)
			if (world.getBlockMetadata(x - 1, y, z) == 7)
				if (world.getBlockMetadata(x, y, z - 1) == 6)
					if (world.getBlockMetadata(x - 1, y, z - 1) == 5)
						openPyramid(world, x, y, z, -1, -1);
	}

	private void openPyramid(World world, int x, int y, int z, int offsetX, int offsetZ) {
		EntityErebusLightningBolt entitybolt = new EntityErebusLightningBolt(world, 0D, 0D, 0D);
		entitybolt.setLocationAndAngles(x + offsetX, y, z + offsetZ, 0F, 0F);
		world.addWeatherEffect(entitybolt);
		world.playAuxSFXAtEntity(null, 2001, x, y, z, Block.getIdFromBlock(world.getBlock(x, y, z)));
		world.setBlockToAir(x, y, z);
		world.playAuxSFXAtEntity(null, 2001, x + offsetX, y, z, Block.getIdFromBlock(world.getBlock(x + offsetX, y, z)));
		world.setBlockToAir(x + offsetX, y, z);
		world.playAuxSFXAtEntity(null, 2001, x, y, z + offsetZ, Block.getIdFromBlock(world.getBlock(x, y, z + offsetZ)));
		world.setBlockToAir(x, y, z + offsetZ);
		world.playAuxSFXAtEntity(null, 2001, x + offsetX, y, z + offsetZ, Block.getIdFromBlock(world.getBlock(x + offsetX, y, z + offsetZ)));
		world.setBlockToAir(x + offsetX, y, z + offsetZ);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return null;
	}
}