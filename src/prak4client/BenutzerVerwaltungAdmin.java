package prak4client;

import prak4gemklassen.*;

import java.io.*;
import java.util.Vector;

/**
 * Klasse die Benutzer in einer Datenhaltung speichert
 * und diese verwaltet
 */
public class BenutzerVerwaltungAdmin implements BenutzerVerwaltung, Serializable {

    /**
     * Datenhaltung in der die Nutzer gespeichert werden
     */
    private Vector<Benutzer> ben_List;

    /**
     * Default Konstruktor
     * Initialisiert den Vektor
     */
    BenutzerVerwaltungAdmin(){
        ben_List = new Vector<Benutzer>();
    }

    /**
     * Initialisiert die persistente Datenstruktur
     */
    void dbInitialisieren(){
        try{
            FileOutputStream fs = new FileOutputStream("daten_client.s");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(ben_List);
            os.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Fügt der Datenhaltung ein Objekt vom Typ Benutzer hinzu
     * @param benutzer                          :Der Benutzer, der Eingefügt werden soll
     * @throws IllegalArgumentException      :Objekt ist nicht vom Typ Benutzer
     * @throws NullPointerException          :Das Objekt ist NULL
     */
    public void benutzerEintragen (Benutzer benutzer) throws IllegalArgumentException,IOException,ClassNotFoundException{
        if(benutzer.getPW().length() == 0){
            throw  new IllegalArgumentException();
        }
        if(benutzerOk(benutzer)) {
            throw new IOException();
        }
            FileInputStream fis = new FileInputStream("daten_client.s");
            ObjectInputStream is = new ObjectInputStream(fis);
            ben_List = (Vector<Benutzer>) is.readObject();
            is.close();
            ben_List.add(benutzer);
            FileOutputStream fos = new FileOutputStream("daten_client.s");
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(ben_List);
            os.close();
    }

    /**
     * Überprüft ob sich der Benutzer in der Datenhaltung befindet
     * @param benutzer  :Benutzer der überprüft werden soll
     * @return          :True, wenn Benutzer existiert. False, wenn nicht
     */
    public boolean benutzerOk(Benutzer benutzer) throws IOException,ClassNotFoundException{
            FileInputStream fis = new FileInputStream("daten_client.s");
            ObjectInputStream is = new ObjectInputStream(fis);
            ben_List = (Vector<Benutzer>) is.readObject();
            is.close();
            boolean result = ben_List.contains(benutzer);
            FileOutputStream fos = new FileOutputStream("daten_client.s");
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(ben_List);
            os.close();
            return result;
    }

    /**
     * Löscht einen Benutzer aus der Datenhaltung
     * @param benutzer                      :Benutzer der gelöscht werden soll
     * @throws IllegalArgumentException     :Eingegebenes Objekt ist kein Benutzer
     * @throws NullPointerException         :Datenhaltung ist leer
     */
    void benutzerLöschen(Benutzer benutzer) throws IllegalArgumentException,VektorLeerException,IOException,ClassNotFoundException {
        if(benutzer.getPW().length() == 0){
            throw new IllegalArgumentException();
        }
            FileInputStream fis = new FileInputStream("daten_client.s");
            ObjectInputStream is = new ObjectInputStream(fis);
            ben_List = (Vector<Benutzer>) is.readObject();
            if(ben_List.isEmpty()){
                throw new VektorLeerException();
            }
            is.close();
            ben_List.remove(benutzer);
            FileOutputStream fos = new FileOutputStream("daten_client.s");
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(ben_List);
            os.close();
    }

    /**
     * Zeigt alle Benutzer in der Datenhaltung
     */
    public void showUsers() {
        for(Benutzer element: ben_List ) {
            System.out.println(element.toString());
        }
    }
}
