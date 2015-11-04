package erebus.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.item.ItemMaterials.DATA;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemHammerWar extends ItemSword {

	public ItemHammerWar() {
		super(ModMaterials.weaponWarHammer);
		setMaxStackSize(1);
		setCreativeTab(ModTabs.gears);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag) {
		list.add(StatCollector.translateToLocal("tooltip.erebus.warhammer_1"));
		list.add(StatCollector.translateToLocal("tooltip.erebus.warhammer_2"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.materials && material.getItemDamage() == DATA.reinforcedPlateExo.ordinal();
	}

	@Override
	public boolean hitEntity(ItemStack is, EntityLivingBase entity, EntityLivingBase player) {
		is.damageItem(1, player);
		return true;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
		if (!stack.hasTagCompound())
			stack.stackTagCompound = new NBTTagCompound();
		if (!stack.getTagCompound().hasKey("charge"))
			stack.getTagCompound().setInteger("charge", 0);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack is) {
		return 1000;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (stack.getTagCompound().getInteger("charge") < 25)
			stack.getTagCompound().setInteger("charge", stack.getTagCompound().getInteger("charge") + 1);
		if (stack.getTagCompound().getInteger("charge") >= 25)
			stack.getTagCompound().setInteger("charge", 25);
		return stack;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!stack.hasTagCompound()) {
			stack.stackTagCompound = new NBTTagCompound();
			return false;
		}
		if (side == 1 && player.isSneaking()) {
			if (stack.getTagCompound().getInteger("charge") > 0) {
				PacketPipeline.sendToAllAround(player, 64D, new PacketParticle(player, ParticleType.HAMMER_BLAM));
				world.playSoundAtEntity(player, "erebus:antlionslam", 1.0F, 1.0F);
			}
			areaOfEffect(world, stack, player);
			stack.getTagCompound().setInteger("charge", 0);
			return true;
		}
		return false;
	}

	protected Entity areaOfEffect(World world, ItemStack stack, EntityPlayer player) {
		List<?> list = world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(player.boundingBox.minX, player.boundingBox.minY, player.boundingBox.minZ, player.boundingBox.maxX, player.boundingBox.maxY, player.boundingBox.maxZ).expand(stack.getTagCompound().getInteger("charge") * 0.25D, 1D, stack.getTagCompound().getInteger("charge") * 0.25D));
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
}
