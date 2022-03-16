package com.elouamghari.calculator.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CalculatorUtils {
    public CalculatorUtils() {
    }

    public static float parseFloat(String display) {
        display = display.replace(".", ",");
        Locale EASTERN_ARABIC_NUMBERS_LOCALE = (new Locale.Builder()).setLanguage("ar").setExtension('u', "nu-arab").build();

        try {
            return NumberFormat.getInstance(EASTERN_ARABIC_NUMBERS_LOCALE).parse(display).floatValue();
        } catch (Exception var3) {
            return 0.0F;
        }
    }

    public static float roundFloatToFixedTwoDecimals(float afloat) {
        return (float)((double)Math.round((double)afloat * 100.0D) / 100.0D);
    }

    public static String convertToNonScientific(float f) {
        String fStr = String.format("%.2f", f);
        float parseFloat = parseFloat(fStr);
        String res = String.format(Locale.FRANCE, "%.2f", parseFloat);
        return res;
    }
}
