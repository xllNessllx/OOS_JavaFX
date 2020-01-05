package prak4client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import prak4gemklassen.*;

public class AnmeldungsController {

    private Client main_app;
    private boolean remote = false;

    void setApp(Client n_app){
        main_app = n_app;
    }

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
            main_app.neuerBenutzer(benutzer,remote);
        } else {
            user.setText("Passwort != Wiederholung");
        }
    }

    @FXML
    void anmeldeError(String error_msg){
        user.setText(error_msg);
    }

    @FXML
    void setRemote(){
        remote = true;
    }

}
