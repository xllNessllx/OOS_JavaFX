package prak4client;

import prak4gemklassen.Benutzer;

import java.io.IOException;

/**
 * Interface für die Benutzerverwaltung
 */
public interface BenutzerVerwaltung {

    /**
     * @see BenutzerVerwaltungAdmin
     */
    abstract void benutzerEintragen(Benutzer benutzer) throws IllegalArgumentException,IOException,ClassNotFoundException;

    /**
     * @see BenutzerVerwaltungAdmin
     */
    abstract boolean benutzerOk(Benutzer benutzer) throws IOException,ClassNotFoundException;
}
