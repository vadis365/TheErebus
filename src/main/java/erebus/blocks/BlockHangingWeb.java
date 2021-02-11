package erebus.blocks;

import java.util.ArrayList;
import java.util.List;

import erebus.ModTabs;
import erebus.core.handler.configs.ConfigHandler;
import net.minecraft.block.BlockVine;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockHangingWeb extends BlockVine implements IShearable {

	public BlockHangingWeb() {
		super();
		setHardness(ConfigHandler.INSTANCE.webHardness);
		setCreativeTab(ModTabs.BLOCKS);
		setSoundType(SoundType.CLOTH);
	}

	@Override
    public Material getMaterial(IBlockState state) {
        return Material.LEAVES; //Using this deprecated method because Shears hardcode blocks -v-
    }

	@Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
        entity.setInWeb();
    }

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

    @Override
    public List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(Items.STRING, 1));
        return ret;
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos) {
        return false;
    }
}