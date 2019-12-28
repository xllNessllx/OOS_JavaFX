package sample;

import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class LoginController {

    private boolean neuAnmeldung = false;
    private MainApplication main_app;

    void setApp(MainApplication n_app){
        main_app = n_app;
    }

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

        if(neuAnmeldung){
            main_app.neuAnmeldung();
        } else if(nutzer.isEmpty() || passwort.isEmpty()){
            System.out.println("Nicht alle Felder wurden ausgefüllt!");
        } else {
            Benutzer benutzer = new Benutzer(nutzer,passwort);
            System.out.println(benutzer.toString());
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
            if(neuAnmeldung){
                main_app.neuAnmeldung();
            } else{
                main_app.benutzerLogin(benutzer);
            }
        }

    }

    @FXML
    void loginError(){
        user.setText("Benutzer existiert nicht!");
    }


}
