import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** This class creates an app that manages product inventory. */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/mainpage.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 1200, 450));
        primaryStage.show();
    }

    /** This is the main method. This is the first method to be called when program is run. */

    public static void main(String[] args) {

        InHouse part1 = new InHouse(1,"Brakes", 15.00, 25, 0, 100, 67);
        Outsourced part2 = new Outsourced(2,"Seat",16.99,5,0,1000,"Seatco");
        Product product1 = new Product(1000, "Bicycle", 299.99, 25, 0, 100);
        Product product2 = new Product(1001, "Skateboard", 119.99, 5, 0, 9999);

        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);

        launch(args);
    }
}
