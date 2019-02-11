package me.sores.founddiamonds.util;

import me.sores.founddiamonds.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by sores on 1/24/2019.
 */
public class StringUtil {

    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void noPerm(Player player){
        player.sendMessage(color(Lang.NO_PERMISSION));
    }

    public static void log(String message){
        Bukkit.getConsoleSender().sendMessage(color(message));
    }

    public static String intToString(int intt){
        return String.valueOf(intt);
    }

}
