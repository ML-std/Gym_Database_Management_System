package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Launcher extends Application {
    public static void main(String[] args) {
        Application.launch(Launcher.class, args);
    }

    @Override
    public void start(Stage stage){
        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("loginController.fxml")));
            stage.setScene(new Scene(root));
            stage.setTitle("Gym Database Management System");
            Image icon = new Image("gym.jpg");
            stage.getIcons().add(icon);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
