package erebus.block;

import net.minecraft.block.BlockCake;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;

public class BlockHoneyTreat extends BlockCake {
	@SideOnly(Side.CLIENT)
	private Icon honeyTreatTop;
	@SideOnly(Side.CLIENT)
	private Icon honeyTreatBottom;
	@SideOnly(Side.CLIENT)
	private Icon honeyTreatInner;

	public BlockHoneyTreat(int id) {
		super(id);
		this.setTickRandomly(true);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		return side == 1 ? this.honeyTreatTop : (side == 0 ? this.honeyTreatBottom : (meta > 0 && side == 4 ? this.honeyTreatInner : this.blockIcon));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister icon) {
		this.blockIcon = icon.registerIcon(this.getTextureName() + "_side");
		this.honeyTreatInner = icon.registerIcon(this.getTextureName() + "_inner");
		this.honeyTreatTop = icon.registerIcon(this.getTextureName() + "_top");
		this.honeyTreatBottom = icon.registerIcon(this.getTextureName() + "_bottom");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		this.eatTreatSlice(world, x, y, z, player);
		return true;
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		this.eatTreatSlice(world, x, y, z, player);
	}

	private void eatTreatSlice(World world, int x, int y, int z, EntityPlayer player) {
		if (player.canEat(false)) {
			player.getFoodStats().addStats(2, 0.1F);
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 5 * 20, 1));
			int meta = world.getBlockMetadata(x, y, z) + 1;
			if (meta >= 6) {
				world.setBlockToAir(x, y, z);
			} else {
				world.setBlockMetadataWithNotify(x, y, z, meta, 2);
			}
		}
	}

	@Override
	public int idPicked(World world, int x, int y, int z) {
		return ModBlocks.honeyTreat.blockID;
	}
}
