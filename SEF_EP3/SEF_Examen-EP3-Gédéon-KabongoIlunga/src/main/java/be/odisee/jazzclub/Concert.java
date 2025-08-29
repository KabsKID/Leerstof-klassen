package be.odisee.jazzclub;

import java.time.LocalDate;

/**
 * Met behulp van deze klasse kennen we de bijzonderheden van het concert:
 * wie treedt op, wanneer, wat is de capaciteit van de zaal?
 * Deze klasse kan ons ook helpen om te weten of er nog plaatsen vrij zijn
 *
 * Verantwoordelijk voor:
 * REQ0012 - De concerten waarvoor gereserveerd kan worden zijn bekend, inclusief hun bezetting
 * REQ0015 - Het is bekend welk concert het lid geselecteerd heeft (via concertDatum)
 * REQ0016 - De concertdatum is nodig om na te gaan of het lidmaatschap geldig is
 * REQ0017 - De concertdatum wordt gebruikt om de status van het lidmaatschap te bepalen
 */
public class Concert {

    private String bandNaam; // hier zijn voorlopig geen requirements voor
    private LocalDate concertDatum;
    private int capaciteitZaal; // hier zijn voorlopig geen requirements voor

    /**
     *
     * @param bandNaam - naam van de band die gaat optreden
     * @param concertDatum
     * @param capaciteitZaal
     */
    public Concert(String bandNaam, LocalDate concertDatum, int capaciteitZaal) {
        this.bandNaam = bandNaam;
        this.concertDatum = concertDatum;
        this.capaciteitZaal = capaciteitZaal;
    }

    /**
     * getter voor concertDatum voor
     * Verantwoordelijk voor:
     * REQ0018 - Bij geldig lidmaatschap kan de plaatsreservatie vastgelegd worden
     * REQ0019 - Bij vervallen lidmaatschap kan de plaatsreservatie onder voorbehoud vastgelegd worden
     *
     *
     * @return de concertDatum
     */
    public LocalDate getConcertDatum() {
        return concertDatum;
    }

    public void setConcertDatum(LocalDate concertDatum) {
        this.concertDatum = concertDatum;
    }
}
