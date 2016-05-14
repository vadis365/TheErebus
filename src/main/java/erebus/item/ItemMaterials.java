package erebus.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModTabs;

public class ItemMaterials extends Item {

	public ItemMaterials() {
		setMaxDamage(0);
		setRegistryName("materials");
		setUnlocalizedName(getRegistryName().toString());
		setHasSubtypes(true);
		setCreativeTab(ModTabs.items);
	}

	@Override
	 public EnumActionResult onItemUse(ItemStack is, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
	// TODO Blocks for this to work
	/*	BlockPos top = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
		BlockPos bottom = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
		if (facing.getIndex() == 1 && is.getItemDamage() == ITEM_DATA.bambooShoot.ordinal() && player.canPlayerEdit(pos, facing, is) && player.canPlayerEdit(top, facing, is)) {
			Block soil = world.getBlockState(pos).getBlock();

			if (soil != null && soil.canSustainPlant(soil.getDefaultState(), world, pos, EnumFacing.UP, (BlockBambooShoot) ModBlocks.bambooShoot) && world.isAirBlock(top)) {
				world.setBlockState(top, ModBlocks.bambooShoot.getDefaultState());

				if (!player.capabilities.isCreativeMode)
					--is.stackSize;
				return EnumActionResult.SUCCESS;
			}
		}

		if (facing.getIndex() == 0 && is.getItemDamage() == ITEM_DATA.darkFruitSeeds.ordinal() && player.canPlayerEdit(pos, facing, is) && player.canPlayerEdit(bottom, facing, is)) {
			Block block = world.getBlockState(pos).getBlock();

			if (block != null && block.getMaterial((IBlockState) pos).blocksMovement()) {
				FMLLog.info("Placed a hanger");
				world.setBlockState(bottom, ModBlocks.hanger.getStateFromMeta(BlockHangerPlants.ITEM_DATAHanger0), 2);

				if (!player.capabilities.isCreativeMode)
					--is.stackSize;
				return EnumActionResult.SUCCESS;
			}
		}
*/
		return EnumActionResult.FAIL;
	}

	@Override
	 public ActionResult<ItemStack> onItemRightClick(ItemStack is, World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			int damage = is.getItemDamage();

			if (damage == ITEM_DATA.bioVelocity.ordinal() || damage == ITEM_DATA.supernaturalvelocity.ordinal()) {
				PotionEffect currentSpeed = player.getActivePotionEffect(MobEffects.moveSpeed);

				if (currentSpeed == null || damage == ITEM_DATA.bioVelocity.ordinal() && currentSpeed.getAmplifier() < 1 || damage == ITEM_DATA.supernaturalvelocity.ordinal() && currentSpeed.getAmplifier() < 3) {
					player.addPotionEffect(new PotionEffect(MobEffects.moveSpeed, damage == ITEM_DATA.bioVelocity.ordinal() ? 280 : 210, damage == ITEM_DATA.bioVelocity.ordinal() ? 1 : 3, true, false));
					//PacketPipeline.sendToAll(new PacketSound(PacketSound.SOUND_VELOCITY_USE, player.posX, player.posY, player.posZ, 1.2F, 1F));
				} else
					return new ActionResult(EnumActionResult.PASS, is);
			}

			if (damage == ITEM_DATA.camoPowder.ordinal()) {
				PotionEffect currentVisibility = player.getActivePotionEffect(MobEffects.invisibility);

				if (currentVisibility == null || damage == ITEM_DATA.camoPowder.ordinal() && currentVisibility.getAmplifier() < 3) {
					player.addPotionEffect(new PotionEffect(MobEffects.invisibility, damage == ITEM_DATA.camoPowder.ordinal() ? 280 : 210, damage == ITEM_DATA.camoPowder.ordinal() ? 1 : 3, true, false));
				//	PacketPipeline.sendToAll(new PacketSound(PacketSound.SOUND_CAMO_USE, player.posX, player.posY, player.posZ, 1.2F, 1F));
				} else
					return new ActionResult(EnumActionResult.PASS, is);
			} else
				return new ActionResult(EnumActionResult.PASS, is);

			if (!player.capabilities.isCreativeMode)
				--is.stackSize;
		}

		return new ActionResult(EnumActionResult.PASS, is);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (ITEM_DATA d : ITEM_DATA.values())
			list.add(new ItemStack(item, 1, d.ordinal()));
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		int meta = is.getItemDamage();
		meta = Math.min(Math.max(meta, 0), ITEM_DATA.values().length - 1);
		return super.getUnlocalizedName() + "." + ITEM_DATA.values()[meta].name();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack is) {
		return is.getItemDamage() == ITEM_DATA.whetstonePowder.ordinal();
	}

	public enum ITEM_DATA {
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

	}
}