package ru.urlShortCutter.spring.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class UrlCutter {
    private static final String ALLOWED_BASE6_2ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final char[] ALLOWED_BASE62_ALPHABET_CHARS = ALLOWED_BASE6_2ALPHABET.toCharArray();
    private static final int BASE = ALLOWED_BASE62_ALPHABET_CHARS.length;
    private static final int SHORT_URL_LENGTH = 6;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static int getShortUrlLength() {
        return SHORT_URL_LENGTH;
    }

    public static String getAllowedBase62AlphabetString() {
        return ALLOWED_BASE6_2ALPHABET;
    }

    public static char[] getAllowedBase62AlphabetChars() {
        return ALLOWED_BASE62_ALPHABET_CHARS;
    }

    public static int getBase() {
        return BASE;
    }

    private static List<Integer> getBase62IndexesList(int sourceValue) {
        int number = sourceValue;
        List<Integer> alphabet62Indexes = new ArrayList<>();
        while (number != 0) {
            int remainder = number % UrlCutter.getBase();
            number = number / UrlCutter.getBase();
            alphabet62Indexes.add(remainder);
        }
        alphabet62Indexes.sort(Comparator.reverseOrder());
        return alphabet62Indexes;
    }

    private static StringBuilder getBase62String(int sourceValue) {
        List<Integer> alphabet62Indexes = getBase62IndexesList(sourceValue);
        StringBuilder buffer = new StringBuilder();
        for (int val : alphabet62Indexes) {
            buffer.append(ALLOWED_BASE62_ALPHABET_CHARS[val]);
        }
        return buffer;
    }

    public static String createShortUrl(String url) {
        StringBuilder buffer = new StringBuilder();
        int randomInt;
        for (char ignored : url.toCharArray()) {
            randomInt = SECURE_RANDOM.nextInt(1000);
            buffer.append(getBase62String(randomInt));
        }
        String res = new String(buffer);
        res = res.substring(0, SHORT_URL_LENGTH);
        return res;
    }
}


