package sample;

import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class LoginController {

    private boolean neuAnmeldung = false;


    @FXML
    public void boxToggle(Event event){
        neuAnmeldung = !neuAnmeldung;
        System.out.println("Neu anmeldung: " + neuAnmeldung);
    }

    @FXML TextField user;
    @FXML PasswordField pw;
    @FXML Button button;
    @FXML
    public void handleButtonpush(){
        String nutzer = user.getText();
        String passwort = pw.getText();

        if(nutzer.isEmpty() || passwort.isEmpty()){
            System.out.println("Nicht alle Felder wurden ausgefüllt!");
        } else {
        Benutzer benutzer = new Benutzer(nutzer,passwort);
        System.out.println(benutzer.toString());
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        }

    }


}
