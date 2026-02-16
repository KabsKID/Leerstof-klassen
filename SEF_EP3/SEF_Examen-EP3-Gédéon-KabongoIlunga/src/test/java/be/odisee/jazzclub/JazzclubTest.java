package be.odisee.jazzclub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JazzclubTest {

    Jazzclub jazzclub;

    @BeforeEach
    public void setUp() {
        // Arrange - check ook de constructor van Jazzclub voor de concrete initialisaties
        jazzclub = new Jazzclub();
        jazzclub.setGeselecteerdConcert(jazzclub.getConcertenLijst().get(1)); // tweede concert is geselecteerd
    }

    @Test // REQ0001
    public void testInitJazzclubLeden() {
        // Act
        List<Lid> ledenlijst = jazzclub.getLedenLijst();
        // Assert
        assertNotNull(ledenlijst);
        assertNotEquals(ledenlijst.size(), 0);
    }

    @Test // De methode getLidById is onrechtstreeks nodig voor verschillende REQs
    public void getLidById_WhenNietBeschikbaar_ThenReturnsNull() {
        // Arrange
        // De data setup zorgt ervoor dat er geen lid met lidnummer 99 bestaat
        // Act
        Lid gevondenLid = jazzclub.getLidById(99);
        // Assert
        assertNull(gevondenLid);
    }

    @Test // De methode getLidById is onrechtstreeks nodig voor verschillende REQs
    public void getLidById_WhenBeschikbaar_ThenReturnsLidObjectMetZelfdeLidnummer() {
        // Arrange
        // De data setup zorgt ervoor dat er geen lid met lidnummer 2 bestaat
        // Act
        Lid gevondenLid = jazzclub.getLidById(2);
        // Assert
        assertNotNull(gevondenLid);
        assertEquals(2, gevondenLid.getLidnummer());
    }

    @Test // REQ0012
    public void testInitJazzclubConcerten() {
        // Act
        List<Concert> concertenlijst = jazzclub.getConcertenLijst();
        // Assert
        assertNotNull(concertenlijst);
        assertNotEquals(concertenlijst.size(), 0);
    }

    @Test // REQ0015
    public void getGeselecteerdConcert_ReturnsBeschikbaarConcert () {
        // Act
        Concert geselecteerdConcert = jazzclub.getGeselecteerdConcert();
        // Assert
        assertTrue(jazzclub.getConcertenLijst().contains(geselecteerdConcert));
        assertNotNull(geselecteerdConcert);
        assertEquals(geselecteerdConcert, jazzclub.getConcertenLijst().get(1)); // We weten dat het tweede concert geselecteerd is in setUp

    }

    @Test // REQ0018 en REQ0019
    public void addPlaatsreservatie_whenPlaatsreservatieAangeboden_ThenPlaatsreservatieInLijst() {
        // Arrange
        Poging poging = new Poging(3, "HansPW");  // geldig tot 10 oktober
        jazzclub.getAuthenticator().authenticeer(poging, jazzclub.getLedenLijst());
        Lid geauthenticeerdLid = jazzclub.getAuthenticator().getBekendmaking().getLid();
        int oorspronkelijkAantalReservaties = jazzclub.getPlaatsreservatiesLijst().size();
        // Act
        jazzclub.addPlaatsreservatie(geauthenticeerdLid, jazzclub.getGeselecteerdConcert());
        // Assert
        assertEquals(oorspronkelijkAantalReservaties + 1, jazzclub.getPlaatsreservatiesLijst().size());
        Plaatsreservatie laatsteReservatie = jazzclub.getPlaatsreservatiesLijst().get(jazzclub.getPlaatsreservatiesLijst().size() - 1);
        assertEquals(geauthenticeerdLid, laatsteReservatie.getLid());
        assertEquals(jazzclub.getGeselecteerdConcert(), laatsteReservatie.getConcert());

    }
}

///
