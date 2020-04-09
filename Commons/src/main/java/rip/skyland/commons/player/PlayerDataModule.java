package rip.skyland.commons.player;

import com.mongodb.Block;
import lombok.EqualsAndHashCode;
import org.bson.Document;
import org.bukkit.Bukkit;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.module.Module;
import rip.skyland.commons.mongo.MongoAPI;
import rip.skyland.commons.player.data.Data;
import rip.skyland.commons.util.MethodUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
public class PlayerDataModule extends Module {

    private final List<PlayerData> playerData = new ArrayList<>();
    private final List<Data> registeredData = new ArrayList<>();

    @Override
    public void enable() {
        super.enable();

        Bukkit.getPluginManager().registerEvents(new PlayerDataListener(), CommonsPlugin.getInstance());

        MethodUtil.executeLater(this::loadProfiles, 20);
    }

    public void loadProfiles() {
        MongoAPI.get().getCollection("global", "profiles").find().forEach((Block<? super Document>) PlayerData::new);

        Bukkit.getOnlinePlayers().stream()
                .filter(player -> this.findPlayerData(player.getUniqueId()) == null)
                .forEach(player -> new PlayerData(player.getUniqueId()));
    }

    @Override
    public void disable() {
        super.disable();

        playerData.forEach(PlayerData::save);
    }

    /**
     * Find a PlayerData object by UUID
     *
     * @param uuid the uuid
     * @return the player data object
     */
    public PlayerData findPlayerData(UUID uuid) {
        return this.playerData.stream()
                .filter(data -> data.getUuid().equals(uuid))
                .findFirst().orElse(null);
    }
}