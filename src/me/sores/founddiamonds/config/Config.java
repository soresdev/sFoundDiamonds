package me.sores.founddiamonds.config;

/**
 * Created by sores on 1/25/2019.
 */
public class Config {

    public static boolean BROADCAST_ENABLED = true;

    //sings
    public static boolean SIGNS_ENABLED = true;
    public static int SIGN_COOLDOWN = 0;
    public static String SIGN_STRING = "";
    public static String LINE_0 = "";
    public static String LINE_1 = "";
    public static String LINE_2 = "";
    public static String LINE_3 = "";

    public Config() {
        ConfigFile config = new ConfigFile("config.yml");

        BROADCAST_ENABLED = config.getBoolean("broadcast.enabled");
        SIGNS_ENABLED = config.getBoolean("signs.enabled");
        SIGN_COOLDOWN = config.getInt("signs.cooldown");
        SIGN_STRING = config.getString("signs.string");
        LINE_0 = config.getString("signs.line0");
        LINE_1 = config.getString("signs.line1");
        LINE_2 = config.getString("signs.line2");
        LINE_3 = config.getString("signs.line3");

    }
}
