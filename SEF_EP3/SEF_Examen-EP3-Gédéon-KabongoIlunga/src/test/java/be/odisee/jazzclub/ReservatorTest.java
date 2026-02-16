package be.odisee.jazzclub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservatorTest {

    Jazzclub jazzclub;
    Reservator reservator;

    @BeforeEach
    void setUp() {
        // Arrange
        jazzclub = new Jazzclub();
        reservator = jazzclub.getReservator();
        jazzclub.setGeselecteerdConcert(jazzclub.getConcertenLijst().get(1)); // concertdatum 10 oktober
    }


    @Test //REQ0018
    void maakReservatie_whenLidmaatschapGeldig_ThenReservatieDefinitief() {
        // Arrange
        Poging poging = new Poging(2, "AnissaPW");  // geldig tot 15 oktober
        jazzclub.getAuthenticator().authenticeer(poging, jazzclub.getLedenLijst());
        Lid geauthenticeerdLid = jazzclub.getAuthenticator().getBekendmaking().getLid();
        Concert geselecteerdConcert = jazzclub.getGeselecteerdConcert();  // concertdatum 10 oktober
        // Act
        Plaatsreservatie reservatie = reservator.maakReservatie(geauthenticeerdLid, geselecteerdConcert);
        // Assert
        assertNotNull(reservatie);
        assertEquals(ReservatieStatus.VASTGELEGD, reservatie.getReservatieStatus());

    }

    @Test //REQ0019
    void maakReservatie_whenLidmaatschapVervallen_ThenReservatieOnderVoorbehoud() {
        // Arrange
        Poging poging = new Poging(1, "FrankPW");  // geldig tot 15 september
        jazzclub.getAuthenticator().authenticeer(poging, jazzclub.getLedenLijst());
        Lid geauthenticeerdLid = jazzclub.getAuthenticator().getBekendmaking().getLid();
        Concert geselecteerdConcert = jazzclub.getGeselecteerdConcert();  // concertdatum 10 oktober
        // Act
        Plaatsreservatie reservatie = reservator.maakReservatie(geauthenticeerdLid, geselecteerdConcert);
        // Assert
        assertNotNull(reservatie);
        assertEquals(ReservatieStatus.ONDER_VOORBEHOUD, reservatie.getReservatieStatus());

    }
}

