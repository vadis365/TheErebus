package erebus.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModItems.ISubItemsItem;
import erebus.ModTabs;
import erebus.api.IErebusEnum;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMaterials extends Item implements ISubItemsItem {

	public ItemMaterials() {
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(ModTabs.ITEMS);
	}

	@Override
	 public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		BlockPos bottom = pos.down();
		IBlockState state = world.getBlockState(pos);
		ItemStack stack = player.getHeldItem(hand);
		if (facing.getIndex() == 0 && stack.getItemDamage() == EnumErebusMaterialsType.DARK_FRUIT_SEEDS.ordinal() && player.canPlayerEdit(pos, facing, stack) && player.canPlayerEdit(bottom, facing, stack)) {
			Block block = state.getBlock();
			if (block != null && state.getMaterial().blocksMovement()) {
				world.setBlockState(bottom, ModBlocks.DARK_FRUIT_VINE.getDefaultState(), 2);
				if (!player.capabilities.isCreativeMode)
					stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.FAIL;
	}

	@Override
	 public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote) {
			int damage = stack.getItemDamage();

			if (damage == EnumErebusMaterialsType.BIO_VELOCITY.ordinal() || damage == EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.ordinal()) {
				PotionEffect currentSpeed = player.getActivePotionEffect(MobEffects.SPEED);

				if (currentSpeed == null || damage == EnumErebusMaterialsType.BIO_VELOCITY.ordinal() && currentSpeed.getAmplifier() < 1 || damage == EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.ordinal() && currentSpeed.getAmplifier() < 3) {
					player.addPotionEffect(new PotionEffect(MobEffects.SPEED, damage == EnumErebusMaterialsType.BIO_VELOCITY.ordinal() ? 280 : 210, damage == EnumErebusMaterialsType.BIO_VELOCITY.ordinal() ? 1 : 3, true, false));
					//PacketPipeline.sendToAll(new PacketSound(PacketSound.SOUND_VELOCITY_USE, player.posX, player.posY, player.posZ, 1.2F, 1F));
					if (!player.capabilities.isCreativeMode)
						stack.shrink(1);
				} else
					return new ActionResult(EnumActionResult.PASS, stack);
			}

			if (damage == EnumErebusMaterialsType.CAMO_POWDER.ordinal()) {
				PotionEffect currentVisibility = player.getActivePotionEffect(MobEffects.INVISIBILITY);

				if (currentVisibility == null || damage == EnumErebusMaterialsType.CAMO_POWDER.ordinal() && currentVisibility.getAmplifier() < 3) {
					player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, damage == EnumErebusMaterialsType.CAMO_POWDER.ordinal() ? 280 : 210, damage == EnumErebusMaterialsType.CAMO_POWDER.ordinal() ? 1 : 3, true, false));
				//	PacketPipeline.sendToAll(new PacketSound(PacketSound.SOUND_CAMO_USE, player.posX, player.posY, player.posZ, 1.2F, 1F));
					if (!player.capabilities.isCreativeMode)
						stack.shrink(1);
				} else
					return new ActionResult(EnumActionResult.PASS, stack);
			} else
				return new ActionResult(EnumActionResult.PASS, stack);
		}

		return new ActionResult(EnumActionResult.PASS, stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
		for (EnumErebusMaterialsType type : EnumErebusMaterialsType.values())
			list.add(type.createStack(1));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getItemDamage();
		meta = Math.min(Math.max(meta, 0), EnumErebusMaterialsType.values().length - 1);
		return super.getUnlocalizedName() + "." + EnumErebusMaterialsType.values()[meta].name().toLowerCase();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return stack.getItemDamage() == EnumErebusMaterialsType.WHETSTONE_POWDER.ordinal();
	}

	@Override
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		for (EnumErebusMaterialsType type : EnumErebusMaterialsType.values())
			models.add(type.getName());
		return models;
	}

	public enum EnumErebusMaterialsType implements IErebusEnum {

		PLATE_EXO,
		JADE,
		SHARD_BONE,
		BAMBOO,
		COMPOUND_EYES,
		COMPOUND_LENS,
		FLY_WING,
		PETRIFIED_WOOD,
		BIO_VELOCITY,
		ELASTIC_FIBRE,
		WASP_STING,
		RED_GEM,
		BIO_LUMINESCENCE,
		SUPERNATURAL_VELOCITY,
		ALTAR_FRAGMENT,
		REINFORCED_PLATE_EXO,
		GLIDER_WING,
		SCORPION_PINCER,
		CAMO_POWDER,
		NECTAR,
		HONEY_DRIP,
		POISON_GLAND,
		MUD_BRICK,
		WHETSTONE_POWDER,
		DRAGONFLY_WING,
		BLUEBELL_PETAL,
		PAPYRUS,
		ENHANCED_GLIDER_WING,
		REPELLENT,
		MUCUS_CHARGE,
		NETTLE_LEAVES,
		NETTLE_FLOWERS,
		DARK_FRUIT_SEEDS,
		MOSS_BALL,
		GLOWSHROOM,
		PLATE_EXO_RHINO,
		RHINO_BEETLE_HORN,
		ANT_PHEROMONES,
		GAEAN_GEM,
		CRIMSON_HEART,
		RESIN,
		AMBER_STAR,
		INGOT_ALUMINIUM,
		INGOT_COPPER,
		INGOT_LEAD,
		INGOT_SILVER,
		INGOT_TIN,
		GNEISS_ROCK,
		HIDE_SHROOM,
		BEETLE_RIDING_KIT,
		BEETLE_TAMING_AMULET,
		UMBERGOLEM_CORE,
		UMBERGOLEM_HEAD,
		UMBERGOLEM_CLAW,
		UMBERGOLEM_LEGS,
		JADE_BERRIES,
		BOGMAW_ROOT,
		HYDROFUGE,
		WATER_REPELLENT,
		SMOOTHIE_GLASS,
		MAGMA_CRAWLER_EYE,
		STEW_POT,
		TITAN_STEW,
		FORCE_KEY,
		SOUL_CRYSTAL,
		PLATE_ZOMBIE_ANT,
		STAG_BEETLE_MANDIBLES,
		TERPSISHROOM;

		@Override
		public ItemStack createStack(int size) {
			return new ItemStack(ModItems.MATERIALS, size, ordinal());
		}

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}