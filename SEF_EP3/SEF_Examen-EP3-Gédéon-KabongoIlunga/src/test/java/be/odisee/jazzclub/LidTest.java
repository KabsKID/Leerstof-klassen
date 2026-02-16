package be.odisee.jazzclub;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;


class LidTest {

    private Jazzclub jazzclub;

    @BeforeEach
    public void setUp() {
        // Arrange - check ook de constructor van Jazzclub voor de concrete initialisaties
        jazzclub = new Jazzclub();
        jazzclub.setGeselecteerdConcert(jazzclub.getConcertenLijst().get(1)); // concertdatum 10 oktober
    }



    @Test // REQ0016 en REQ0017
    void checkLidmaatschapStatus_whenGeldigTotIsStriktKleiner_thenReturnVervallen() {
        // Arrange
        Poging poging = new Poging(1, "FrankPW");  // geldig tot 15 september
        jazzclub.getAuthenticator().authenticeer(poging, jazzclub.getLedenLijst());
        Lid geauthenticeerdLid = jazzclub.getAuthenticator().getBekendmaking().getLid();
        LocalDate concertDatum = jazzclub.getGeselecteerdConcert().getConcertDatum();  // 10 oktober
        // Act
        LidmaatschapStatus status = geauthenticeerdLid.checkLidmaatschapStatus(concertDatum);
        // Assert
        assertEquals(LidmaatschapStatus.VERVALLEN, status);
    }

    @Test // REQ0016 en REQ0017
    void checkLidmaatschapStatus_whenGeldigTotStriktGroter_thenReturnGeldig() {
        // Arrange
        Poging poging = new Poging(2, "AnissaPW");  // geldig tot 15 oktober
        jazzclub.getAuthenticator().authenticeer(poging, jazzclub.getLedenLijst());
        Lid geauthenticeerdLid = jazzclub.getAuthenticator().getBekendmaking().getLid();
        LocalDate concertDatum = jazzclub.getGeselecteerdConcert().getConcertDatum();  // 10 oktober
        // Act
        LidmaatschapStatus status = geauthenticeerdLid.checkLidmaatschapStatus(concertDatum);
        // Assert
        assertEquals(LidmaatschapStatus.GELDIG, status);
    }

    @Test // REQ0016 en REQ0017
    void checkLidmaatschapStatus_whenGeldigTotGelijkAanConcertDatum_thenReturnGeldig() {
        // Arrange
        Poging poging = new Poging(3, "HansPW");  // geldig tot 10 oktober
        jazzclub.getAuthenticator().authenticeer(poging, jazzclub.getLedenLijst());
        Lid geauthenticeerdLid = jazzclub.getAuthenticator().getBekendmaking().getLid();
        LocalDate concertDatum = jazzclub.getGeselecteerdConcert().getConcertDatum();  // 10 oktober
        // Act
        LidmaatschapStatus status = geauthenticeerdLid.checkLidmaatschapStatus(concertDatum);
        // Assert
        assertEquals(LidmaatschapStatus.GELDIG, status);
    }
}