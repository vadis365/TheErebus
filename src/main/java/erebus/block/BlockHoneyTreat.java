package erebus.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BlockHoneyTreat extends BlockCake {

	public BlockHoneyTreat() {
		setHardness(0.5F);
		setTickRandomly(true);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.honeyTreat");
		setStepSound(Block.soundTypeCloth);
		setBlockTextureName("erebus:honeyTreat");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		eatTreatSlice(world, x, y, z, player);
		return true;
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		eatTreatSlice(world, x, y, z, player);
	}

	private void eatTreatSlice(World world, int x, int y, int z, EntityPlayer player) {
		if (player.canEat(false)) {
			player.getFoodStats().addStats(2, 0.1F);
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 5 * 20, 1));
			int meta = world.getBlockMetadata(x, y, z) + 1;
			if (meta >= 6)
				world.setBlockToAir(x, y, z);
			else
				world.setBlockMetadataWithNotify(x, y, z, meta, 2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(ModBlocks.honeyTreat);
	}
}