package erebus.block;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityWoodlouse;
import erebus.item.ItemMaterials;
import erebus.lib.EnumWood;
import erebus.lib.Reference;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BlockLogErebus extends BlockLog {

	@SideOnly(Side.CLIENT)
	private IIcon iconSide, iconTop;

	private final EnumWood wood;

	public BlockLogErebus(EnumWood wood) {
		this.wood = wood;
		setCreativeTab(ModTabs.blocks);
		setBlockName(Reference.MOD_ID + ".log" + wood.name());
	}

	@Override
	public String getLocalizedName() {
		return StatCollector.translateToLocal("tile." + Reference.MOD_ID + ".log_" + wood.getUnlocalisedName() + ".name");
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		if (wood == EnumWood.Sap) {
			ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
			drops.add(new ItemStack(ModBlocks.saplessLog));
			for (int i = 0; i < 1 + world.rand.nextInt(2 + fortune); i++)
				drops.add(new ItemStack(ModItems.materials, 1, ItemMaterials.DATA.sapBall.ordinal()));
			return drops;
		}
		return super.getDrops(world, x, y, z, metadata, fortune);
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		return wood == EnumWood.Sap ? true : super.canSilkHarvest(world, player, x, y, z, metadata);
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int meta) {
		return iconSide;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getTopIcon(int meta) {
		return iconTop;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		iconSide = iconRegister.registerIcon("erebus:log_" + wood.name().toLowerCase());
		iconTop = iconRegister.registerIcon("erebus:log_" + wood.name().toLowerCase() + "_top");
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		if (!world.isRemote && wood.name() == "Rotten")
			if (world.rand.nextInt(30) == 0) {
				EntityWoodlouse entity = new EntityWoodlouse(world);
				entity.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
				world.spawnEntityInWorld(entity);
			} else if (world.rand.nextInt(30) == 0) {
				EntityBeetleLarva entity = new EntityBeetleLarva(world);
				entity.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
				world.spawnEntityInWorld(entity);
			} else if (world.rand.nextInt(30) == 0) {
				EntitySilverfish entity = new EntitySilverfish(world);
				entity.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
				world.spawnEntityInWorld(entity);
			}
		super.onBlockDestroyedByPlayer(world, x, y, z, meta);
	}

	@Override
	public int damageDropped(int meta) {
		return 0;
	}
}