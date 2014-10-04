package erebus.tileentity;

public class TileEntityBones extends TileEntityBasicInventory
{
	public TileEntityBones()
	{
		super(40, "container.bones");
	}

	@Override
	public boolean canUpdate()
	{
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		return new int[0];
	}
}