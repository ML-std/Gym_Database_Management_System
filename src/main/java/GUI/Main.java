package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("loginController.fxml")));
            stage.setScene(new Scene(root));
            stage.setTitle("Gym Database Management System");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
