package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;

public class EntitySporeJet extends EntityLiving {

	public EntitySporeJet(World world) {
		super(world);
		setSize(0.5F, 2.5F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			if (ticksExisted > 20)
				setDead();
			if (ticksExisted == 1)
				PacketPipeline.sendToAllAround(this, 16D, new PacketParticle(this, ParticleType.SPORE_JET));
		}
	}

	@Override
	public boolean isEntityInvulnerable() {
		return true;
	}

	@Override
	protected void collideWithEntity(Entity entity) {
		if (!worldObj.isRemote) {;
			if (entity instanceof EntityPlayer) {
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, 5 * 20, 0));
				EntityPlayer player = (EntityPlayer) entity;
				ItemStack is = player.inventory.getCurrentItem();
				if (is != null)
					player.dropOneItem(true);
			}
		}
		setDead();
		super.collideWithEntity(entity);
	}

}