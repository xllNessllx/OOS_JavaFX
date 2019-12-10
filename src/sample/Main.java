package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{

    public void anwendug(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("anwendung.fxml"));
        primaryStage.setTitle("Benutzerverwaltung");
        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.show();
    }

    public void login(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Benutzerverwaltung");
        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.show();
    }

    public void anmeldung(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("anmeldung.fxml"));
        primaryStage.setTitle("Benutzerverwaltung");
        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //anwendug(primaryStage);
        //login(primaryStage);
        anmeldung(primaryStage);
    }

    public static void main(String[] args) {

        launch(args);

    }
}
