package Model;

import Controller.MainPageController;
import com.sun.tools.javac.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.Locale;

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

//        if(namedParts.size() == 0) {
//            return notFound;
//        } else {
//            return namedParts;
//        }

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

    /** @param index
     * @param selectedPart updates the selected part */

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /** @param index
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

    public static double getPrice(String value) throws NumberFormatException {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Price must be a decimal value");
        }
    }
}
