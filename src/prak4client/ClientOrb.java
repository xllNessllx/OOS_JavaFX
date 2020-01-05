package prak4client;

import prak4gemklassen.Benutzer;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientOrb implements prak4client.BenutzerVerwaltung{

    public boolean benutzerOk(Benutzer benutzer){

        boolean status = false;

        try {
            Socket server = new Socket("localhost", 4574);

            ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(server.getInputStream());

            out.writeObject("benutzerOk");
            out.writeObject(benutzer);
            out.flush();

            status = in.readBoolean();
            System.out.println("Benutzer ist: " + status);

            server.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }

    public void benutzerEintragen(Benutzer benutzer){

        try{
            Socket server = new Socket("localhost",4574);

            ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(server.getInputStream());

            out.writeObject("benutzerEintragen");
            out.writeObject(benutzer);
            out.flush();

            System.out.println((String) in.readObject());

        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
