package be.odisee.jazzclub;

import java.time.LocalDate;

/**
 * Lid van de Jazzclub,
 *
 * heeft een lidnummer, paswoord en het aantal foute pogingen tot bekendmaking
 * die werden uitgevoerd sinds de laatste succesvolle bekendmaking
 *
 * speelt mee in:
 * REQ0007 Het aantal foute bekendmakingspogingen moet bijgehouden worden
 * REQ0003 Indien er voordien voor dit lidnummer teveel foute pogingen gebeurden, onstaat een foutconditie
 * REQ0004 Bij succesvolle bekendmaking wordt het aantal foute pogingen voor dat lid terug op nul gezet
 * REQ0006 Bij niet succesvolle bekendmaking, wordt het aantal pogingen voor dat lidnummer verhoogd als het bestaat
 *
 * verantwoordelijk voor:
 * REQ0016 - Er kan nagegaan worden of het lidmaatschap geldig is op de datum van het geselecteerde concert
 * REQ0017 - De status van het lidmaatschap op concertdatum is bekend
 */
public class Lid {

    private int lidnummer;
    private String paswoord;

    private int aantalFoutePogingen;

    private LocalDate geldigTot;

    public Lid(int lidnummer, String paswoord, LocalDate geldigTot) {
        this.lidnummer = lidnummer;
        this.paswoord = paswoord;
        this.geldigTot = geldigTot;
    }

    public int getLidnummer() {
        return lidnummer;
    }

    public String getPaswoord() {
        return paswoord;
    }

    /**
     * Retourneert het aantal foute pogingen
     *
     * speelt mee in:
     * REQ0003 Indien er voordien voor dit lidnummer teveel foute pogingen gebeurden, onstaat een foutconditie
     *
     * @return het aantalFoutePogingen tot nu toe
     */
    public int getAantalFoutePogingen() {
        return aantalFoutePogingen;
    }

    /**
     *
     * REQ0007 Het aantal foute bekendmakingspogingen moet bijgehouden worden
     * REQ0004 Bij succesvolle bekendmaking wordt het aantal foute pogingen voor dat lid terug op nul gezet
     * REQ0006 Bij niet succesvolle bekendmaking, wordt het aantal pogingen voor dat lidnummer verhoogd als het bestaat
     *
     * @param aantalFoutePogingen
     */
    public void setAantalFoutePogingen(int aantalFoutePogingen) {
        this.aantalFoutePogingen = aantalFoutePogingen;
    }

    /**
     * Checkt de status van het lidmaatschap op een bepaalde datum
     *
     * REQ0016 - Er kan nagegaan worden of het lidmaatschap geldig is op de datum van het geselecteerde concert
     * REQ0017 - De status (geldig of vervallen) van het lidmaatschap op concertdatum is bekend
     *
     * @param datum de datum waarop de geldigheid moet worden gecontroleerd
     * @return LidmaatschapStatus.GELDIG of LidmaatschapStatus.VERVALLEN
     */

    public LidmaatschapStatus checkLidmaatschapStatus(LocalDate datum) {
        LidmaatschapStatus lidmaatschapStatus = null;

        if (datum.isEqual(geldigTot) || datum.isBefore(geldigTot)) {
            lidmaatschapStatus = LidmaatschapStatus.GELDIG;
        } else {
            lidmaatschapStatus = LidmaatschapStatus.VERVALLEN;
        }
        return lidmaatschapStatus;
    }
}
