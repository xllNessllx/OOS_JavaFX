package prak4serv;

import prak4gemklassen.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerOrb {

    ServerOrb(BenutzerVerwaltungAdmin bv) throws IllegalArgumentException, VektorLeerException,IOException,ClassNotFoundException {

        ServerSocket server = new ServerSocket(4574);

        while (true){
            Socket client = server.accept();
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

            String command = (String) in.readObject();
            Benutzer benutzer = (Benutzer) in.readObject();

            if(command.equals("benutzerOk")){
                boolean istOk = bv.benutzerOk(benutzer);
                System.out.println("Benutzer ist:" + istOk);
                if(istOk){
                    out.writeBoolean(istOk);
                } else {
                    out.writeBoolean(istOk);
                }
                out.flush();
            }

            if(command.equals("benutzerEintragen")){
                if(bv.benutzerOk(benutzer)){
                    System.out.println("Benutzer existiert schon");
                    out.writeObject("Benutzer existiert schon!");
                } else {
                    bv.benutzerEintragen(benutzer);
                    System.out.println("Benutzer wurde eingetragen");
                    out.writeObject("Benutzer wurde eingetragen!");
                    out.flush();
                }
            }

            if(command.equals("benutzerLöschen")){
                bv.benutzerLöschen(benutzer);
                System.out.println("Benutzer wurde gelöscht");
                out.flush();
            }

            client.close();

        }
    }
}

