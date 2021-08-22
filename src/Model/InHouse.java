package Model;

/** This class creates In House Parts*/

public class InHouse extends Part {

    public static int partID = 3;
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

    public static int getPartID() {
        return partID++;
    }

    public static void setPartID(int partID) {
        InHouse.partID = partID;
    }
}
