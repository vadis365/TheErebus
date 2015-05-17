package erebus.item;

public class ItemWitherWebSlinger extends ItemWebSlinger {
	@Override
	protected byte getShootType() {
		return (byte) 1;
	}
}