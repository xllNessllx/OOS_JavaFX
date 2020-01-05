package prak4client;

import prak4gemklassen.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Client extends Application{

    ClientOrb orb;

    BenutzerVerwaltung admin;

    Stage primaryStage;

    AnwendungsController anwendung_ctrl;

    AnmeldungsController anmeldung_ctrl;

    LoginController login_ctrl;

    public void anwendung(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("anwendung.fxml"));
        primaryStage.setTitle("Benutzerverwaltung");
        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.show();
    }

    public void login(Stage primaryStage,boolean error, String error_msg) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root =loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        login_ctrl = loader.getController();
        login_ctrl.setApp(this);
        if(error){
            login_ctrl.loginError(error_msg);
        }
        primaryStage.setTitle("Benutzerverwaltung");
        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.show();
    }

    public void anmeldung(Stage primaryStage, boolean error, String error_msg,boolean remote){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("anmeldung.fxml"));
            Parent root = loader.load();
            //Parent root = FXMLLoader.load(getClass().getResource("anmeldung.fxml"));
            anmeldung_ctrl = loader.getController();
            anmeldung_ctrl.setApp(this);
            if(error){
                anmeldung_ctrl.anmeldeError(error_msg);
            }
            if(remote){
                anmeldung_ctrl.setRemote();
            }
            primaryStage.setTitle("Benutzerverwaltung");
            primaryStage.setScene(new Scene(root, 400, 250));
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void benutzerLogin(Benutzer benutzer, boolean remote){
        if(!remote) {
            try {
                if (((BenutzerVerwaltungAdmin)admin).benutzerOk(benutzer)) {
                    anwendung(primaryStage);
                } else {
                    login(primaryStage, true, "Benutzer existiert nicht!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (orb.benutzerOk(benutzer)) {
                    anwendung(primaryStage);
                } else {
                    login(primaryStage, true, "Benutzer existiert nicht!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void neuerBenutzer(Benutzer benutzer, boolean remote){
        if(!remote) {
            try {
                ((BenutzerVerwaltungAdmin)admin).benutzerEintragen(benutzer);
                login(primaryStage, false, "");
            } catch (IOException e1) {
                anmeldung(primaryStage, true, "Benutzer existiert schon!", remote);
            } catch (ClassNotFoundException e2) {
                anmeldung(primaryStage, true, "Fehler bei Benutzer-Check!", remote);
            } catch (Exception e) {
                anmeldung(primaryStage, true, "Fehler beim erstellen der Scene!", remote);
                e.printStackTrace();
            }
        } else {
            try {
                orb.benutzerEintragen(benutzer);
                login(primaryStage, false, "");
            } catch (IOException e1) {
                anmeldung(primaryStage, true, "Benutzer existiert schon!", remote);
            } catch (ClassNotFoundException e2) {
                anmeldung(primaryStage, true, "Fehler bei Benutzer-Check!", remote);
            } catch (Exception e) {
                anmeldung(primaryStage, true, "Fehler beim erstellen der Scene!", remote);
            }
        }
    }

    void neuAnmeldung(boolean remote){
        anmeldung(primaryStage,false,"", remote);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        orb = new ClientOrb();
        admin = new BenutzerVerwaltungAdmin();
        System.out.println("DB Initialisieren?");
        BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
        int dbInitialisieren = Integer.parseInt(din.readLine());
        if(dbInitialisieren == 1){
            ((BenutzerVerwaltungAdmin)admin).dbInitialisieren();
            Benutzer benutzer = new Benutzer("test","test");
            ((prak4client.BenutzerVerwaltungAdmin)admin).benutzerEintragen(benutzer);
        }
        //anwendug(primaryStage);
        login(this.primaryStage,false,"");
        //anmeldung(primaryStage);
    }

    public static void main(String[] args) {

        launch(args);

    }
}
