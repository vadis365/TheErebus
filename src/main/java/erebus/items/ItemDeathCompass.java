package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDeathCompass extends Item {

	public int dimID, targetX, targetZ;

	public ItemDeathCompass() {
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
			private double getFrameRotation(EntityItemFrame p_185094_1_) {
				return (double) MathHelper.wrapDegrees(180 + p_185094_1_.facingDirection.getHorizontalIndex() * 90);
			}

			@SideOnly(Side.CLIENT)
			private double getSpawnToAngle(World world, Entity entity) {
				if (world != null && entity instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer)entity;
					ItemStack stack = player.inventory.getCurrentItem();
					
					if (!stack.isEmpty() && stack.getItem() == ModItems.DEATH_COMPASS && stack.hasTagCompound() && stack.getTagCompound().hasKey("dimID")) {
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
			list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.targetz", stack.getTagCompound().getInteger("homeZ")).getFormattedText());
		} else
			list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.deathcompass").getFormattedText());
	}
	
	private boolean hasTag(ItemStack stack) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			return false;
		}
		return true;
	}
}