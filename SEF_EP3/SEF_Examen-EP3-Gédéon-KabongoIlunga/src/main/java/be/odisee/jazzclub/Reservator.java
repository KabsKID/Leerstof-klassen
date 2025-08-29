package be.odisee.jazzclub;


/**
 * De Reservator is verantwoordelijk voor het creëren van het juiste type Plaatsreservatie
 *
 * verantwoordelijk voor:
 * REQ0018 - Bij geldig lidmaatschap kan de plaatsreservatie vastgelegd worden
 * REQ0019 - Bij vervallen lidmaaschap kan de plaatsreservatie onder voorbehoud vastgelegd worden
 */

public class Reservator {

    public Plaatsreservatie maakReservatie(Lid geauthenticeerdLid, Concert geselecteerdConcert) {
        Plaatsreservatie plaatsReservatie=null;

        if (geauthenticeerdLid != null && geselecteerdConcert != null) {
            if (geauthenticeerdLid.checkLidmaatschapStatus(geselecteerdConcert.getConcertDatum())== LidmaatschapStatus.GELDIG) {
                plaatsReservatie = new Plaatsreservatie(geauthenticeerdLid, geselecteerdConcert, ReservatieStatus.DEFINITIEF);
            } else {
                plaatsReservatie = new Plaatsreservatie(geauthenticeerdLid, geselecteerdConcert, ReservatieStatus.ONDER_VOORBEHOUD);
            }
        };
        return plaatsReservatie;
    }
}
