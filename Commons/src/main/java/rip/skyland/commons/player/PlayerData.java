package rip.skyland.commons.player;

import com.google.gson.JsonParser;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.mongo.MongoAPI;
import rip.skyland.commons.player.data.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
public class PlayerData {

    private String name = "";
    private boolean newProfile;

    private final UUID uuid;

    private final JsonParser parser = new JsonParser();
    private final List<Data> data = new ArrayList<>();
    private final PlayerDataModule playerDataModule = CommonsPlugin.getInstance().getHandler().findModule(PlayerDataModule.class);

    /**
     * Constructor for creating a new Profile instance.
     *
     * @param uuid          the uuid of the owner
     */
    public PlayerData(UUID uuid) {
        this.uuid = uuid;
        this.newProfile = true;

        if(this.getPlayer() != null) {
            this.name = this.getPlayer().getName();
        }

        playerDataModule.getPlayerData().add(this);
    }

    public PlayerData(Document document) {
        this(UUID.fromString(document.getString("uuid")));

        this.newProfile = false;
        this.name = document.getString("name").replace(" ", "");

        document.entrySet().stream()
                .filter(entry -> playerDataModule.getRegisteredData().stream().anyMatch(data -> data.getSavePath().equals(entry.getKey())))
                .forEach(entry -> this.data.add(playerDataModule.getRegisteredData().stream()
                        .filter(data -> data.getSavePath().equals(entry.getKey()))
                        .findFirst().get().fromJson(parser.parse(entry.getValue().toString()).getAsJsonObject())));
    }

    /**
     * Add a Data object to a Profile
     *
     * @param data the data object
     * @return the data object
     */
    public <T extends Data> Data addData(T data) {
        if (this.data.stream().anyMatch(data1 -> data1.getClass().equals(data.getClass()))) {
            return this.data.stream()
                    .filter(data1 -> data1.getClass().equals(data.getClass()))
                    .findFirst().get();
        }

        this.data.add(data);
        return data;
    }

    /**
     * Find a Data object by a Class
     *
     * @param clazz the class
     * @param <T>   the type of the Data
     * @return the Data object
     */
    public <T> T findData(Class<T> clazz) {
        return clazz.cast(this.data.stream()
                .filter(data -> data.getClass().equals(clazz))
                .findFirst().orElse(null));
    }

    /**
     * Get the Player of an UUID
     *
     * @return the player
     */
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    /**
     * Save a Profile object
     */
    public void save() {
        if (MongoAPI.get() != null) {
            final MongoCollection<Document> collection = MongoAPI.get().getCollection("global", "profiles");
            final Document document = new Document();

            document.put("uuid", uuid.toString());
            document.put("name", name);

            data.forEach(data -> document.put(data.getSavePath(), data.toJson().toString()));

            collection.replaceOne(Filters.eq("uuid", uuid.toString()), document, new ReplaceOptions().upsert(true));
        }
    }
}