package erebus.block.ores;

import java.util.Random;

import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;

public class UmberOre extends Block {

	private final Block base;

	public UmberOre(Block base, String name, int harvestLevel) {
		super(Material.rock);
		this.base = base;
		setCreativeTab(ModTabs.blocks);
		setHardness(3.0F);
		setResistance(5.0F);
		setBlockName("erebus.ore" + name);
		setStepSound(Block.soundTypeStone);
		setHarvestLevel("pickaxe", harvestLevel);
		setBlockTextureName("erebus:umberOre" + name);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		Item item = base.getItemDropped(meta, rand, fortune);

		if (item == Item.getItemFromBlock(base))
			return Item.getItemFromBlock(this);
		else
			return item;
	}

	@Override
	public int quantityDropped(Random rand) {
		return base.quantityDropped(rand);
	}

	@Override
	public int quantityDroppedWithBonus(int meta, Random rand) {
		return base.quantityDroppedWithBonus(meta, rand);
	}

	@Override
	public int getExpDrop(IBlockAccess world, int meta, int fortune) {
		return base.getExpDrop(world, meta, fortune);
	}

	@Override
	public int damageDropped(int meta) {
		return base.damageDropped(meta);
	}
}