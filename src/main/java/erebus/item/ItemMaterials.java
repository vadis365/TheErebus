package erebus.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.block.bamboo.BlockBambooShoot;
import erebus.block.plants.BlockHangerPlants;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketSound;
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

public class ItemMaterials extends Item {

	@SideOnly(Side.CLIENT)
	public static IIcon[] icons;

	public ItemMaterials() {
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(ModTabs.items);
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

		if (side == 0 && is.getItemDamage() == DATA.darkFruitSeeds.ordinal() && player.canPlayerEdit(x, y, z, side, is) && player.canPlayerEdit(x, y - 1, z, side, is)) {
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
					if (!player.capabilities.isCreativeMode)
						--is.stackSize;
				} else
					return is;
			}
			if (damage == DATA.camoPowder.ordinal()) {
				PotionEffect currentVisibility = player.getActivePotionEffect(Potion.invisibility);
				if (currentVisibility == null || damage == DATA.camoPowder.ordinal() && currentVisibility.getAmplifier() < 1) {
					player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 280, 1, true));
					PacketPipeline.sendToAll(new PacketSound(PacketSound.SOUND_CAMO_USE, player.posX, player.posY, player.posZ, 1.2F, 1F));
					if (!player.capabilities.isCreativeMode)
						--is.stackSize;
				} else
					return is;
			} else
				return is;
		}
		return is;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		icons = new IIcon[DATA.values().length];
		for (DATA d : DATA.values())
			icons[d.ordinal()] = iconRegister.registerIcon("erebus:" + d.name());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		if (meta < 0 || meta >= icons.length)
			return null;
		return icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (DATA d : DATA.values())
			list.add(new ItemStack(item, 1, d.ordinal()));
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		int meta = is.getItemDamage();
		meta = Math.min(Math.max(meta, 0), DATA.values().length - 1);
		return super.getUnlocalizedName() + "." + DATA.values()[meta].name();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack is, int pass) {
		return is.getItemDamage() == DATA.whetstonePowder.ordinal();
	}

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
		darkFruitSeeds,
		mossBall,
		yellowDottedFungus,
		plateExoRhino,
		rhinoBeetleHorn,
		antPheromones,
		gaeanGem,
		crimsonHeart,
		sapBall,
		amberStar,
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
		snapperRoot,
		hydrofuge,
		waterRepellent,
		smoothieGlass,
		magmaCrawlerEye,
		stewPot,
		titanStew;

		public ItemStack makeStack() {
			return makeStack(1);
		}

		public ItemStack makeStack(int size) {
			return new ItemStack(ModItems.materials, size, ordinal());
		}
	}
}