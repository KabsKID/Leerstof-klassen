package be.odisee.jazzclub;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticatorTest {

    Jazzclub jazzclub;
    Authenticator authenticator;
    List<Lid> ledenLijst;

    @BeforeEach
    void setUp() {
        jazzclub = new Jazzclub();
        authenticator = jazzclub.getAuthenticator();
        ledenLijst = jazzclub.getLedenLijst();
    }

    @Test // REQ0003
    void authenticeer_WhenTeveelFoutePogingen_ThenFoutconditie() {
        // Arrange
        Poging poging = new Poging(2, "VerkeerdPW");
        // 3 keer fout voordien = teveel
        for (int i = 0; i < 3; i++) {
            authenticator.authenticeer(poging, ledenLijst);
        }
        // Act
        poging = new Poging(2, "AnissaPW");
        // Assert
        assertFalse(authenticator.authenticeer(poging, ledenLijst));
        assertNull(authenticator.getBekendmaking());
    }

    @Test // REQ0004 - deel 1 ... alsook REQ0006 en REQ0007
    void authenticeer_WhenFouten_ThenAantalFoutePogingenNietNul() {
        // Arrange
        Poging poging = new Poging(2, "VerkeerdPW");
        // 2 keer fout ervoor = niet te veel
        for (int i = 0; i < 2; i++) {
            authenticator.authenticeer(poging, ledenLijst);
        }
        // Act
        Lid lid = jazzclub.getLidById(2);
        // Assert
        assertNotEquals(0, lid.getAantalFoutePogingen());
        assertEquals(2, lid.getAantalFoutePogingen());
    }

    @Test // REQ0004 - deel 2
    void authenticeer_WhenNaNietTeveelFoutenTochJuist_ThenAantalFoutePogingenNul() {
        // Arrange
        Poging poging = new Poging(2, "VerkeerdPW");
        // 2 keer fout ervoor = niet te veel
        for (int i = 0; i < 2; i++) {
            authenticator.authenticeer(poging, ledenLijst);
        }
        // Act
        poging = new Poging(2, "AnissaPW");
        authenticator.authenticeer(poging, ledenLijst);
        // Assert
        assertEquals(0, jazzclub.getLidById(2).getAantalFoutePogingen());
    }

    @Test // REQ0005
    void authenticeer_WhenAuthSucces_ThenReturnsTrueEnAuthLidBekend() {
        // Arrange
        Poging poging = new Poging(3, "HansPW");
        // Act
        boolean resultAuth = authenticator.authenticeer(poging, ledenLijst);
        // Assert
        assertTrue(resultAuth);
        assertTrue(authenticator.getBekendmaking().getLid().getLidnummer() == 3);
    }
}