package prak4serv;

import javafx.scene.control.RadioMenuItem;
import prak4gemklassen.Benutzer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Server {

    BenutzerVerwaltungAdmin bv;

    Server(){

       try {
           bv = new BenutzerVerwaltungAdmin();
           System.out.println("DB Initialisieren?");
           BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
           int dbInitialisieren = Integer.parseInt(din.readLine());
           if (dbInitialisieren == 1) {
               bv.dbInitialisieren();
               Benutzer benutzer = new Benutzer("test", "test");
               bv.benutzerEintragen(benutzer);
           }
           ServerOrb orb = new ServerOrb(bv);
       } catch (Exception e){
           e.printStackTrace();
       }

    }

    public static void main(String[] args){

        Server server = new Server();

    }

}
