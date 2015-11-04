package erebus.block;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;

public class BlockQuickSand extends Block {

	public BlockQuickSand() {
		super(Material.sand);
		setHardness(28F);
		setStepSound(soundTypeSand);
		setHarvestLevel("shovel", 2);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.quickSand");
		setBlockTextureName("erebus:quickSand");
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		entity.isAirBorne = false;
		entity.setInWeb();
	}

	@SubscribeEvent
	public void onEntityJump(LivingJumpEvent e) {
		if (e.entity.worldObj.getBlock((int) Math.floor(e.entity.posX), (int) Math.floor(e.entity.posY) - 1, (int) Math.floor(e.entity.posZ)) == this)
			e.entityLiving.motionY = 0D;
	}
}