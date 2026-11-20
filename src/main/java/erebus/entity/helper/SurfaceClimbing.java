package erebus.entity.helper;

import erebus.Erebus;
import erebus.entity.RhinoBeetle;
import erebus.registries.data.ModAttachmentTypes;
import erebus.registries.data.tags.ModEntityTypeTags;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import org.joml.Quaternionf;

@EventBusSubscriber(modid = Erebus.MODID)
public final class SurfaceClimbing {
    private static final double CONTACT_DISTANCE = 0.125;

    private SurfaceClimbing() {
    }

    /** The direction from the mob toward its support. Down is the default upright pose. */
    public static Direction surface(Entity entity) {
        Direction surface = entity.getExistingDataOrNull(ModAttachmentTypes.CLIMBING_SURFACE);
        return surface == null ? Direction.DOWN : surface;
    }

    public static boolean isOnWall(Entity entity) {
        return surface(entity).getAxis().isHorizontal();
    }

    @SubscribeEvent
    public static void afterTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof Mob mob) || !mob.is(ModEntityTypeTags.SURFACE_CLIMBERS)) return;

        Direction previous = surface(mob);
        if (mob.level().isClientSide()) {
            if (previous == Direction.UP && mob.isLocalInstanceAuthoritative() && touches(mob, Direction.UP)) {
                holdCeiling(mob, previous);
            }
            return;
        }

        Direction current = findSurface(mob, previous);
        if (current != previous) {
            if (current == Direction.DOWN) mob.removeData(ModAttachmentTypes.CLIMBING_SURFACE);
            else mob.setData(ModAttachmentTypes.CLIMBING_SURFACE, current);
        }
        if (current == Direction.UP) holdCeiling(mob, previous);
    }

    private static Direction findSurface(Mob mob, Direction previous) {
        if (mob.onGround() || !mob.isAlive() || mob.isNoAi() || mob.isPassenger() || mob.isInLiquid()
                || mob instanceof RhinoBeetle beetle && beetle.getControllingPassenger() == null) return Direction.DOWN;

        boolean wasOnWall = previous.getAxis().isHorizontal();
        if ((previous == Direction.UP || wasOnWall) && touches(mob, Direction.UP)
                && (!(mob instanceof RhinoBeetle beetle) || beetle.hasRoomForCeilingRider())) return Direction.UP;
        if (wasOnWall && touches(mob, previous)) return previous;
        if (!mob.horizontalCollision || !mob.onClimbable()) return Direction.DOWN;

        Direction facing = mob.getDirection();
        if (touches(mob, facing)) return facing;
        for (Direction side : Direction.Plane.HORIZONTAL) {
            if (side != facing && touches(mob, side)) return side;
        }
        return Direction.DOWN;
    }

    private static boolean touches(Mob mob, Direction side) {
        AABB box = mob.getBoundingBox();
        Vec3 probe = side.getUnitVec3().scale(CONTACT_DISTANCE);
        double distance = side.getAxisDirection().getStep() * CONTACT_DISTANCE;
        return Shapes.collide(side.getAxis(), box, mob.level().getBlockCollisions(mob, box.expandTowards(probe)), distance) != distance;
    }

    private static void holdCeiling(Mob mob, Direction previous) {
        Vec3 motion = mob.getDeltaMovement();
        if (previous.getAxis().isHorizontal() && !mob.isVehicle()) {
            // Move onto the underside when the mob reaches it by pressing into a wall.
            motion = motion.add(-previous.getStepX() * 0.15, 0, -previous.getStepZ() * 0.15);
        }
        mob.setDeltaMovement(motion.x, 0.08, motion.z);
        mob.resetFallDistance();
    }

    public static Vec3 contactOffset(Direction surface, float width, float height) {
        if (surface == Direction.UP) return new Vec3(0, height, 0);
        return new Vec3(surface.getStepX() * width * 0.5, height * 0.5, surface.getStepZ() * width * 0.5);
    }

    public static Quaternionf rotation(Direction surface, float bodyYaw) {
        if (surface == Direction.UP) {
            float yaw = bodyYaw * Mth.DEG_TO_RAD;
            return new Quaternionf().rotateY(-yaw).rotateZ(Mth.PI).rotateY(yaw);
        }
        float yaw = surface.toYRot() * Mth.DEG_TO_RAD;
        return new Quaternionf().rotateY(-yaw).rotateX(-Mth.HALF_PI).rotateY(yaw);
    }
}
