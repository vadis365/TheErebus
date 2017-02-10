package erebus.items;

import erebus.ModTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemErebusShield extends ItemShield {

    private ArmorMaterial material;

    public ItemErebusShield(ArmorMaterial material) {
        this.material = material;
        this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });

    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        if (stack.getTagCompound() != null && stack.getTagCompound().hasKey("damage")) {
            return ((double) stack.getTagCompound().getInteger("damage") / (double) material.getDurability(EntityEquipmentSlot.CHEST));
        } else
            return 1;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return stack.getTagCompound() != null && stack.getTagCompound().hasKey("damage") && stack.getTagCompound().getInteger("damage") > 0;
    }

    public String getItemStackDisplayName(ItemStack stack) {
        return ("" + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return ModTabs.GEAR;
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
    	//TODO Add some stuff here ;)
        return false;
    }

    @Override
    public boolean updateItemStackNBT(NBTTagCompound nbt) {
        return super.updateItemStackNBT(nbt);
    }

    public boolean damageShield(int i, ItemStack stack, EntityLivingBase entityIn) {
        if (stack.getTagCompound() == null || !stack.getTagCompound().hasKey("damage")) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setInteger("damage", material.getDurability(EntityEquipmentSlot.CHEST));
            stack.setTagCompound(new NBTTagCompound());
        }

        int damage = stack.getTagCompound().getInteger("damage") + i;
        System.out.println(damage);
        if (damage >= material.getDurability(EntityEquipmentSlot.CHEST)) {
            entityIn.renderBrokenItemStack(stack);
            stack.setCount(stack.getCount() - 1);

            if (entityIn instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) entityIn;
                entityplayer.addStat(StatList.getObjectBreakStats(stack.getItem()));
            }

            if (stack.getCount() < 0) {
                stack.setCount(0);
            }

        } else {
            stack.getTagCompound().setInteger("damage", damage);
        }

        return true;
    }
}
