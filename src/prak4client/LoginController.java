package prak4client;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import prak4gemklassen.Benutzer;


public class LoginController {

    private boolean neuAnmeldung = false;
    private boolean remote_bool = false;
    private Client main_app;

    void setApp(Client n_app){
        main_app = n_app;
    }

    @FXML
    public void boxToggle(Event event){
        neuAnmeldung = !neuAnmeldung;
        System.out.println("Neu anmeldung: " + neuAnmeldung);
    }

    @FXML
    public void boxToggle_remote(Event event){
        remote_bool = !remote_bool;
        System.out.println("Remote anmeldung: " + remote_bool);
    }

    @FXML TextField user;
    @FXML PasswordField pw;
    @FXML Button button;
    @FXML
    public void handleButtonpush() {
        String nutzer = user.getText();
        String passwort = pw.getText();

        if (neuAnmeldung && !remote_bool) {
            main_app.neuAnmeldung(remote_bool);
        } else if(neuAnmeldung && remote_bool){
            main_app.neuAnmeldung(remote_bool);
        }else if(nutzer.isEmpty() || passwort.isEmpty()){
            System.out.println("Nicht alle Felder wurden ausgefüllt!");
        } else {
            Benutzer benutzer = new Benutzer(nutzer,passwort);
            System.out.println(benutzer.toString());
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
            if(neuAnmeldung && !remote_bool){
                main_app.neuAnmeldung(remote_bool);
            } else if(neuAnmeldung && remote_bool) {
                main_app.neuAnmeldung(remote_bool);
            } else{
                main_app.benutzerLogin(benutzer,remote_bool);
            }
        }

    }

    @FXML
    void loginError(String error_msg){
        user.setText(error_msg);
    }


}
