package erebus.blocks;

import erebus.Erebus;
import erebus.ModTabs;
import erebus.proxy.CommonProxy;
import erebus.tileentity.TileEntityPetrifiedWoodChest;
import net.minecraft.block.BlockChest;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class BlockPetrifiedChest extends BlockChest {
	public static BlockChest.Type PETRIFIED_CHEST = EnumHelper.addEnum(BlockChest.Type.class, "PETRIFIED_CHEST", new Class[0], new Object[0]);

	public BlockPetrifiedChest(Type chestTypeIn) {
		super(chestTypeIn);
		this.setCreativeTab(ModTabs.BLOCKS);
		this.setHardness(2.0F);
		this.setSoundType(SoundType.WOOD);
		this.setHarvestLevel("axe", 0);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPetrifiedWoodChest(PETRIFIED_CHEST);
	}
	
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote)
            return true;
        else {
            ILockableContainer ilockablecontainer = this.getLockableContainer(world, pos);

            if (ilockablecontainer != null) {
            	player.openGui(Erebus.INSTANCE, CommonProxy.GuiID.PETRIFIED_CHEST.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
                if (this.chestType == PETRIFIED_CHEST)
                    player.addStat(StatList.CHEST_OPENED);
            }
            return true;
        }
    }
}
