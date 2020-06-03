package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load((getClass().getResource("loginController.fxml")));
            stage.setScene(new Scene(root));
            stage.setTitle("Gym Database Management System");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
