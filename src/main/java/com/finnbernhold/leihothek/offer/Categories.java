package com.finnbernhold.leihothek.offer;


import org.springframework.util.StringUtils;
public enum Categories {
    ELEKTRISCHE_GERÄTE,
    HANDWERK,
    HAUS_UND_GARTEN,
    LITERATUR,
    SPIELE_SPORT_UND_FREIZEIT,
    HELFENDE_HÄNDE,
    HAUSHALT;

    public String displayText() {
        return StringUtils.capitalize(super.toString().toLowerCase().replace("_", " "));
    }
}
