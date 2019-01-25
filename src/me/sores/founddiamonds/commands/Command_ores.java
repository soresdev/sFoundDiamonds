package me.sores.founddiamonds.commands;

import me.sores.founddiamonds.FoundDiamonds;
import me.sores.founddiamonds.config.Lang;
import me.sores.founddiamonds.player.PlayerData;
import me.sores.founddiamonds.player.PlayerDataHandler;
import me.sores.founddiamonds.player.data.PlayerOreData;
import me.sores.founddiamonds.util.PlayerUtil;
import me.sores.founddiamonds.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by sores on 1/25/2019.
 */
public class Command_ores implements CommandExecutor {

    private FoundDiamonds foundDiamonds;

    public Command_ores(FoundDiamonds foundDiamonds) {
        this.foundDiamonds = foundDiamonds;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            PlayerData data = PlayerDataHandler.getInstance().getFrom(player.getUniqueId());
            PlayerOreData oreData = data.getOreData();

            if(!player.hasPermission("sfd.ores")){
                StringUtil.noPerm(player);
                return true;
            }

            if(args.length == 0){
                player.sendMessage(" ");
                player.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "======" + ChatColor.YELLOW + " Ore Stats " + ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "====== ");
                player.sendMessage(ChatColor.AQUA + "Diamonds: " + ChatColor.RESET + oreData.getDiamonds());
                player.sendMessage(ChatColor.GOLD + "Gold: " + ChatColor.RESET + oreData.getGold());
                player.sendMessage(ChatColor.GRAY + "Iron: " + ChatColor.RESET + oreData.getIron());
                player.sendMessage(ChatColor.RED + "Redstone: " + ChatColor.RESET + oreData.getRedstone());
                player.sendMessage(ChatColor.BLUE + "Lapis: " + ChatColor.RESET + oreData.getLapis());
                player.sendMessage(ChatColor.GREEN + "Emerald: " + ChatColor.RESET + oreData.getEmerald());
                player.sendMessage(ChatColor.GRAY + "Stone: " + ChatColor.RESET + oreData.getStone());
                player.sendMessage(" ");
            }else{

                Player target = Bukkit.getPlayer(args[0]);

                if(!PlayerUtil.doesExist(target)){
                    player.sendMessage(StringUtil.color(Lang.NO_PLAYER_FOUND).replace("%player%", target.getName()));
                    return true;
                }

                PlayerData targetData = PlayerDataHandler.getInstance().getFrom(target.getUniqueId());
                PlayerOreData targetOreData = targetData.getOreData();

                player.sendMessage(" ");
                player.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "====== " + ChatColor.YELLOW.toString() + ChatColor.BOLD + target.getName()
                        + "'s Ore Stats " + ChatColor.YELLOW + ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "====== ");
                player.sendMessage(ChatColor.AQUA + "Diamonds: " + ChatColor.RESET + targetOreData.getDiamonds());
                player.sendMessage(ChatColor.GOLD + "Gold: " + ChatColor.RESET + targetOreData.getGold());
                player.sendMessage(ChatColor.GRAY + "Iron: " + ChatColor.RESET + targetOreData.getIron());
                player.sendMessage(ChatColor.RED + "Redstone: " + ChatColor.RESET + targetOreData.getRedstone());
                player.sendMessage(ChatColor.BLUE + "Lapis: " + ChatColor.RESET + targetOreData.getLapis());
                player.sendMessage(ChatColor.GREEN + "Emerald: " + ChatColor.RESET + targetOreData.getEmerald());
                player.sendMessage(ChatColor.GRAY + "Stone: " + ChatColor.RESET + targetOreData.getStone());
                player.sendMessage(" ");
            }
        }
        return false;
    }
}
