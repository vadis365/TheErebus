package erebus.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockDoorErebus extends BlockDoor {
	public final String name;
	private Item item;
	private int renderPass = 0;

	public BlockDoorErebus(String name) {
		this(name, Material.wood);
	}

	public BlockDoorErebus(String name, Material material) {
		super(material);
		disableStats();
		this.name = name;
		setHardness(3.0F);
		setStepSound(soundTypeWood);
		setBlockName("erebus.door" + name);
		setBlockTextureName("erebus:door_" + name);
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BlockDoorErebus setRenderPass(int pass) {
		renderPass = pass;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return renderPass;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return item;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return (meta & 8) != 0 ? null : item;
	}

	@Override
	public int getRenderType() {
		return BlockRenderIDs.DOOR.id();
	}
}