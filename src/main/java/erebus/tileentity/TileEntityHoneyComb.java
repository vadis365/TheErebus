package erebus.tileentity;

public class TileEntityHoneyComb extends TileEntityBasicInventory
{

	public TileEntityHoneyComb()
	{
		super(27, "");
	}

	@Override
	public boolean canUpdate()
	{
		return false;
	}

	@Override
	public String getInventoryName()
	{
		return "Honey Comb X:" + xCoord + " Y:" + yCoord + " Z:" + zCoord;
	}
}