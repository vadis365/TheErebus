package erebus.entity;

import erebus.Erebus;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntitySporeJet extends EntityLiving {

	public EntitySporeJet(World world) {
		super(world);
		setSize(0.5F, 2.5F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!getEntityWorld().isRemote) {
			if (ticksExisted > 20)
				setDead();
			if (ticksExisted == 1)
				Erebus.NETWORK_WRAPPER.sendToAll(new PacketParticle(ParticleType.SPORE_JET, (float) posX, (float)posY, (float)posZ));
		}
	}

	@Override
	public boolean getIsInvulnerable() {
		return true;
	}

	@Override
	protected void collideWithEntity(Entity entity) {
		if (!getEntityWorld().isRemote) {
			if (entity instanceof EntityPlayer) {
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 5 * 20, 0));
				EntityPlayer player = (EntityPlayer) entity;
				ItemStack is = player.getHeldItemMainhand();
				if (!is.isEmpty())
					player.dropItem(true);
			}
		}
		setDead();
		super.collideWithEntity(entity);
	}

}