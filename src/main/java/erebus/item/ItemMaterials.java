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
	private IIcon[] icons;

	public ItemMaterials() {
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(ModTabs.items);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side == 1 && stack.getItemDamage() == DATA.BAMBOO_SHOOT.ordinal() && player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x, y + 1, z, side, stack)) {
			Block soil = world.getBlock(x, y, z);

			if (soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, (BlockBambooShoot) ModBlocks.bambooShoot) && world.isAirBlock(x, y + 1, z)) {
				world.setBlock(x, y + 1, z, ModBlocks.bambooShoot);

				if (!player.capabilities.isCreativeMode)
					stack.stackSize--;
				return true;
			}
		}

		if (side == 0 && stack.getItemDamage() == DATA.DARK_FRUIT_SEEDS.ordinal() && player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x, y - 1, z, side, stack)) {
			Block block = world.getBlock(x, y, z);

			if (block != null && block.getMaterial().blocksMovement()) {
				world.setBlock(x, y - 1, z, ModBlocks.hanger, BlockHangerPlants.dataHanger0, 2);

				if (!player.capabilities.isCreativeMode)
					stack.stackSize--;
				return true;
			}
		}

		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			int damage = stack.getItemDamage();
			if (damage == DATA.BIO_VELOCITY.ordinal() || damage == DATA.SUPERNATURAL_VELOCITY.ordinal()) {
				PotionEffect currentSpeed = player.getActivePotionEffect(Potion.moveSpeed);
				if (currentSpeed == null || damage == DATA.BIO_VELOCITY.ordinal() && currentSpeed.getAmplifier() < 1 || damage == DATA.SUPERNATURAL_VELOCITY.ordinal() && currentSpeed.getAmplifier() < 3) {
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, damage == DATA.BIO_VELOCITY.ordinal() ? 280 : 210, damage == DATA.BIO_VELOCITY.ordinal() ? 1 : 3, true));
					PacketPipeline.sendToAll(new PacketSound(PacketSound.SOUND_VELOCITY_USE, player.posX, player.posY, player.posZ, 1.2F, 1F));
					if (!player.capabilities.isCreativeMode)
						stack.stackSize--;
				} else
					return stack;
			}
			if (damage == DATA.CAMO_POWDER.ordinal()) {
				PotionEffect currentVisibility = player.getActivePotionEffect(Potion.invisibility);
				if (currentVisibility == null || damage == DATA.CAMO_POWDER.ordinal() && currentVisibility.getAmplifier() < 1) {
					player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 280, 1, true));
					PacketPipeline.sendToAll(new PacketSound(PacketSound.SOUND_CAMO_USE, player.posX, player.posY, player.posZ, 1.2F, 1F));
					if (!player.capabilities.isCreativeMode)
						stack.stackSize--;
				} else
					return stack;
			} else
				return stack;
		}
		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		icons = new IIcon[DATA.values().length];
		for (DATA data : DATA.values())
			icons[data.ordinal()] = reg.registerIcon("erebus:" + data.name().toLowerCase());
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
	public void getSubItems(Item stack, CreativeTabs tab, List list) {
		for (DATA data : DATA.values())
			list.add(new ItemStack(stack, 1, data.ordinal()));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getItemDamage();
		meta = Math.min(Math.max(meta, 0), DATA.values().length - 1);
		return super.getUnlocalizedName() + "." + DATA.values()[meta].name().toLowerCase();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack, int pass) {
		return stack.getItemDamage() == DATA.WHETSTONE_POWDER.ordinal();
	}

	public enum DATA {
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
		BAMBOO_SHOOT,
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
		WEEPING_BLUE_PETAL,
		PAPYRUS,
		ENHANCED_GLIDER_WING,
		REPELLENT,
		MUCUS_CHARGE,
		NETTLE_LEAVES,
		NETTLE_FLOWERS,
		DARK_FRUIT_SEEDS,
		MOSS_BALL,
		YELLOW_DOTTED_FUNGUS,
		PLATE_EXO_RHINO,
		RHINO_BEETLE_HORN,
		ANT_PHEROMONES,
		GAEAN_GEM,
		CRIMSON_HEART,
		SAP_BALL,
		AMBER_STAR,
		INGOT_ALUMINIUM,
		INGOT_COPPER,
		INGOT_LEAD,
		INGOT_SILVER,
		INGOT_TIN,
		GNEISS_ROCK,
		HIDE_SHROOM,
		RHINO_RIDING_KIT,
		BEETLE_TAMING_AMULET,
		UMBERGOLEM_CORE,
		UMBERGOLEM_HEAD,
		UMBERGOLEM_CLAW,
		UMBERGOLEM_LEGS,
		JADE_BERRIES,
		SNAPPER_ROOT,
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

		public ItemStack makeStack() {
			return makeStack(1);
		}

		public ItemStack makeStack(int size) {
			return new ItemStack(ModItems.materials, size, ordinal());
		}
	}
}