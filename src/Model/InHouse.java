package Model;

/** This class creates In House Parts */

public class InHouse extends Part {

    public static int partID = 6;
    private int machineID;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /** @Return the machineID */

    public int getMachineID() {
        return machineID;
    }

    /** @param machineID sets the machineID */

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

    /** @return the part ID plus 2 */

    public static int getPartID() {
        return partID+= 2;
    }

    /** @param partID sets the part ID */

    public static void setPartID(int partID) {
        InHouse.partID = partID;
    }
}
