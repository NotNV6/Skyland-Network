package rip.skyland.ranks.ranks;

import com.google.gson.JsonObject;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.Document;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.module.Module;
import rip.skyland.commons.mongo.MongoAPI;
import rip.skyland.commons.player.PlayerData;
import rip.skyland.commons.player.PlayerDataModule;
import rip.skyland.commons.util.json.JsonUtil;
import rip.skyland.ranks.grants.Grant;
import rip.skyland.ranks.player.GrantData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
public class RankModule extends Module {

    private final List<Rank> ranks = new ArrayList<>();

    private final Comparator<Rank> rankComparator = Comparator.comparingInt(Rank::getWeight).reversed();
    private final Comparator<Grant> grantComparator = Comparator.comparingInt(Grant::getWeight).reversed();

    @Override
    public void enable() {
        super.enable();

        MongoAPI.get().getCollection("global", "ranks").find().forEach((Block<? super Document>) document -> new Rank(JsonUtil.getJsonObject(document)));

        if(this.ranks.stream().noneMatch(Rank::isDefaultRank)) {
            final Rank rank = new Rank(UUID.randomUUID(), "Default");
            rank.setDefaultRank(true);
        }
    }

    @Override
    public void disable() {
        super.disable();

        ranks.forEach(rank -> {
            Document document = Document.parse(rank.toJson().toString());

            MongoAPI.get().getCollection("global", "ranks")
                    .replaceOne(Filters.eq("uuid", rank.getUuid().toString()), document, new ReplaceOptions().upsert(true));
        });
    }

    /**
     * Add a rank to the rank list and sort the list
     *
     * @param rank the rank
     */
    public void addRank(Rank rank) {
        this.ranks.add(rank);
        this.ranks.sort(rankComparator);
    }

    /**
     * Remove a rank from the arraylist and the database
     *
     * @param rank the rank
     */
    public void removeRank(Rank rank) {
        this.ranks.remove(rank);
        this.ranks.sort(rankComparator);

        final MongoCollection<Document> collection = MongoAPI.get().getCollection("ranks", "ranks");
        final Document document = collection.find(Filters.eq("uuid", rank.getUuid().toString())).first();

        if (document != null) {
            collection.deleteOne(document);
        }

        final PlayerDataModule playerDataModule = CommonsPlugin.getInstance().getHandler().findModule(PlayerDataModule.class);
        final List<PlayerData> playerData = playerDataModule.getPlayerData().stream()
                .filter(data -> data.findData(GrantData.class).getGrants().stream().anyMatch(grant -> grant.getRank().equals(rank)))
                .collect(Collectors.toList());

        playerData.forEach(data -> {
            final GrantData grantData = data.findData(GrantData.class);

            grantData.getGrants().stream()
                    .filter(grant -> grant.getRank().equals(rank))
                    .forEach(grant -> grantData.getGrants().remove(grant));

            grantData.getGrants().sort(grantComparator);
        });
    }

    /**
     * Find a rank by a UUID
     *
     * @param uuid the uuid
     * @return the rank
     */
    public Rank findRank(UUID uuid) {
        return ranks.stream()
                .filter(rank -> rank.getUuid().equals(uuid))
                .findFirst().orElse(null);
    }

    /**
     * Find a rank by their name
     *
     * @param rankName the name
     * @return the rank
     */
    public Rank findRank(String rankName) {
        return ranks.stream()
                .filter(rank -> rank.getRankName().equalsIgnoreCase(rankName))
                .findFirst().orElse(null);
    }
}