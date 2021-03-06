import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** This class creates an app that manages product inventory.
 * JavaDoc is found in JavaDoc folder*/

public class Main extends Application {

    /** FUTURE ENHANCEMENT - Combine the logical error and input validation checks into a single method in the Inventory class*/

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/mainpage.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 1200, 450));
        primaryStage.show();
    }

    /** This is the main method. This is the first method to be called when program is run.
     * @param args */

    public static void main(String[] args) {

        InHouse part1 = new InHouse(2,"Single Coil Pickup", 32.99, 25, 0, 100, 67);
        Outsourced part2 = new Outsourced(4,"Strings",11.99,34,0,1000,"Ernie Ball");
        InHouse part3 = new InHouse(6, "Humbucker Pickup", 49.99, 25, 0,100, 111);
        Product product1 = new Product(1, "Les Paul", 999.99, 7, 0, 100);
        Product product2 = new Product(3, "Telecaster", 799.99, 5, 0, 100);

        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);

        product1.addAssociatedPart(part2);
        product1.addAssociatedPart(part3);
        product2.addAssociatedPart(part2);
        product2.addAssociatedPart(part1);

        launch(args);
    }
}
