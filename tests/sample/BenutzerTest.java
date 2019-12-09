package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse Benutzer
 * @see Benutzer
 */
class BenutzerTest {

    Benutzer benutzer1 = new Benutzer();
    Benutzer benutzer2 = new Benutzer();
    Benutzer benutzer3 = new Benutzer("test","test");

    @Test
    public void equalsTest(){
        assertEquals(benutzer1.equals(benutzer2),true);
        assertEquals(benutzer1.equals(benutzer3),false);
    }

    @Test
    public void toStringTest(){
        assertEquals(benutzer1.toString(),"User: default, PW: std_passwort");
        assertEquals(benutzer3.toString(),"User: test, PW: test");
    }

}