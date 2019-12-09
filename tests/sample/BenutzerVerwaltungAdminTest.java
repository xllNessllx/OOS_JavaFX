package sample;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse BenutzerVerwaltungAdmin
 * @see BenutzerVerwaltungAdmin
 */
class BenutzerVerwaltungAdminTest {

    Benutzer benutzer1 = new Benutzer();
    Benutzer benutzer2 = new Benutzer("test","test");
    BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();

    @BeforeEach
    public void init(){
        admin.dbInitialisieren();
    }

    @AfterAll
    public static void cleanup(){
        File f = new File("daten.s");
        f.delete();
    }

    @Test
    public void dbInitialisierenTest(){
        admin.dbInitialisieren();
        File f = new File("daten.s");
        assertTrue(f.isFile() && f.canRead());
    }

    @Test
    public void benutzerEintragenTest(){
        try {
            admin.benutzerEintragen(benutzer1);
            assertTrue(admin.benutzerOk(benutzer1));
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Test
    public void benutzerOKTest(){
        try {
            admin.benutzerEintragen(benutzer1);
            assertTrue(admin.benutzerOk(benutzer1));
            assertFalse(admin.benutzerOk(benutzer2));
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Test
    public void benutzerLöschenTest() throws VektorLeerException {
        try {
            admin.benutzerEintragen(benutzer1);
            admin.benutzerLöschen(benutzer1);
            assertFalse(admin.benutzerOk(benutzer1));
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    //Tests ohne DB:

    @Test
    public void benutzerEintragenTestOhneDB(){
        cleanup();
        assertThrows(IOException.class,() -> {
            admin.benutzerEintragen(benutzer1);
        });
    }

    @Test
    void benutzerOkTestOhneDB() {
        cleanup();
        assertThrows(IOException.class,() -> {
            admin.benutzerEintragen(benutzer1);
        });
    }

    @Test
    void benutzerLöschenTestOhneDB() {
        cleanup();
        assertThrows(IOException.class,() -> {
            admin.benutzerLöschen(benutzer1);
        });
    }

    //Weitere Tests:

    @Test
    public void IllegalArgumentExceptionTest(){
        Benutzer benutzer = new Benutzer("","");
        assertThrows(IllegalArgumentException.class,() -> {
            admin.benutzerEintragen(benutzer);
        });
        assertThrows(IllegalArgumentException.class,() -> {
            admin.benutzerLöschen(benutzer);
        });
    }
}