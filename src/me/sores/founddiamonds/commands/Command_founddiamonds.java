package me.sores.founddiamonds.commands;

import me.sores.founddiamonds.FoundDiamonds;
import me.sores.founddiamonds.config.Config;
import me.sores.founddiamonds.util.StringUtil;
import me.sores.founddiamonds.util.cooldown.CooldownManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by sores on 1/25/2019.
 */
public class Command_founddiamonds implements CommandExecutor {

    private FoundDiamonds foundDiamonds;
    private CooldownManager cooldownManager;

    public Command_founddiamonds(FoundDiamonds foundDiamonds) {
        this.foundDiamonds = foundDiamonds;
        this.cooldownManager = new CooldownManager();
    }

    private String[] usage = {
            StringUtil.color("&8&m------------------------------------------------"),
            StringUtil.color("&9&lFound Diamonds Usage:"),
            StringUtil.color("&b/fd toggle <on/off> &f- Toggle FoundDiamond Broadcasting on/off."),
            StringUtil.color("&b/fd togglecommand <on/off> &f- Toggle Command Sending on/off."),
            StringUtil.color("&b/fd togglesigns <on/off> &f- Toggle Ore Signs on/off."),
            StringUtil.color("&b/fd togglecooldown <on/off> &f- Toggle Ore Sign cooldown on/off."),
            StringUtil.color("&b/fd setcooldown <int> &f- Set the cooldown time for Ore Signs."),
            StringUtil.color("&b/fd togglereward <on/off> &f- Toggle Reward on/off."),
            StringUtil.color("&b/fd setrewardmult <int> &f- Set the Reward multiplier."),
            StringUtil.color("&b/fd status &f- View current settings for FoundDiamonds."),
            StringUtil.color("&8&m------------------------------------------------")
    };

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(!player.hasPermission("sfd.founddiamonds")){
                StringUtil.noPerm(player);
                return true;
            }

            if(args.length == 0){
                player.sendMessage(usage);
                return true;
            }

