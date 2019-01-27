package me.sores.founddiamonds.util.cooldown;

import com.google.common.collect.Maps;
import me.sores.founddiamonds.config.Config;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by sores on 1/25/2019.
 */
public class CooldownManager {

    public HashMap<UUID, Long> cooldown;

    public CooldownManager() {
        this.cooldown = Maps.newHashMap();
    }

    public int getTimeLeft(Player player){
        return Math.toIntExact(Math.round((cooldown.get(player.getUniqueId()) - System.currentTimeMillis())/1000));
    }

    public boolean hasCooldown(UUID uuid){
        return cooldown.containsKey(uuid) && cooldown.get(uuid) > System.currentTimeMillis();
    }

    public void removeCooldown(UUID uuid){
        cooldown.remove(uuid);
    }

    public void addCooldown(UUID uuid, Long time){
        cooldown.put(uuid, time);
    }
}
