package erebus.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.block.bamboo.BlockBambooShoot;
import erebus.block.plants.BlockHangerPlants;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketSound;

public class Materials extends Item {

	public enum DATA {
		plateExo,
		jade,
		shardBone,
		bamboo,
		compoundEyes,
		compoundLens,
		flyWing,
		petrifiedWood,
		bioVelocity,
		elasticFibre,
		waspSting,
		bambooShoot,
		redGem,
		bioLuminescence,
		supernaturalvelocity,
		altarFragment,
		reinforcedPlateExo,
		gliderWing,
		scorpionPincer,
		camoPowder,
		nectar,
		honeyDrip,
		poisonGland,
		mudBrick,
		whetstonePowder,
		dragonflyWing,
		weepingBluePetal,
		papyrus,
		enhancedGliderWing,
		repellent,
		mucusCharge,
		nettleleaves,
		nettleflowers,
		middleFruitSeeds,
		mossBall,
		yellowDottedFungus,
		plateExoRhino,
		rhinoBeetleHorn,
		antPheromones,
		gaeanGem,
		crimsonHeart,
		sapBall,
		templeRock,
		ingotAluminium,
		ingotCopper,
		ingotLead,
		ingotSilver,
		ingotTin,
		gneissRock,
		hideShroom,
		rhinoRidingKit,
		beetleTamingAmulet,
		umberGolemCore,
		umberGolemHead,
		umberGolemClaw,
		umberGolemLegs,
		jadeBerries,
		snapperRoot;
	}

	public static ItemStack createStack(DATA data) {
		return createStack(data, 1);
	}

	public static ItemStack createStack(DATA data, int size) {
		return new ItemStack(ModItems.materials, size, data.ordinal());
	}

	@SideOnly(Side.CLIENT)
	public static IIcon[] icons;

	public Materials() {
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side == 1 && is.getItemDamage() == DATA.bambooShoot.ordinal() && player.canPlayerEdit(x, y, z, side, is) && player.canPlayerEdit(x, y + 1, z, side, is)) {
			Block soil = world.getBlock(x, y, z);

			if (soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, (BlockBambooShoot) ModBlocks.bambooShoot) && world.isAirBlock(x, y + 1, z)) {
				world.setBlock(x, y + 1, z, ModBlocks.bambooShoot);

				if (!player.capabilities.isCreativeMode)
					--is.stackSize;
				return true;
			}
		}

		if (side == 0 && is.getItemDamage() == DATA.middleFruitSeeds.ordinal() && player.canPlayerEdit(x, y, z, side, is) && player.canPlayerEdit(x, y - 1, z, side, is)) {
			Block block = world.getBlock(x, y, z);

			if (block != null && block.getMaterial().blocksMovement()) {
				world.setBlock(x, y - 1, z, ModBlocks.hanger, BlockHangerPlants.dataHanger0, 2);

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

			if (damage == DATA.bioVelocity.ordinal() || damage == DATA.supernaturalvelocity.ordinal()) {
				PotionEffect currentSpeed = player.getActivePotionEffect(Potion.moveSpeed);

				if (currentSpeed == null || damage == DATA.bioVelocity.ordinal() && currentSpeed.getAmplifier() < 1 || damage == DATA.supernaturalvelocity.ordinal() && currentSpeed.getAmplifier() < 3) {
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, damage == DATA.bioVelocity.ordinal() ? 280 : 210, damage == DATA.bioVelocity.ordinal() ? 1 : 3, true));
					PacketPipeline.sendToAll(new PacketSound(PacketSound.SOUND_VELOCITY_USE, player.posX, player.posY, player.posZ, 1.2F, 1F));
				} else
					return is;
			}

			if (damage == DATA.camoPowder.ordinal()) {
				PotionEffect currentVisibility = player.getActivePotionEffect(Potion.invisibility);

				if (currentVisibility == null || damage == DATA.camoPowder.ordinal() && currentVisibility.getAmplifier() < 3) {
					player.addPotionEffect(new PotionEffect(Potion.invisibility.id, damage == DATA.camoPowder.ordinal() ? 280 : 210, damage == DATA.camoPowder.ordinal() ? 1 : 3, true));
					PacketPipeline.sendToAll(new PacketSound(PacketSound.SOUND_CAMO_USE, player.posX, player.posY, player.posZ, 1.2F, 1F));
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
	public void registerIcons(IIconRegister iconRegister) {
		icons = new IIcon[DATA.values().length];
		int i = 0;
		for (DATA d : DATA.values())
			icons[i++] = iconRegister.registerIcon("erebus:" + d.name());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i < DATA.values().length; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return super.getUnlocalizedName() + "." + is.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack is, int pass) {
		return is.getItemDamage() == DATA.whetstonePowder.ordinal();
	}
}