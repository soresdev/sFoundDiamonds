package me.sores.founddiamonds.commands;

import me.sores.founddiamonds.FoundDiamonds;
import me.sores.founddiamonds.config.Lang;
import me.sores.founddiamonds.util.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by sores on 1/25/2019.
 */
public class Command_reloadlang implements CommandExecutor {

    private FoundDiamonds foundDiamonds;

    public Command_reloadlang(FoundDiamonds foundDiamonds) {
        this.foundDiamonds = foundDiamonds;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(!player.hasPermission("sfd.reloadlang")){
                StringUtil.noPerm(player);
                return true;
            }

            new Lang();
            player.sendMessage(StringUtil.color("&aYou have successfully reloaded the Language File for sFoundDiamonds."));
        }
        return false;
    }

}
