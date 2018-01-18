package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import erebus.Erebus;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModSounds;
import erebus.ModTabs;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWarHammer extends ItemSword {

	public ItemWarHammer() {
		super(ModMaterials.WEAPON_WAR_HAMMER);
		setMaxStackSize(1);
		setCreativeTab(ModTabs.GEAR);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.warhammer_1").getFormattedText());
		list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.warhammer_2").getFormattedText());
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.MATERIALS && material.getItemDamage() == EnumErebusMaterialsType.REINFORCED_PLATE_EXO.ordinal();
	}

	@Override
	public boolean hitEntity(ItemStack is, EntityLivingBase entity, EntityLivingBase player) {
		is.damageItem(1, player);
		return true;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (hasTag(stack))
			if (!stack.getTagCompound().hasKey("charge"))
				stack.getTagCompound().setInteger("charge", 0);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack is) {
		return 1000;
	}

	@Override
	 public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (stack.getTagCompound().getInteger("charge") < 25)
			stack.getTagCompound().setInteger("charge", stack.getTagCompound().getInteger("charge") + 1);
		if (stack.getTagCompound().getInteger("charge") >= 25)
			stack.getTagCompound().setInteger("charge", 25);
		return new ActionResult(EnumActionResult.PASS, stack);
	}

	@Override
	 public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (hasTag(stack))
			if (facing == EnumFacing.UP && player.isSneaking()) {
			if (stack.getTagCompound().getInteger("charge") > 0) {
				Erebus.NETWORK_WRAPPER.sendToAll(new PacketParticle(ParticleType.HAMMER_BLAM, (float) player.posX, (float)player.posY, (float)player.posZ));
				world.playSound(null, pos, ModSounds.BLAM_SOUND, SoundCategory.PLAYERS, 1.0F, 1.0F);
			}
			areaOfEffect(world, stack, player);
			stack.getTagCompound().setInteger("charge", 0);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}

	protected Entity areaOfEffect(World world, ItemStack stack, EntityPlayer player) {
		List<?> list = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.getEntityBoundingBox().minX, player.getEntityBoundingBox().minY, player.getEntityBoundingBox().minZ, player.getEntityBoundingBox().maxX, player.getEntityBoundingBox().maxY, player.getEntityBoundingBox().maxZ).grow(stack.getTagCompound().getInteger("charge") * 0.25D, 1D, stack.getTagCompound().getInteger("charge") * 0.25D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = (Entity) list.get(i);
			if (entity != null)
				if (entity instanceof EntityLivingBase && entity != player) {
					float Knockback = (float) (stack.getTagCompound().getInteger("charge") * 0.025D);
					entity.attackEntityFrom(DamageSource.causeMobDamage(player), stack.getTagCompound().getInteger("charge") * 0.25F);
					entity.addVelocity(-MathHelper.sin(player.rotationYaw * -3.141593F + world.rand.nextInt(3) + 0.141593F / 180.0F) * Knockback, 0.01D, MathHelper.cos(player.rotationYaw * -3.141593F + world.rand.nextInt(3) + 0.141593F / 180.0F) * Knockback);
				}
		}
		return null;
	}

	private boolean hasTag(ItemStack stack) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			return false;
		}
		return true;
	}
}
