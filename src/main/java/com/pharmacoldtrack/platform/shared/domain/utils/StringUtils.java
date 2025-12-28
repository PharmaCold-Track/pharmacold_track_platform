package com.pharmacoldtrack.platform.shared.domain.utils;

public class StringUtils {

    public static String toSnakeCase(String text) {
        if (text == null) return null;
        return text.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }

    public static String pluralize(String text) {
        if (text == null) return null;
        if (text.endsWith("s")) return text;
        if (text.endsWith("y") && !text.endsWith("ay") && !text.endsWith("ey") && !text.endsWith("oy") && !text.endsWith("uy")) {
            return text.substring(0, text.length() - 1) + "ies";
        }
        return text + "s";
    }
}