package sample;

import java.io.Serializable;

/**
 * Benutzerdaten
 * Passwort und Nutzername
 */
public class Benutzer implements Serializable {

    /**
     * Nutzername
     */
    String userId;

    /**
     * Passwort
     */
    char[] passWort;

    /**
     * Default Konstruktor
     */
    Benutzer() {
        userId = "default";
        passWort = "std_passwort".toCharArray();
    }

    /**
     * Konstruktor bei dem beide Attribute direkt gesetzt werden
     * @param n_userId      :Username
     * @param n_passWort    :Passwort
     */
    Benutzer(String n_userId, String n_passWort){
        userId = n_userId;
        passWort = n_passWort.toCharArray();
    }

    /**
     * Methode, die ein Objekt mit dem Benutzer vergleicht
     * @param benutzer      :Objekt, dass mit dem Benutzer verglichen wird
     * @return              :True, wenn Objekt gleich ist und False wenn nciht
     */
    public boolean equals(Object benutzer){
        if(benutzer == null || !(benutzer instanceof Benutzer)){
            return false;
        }
        //Kopieren der Char[] in Strings zum einfacheren Vergleich
        String pw = new String(this.passWort);
        String pw2 = new String(((Benutzer) benutzer).passWort);
        //pw.copyValueOf(this.passWort);
        //pw2.copyValueOf(((Benutzer) benutzer).passWort);
        if(this.userId.equals(((Benutzer) benutzer).userId) && pw.equals(pw2)){
            return true;
        }
        return false;
    }

    /**
     * Methode, die den Inhalt der Attribute des Objekts in einen String kopiert
     * @return      :Ein String der Struktur: User: "Nutzername", PW: "Passwort des nutzers"
     */
     public String toString(){
        String pw = new String(passWort);
        //pw.copyValueOf(passWort);
        return "User: " + userId + ", PW: " + pw;
    }

}

