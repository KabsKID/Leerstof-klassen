package be.odisee.jazzclub;

/**
 * De mogelijke statussen van de reservatie
 *
 * nodig voor:
 * REQ0018 - Bij geldig lidmaatschap kan de plaatsreservatie vastgelegd worden
 * REQ0019 - Bij vervallen lidmaatschap kan de plaatsreservatie onder voorbehoud vastgelegd worden
 */
public enum ReservatieStatus {
    DEFINITIEF,
    ONDER_VOORBEHOUD
}
