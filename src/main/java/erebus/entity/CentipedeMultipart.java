package erebus.entity;

public class CentipedeMultipart extends GenericPartEntity<Centipede> {

    public CentipedeMultipart(Centipede parentMob, float width, float height) {
        super(parentMob, width, height);
    }

	@Override
    protected double getDefaultGravity() {
        return 0.2;
    }

}
