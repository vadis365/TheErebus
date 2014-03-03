package erebus.block;

import java.util.Random;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class BlockDoorAmber extends BlockDoor {
	public Icon topDoorIcon;
	public Icon crapFixIcon;
	public Icon[] flippedIcons = new Icon[2];

	public BlockDoorAmber(int id, Material material) {
		super(id, material);
		float f = 0.5F;
		float f1 = 1.0F;
		this.setLightOpacity(0);
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
	}

	public Icon getBlockTexture(IBlockAccess iBlockAccess, int x, int y, int z, int direction) {
		if (direction == 1) {
			return this.blockIcon;
		}
		if (direction == 0) {
			return this.crapFixIcon;
		}
		int meta = getFullMetadata(iBlockAccess, x, y, z);
		boolean flag = (meta & 4) != 0;
		int halfMeta = meta & 3;
		boolean flipped = false;

		if (flag) {
			if (halfMeta == 0 && direction == 2)
				flipped = !flipped;
			else if (halfMeta == 1 && direction == 5)
				flipped = !flipped;
			else if (halfMeta == 2 && direction == 3)
				flipped = !flipped;
			else if (halfMeta == 3 && direction == 4)
				flipped = !flipped;
		} else {
			if (halfMeta == 0 && direction == 5)
				flipped = !flipped;
			else if (halfMeta == 1 && direction == 3)
				flipped = !flipped;
			else if (halfMeta == 2 && direction == 4)
				flipped = !flipped;
			else if (halfMeta == 3 && direction == 2)
				flipped = !flipped;
			if ((meta & 16) != 0)
				flipped = !flipped;
		}

		if (flipped)
			return flippedIcons[(meta & 8) != 0 ? 1 : 0];
		else
			return (meta & 8) != 0 ? this.topDoorIcon : this.blockIcon;
	}

	public Icon getIcon(int par1, int par2) {
		return this.blockIcon;
	}

	@Override
	public void registerIcons(IconRegister icon) {
		this.blockIcon = icon.registerIcon("erebus:doorAmberLower");
		this.topDoorIcon = icon.registerIcon("erebus:doorAmberUpper");
		this.crapFixIcon = icon.registerIcon("erebus:doorAmberBlank");
		this.flippedIcons[0] = new IconFlipped(blockIcon, true, false);
		this.flippedIcons[1] = new IconFlipped(topDoorIcon, true, false);
	}

	public int idPicked(World world, int x, int y, int z) {
		return ModItems.doorAmberItem.itemID;
	}

	public int idDropped(int id, Random random, int something) {
		return (id & 8) != 0 ? 0 : (ModItems.doorAmberItem.itemID);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderBlockPass() {
		return 1;
	}
}