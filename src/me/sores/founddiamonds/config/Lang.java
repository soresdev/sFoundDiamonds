package me.sores.founddiamonds.config;

/**
 * Created by sores on 1/25/2019.
 */
public class Lang {

    public static String NO_PERMISSION = "";
    public static String NO_PLAYER_FOUND = "";
    public static String PREFIX = "";
    public static String FD_MESSAGE = "";
    public static String SIGN_COOLDOWN_MESSAGE;
    public static String SIGN_DISABLED;
    public static String REWARDED;

    public Lang() {
        ConfigFile lang = new ConfigFile("lang.yml");

        NO_PERMISSION = lang.getString("NO_PERMISSION");
        NO_PLAYER_FOUND = lang.getString("NO_PLAYER_FOUND");
        PREFIX = lang.getString("PREFIX");
        FD_MESSAGE = lang.getString("FD_MESSAGE");
        SIGN_COOLDOWN_MESSAGE = lang.getString("SIGN_COOLDOWN_MESSAGE");
        SIGN_DISABLED = lang.getString("SIGN_DISABLED");
        REWARDED = lang.getString("REWARDED");
    }
}
