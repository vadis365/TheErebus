package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ShieldTypeBasic implements IShieldType
{
	private ItemStack shieldItem;
    private ItemStack repairItem;
    private SoundEvent hitSound;
    private SoundEvent breakSound;
    private int durability;
    
    public ShieldTypeBasic(ItemStack shieldItem, ItemStack repairItem, SoundEvent hitSound, SoundEvent breakSound, int durability)
    {

        this.shieldItem = shieldItem;
        this.repairItem = repairItem;
        this.hitSound = hitSound;
        this.breakSound = breakSound;
        this.durability = durability;
    }

    @Override
    public ItemStack getShieldItem()
    {
        return shieldItem;
    }
    
    @Override
    public ItemStack getRepairItem()
    {
        return repairItem;
    }
    
    @Override
    public SoundEvent getHitSound(ItemErebusShield shield, ItemStack stack)
    {
        return hitSound;
    }
    
    @Override
    public SoundEvent getBreakSound(ItemErebusShield shield, ItemStack stack)
    {
        return breakSound;
    }
    
    @Override
    public int getDurability()
    {
        return durability;
    }
    
    @Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> list, ITooltipFlag flag) {
    	list.add("Durability: " + (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage());
    	list.add("Anvil Repair: " + repairItem.getDisplayName());
    }
    
    @Override
    public void onUpdate(ItemErebusShield shield, ItemStack stack, World world, Entity entity, int slot, boolean selected)
    { 
    }
}
