package me.sores.founddiamonds.player;

import me.sores.founddiamonds.player.data.PlayerOreData;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by sores on 1/24/2019.
 */
public class PlayerData {

    private UUID uuid;
    private boolean loaded;
    private PlayerOreData oreData;


    public PlayerData(UUID uuid) {
        this.uuid = uuid;
        loaded = false;
        oreData = new PlayerOreData();
    }

    public PlayerData(Player player){
        this (player.getUniqueId());
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public PlayerOreData getOreData() {
        return oreData;
    }
}
