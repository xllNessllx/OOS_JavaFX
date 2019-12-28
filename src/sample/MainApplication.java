package sample;

import java.io.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApplication extends Application{

    BenutzerVerwaltungAdmin admin;

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

    public void login(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root =loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        login_ctrl = loader.getController();
        login_ctrl.setApp(this);
        primaryStage.setTitle("Benutzerverwaltung");
        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.show();
    }

    public void loginError(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root =loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        login_ctrl = loader.getController();
        login_ctrl.setApp(this);
        login_ctrl.loginError();
        primaryStage.setTitle("Benutzerverwaltung");
        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.show();
    }

    public void anmeldung(Stage primaryStage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("anmeldung.fxml"));
            Parent root = loader.load();
            //Parent root = FXMLLoader.load(getClass().getResource("anmeldung.fxml"));
            anmeldung_ctrl = loader.getController();
            anmeldung_ctrl.setApp(this);
            primaryStage.setTitle("Benutzerverwaltung");
            primaryStage.setScene(new Scene(root, 400, 250));
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void anmeldungError(Stage primaryStage, String error_msg){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("anmeldung.fxml"));
            Parent root = loader.load();
            //Parent root = FXMLLoader.load(getClass().getResource("anmeldung.fxml"));
            anmeldung_ctrl = loader.getController();
            anmeldung_ctrl.setApp(this);
            anmeldung_ctrl.anmeldeError(error_msg);
            primaryStage.setTitle("Benutzerverwaltung");
            primaryStage.setScene(new Scene(root, 400, 250));
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void benutzerLogin(Benutzer benutzer){
        try {
            if(admin.benutzerOk(benutzer)){
                anwendung(primaryStage);
            } else {
                loginError(primaryStage);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void neuerBenutzer(Benutzer benutzer){
        try{
            admin.benutzerEintragen(benutzer);
            login(primaryStage);
        } catch (IOException e1){
            anmeldungError(primaryStage,"Benutzer existiert schon!");
        } catch (ClassNotFoundException e2){
            anmeldungError(primaryStage,"Fehler bei Benutzer-Check!");
        } catch (Exception e){
            anmeldungError(primaryStage,"Fehler beim erstellen der Scene!");
        }
    }

    void neuAnmeldung(){
        anmeldung(primaryStage);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        admin = new BenutzerVerwaltungAdmin();
        System.out.println("DB Initialisieren?");
        BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
        int dbInitialisieren = Integer.parseInt(din.readLine());
        if(dbInitialisieren == 1){
            admin.dbInitialisieren();
            Benutzer benutzer = new Benutzer("test","test");
            admin.benutzerEintragen(benutzer);
        }
        //anwendug(primaryStage);
        login(this.primaryStage);
        //anmeldung(primaryStage);
    }

    public static void main(String[] args) {

        launch(args);

    }
}
