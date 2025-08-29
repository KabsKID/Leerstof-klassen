package be.odisee.jazzclub;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * Organisatie met leden die voor concerten kunnen reserveren.
 *
 * De Jazzclub kan de authenticator vragen om na te gaan of een poging tot bekendmaking lukt.
 *
 * REQ0001 - Correcte lidnummer-paswoordcombinaties zijn bekend
 * REQ0005 - Het is bekend welk lid zich succesvol bekend gemaakt heeft
 * REQ0012 - De concerten waarvoor gereserveerd kan worden zijn bekend, inclusief hun bezetting
 *
 * REQ0015 - Het is bekend welk concert het lid geselecteerd heeft
 * REQ0018 - Bij geldig lidmaatschap kan de plaatsreservatie vastgelegd worden
 * REQ0019 - Bij vervallen lidmaatschap kan de plaatsreservatie onder voorbehoud vastgelegd worden
 *
 * De Jazzclub coördineert:
 * - Het beheer van leden (via ledenLijst)
 * - Het beheer van concerten (via concertenLijst)
 * - De authenticatie van leden (via authenticator)
 * - Het maken van plaatsreservaties (via reservator)
 */
public class Jazzclub {

    private List<Lid> ledenLijst;
    private List<Concert> concertenLijst;

    private Authenticator authenticator;
    private Reservator reservator;

    private Concert geselecteerdConcert;

    private List<Plaatsreservatie> plaatsreservatiesLijst;

    /**
     * Constructor - zorgt voor een Jazzclub met authenticator, ledenlijst en concertenlijst
     */
    public Jazzclub() {

        ledenLijst = new ArrayList<Lid>();
        ledenLijst.add(new Lid(1, "FrankPW", LocalDate.of(2025, 9, 15)));
        ledenLijst.add(new Lid(2, "AnissaPW", LocalDate.of(2025, 10, 15)));
        ledenLijst.add(new Lid(3, "HansPW", LocalDate.of(2025, 10, 10)));

        concertenLijst = new ArrayList<Concert>();
        concertenLijst.add(new Concert("Zwornturf", LocalDate.of(2025, 9, 26), 130));
        concertenLijst.add(new Concert("Wilderman", LocalDate.of(2025, 10, 10), 130));
        concertenLijst.add(new Concert("The Untaggables", LocalDate.of(2025, 10, 24), 130));
        concertenLijst.add(new Concert("Olivier Chavet Quartet", LocalDate.of(2025, 11, 14), 130));

        plaatsreservatiesLijst = new ArrayList<Plaatsreservatie>();

        authenticator = new Authenticator();
        reservator = new Reservator();
    }

    /**
     * getter voor
     * REQ0001 Correcte lidnummer-paswoordcombinaties zijn bekend
     *
     * @return de lijst met leden
     */
    public List<Lid> getLedenLijst() {
        return ledenLijst;
    }

    /**
     * Zoek het lid op via zijn lidnummer
     *
     * @param lidnummer dat opgezocht moet worden
     * @return het gezochte Lid-object of null indien niet gevonden
     */
    public Lid getLidById(int lidnummer) {
        for (Lid lid : ledenLijst) {
            if (lid.getLidnummer() == lidnummer) {
                return lid;
            }
        }
        return null;
    }

    /**
     * getter voor
     * REQ0012 De concerten waarvoor gereserveerd kan worden zijn bekend, inclusief hun bezetting
     *
     * @return lijst met concerten waarvoor gereserveerd kan worden
     */
    public List<Concert> getConcertenLijst() {
        return concertenLijst;
    }

    public Authenticator getAuthenticator() {
        return authenticator;
    }

    /**
     * Geeft het momenteel geselecteerde concert terug
     * <p>
     * REQ0015 - Het is bekend welk concert het lid geselecteerd heeft
     *
     * @return het geselecteerde Concert object
     */

    public Concert getGeselecteerdConcert() {
        return geselecteerdConcert;
    }

    public void setGeselecteerdConcert(Concert geselecteerdConcert) {
        this.geselecteerdConcert = geselecteerdConcert;
    }

    public Reservator getReservator() {
        return reservator;
    }

    public List<Plaatsreservatie> getPlaatsreservatiesLijst() {
        return plaatsreservatiesLijst;
    }

    /**
     * Voegt een nieuwe plaatsreservatie toe aan de plaatsreservatiesLijst.
     * Gebruikt de Reservator om het juiste type reservatie te bepalen op basis van de lidmaatschapstatus.
     *
     * Verantwoordelijk voor:
     * REQ0018 - Bij geldig lidmaatschap kan de plaatsreservatie vastgelegd worden
     * REQ0019 - Bij vervallen lidmaatschap kan de plaatsreservatie onder voorbehoud vastgelegd wordt
     *
     * @param lid     Het lid dat de reservatie maakt
     * @param concert Het concert waarvoor gereserveerd wordt
     */
    public void addPlaatsreservatie(Lid lid, Concert concert) {
        Plaatsreservatie nieuweReservatie = reservator.maakReservatie(lid, concert);
        if (nieuweReservatie != null) {
            plaatsreservatiesLijst.add(nieuweReservatie);


        }
    }
}




