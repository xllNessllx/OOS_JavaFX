package sample;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;

public class AnmeldungsController {

    @FXML
    TextField user;
    @FXML
    PasswordField pw;
    @FXML
    PasswordField wiederholung;
    @FXML
    Button button;

    @FXML
    public void handleButtonpush(){
        String nutzer = user.getText();
        String pw1 = pw.getText();
        String pw2 = wiederholung.getText();

        if(nutzer.isEmpty() || pw1.isEmpty() || pw2.isEmpty()){
            System.out.println("Nicht alle Felder wurden ausgefüllt!");
        }
        else if(pw1.equals(pw2)){
            Benutzer benutzer = new Benutzer(nutzer,pw1);
            System.out.println(benutzer.toString());
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        } else {
            user.setText("Passwort != Wiederholung");
        }
    }

}
