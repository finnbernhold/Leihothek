package com.finnbernhold.leihothek.offer;


import org.springframework.util.StringUtils;
public enum Categories {
    ELEKTRISCHE_0GERÄTE,
    HANDWERK,
    HAUS_UND_0GARTEN,
    LITERATUR,
    SPIELE_0SPORT_UND_0FREIZEIT,
    HELFENDE_0HÄNDE,
    HAUSHALT,
    SONSTIGES,
    ZU_VERSCHENKEN;

    public String displayText() {
        return capitalizeString(StringUtils.capitalize(super.toString().toLowerCase().replace("_", " ")));
    }

    public static String capitalizeString(String str) {
        StringBuilder result = new StringBuilder();
        boolean uppercaseNext = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '0') {
                uppercaseNext = true;
            } else if (uppercaseNext) {
                result.append(Character.toUpperCase(c));
                uppercaseNext = false;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
