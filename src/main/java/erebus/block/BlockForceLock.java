package erebus.block;

import erebus.ModItems;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.item.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockForceLock extends Block {

	public BlockForceLock() {
		super(Material.rock);
		setBlockUnbreakable();
		setResistance(6000000.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.forceLock");
		setBlockTextureName("erebus:anthillBlock");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getCurrentEquippedItem();
		if (stack != null && stack.getItem() == ModItems.materials && stack.getItemDamage() == ItemMaterials.DATA.forceKey.ordinal()) {
			if (!player.capabilities.isCreativeMode)
				stack.stackSize--;
			if (!world.isRemote)
				Utils.breakBlockWithParticles(world, x, y, z, 0);
			return true;
		}

		return false;
	}
}