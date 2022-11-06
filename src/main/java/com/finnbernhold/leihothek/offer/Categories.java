package com.finnbernhold.leihothek.offer;


import org.springframework.util.StringUtils;

public enum Categories {
    HANDWERK,
    SPORT,
    SPIELE,
    ELEKTRONIK,
    HAUSHALT;

    public String displayText(){
        return StringUtils.capitalize(super.toString().toLowerCase());
    }
}
