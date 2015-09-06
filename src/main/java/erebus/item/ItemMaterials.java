package erebus.item;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.block.bamboo.BlockBambooShoot;
import erebus.block.plants.BlockHangerPlants;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketSound;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemMaterials extends Item {

	public ItemMaterials() {
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(ModTabs.items);
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
		BlockPos top = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
		BlockPos bottom = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
		if (side.getIndex() == 1 && is.getItemDamage() == DATA.bambooShoot.ordinal() && player.canPlayerEdit(pos, side, is) && player.canPlayerEdit(top, side, is)) {
			Block soil = world.getBlockState(pos).getBlock();

			if (soil != null && soil.canSustainPlant(world, pos, EnumFacing.UP, (BlockBambooShoot) ModBlocks.bambooShoot) && world.isAirBlock(top)) {
				world.setBlockState(top, ModBlocks.bambooShoot.getDefaultState());

				if (!player.capabilities.isCreativeMode)
					--is.stackSize;
				return true;
			}
		}

		if (side.getIndex() == 0 && is.getItemDamage() == DATA.darkFruitSeeds.ordinal() && player.canPlayerEdit(pos, side, is) && player.canPlayerEdit(bottom, side, is)) {
			Block block = world.getBlockState(pos).getBlock();

			if (block != null && block.getMaterial().blocksMovement()) {
				FMLLog.info("Placed a hanger");
				world.setBlockState(bottom, ModBlocks.hanger.getStateFromMeta(BlockHangerPlants.dataHanger0), 2);

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
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, damage == DATA.bioVelocity.ordinal() ? 280 : 210, damage == DATA.bioVelocity.ordinal() ? 1 : 3, true, false));
					PacketPipeline.sendToAll(new PacketSound(PacketSound.SOUND_VELOCITY_USE, player.posX, player.posY, player.posZ, 1.2F, 1F));
				} else
					return is;
			}

			if (damage == DATA.camoPowder.ordinal()) {
				PotionEffect currentVisibility = player.getActivePotionEffect(Potion.invisibility);

				if (currentVisibility == null || damage == DATA.camoPowder.ordinal() && currentVisibility.getAmplifier() < 3) {
					player.addPotionEffect(new PotionEffect(Potion.invisibility.id, damage == DATA.camoPowder.ordinal() ? 280 : 210, damage == DATA.camoPowder.ordinal() ? 1 : 3, true, false));
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (DATA d : DATA.values())
			if (d.isActive())
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
	public boolean hasEffect(ItemStack is) {
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
		UNUSED, // TODO Replace me!!!
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

		public ItemStack createStack() {
			return createStack(1);
		}

		public ItemStack createStack(int size) {
			return new ItemStack(ModItems.materials, size, ordinal());
		}

		public boolean isActive() {
			return this != UNUSED;
		}
	}
}