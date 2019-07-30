package me.sores.founddiamonds.listeners;

import me.sores.founddiamonds.FoundDiamonds;
import me.sores.founddiamonds.config.Config;
import me.sores.founddiamonds.config.Lang;
import me.sores.founddiamonds.player.PlayerData;
import me.sores.founddiamonds.player.PlayerDataHandler;
import me.sores.founddiamonds.player.data.PlayerOreData;
import me.sores.founddiamonds.util.StringUtil;
import me.sores.founddiamonds.util.vault.Vault;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by sores on 1/24/2019.
 */
public class Listener_orelistener implements Listener{

    private FixedMetadataValue foundMeta;
    private FixedMetadataValue placedMeta;

    public Listener_orelistener(FoundDiamonds foundDiamonds) {
        this.foundMeta = new FixedMetadataValue(foundDiamonds, "FOUND");
        this.placedMeta = new FixedMetadataValue(foundDiamonds, "PLACED");
    }

    private int getNear(Location location) {
        int amount = 0;

        if (location.getBlock().getType() == Material.DIAMOND_ORE) {

            for (int x = -3; x < 3; x++) {
                for (int y = -3; y < 3; y++) {
                    for (int z = -3; z < 3; z++) {
                        Location location1 = location.clone().add(x, y, z);
                        if (location1.getBlock() != null) {
                            if (location1.getBlock().getType() == Material.DIAMOND_ORE) {
                                Block block = location1.getBlock();
                                block.setMetadata("FOUND", foundMeta);

                                amount++;
                            }
                        }
                    }
                }
            }
        }
        return amount;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        Block block = event.getBlockPlaced();

        switch (block.getType()){
            case DIAMOND_ORE:
            case IRON_ORE:
            case GOLD_ORE:
            case COAL_ORE:
            case REDSTONE_ORE:
            case LAPIS_ORE:
            case EMERALD_ORE:
            case STONE:

                block.setMetadata("PLACED", placedMeta);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        Block block = event.getBlock();
        PlayerData data = PlayerDataHandler.getInstance().getFrom(player.getUniqueId());
        PlayerOreData oreData = data.getOreData();

        if(player.getGameMode() != GameMode.SURVIVAL || block.hasMetadata("PLACED")) return;

        switch (block.getType()){
            case DIAMOND_ORE:{
                if(!block.hasMetadata("FOUND")){
                    int found = getNear(block.getLocation());
                    String number = String.valueOf(found);

                    oreData.setDiamonds(oreData.getDiamonds() + found);

                    for(Player online : Bukkit.getOnlinePlayers()){
                        if(Config.BROADCAST_ENABLED && online.hasPermission("sfd.broadcastsee")){
                            online.sendMessage(StringUtil.color(Lang.PREFIX + Lang.FD_MESSAGE.replace("%player%", player.getName()).replace("%amount%", number))
                            .replace("%replace%", found > 1 ? "diamonds" : "diamond"));
                        }
                    }

                    if(Config.COMMAND_ENABLED){
                        CommandSender console = Bukkit.getConsoleSender();

                        for(String command : Config.COMMAND_STRINGS){
                            Bukkit.dispatchCommand(console, command.replace("%player%", player.getName()));
                        }
                        StringUtil.log("&a[sFoundDiamonds] Commands executed.");
                    }

                    if(Config.REWARD_ENABLED){
                        int reward = found * Config.REWARD_MULT;
                        String output = String.valueOf(reward);


                        Vault.getEconomy().depositPlayer(player, reward);
                        player.sendMessage(StringUtil.color(Lang.REWARDED.replace("%money%", output)).replace("%amount%", number));
                    }
                }

                break;
            }
            case IRON_ORE:{
                oreData.setIron(oreData.getIron() + 1);

                break;
            }
            case GOLD_ORE:{
                oreData.setGold(oreData.getGold() + 1);

                break;
            }
            case COAL_ORE:{
                oreData.setCoal(oreData.getCoal() + 1);

                break;
            }
            case REDSTONE_ORE:{
                oreData.setRedstone(oreData.getRedstone() + 1);

                break;
            }
            case GLOWING_REDSTONE_ORE:{
                oreData.setRedstone(oreData.getRedstone() + 1);

                break;
            }
            case LAPIS_ORE:{
                oreData.setLapis(oreData.getLapis() + 1);

                break;
            }
            case EMERALD_ORE:{
                oreData.setEmerald(oreData.getEmerald() + 1);

                break;
            }
            case STONE:{
                oreData.setStone(oreData.getStone() + 1);

                break;
            }
        }
    }


}
