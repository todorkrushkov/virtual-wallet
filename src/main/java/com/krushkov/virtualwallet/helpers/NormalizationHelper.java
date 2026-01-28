package com.krushkov.virtualwallet.helpers;

public final class NormalizationHelper {

    private NormalizationHelper() {}

    public static String normalizeStringToLower(String value) {
        return safeLower(value);
    }

    private static String safeLower(String value) {
        return value == null ? null : value.trim().toLowerCase();
    }
}
