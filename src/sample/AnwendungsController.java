package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.desktop.AppEvent;

public class AnwendungsController {

    @FXML
    Button button;
    @FXML public void handleButtonpush(Event event){
        System.out.println("Sie haben das Programm abgebrochen!");
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
