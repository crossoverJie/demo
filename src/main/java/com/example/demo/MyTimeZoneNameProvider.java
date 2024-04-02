package com.example.demo;

import java.util.Locale;
import java.util.spi.TimeZoneNameProvider;

public class MyTimeZoneNameProvider extends TimeZoneNameProvider {

    @Override
    public String getDisplayName(String ID, boolean daylight, int style, Locale locale) {
        if ("America/Los_Angeles".equals(ID)) {
            return "洛杉矶时间";
        } else {
            return "";
        }
    }

    @Override
    public Locale[] getAvailableLocales() {
        return new Locale[0];
    }
}