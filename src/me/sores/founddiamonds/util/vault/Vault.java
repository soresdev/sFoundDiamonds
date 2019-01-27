package me.sores.founddiamonds.util.vault;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Created by sores on 1/27/2019.
 */
public class Vault {

    private static Economy economy;

    public static boolean setupEconomy(){
        if(Bukkit.getPluginManager().getPlugin("Vault") == null) return false;

        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        if(rsp == null) return false;

        economy = rsp.getProvider();
        return economy != null;
    }


    public static Economy getEconomy() {
        return economy;
    }
}
