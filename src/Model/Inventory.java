package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Locale;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static Part lookupPart(int partID) {

        ObservableList<Part> allParts = getAllParts();

        for(Part p : allParts) {
            if(p.getId() == partID) {
                return p;
            }
        }

        return null;
    }

    public static Product lookupProduct(int productID) {

        ObservableList<Product> allProducts = getAllProducts();

        for(Product p : allProducts) {
            if(p.getId() == productID) {
                return p;
            }
        }

        return null;
    }

    public static ObservableList<Part> lookupPart(String partName) {

        ObservableList<Part> namedParts = FXCollections.observableArrayList();

        ObservableList<Part> allParts = getAllParts();

        for(Part p : allParts) {
            if(p.getName().toUpperCase().contains(partName.toUpperCase())) {
                namedParts.add(p);
            }
        }

        return namedParts;
    }

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

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }
//
//    public static void updateProduct(int index, Product newProduct) {
//
//    }
//
    public static boolean deletePart(Part selectedPart) {
        return true;
    }

    public static boolean deleteProduct(Product selectedProduct) {
        return true;
    }
}
