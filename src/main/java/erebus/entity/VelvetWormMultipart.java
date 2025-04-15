package erebus.entity;

public class VelvetWormMultipart extends GenericPartEntity<VelvetWorm> {

    public VelvetWormMultipart(VelvetWorm parentMob, float width, float height) {
        super(parentMob, width, height);
    }

	@Override
    protected double getDefaultGravity() {
        return 0.2;
    }

}