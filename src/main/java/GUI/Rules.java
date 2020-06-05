package GUI;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Optional;

public class Rules {
    public static void alertHandler(boolean isSuccess, String condition) {
        if (isSuccess){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Operation");
            alert.setHeaderText("Operation has succeed");
            Thread thread = new Thread(() -> {
                try {
                    // Wait for 5 secs
                    Thread.sleep(5000);
                    if (alert.isShowing()) {
                        Platform.runLater(() -> alert.close());
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            });
            thread.setDaemon(true);
            thread.start();
            Optional<ButtonType> result = alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Operation");
            alert.setHeaderText("Operation has failed");
            alert.setContentText(condition);
            alert.showAndWait();
        }
    }
    public static boolean hasAnEmptyInput(ArrayList<TextField> textFields){
        for (TextField textField: textFields) {
            if (textField.getText().equals("")){
               alertHandler(false, "please fill the required blanks");
               return true;

            }


        }
        return false;
    }
}
