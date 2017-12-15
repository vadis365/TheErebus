package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHomingBeeconAdvanced extends Item {

	public int dimID, targetX, targetZ;

	public ItemHomingBeeconAdvanced() {
		setMaxStackSize(1);
		this.addPropertyOverride(new ResourceLocation("angle"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			double rotation;
			@SideOnly(Side.CLIENT)
			double rota;
			@SideOnly(Side.CLIENT)
			long lastUpdateTick;

			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entityIn) {
				if (entityIn == null && !stack.isOnItemFrame()) {
					return 0.0F;
				} else {
					boolean flag = entityIn != null;
					Entity entity = (Entity) (flag ? entityIn : stack.getItemFrame());

					if (world == null) {
						world = entity.world;
					}

					double d0;

					if (hasTag(stack) && stack.getTagCompound().hasKey("dimID"))
						dimID = stack.getTagCompound().getInteger("dimID");

					if (world.provider.getDimension() == dimID) {
						double d1 = flag ? (double) entity.rotationYaw : this.getFrameRotation((EntityItemFrame) entity);
						d1 = MathHelper.positiveModulo(d1 / 360.0D, 1.0D);
						double d2 = this.getSpawnToAngle(world, entity) / (Math.PI * 2D);
						d0 = 0.5D - (d1 - 0.25D - d2);
					} else {
						d0 = Math.random();
					}

					if (flag) {
						d0 = this.wobble(world, d0);
					}

					return MathHelper.positiveModulo((float) d0, 1.0F);
				}
			}

			@SideOnly(Side.CLIENT)
			private double wobble(World worldIn, double p_185093_2_) {
				if (worldIn.getTotalWorldTime() != this.lastUpdateTick) {
					this.lastUpdateTick = worldIn.getTotalWorldTime();
					double d0 = p_185093_2_ - this.rotation;
					d0 = MathHelper.positiveModulo(d0 + 0.5D, 1.0D) - 0.5D;
					this.rota += d0 * 0.1D;
					this.rota *= 0.8D;
					this.rotation = MathHelper.positiveModulo(this.rotation + this.rota, 1.0D);
				}

				return this.rotation;
			}

			@SideOnly(Side.CLIENT)
			private double getFrameRotation(EntityItemFrame entityItemFrame) {
				return (double) MathHelper.wrapDegrees(180 + entityItemFrame.facingDirection.getHorizontalIndex() * 90);
			}

			@SideOnly(Side.CLIENT)
			private double getSpawnToAngle(World world, Entity entity) {
				if (world != null && entity instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) entity;
					ItemStack stack = player.inventory.getCurrentItem();

					if (!stack.isEmpty() && stack.getItem() == ModItems.HOMING_BEECON_ADVANCED && stack.hasTagCompound() && stack.getTagCompound().hasKey("dimID")) {
						targetX = stack.getTagCompound().getInteger("homeX");
						targetZ = stack.getTagCompound().getInteger("homeZ");
					}
				}
				return Math.atan2((double) targetZ - entity.posZ, (double) targetX - entity.posX);
			}
		});
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		if (hasTag(stack) && stack.getTagCompound().hasKey("dimID")) {
			list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.dimension", stack.getTagCompound().getInteger("dimID") + " " + stack.getTagCompound().getString("dimName")).getFormattedText());
			list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.targetx", stack.getTagCompound().getInteger("homeX")).getFormattedText());
			list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.targety", stack.getTagCompound().getInteger("homeY")).getFormattedText());
			list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.targetz", stack.getTagCompound().getInteger("homeZ")).getFormattedText());
		} else {
			list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.homingbeeconadvanced_1").getFormattedText());
			list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.homingbeeconadvanced_2").getFormattedText());
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (hasTag(stack) && player.isSneaking() && hand.equals(EnumHand.MAIN_HAND)) {
			Block block = world.getBlockState(pos).getBlock();
			if (!world.isRemote && block != null) {
				stack.getTagCompound().setString("dimName", player.getEntityWorld().provider.getDimensionType().getName());
				stack.getTagCompound().setInteger("dimID", player.getEntityWorld().provider.getDimension());
				stack.getTagCompound().setInteger("homeX", pos.getX());
				stack.getTagCompound().setInteger("homeY", pos.getY());
				stack.getTagCompound().setInteger("homeZ", pos.getZ());
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.FAIL;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote && !player.isSneaking() && stack.getTagCompound().hasKey("dimID") && hand.equals(EnumHand.MAIN_HAND)) {
			int x = stack.getTagCompound().getInteger("homeX");
			int y = stack.getTagCompound().getInteger("homeY");
			int z = stack.getTagCompound().getInteger("homeZ");
			BlockPos pos = new BlockPos(x, y, z);
			int dimension = stack.getTagCompound().getInteger("dimID");
			if (player.getEntityWorld().isAirBlock(pos.up(1)) && player.getEntityWorld().isAirBlock(pos.up(2)) && player.dimension == dimension) {
				player.setPositionAndUpdate(x + 0.5D, y + 1D, z + 0.5D);
				player.getEntityWorld().playSound(null, pos, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
			}
		}
		return new ActionResult(EnumActionResult.PASS, stack);
	}

	private boolean hasTag(ItemStack stack) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			return false;
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
}