package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ErebusHugeMushroom extends BlockHugeMushroom {

	@SideOnly(Side.CLIENT)
	private IIcon skin, stem, inside;
	private final Block drop;

	public ErebusHugeMushroom(int type, String name, Block drop) { // "type" was left just for backwards compatibility
		super(Material.wood, 0);
		setHardness(0.2F);
		this.drop = drop;
		setStepSound(Block.soundTypeWood);
		setBlockTextureName("erebus:mushcap_" + name);
		setBlockName("erebus.mushroomCap" + type);
	}

	public ErebusHugeMushroom(String name, Block drop) {
		super(Material.wood, 0);
		setHardness(0.2F);
		this.drop = drop;
		setStepSound(Block.soundTypeWood);
		setBlockTextureName("erebus:mushcap_" + name);
		setBlockName("erebus." + name + "_mushroom_block");
	}

	@Override
	protected ItemStack createStackedBlock(int meta) {
		return new ItemStack(this, 1, 14);
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		return 14;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return meta == 10 && side > 1 ? stem : meta >= 1 && meta <= 9 && side == 1 ? skin : meta >= 1 && meta <= 3 && side == 2 ? skin : meta >= 7 && meta <= 9 && side == 3 ? skin : (meta == 1 || meta == 4 || meta == 7) && side == 4 ? skin : (meta == 3 || meta == 6 || meta == 9) && side == 5 ? skin : meta == 14 ? skin : meta == 15 ? stem : inside;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Item.getItemFromBlock(drop);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		skin = reg.registerIcon(getTextureName() + "_skin");
		inside = reg.registerIcon(getTextureName() + "_inside");
		stem = reg.registerIcon(getTextureName() + "_stem");
	}
}