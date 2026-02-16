package be.odisee.jazzclub;

/**
 * Hiermee kunnen we voor een lid een plaatsreservatie voor een concert maken
 * De plaatsreservatie kan vastgelegd zijn of onder voorbehoud
 *
 * doet mee bij:
 * REQ0018 - Bij geldig lidmaatschap kan de plaatsreservatie vastgelegd worden
 * REQ0019 - Bij vervallen lidmaaschap kan de plaatsreservatie onder voorbehoud vastgelegd worden
 */

public class Plaatsreservatie {

    private Lid lid;
    private Concert concert;
    private ReservatieStatus reservatieStatus;

    /**
     * Constructor - creëert een reservatie van het gevraagde type

     * @param lid het lid waarvoor gereserveerd wordt
     * @param concert het concert waarvoor gereserveerd wordt
     * @param reservatieStatus de gewenste status van de plaatsreservatie
     */
    public Plaatsreservatie(Lid lid, Concert concert, ReservatieStatus reservatieStatus) {
        this.lid = lid;
        this.concert = concert;
        this.reservatieStatus= reservatieStatus;
    }

    public ReservatieStatus getReservatieStatus() {
        return reservatieStatus;
    }
}
