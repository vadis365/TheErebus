package erebus.blocks;

import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockOreErebus extends Block {

	public BlockOreErebus(int harvestLevel) {
		super(Material.ROCK);
		setHardness(3.0F);
		setResistance(5.0F);
		setSoundType(SoundType.STONE);
		setCreativeTab(ModTabs.BLOCKS);
		setHarvestLevel("pickaxe", harvestLevel);
	}

	@Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this));
    }
}