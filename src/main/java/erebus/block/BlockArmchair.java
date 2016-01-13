package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.EntityArmchairMount;
import erebus.network.PacketPipeline;
import erebus.network.server.PacketArmchairClientMessages;
import erebus.tileentity.TileEntityArmchair;

public class BlockArmchair extends BlockContainer {
	
	private  EntityArmchairMount entityMountableBlock;

    public BlockArmchair() {
        super(Material.wood);
        setBlockName("erebus.armchair");
        setHardness(2.0F);
        setResistance(10.0F);
        setBlockBounds(0, 0, 0, 1, 0.4375F, 1);
        setCreativeTab(ModTabs.blocks);
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return Blocks.bed.getIcon(side, 0);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityArmchair();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack is) {
		byte b0 = 0;
		int l1 = MathHelper.floor_double(entityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		if (l1 == 0)
			b0 = 2;
		if (l1 == 1)
			b0 = 5;
		if (l1 == 2)
			b0 = 3;
		if (l1 == 3)
			b0 = 4;
		world.setBlockMetadataWithNotify(x, y, z, b0, 3);
	}

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    	if(world.isRemote)
    		return false;
        if(player.isSneaking()) {
            return false;
        } else {
		if (player.dimension == ConfigHandler.INSTANCE.erebusDimensionID && ConfigHandler.INSTANCE.allowRespawning) {
			if(!world.isAirBlock(x, y + 1, z) || !world.isAirBlock(x, y + 2, z))
				PacketPipeline.sendToPlayer(player, new PacketArmchairClientMessages((byte)0));
			else {
				byte meta = (byte) world.getBlockMetadata(x, y, z);
				player.getEntityData().setInteger("armchairX", (int) x);
				player.getEntityData().setInteger("armchairY", (int) y);
				player.getEntityData().setInteger("armchairZ", (int) z);
				player.getEntityData().setBoolean("armchairSpawn", true);
				PacketPipeline.sendToPlayer(player, new PacketArmchairClientMessages((byte)1));
				entityMountableBlock = new EntityArmchairMount(world);
				entityMountableBlock.setPosition(x + 0.5D, y  + 0.5D, z  + 0.5D);
				entityMountableBlock.setChairAngle(meta);
				world.spawnEntityInWorld(entityMountableBlock);
				player.mountEntity(entityMountableBlock);
			}
		}
	}
        return true;
    }

	@Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
    	if (!world.isRemote && entityMountableBlock != null)
    		entityMountableBlock.setDead();
        return;
    }

}