            switch (args[0].toLowerCase()){
                case "toggle":{
                    if(args.length < 2){
                        player.sendMessage(StringUtil.color("&cUsage: /fd toggle <on/off>"));
                        return true;
                    }

                    String input = args[1];

                    if(input.toLowerCase().equals("on")){
                        if(Config.BROADCAST_ENABLED){
                            player.sendMessage(StringUtil.color("&cFoundDiamond broadcasting is already enabled."));
                            return true;
                        }else{
                            foundDiamonds.getConfig().set("broadcast.enabled", true);
                            foundDiamonds.saveConfig();
                            new Config();
                            player.sendMessage(StringUtil.color("&7You have toggle FoundDiamond broadcasting to &aon."));
                            return true;
                        }
                    }

                    if(input.toLowerCase().equals("off")){
                        if(!Config.BROADCAST_ENABLED){
                            player.sendMessage(StringUtil.color("&cFoundDiamond broadcasting is already disabled."));
                            return true;
                        }else{
                            foundDiamonds.getConfig().set("broadcast.enabled", false);
                            foundDiamonds.saveConfig();
                            new Config();
                            player.sendMessage(StringUtil.color("&7You have toggle FoundDiamond broadcasting to &coff."));
                            return true;
                        }
                    }

                    if(!input.toLowerCase().equals("on") || !input.toLowerCase().equals("off")){
                        player.sendMessage(StringUtil.color("&cUsage: /fd toggle <on/off>"));
                        return true;
                    }
                }
                case "togglecommand":{
                    if(args.length < 2){
                        player.sendMessage(StringUtil.color("&cUsage: /fd togglecommand <on/off>"));
                        return true;
                    }

                    String input = args[1];

                    if(input.toLowerCase().equals("on")){
                        if(Config.COMMAND_ENABLED){
                            player.sendMessage(StringUtil.color("&cCommand sending is already enabled."));
                            return true;
                        }else{
                            foundDiamonds.getConfig().set("command.enabled", true);
                            foundDiamonds.saveConfig();
                            new Config();
                            player.sendMessage(StringUtil.color("&7You have toggled Command Sending to &aon."));
                            return true;
                        }
                    }

                    if(input.toLowerCase().equals("off")){
                        if(!Config.COMMAND_ENABLED){
                            player.sendMessage(StringUtil.color("&cCommand Sending is already disabled."));
                            return true;
                        }else{
                            foundDiamonds.getConfig().set("command.enabled", false);
                            foundDiamonds.saveConfig();
                            new Config();
                            player.sendMessage(StringUtil.color("&7You have toggled Command Sending to &coff."));
                            return true;
                        }
                    }

                    if(!input.toLowerCase().equals("on") || !input.toLowerCase().equals("off")){
                        player.sendMessage(StringUtil.color("&cUsage: /fd togglecommand <on/off>"));
                        return true;
                    }
                }
                case "togglesigns":{
                    if(args.length < 2){
                        player.sendMessage(StringUtil.color("&cUsage: /fd togglesigns <on/off>"));
                        return true;
                    }

                    String input = args[1];

                    if(input.toLowerCase().equals("on")){
                        if(Config.SIGNS_ENABLED){
                            player.sendMessage(StringUtil.color("&cOre Signs are already enabled."));
                            return true;
                        }else{
                            foundDiamonds.getConfig().set("signs.enabled", true);
                            foundDiamonds.saveConfig();
                            new Config();
                            player.sendMessage(StringUtil.color("&7You have toggled Ore Signs to &aon."));
                            return true;
                        }
                    }

                    if(input.toLowerCase().equals("off")){
                        if(!Config.SIGNS_ENABLED){
                            player.sendMessage(StringUtil.color("&cOre Signs are already disabled."));
                            return true;
                        }else{
                            foundDiamonds.getConfig().set("signs.enabled", false);
                            foundDiamonds.saveConfig();
                            new Config();
                            player.sendMessage(StringUtil.color("&7You have toggled Ore Signs to &coff."));
                            return true;
                        }
                    }

                    if(!input.toLowerCase().equals("on") || !input.toLowerCase().equals("off")){
                        player.sendMessage(StringUtil.color("&cUsage: /fd togglesigns <on/off>"));
                        return true;
                    }
                }
                case "togglecooldown":{
                    if(args.length < 2){
                        player.sendMessage(StringUtil.color("&cUsage: /fd togglecooldown <on/off>"));
                        return true;
                    }

                    String input = args[1];

                    if(input.toLowerCase().equals("on")){
                        if(Config.SIGN_COOLDOWN_ENABLED){
                            player.sendMessage(StringUtil.color("&cSign cooldowns are already enabled."));
                            return true;
                        }else{
                            foundDiamonds.getConfig().set("signs.cooldown.enabled", true);
                            foundDiamonds.saveConfig();
                            new Config();
                            player.sendMessage(StringUtil.color("&7You have toggled Ore Sign cooldowns to &aon."));
                            return true;
                        }
                    }

                    if(input.toLowerCase().equals("off")){
                        if(!Config.SIGN_COOLDOWN_ENABLED){
                            player.sendMessage(StringUtil.color("&cSign cooldowns are already disabled."));
                            return true;
                        }else{
                            foundDiamonds.getConfig().set("signs.cooldown.enabled", false);
                            foundDiamonds.saveConfig();
                            new Config();
                            player.sendMessage(StringUtil.color("&7You have toggled Ore Sign cooldowns to &coff."));

                            cooldownManager.clean(); //anyone on cooldown at time it's disabled will be cleared
                            return true;
                        }
                    }

                    if(!input.toLowerCase().equals("on") || !input.toLowerCase().equals("off")){
                        player.sendMessage(StringUtil.color("&cUsage: /fd togglecooldown <on/off>"));
                        return true;
                    }
                }
                case "setcooldown":{
                    if(args.length < 2){
                        player.sendMessage(StringUtil.color("&cUsage: /fd setcooldown <int>"));
                        return true;
                    }

                    try {
                        int cooldown = Integer.parseInt(args[1]);

                        if(cooldown == Config.SIGN_COOLDOWN_TIME){
                            player.sendMessage(StringUtil.color("&cThe sign cooldown is already set to " + cooldown + "."));
                            return true;
                        }

                        foundDiamonds.getConfig().set("signs.cooldown.time", cooldown);
                        foundDiamonds.saveConfig();
                        new Config();
                        player.sendMessage(StringUtil.color("&7You have set the Ore Sign cooldown to &a" + cooldown + "."));
                        return true;
                    }catch (NumberFormatException ex){
                        player.sendMessage(StringUtil.color("&cYou must input a proper number."));
                        return true;
                    }
                }
                case "togglereward":{
                    if(args.length < 2){
                        player.sendMessage(StringUtil.color("&cUsage: /fd togglereward <on/off>"));
                        return true;
                    }

                    String input = args[1];

                    if(input.toLowerCase().equals("on")){
                        if(Config.REWARD_ENABLED){
                            player.sendMessage(StringUtil.color("&cRewards are already enabled."));
                            return true;
                        }else{
                            foundDiamonds.getConfig().set("reward.enabled", true);
                            foundDiamonds.saveConfig();
                            new Config();
                            player.sendMessage(StringUtil.color("&7You have toggled Rewards to &aon."));
                            return true;
                        }
                    }

                    if(input.toLowerCase().equals("off")){
                        if(!Config.REWARD_ENABLED){
                            player.sendMessage(StringUtil.color("&cRewards are already disabled."));
                            return true;
                        }else{
                            foundDiamonds.getConfig().set("reward.enabled", false);
                            foundDiamonds.saveConfig();
                            new Config();
                            player.sendMessage(StringUtil.color("&7You have toggled Rewards to &coff."));
                            return true;
                        }
                    }

                    if(!input.toLowerCase().equals("on") || !input.toLowerCase().equals("off")){
                        player.sendMessage(StringUtil.color("Usage: /fd togglereward <on/off>"));
                        return true;
                    }
                }
                case "setrewardmult":{
                    if(args.length < 2){
                        player.sendMessage(StringUtil.color("&cUsage: /fd setrewardmult <int>"));
                        return true;
                    }

                    try {
                        int mult = Integer.parseInt(args[1]);

                        if(mult == Config.REWARD_MULT){
                            player.sendMessage(StringUtil.color("&cReward mult is already set to " + mult));
                            return true;
                        }

                        foundDiamonds.getConfig().set("reward.mult", mult);
                        foundDiamonds.saveConfig();
                        new Config();
                        player.sendMessage(StringUtil.color("&7You have set the Reward Mult to &a" + mult));
                        return true;
                    }catch (NumberFormatException ex){
                        player.sendMessage(StringUtil.color("&cYou must input a proper number."));
                        return true;
                    }
                }
                case "status":{

                    String[] status = {
                            StringUtil.color("&8&m------------------------------------------------"),
                            StringUtil.color("&9&lFound Diamonds Status:"),
                            StringUtil.color("&bBroadcasting: &f" + Config.BROADCAST_ENABLED),
                            StringUtil.color("&bCommand Sending: &f" + Config.COMMAND_ENABLED),
                            StringUtil.color("&bOre Signs: &f" + Config.SIGNS_ENABLED),
                            StringUtil.color("&bOre Sign Cooldown: &f" + Config.SIGN_COOLDOWN_ENABLED),
                            StringUtil.color("&bOre Sign Cooldown Time: &f" + Config.SIGN_COOLDOWN_TIME),
                            StringUtil.color("&bReward: &f" + Config.REWARD_ENABLED),
                            StringUtil.color("&bReward Mult: &f" + Config.REWARD_MULT),
                            StringUtil.color("&8&m------------------------------------------------")

                    };

                    player.sendMessage(status);
                    return true;
                }
                default:{
                    player.sendMessage(usage);
                }
            }
        }
        return false;
    }
}
