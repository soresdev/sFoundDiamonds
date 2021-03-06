package me.sores.founddiamonds;

import me.sores.founddiamonds.commands.Command_founddiamonds;
import me.sores.founddiamonds.commands.Command_ores;
import me.sores.founddiamonds.commands.Command_reloadconfig;
import me.sores.founddiamonds.commands.Command_reloadlang;
import me.sores.founddiamonds.config.Config;
import me.sores.founddiamonds.config.Lang;
import me.sores.founddiamonds.listeners.Listener_orelistener;
import me.sores.founddiamonds.listeners.Listener_playerlistener;
import me.sores.founddiamonds.player.PlayerDataHandler;
import me.sores.founddiamonds.util.StringUtil;
import me.sores.founddiamonds.util.cooldown.CooldownManager;
import me.sores.founddiamonds.util.vault.Vault;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by sores on 1/24/2019.
 */
public class FoundDiamonds extends JavaPlugin {

    private static FoundDiamonds instance;
    private PlayerDataHandler handler;
    private CooldownManager cooldownManager;

    @Override
    public void onEnable(){
        instance = this;

        if(!Vault.setupEconomy()){
            StringUtil.log("&c[sFoundDiamonds] Plugin Disabled - Missing Vault Dependency");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        initClasses();
        new Config();
        new Lang();

        handler.load();

        registerCommands();
        registerListeners();

        StringUtil.log("&a[sFoundDiamonds] Enabled FoundDiamonds by sores");
    }

    @Override
    public void onDisable(){
        instance = null;
        handler.save();
    }

    public void initClasses(){
        handler = new PlayerDataHandler(instance);
        cooldownManager = new CooldownManager();
    }

    public void registerListeners(){
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new Listener_orelistener(this), this);
        pm.registerEvents(new Listener_playerlistener(), this);
    }

    public void registerCommands(){
        getCommand("ores").setExecutor(new Command_ores(this));
        getCommand("founddiamonds").setExecutor(new Command_founddiamonds(this));
        getCommand("fdreloadconfig").setExecutor(new Command_reloadconfig(this));
        getCommand("fdreloadlang").setExecutor(new Command_reloadlang(this));
    }

    public static FoundDiamonds getInstance() {
        return instance;
    }
}
