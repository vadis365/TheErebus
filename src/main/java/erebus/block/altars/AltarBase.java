package erebus.block.altars;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.item.ItemMaterials;
import erebus.tileentity.TileEntityErebusAltarEmpty;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class AltarBase extends AltarAbstract {

	private final Map<ItemMaterials.DATA, Block> ALTAR_TYPES = new HashMap<ItemMaterials.DATA, Block>();

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityErebusAltarEmpty();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		initMap();

		ItemStack stack = player.getCurrentEquippedItem();
		if (stack != null && stack.getItem() == ModItems.materials) {
			Block altar = ALTAR_TYPES.get(ItemMaterials.DATA.values()[stack.getItemDamage()]);
			if (altar != null) {
				if (!world.isRemote) {
					world.setBlock(x, y, z, altar, world.getBlockMetadata(x, y, z), 3);
					world.playSoundEffect(x + 0.5, y, z + 0.5, "erebus:altaroffering", 0.2F, 1.0F);
					if (!player.capabilities.isCreativeMode && --stack.stackSize <= 0)
						player.setCurrentItemOrArmor(0, null);
				} else
					for (int i = 0; i < 10; i++) {
						Random rand = world.rand;
						world.spawnParticle("flame", x + rand.nextDouble(), y + 1.1, z + rand.nextDouble(), 0, 0, 0);
						world.spawnParticle("cloud", x + rand.nextDouble(), y + 1.1, z + rand.nextDouble(), 0, 0, 0);
					}
				return true;
			}
		}

		return false;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		int meta = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, meta == 0 ? 2 : meta == 1 ? 5 : meta == 2 ? 3 : 4, 2);
	}

	private void initMap() {
		if (ALTAR_TYPES.isEmpty()) {
			ALTAR_TYPES.put(ItemMaterials.DATA.bioVelocity, ModBlocks.altarXP);
			ALTAR_TYPES.put(ItemMaterials.DATA.elasticFibre, ModBlocks.altarRepair);
			ALTAR_TYPES.put(ItemMaterials.DATA.redGem, ModBlocks.altarLightning);
			ALTAR_TYPES.put(ItemMaterials.DATA.bioLuminescence, ModBlocks.altarHealing);
		}
	}
}