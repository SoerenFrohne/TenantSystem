package tenantsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/mainframe.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Mietverwaltung");
            primaryStage.setOnCloseRequest(e -> System.exit(0));
            primaryStage.show();
            primaryStage.toFront();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
