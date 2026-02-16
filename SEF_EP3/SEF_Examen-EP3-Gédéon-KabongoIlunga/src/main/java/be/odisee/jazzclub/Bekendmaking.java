package be.odisee.jazzclub;

/**
 * Een Bekendmaking verwijst naar een succesvolle authenticatie van een lid.<br/>
 *
 * REQ0005 - Het is bekend welk lid zich succesvol bekend gemaakt heeft
 *
 */
public class Bekendmaking {

    private Lid lid=null;

    public Bekendmaking(Lid lid) {
        this.lid = lid;
    }

    /**
     * Retourneert het bekendgemaakte lid
     *
     * REQ0005 - Het is bekend welk lid zich succesvol bekend gemaakt heeft
     *
     * @return
     */
    public Lid getLid() {
        return lid;
    }
}
