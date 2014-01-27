package erebus.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.block.BlockBambooShoot;
import erebus.network.PacketHandler;
import erebus.network.packet.PacketSound;

public class ItemErebusMaterial extends Item {

	public static final String[] iconPaths = new String[] { "plateExo", "jade", "shardBone", "bamboo", "compoundEyes", "compoundLens", "flyWing", "itemPetrifiedWood", "biovelocity", "elasticFibre", "waspSting", "bambooShoot", "redGem", "bioluminescence", "supernaturalvelocity", "altarFragment",
	"reinforcedPlateExo", "gliderWing", "scorpionPincer", "camoPowder" };

	public static final short dataExoPlate = 0, dataJade = 1, dataBoneShard = 2, dataBamboo = 3, dataCompoundEyes = 4, dataCompoundLens = 5, dataFlyWing = 6, dataPetrifiedWood = 7, dataBioVelocity = 8, dataElasticFibre = 9, dataWaspSting = 10, dataBambooShoot = 11, dataRedGem = 12,
	dataBioluminescence = 13, dataSupernaturalVelocity = 14, dataAltarFragment = 15, dataReinforcedPlateExo = 16, dataGliderWing = 17, dataScorpPincer = 18, dataCamoPowder = 19;

	@SideOnly(Side.CLIENT)
	public static Icon[] icons;

	public ItemErebusMaterial(int id) {
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side == 1 && is.getItemDamage() == dataBambooShoot && player.canPlayerEdit(x, y, z, side, is) && player.canPlayerEdit(x, y + 1, z, side, is)) {
			Block soil = Block.blocksList[world.getBlockId(x, y, z)];

			if (soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, (BlockBambooShoot) ModBlocks.bambooShoot) && world.isAirBlock(x, y + 1, z)) {
				world.setBlock(x, y + 1, z, ModBlocks.bambooShoot.blockID);

				if (!player.capabilities.isCreativeMode)
					--is.stackSize;
				return true;
			}
		}

		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		if (!world.isRemote) {
			int damage = is.getItemDamage();

			if (damage == dataBioVelocity || damage == dataSupernaturalVelocity) {
				PotionEffect currentSpeed = player.getActivePotionEffect(Potion.moveSpeed);

				if (currentSpeed == null || damage == dataBioVelocity && currentSpeed.getAmplifier() < 1 || damage == dataSupernaturalVelocity && currentSpeed.getAmplifier() < 3) {
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, damage == dataBioVelocity ? 280 : 210, damage == dataBioVelocity ? 1 : 3, true));
					PacketDispatcher.sendPacketToAllAround(player.posX, player.posY, player.posZ, 32D, player.dimension, PacketHandler.buildPacket(3, PacketSound.SOUND_VELOCITY_USE, player.posX, player.posY, player.posZ, 1.2F, 1F));
				} else
					return is;
			}

			if (damage == dataCamoPowder) {
				PotionEffect currentVisibility = player.getActivePotionEffect(Potion.invisibility);

				if (currentVisibility == null || damage == dataCamoPowder && currentVisibility.getAmplifier() < 3) {
					player.addPotionEffect(new PotionEffect(Potion.invisibility.id, damage == dataCamoPowder ? 280 : 210, damage == dataCamoPowder ? 1 : 3, true));
					PacketDispatcher.sendPacketToAllAround(player.posX, player.posY, player.posZ, 32D, player.dimension, PacketHandler.buildPacket(3, PacketSound.SOUND_CAMO_USE, player.posX, player.posY, player.posZ, 1.2F, 1F));
				} else
					return is;
			} else
				return is;

			if (!player.capabilities.isCreativeMode)
				--is.stackSize;
		}

		return is;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[iconPaths.length];
		int i = 0;
		for (String path : iconPaths)
			icons[i++] = iconRegister.registerIcon("erebus:" + path);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (int a = 0; a < iconPaths.length; a++)
			list.add(new ItemStack(id, 1, a));
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		int i = is.getItemDamage();
		return super.getUnlocalizedName() + "." + i;
	}
}
