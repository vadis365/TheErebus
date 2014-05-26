package erebus.block;

import net.minecraft.block.BlockCake;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHoneyTreat extends BlockCake {
	@SideOnly(Side.CLIENT)
	private IIcon honeyTreatTop, honeyTreatBottom, honeyTreatInner;

	public BlockHoneyTreat() {
		super();
		setTickRandomly(true);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? honeyTreatTop : side == 0 ? honeyTreatBottom : meta > 0 && side == 4 ? honeyTreatInner : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		blockIcon = icon.registerIcon(getTextureName() + "_side");
		honeyTreatInner = icon.registerIcon(getTextureName() + "_inner");
		honeyTreatTop = icon.registerIcon(getTextureName() + "_top");
		honeyTreatBottom = icon.registerIcon(getTextureName() + "_bottom");
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
/* broken
	@Override
	public int idPicked(World world, int x, int y, int z) {
		return ModBlocks.honeyTreat.blockID;
	}
	*/
}