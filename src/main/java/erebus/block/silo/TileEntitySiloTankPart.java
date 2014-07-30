package erebus.block.silo;
 
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
 
public class TileEntitySiloTankPart extends TileEntity {
    public boolean hasMaster, isMaster;
    private int masterX, masterY, masterZ;
 
    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isRemote) {
            if (hasMaster()) { 
                if (isMaster()) {
                    // Put stuff you want the multiblock to do here!
                	System.out.println("MasterBlock exists");
                }
            } else {
                // Constantly check if structure is formed until it is.
                if (checkMultiBlockForm())
                    setupStructure();
            }
            if (!checkMultiBlockForm())
                reset();
        }
    }
    
    /** Check that structure is properly formed */
    public boolean checkMultiBlockForm() {
        int i = 0;
        // Scan a 3x3x3 area, starting with the bottom left corner
        for (int x = xCoord - 1; x < xCoord + 2; x++)
            for (int y = yCoord; y < yCoord + 3; y++)
                for (int z = zCoord - 1; z < zCoord + 2; z++) {
                	TileEntity tile = worldObj.getTileEntity(x, y, z);
                	// Make sure tile isn't null, is an instance of the same Tile, and isn't already a part of a multiblock (if ours is already part of one)
                	if (tile != null && (tile instanceof TileEntitySiloTankPart)) {
                		if (this.isMaster()) {
                			if (((TileEntitySiloTankPart)tile).hasMaster())
                				i++;
                		} else if (!((TileEntitySiloTankPart)tile).hasMaster())
                				i++;
                	}
                }         // check if there are 26 blocks present ((3*3*3) - 1) and check that center block is empty
        			return i == 26 && worldObj.isAirBlock(xCoord, yCoord + 1, zCoord) && worldObj.getBlock(xCoord, yCoord-1, zCoord) instanceof BlockSiloIntake && checkSupports() && checkBase();
		}
    
    public boolean checkSupports() {
    	if( worldObj.getBlock(xCoord - 1, yCoord - 1, zCoord - 1) == Blocks.fence
    	&& worldObj.getBlock(xCoord + 1, yCoord - 1, zCoord - 1) == Blocks.fence
    	&& worldObj.getBlock(xCoord + 1, yCoord - 1, zCoord + 1) == Blocks.fence
    	&& worldObj.getBlock(xCoord - 1, yCoord - 1, zCoord + 1) == Blocks.fence
    	&& worldObj.getBlock(xCoord - 1, yCoord - 2, zCoord - 1) == Blocks.fence
    	&& worldObj.getBlock(xCoord + 1, yCoord - 2, zCoord - 1) == Blocks.fence
    	&& worldObj.getBlock(xCoord + 1, yCoord - 2, zCoord + 1) == Blocks.fence
    	&& worldObj.getBlock(xCoord - 1, yCoord - 2, zCoord + 1) == Blocks.fence)
    		return true;
    	
	return false; 	
    }
    
    public boolean checkBase() {
    	int i = 0;
    	for (int x = xCoord - 1; x < xCoord + 2; x++)
                for (int z = zCoord - 1; z < zCoord + 2; z++) 
                	if(worldObj.getBlock(x, yCoord-3, z) == Blocks.cobblestone)
                		i ++;
		return i > 8;	
    }
 
    /** Setup all the blocks in the structure*/
    public void setupStructure() {
        for (int x = xCoord - 1; x < xCoord + 2; x++)
            for (int y = yCoord; y < yCoord + 3; y++)
                for (int z = zCoord - 1; z < zCoord + 2; z++) {
                    TileEntity tile = worldObj.getTileEntity(x, y, z);
                    // Check if block is bottom center block
                    boolean master = (x == xCoord && y == yCoord && z == zCoord);
                    if (tile != null && (tile instanceof TileEntitySiloTankPart)) {
                        ((TileEntitySiloTankPart) tile).setMasterCoords(xCoord, yCoord, zCoord);
                        ((TileEntitySiloTankPart) tile).setHasMaster(true);
                        ((TileEntitySiloTankPart) tile).setIsMaster(master);
                    }
                }
    }
 
    /** Reset method to be run when the master is gone or tells them to */
    public void reset() {
        masterX = 0;
        masterY = 0;
        masterZ = 0;
        hasMaster = false;
        isMaster = false;
    }
 
    /** Check that the master exists */
    public boolean checkForMaster() {
        TileEntity tile = worldObj.getTileEntity(masterX, masterY, masterZ);
        return (tile != null && (tile instanceof TileEntitySiloTankPart));
    }
    
    /** Reset all the parts of the structure */
    public void resetStructure() {
        for (int x = xCoord - 1; x < xCoord + 2; x++)
            for (int y = yCoord; y < yCoord + 3; y++)
                for (int z = zCoord - 1; z < zCoord + 2; z++) {
                    TileEntity tile = worldObj.getTileEntity(x, y, z);
                    if (tile != null && (tile instanceof TileEntitySiloTankPart))
                        ((TileEntitySiloTankPart) tile).reset();
                }
    }
 
    @Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger("masterX", masterX);
        data.setInteger("masterY", masterY);
        data.setInteger("masterZ", masterZ);
        data.setBoolean("hasMaster", hasMaster);
        data.setBoolean("isMaster", isMaster);
        if (hasMaster() && isMaster()) {
            // Any other values should ONLY BE SAVED TO THE MASTER
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        masterX = data.getInteger("masterX");
        masterY = data.getInteger("masterY");
        masterZ = data.getInteger("masterZ");
        hasMaster = data.getBoolean("hasMaster");
        isMaster = data.getBoolean("isMaster");
        if (hasMaster() && isMaster()) {
            // Any other values should ONLY BE READ BY THE MASTER
        }
    }
 
    public boolean hasMaster() {
        return hasMaster;
    }
 
    public boolean isMaster() {
        return isMaster;
    }
 
    public int getMasterX() {
        return masterX;
    }
 
    public int getMasterY() {
        return masterY;
    }
 
    public int getMasterZ() {
        return masterZ;
    }
 
    public void setHasMaster(boolean bool) {
        hasMaster = bool;
    }
 
    public void setIsMaster(boolean bool) {
        isMaster = bool;
    }
 
    public void setMasterCoords(int x, int y, int z) {
        masterX = x;
        masterY = y;
        masterZ = z;
    }
}