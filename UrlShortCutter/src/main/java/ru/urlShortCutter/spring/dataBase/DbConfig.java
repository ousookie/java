package ru.urlShortCutter.spring.dataBase;

public class DbConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/urls_database";
    private static final String NAME = "postgres";
    private static final String PASSWORD = "lol2943527";

    public static String getURL() {
        return URL;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
}


