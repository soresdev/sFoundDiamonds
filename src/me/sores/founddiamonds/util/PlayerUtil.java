package me.sores.founddiamonds.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by sores on 1/25/2019.
 */
public class PlayerUtil {

    public static boolean doesExist(Player player){
        return Bukkit.getOnlinePlayers().contains(player);
    }
}
