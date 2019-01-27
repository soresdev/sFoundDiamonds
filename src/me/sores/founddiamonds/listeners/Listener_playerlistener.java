package me.sores.founddiamonds.listeners;

import me.sores.founddiamonds.config.Config;
import me.sores.founddiamonds.config.Lang;
import me.sores.founddiamonds.player.PlayerData;
import me.sores.founddiamonds.player.PlayerDataHandler;
import me.sores.founddiamonds.player.data.PlayerOreData;
import me.sores.founddiamonds.util.StringUtil;
import me.sores.founddiamonds.util.cooldown.CooldownManager;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

/**
 * Created by sores on 1/24/2019.
 */
public class Listener_playerlistener implements Listener {

    private CooldownManager cooldownManager = new CooldownManager();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        PlayerData data = PlayerDataHandler.getInstance().getFrom(player.getUniqueId());


        if(data == null){
            PlayerDataHandler.getInstance().getPlayerData().add(new PlayerData(player));
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        PlayerDataHandler handler = PlayerDataHandler.getInstance();

        try{
            handler.saveData(handler.getFrom(player.getUniqueId()));
        }catch (IOException ex){
            StringUtil.log("&c[FoundDiamonds] Failed to save data of: &f" + player.getName() + "&c with exception: ");
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void onSignCreate(SignChangeEvent event){
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if(block.getState() instanceof Sign){
            Sign sign = (Sign) block.getState();

            if(event.getLine(0).equalsIgnoreCase(StringUtil.color(Config.SIGN_STRING))){

                if(!Config.SIGNS_ENABLED){
                    player.sendMessage(StringUtil.color(Lang.SIGN_DISABLED));
                    return;
                }

                event.setLine(0, StringUtil.color(Config.LINE_0));
                event.setLine(1, StringUtil.color(Config.LINE_1));
                event.setLine(2, StringUtil.color(Config.LINE_2));
                event.setLine(3, StringUtil.color(Config.LINE_3));
                sign.update();
            }
        }
    }

    @EventHandler
    public void onSignClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        PlayerData data = PlayerDataHandler.getInstance().getFrom(player.getUniqueId());
        PlayerOreData oreData = data.getOreData();

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK && block.getState() instanceof Sign){
            Sign sign = (Sign) block.getState();

            if(!Config.SIGNS_ENABLED){
                player.sendMessage(StringUtil.color(Lang.SIGN_DISABLED));
                return;
            }

            if(cooldownManager.hasCooldown(player.getUniqueId())){
                if(cooldownManager.cooldown.get(player.getUniqueId()) > System.currentTimeMillis()){
                    String replaced = String.valueOf(cooldownManager.getTimeLeft(player));
                    player.sendMessage(StringUtil.color(Lang.SIGN_COOLDOWN_MESSAGE).replace("%time%", replaced));
                    return;
                }else{
                    cooldownManager.removeCooldown(player.getUniqueId());
                }
            }

            if(sign.getLine(1).equalsIgnoreCase(StringUtil.color(Config.LINE_1)) && sign.getLine(2).equalsIgnoreCase(StringUtil.color(Config.LINE_2))){

                player.sendMessage(" ");
                player.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "======" + ChatColor.YELLOW + " Ore Stats " + ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "======");
                player.sendMessage(ChatColor.AQUA + "Diamonds: " + ChatColor.RESET + oreData.getDiamonds());
                player.sendMessage(ChatColor.GOLD + "Gold: " + ChatColor.RESET + oreData.getGold());
                player.sendMessage(ChatColor.GRAY + "Iron: " + ChatColor.RESET + oreData.getIron());
                player.sendMessage(ChatColor.RED + "Redstone: " + ChatColor.RESET + oreData.getRedstone());
                player.sendMessage(ChatColor.BLUE + "Lapis: " + ChatColor.RESET + oreData.getLapis());
                player.sendMessage(ChatColor.GREEN + "Emerald: " + ChatColor.RESET + oreData.getEmerald());
                player.sendMessage(ChatColor.GRAY + "Stone: " + ChatColor.RESET + oreData.getStone());
                player.sendMessage(" ");

                cooldownManager.addCooldown(player.getUniqueId(), System.currentTimeMillis() + (Config.SIGN_COOLDOWN * 1000));
            }
        }
    }

}
