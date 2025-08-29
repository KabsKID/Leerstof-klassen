package be.odisee.jazzclub;

/**
 * Een gegeven lidnummer-paswoord-combinatie,
 * gebruikt bij het authenticeren van een lid.
 *
 *  REQ0002 Het lid kan zich met lidnummer en paswoord bekend maken
 */
public class Poging {

    private int lidnummer;
    private String paswoord;

    /**
     * Constructor
     *
     * REQ0002 Het lid kan zich met lidnummer en paswoord bekend maken
     *
     * @param lidnummer
     * @param paswoord
     */
    public Poging(int lidnummer, String paswoord) {
        this.lidnummer = lidnummer;
        this.paswoord = paswoord;
    }

    public int getLidnummer() {
        return lidnummer;
    }

    public String getPaswoord() {
        return paswoord;
    }
}
