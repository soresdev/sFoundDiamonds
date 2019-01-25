package me.sores.founddiamonds.player;

import me.sores.founddiamonds.FoundDiamonds;
import me.sores.founddiamonds.player.data.PlayerOreData;
import me.sores.founddiamonds.util.StringUtil;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by sores on 1/24/2019.
 */
public class PlayerDataHandler {

    private static PlayerDataHandler instance;

    private FoundDiamonds foundDiamonds;
    private Set<PlayerData> playerData;
    private File dataFolder;

    public PlayerDataHandler(FoundDiamonds foundDiamonds){
        this.foundDiamonds = foundDiamonds;
        instance = this;
        dataFolder = new File(FoundDiamonds.getInstance().getDataFolder() + "/playerData/");

        if(!dataFolder.exists()){
            dataFolder.mkdirs();
        }

        playerData = new HashSet<>();

    }

    public void saveData(PlayerData data) throws IOException{
        File dataFile = new File(dataFolder + "/" + data.getUuid().toString() + ".yml");

        if(!dataFile.exists()){
            dataFile.createNewFile();
        }

        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(dataFile);
        PlayerOreData oreData = data.getOreData();

        configuration.set("ores.diamond", oreData.getDiamonds());
        configuration.set("ores.gold", oreData.getGold());
        configuration.set("ores.iron", oreData.getIron());
        configuration.set("ores.redstone", oreData.getRedstone());
        configuration.set("ores.coal", oreData.getCoal());
        configuration.set("ores.stone", oreData.getStone());
        configuration.set("ores.lapis", oreData.getLapis());
        configuration.set("ores.emerald", oreData.getEmerald());

        configuration.save(dataFile);
    }

    public void loadData(UUID uuid) {
        if(getFrom(uuid) == null){
            PlayerData data = new PlayerData(uuid);
            data.setLoaded(true);
            this.playerData.add(data);

            File dataFile = new File(dataFolder + "/" + data.getUuid().toString() + ".yml");

            if(!dataFile.exists()){
                return;
            }

            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(dataFile);
            PlayerOreData oreData = data.getOreData();

            oreData.setDiamonds(configuration.getInt("ores.diamond"));
            oreData.setGold(configuration.getInt("ores.gold"));
            oreData.setIron(configuration.getInt("ores.iron"));
            oreData.setRedstone(configuration.getInt("ores.redstone"));
            oreData.setCoal(configuration.getInt("ores.coal"));
            oreData.setStone(configuration.getInt("ores.stone"));
            oreData.setLapis(configuration.getInt("ores.lapis"));
            oreData.setEmerald(configuration.getInt("ores.emerald"));

        }
    }

    public void load(){
        for(File file : dataFolder.listFiles()){
            UUID uuid = UUID.fromString(file.getName().replace(".yml", ""));
            loadData(uuid);
        }
    }

    public void save(){
        for(PlayerData data : this.playerData){
            try{
                saveData(data);
            }catch (IOException ex){
                StringUtil.log("&c[FoundDiamonds] Failed to save data of: " + data.getUuid() + " with exception:");
                ex.printStackTrace();
            }
        }
    }

    public PlayerData getFrom(UUID uuid){
        for(PlayerData playerData : this.playerData){
            if(playerData.getUuid().equals(uuid)){
                return playerData;
            }
        }
        return null;
    }

    public Set<PlayerData> getPlayerData() {
        return playerData;
    }

    public static PlayerDataHandler getInstance() {
        return instance;
    }
}
