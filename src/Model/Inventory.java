package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /** @param newPart adds new parts */

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /** @param newProduct adds new products */

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /** @return all parts */

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /** @return all products */

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /** @param partID looks up parts with ID
     * @return the found part */

    public static Part lookupPart(int partID) {

        for(Part p : allParts) {
            if(p.getId() == partID) {
                return p;
            }
        }

        return null;
    }

    /** @param productID looks up products with ID
     * @return the found product */

    public static Product lookupProduct(int productID) {

        ObservableList<Product> allProducts = getAllProducts();

        for(Product p : allProducts) {
            if(p.getId() == productID) {
                return p;
            }
        }

        return null;
    }

    /** @param partName looks up part using the name
     * @return the found part */

    public static ObservableList<Part> lookupPart(String partName) {

        ObservableList<Part> namedParts = FXCollections.observableArrayList();

        for(Part p : allParts) {
            if(p.getName().toUpperCase().contains(partName.toUpperCase())) {
                namedParts.add(p);
            }
        }
        return namedParts;
    }

    /** @param productName looks up products using the name
     * @return the found product */

    public static ObservableList<Product> lookupProduct(String productName) {

        ObservableList<Product> namedProducts = FXCollections.observableArrayList();

        ObservableList<Product> allProducts = getAllProducts();

        for(Product p : allProducts) {
            if(p.getName().toUpperCase().contains(productName.toUpperCase())) {
                namedProducts.add(p);
            }
        }

        return namedProducts;
    }

    /** @param index index of selected part
     * @param selectedPart updates the selected part */

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /** @param index index of the selected product
     * @param newProduct updates the selected product */

    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /** @return true
     * @param selectedPart deletes the part */

    public static boolean deletePart(Part selectedPart) {
        return true;
    }

    /** @return true
     * @param selectedProduct deletes the product */

    public static boolean deleteProduct(Product selectedProduct) {
        return true;
    }

    /** @param value validates the price
     *  @return the correct value*/

    public static double getPrice(String value) throws NumberFormatException {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Price must be a decimal value");
        }
    }

    /** @param value validates the inventory
     *  @return the correct value*/

    public static int getInv(String value) throws NumberFormatException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Inventory must be a number");
        }
    }

    /** @param value validates the min value
     *  @return the correct value*/

    public static int getMin(String value) throws NumberFormatException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Min must be a number");
        }
    }

    /** @param value validates the max value
     *  @return the correct value*/

    public static int getMax(String value) throws NumberFormatException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Inventory must be a number");
        }
    }

    /** @param min minimum value
     *  @param max validates min is less than or equal to max
     *  @return confirmation */

    public static int isMinMaxCorrect(int min, int max) {
        if (min <= max) {
            return 1;
        } else throw new NumberFormatException("Min must be less than or equal to Max");
    }

    /** @param min minimum value
     *  @param max maximum value
     *  @param inv validates inventory level is between min and max
     *  @return confirmation */

    public static int isInvCorrect(int inv, int min, int max) {
        if (inv >= min && inv <= max) {
            return 1;
        } else throw new NumberFormatException("Inv must be greater than or equal to Min. Inv must be less than or equal to Max");

    }
}
