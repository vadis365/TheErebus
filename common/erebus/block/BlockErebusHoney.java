package erebus.block;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModMaterials;

public class BlockErebusHoney extends BlockFluidClassic {

	@SideOnly(Side.CLIENT)
	protected Icon stillIcon, flowingIcon;

	public BlockErebusHoney(int id, Fluid fluid) {
		super(id, fluid, ModMaterials.honey);
		setCreativeTab(Erebus.tabErebusBlock);
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return side == 0 || side == 1 ? stillIcon : flowingIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		stillIcon = register.registerIcon("erebus:honey");
		flowingIcon = register.registerIcon("erebus:honeyFlow");
	}

	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		if (world.getBlockMaterial(x, y, z).isLiquid())
			return false;
		return super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) {
		if (world.getBlockMaterial(x, y, z).isLiquid())
			return false;
		return super.displaceIfPossible(world, x, y, z);
	}

	@ForgeSubscribe
	public void postStitch(TextureStitchEvent.Post event) {
		if (event.map.textureType == 0)
			ModBlocks.erebusHoney.setIcons(getBlockTextureFromSide(0), getBlockTextureFromSide(1));
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			entity.motionX *= 0.005D;
			entity.motionZ *= 0.005D;
		}
	}
}
