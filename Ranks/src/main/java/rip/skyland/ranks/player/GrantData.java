package rip.skyland.ranks.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.EqualsAndHashCode;
import org.bukkit.entity.Player;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.mongo.MongoAPI;
import rip.skyland.commons.player.PlayerData;
import rip.skyland.commons.player.data.Data;
import rip.skyland.commons.util.MethodUtil;
import rip.skyland.commons.util.json.JsonBuilder;
import rip.skyland.ranks.grants.Grant;
import rip.skyland.ranks.ranks.Rank;
import rip.skyland.ranks.ranks.RankModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
public class GrantData extends Data {

    private final List<Grant> grants = new ArrayList<>();
    private final PlayerData playerData;

    public GrantData(PlayerData playerData) {
        this.playerData = playerData;
    }

    /**
     * Add a grant to the Grant array list
     *
     * @param grant the grant to remove
     * @return the grant object
     */
    public Grant addGrant(Grant grant) {
        this.grants.add(grant);
        this.grants.sort(CommonsPlugin.getInstance().getHandler().findModule(RankModule.class).getGrantComparator());

        MethodUtil.executeLater(() -> {
            if (playerData.getPlayer() != null) {
                playerData.getPlayer().setDisplayName(this.getRank().getColor() + playerData.getPlayer().getName());
            }
        }, 20);


        return grant;
    }

    /**
     * Remove a grant from the Grant array list
     *
     * @param grant the grant to remove
     * @return the grant object
     */
    public Grant removeGrant(Grant grant) {
        this.grants.remove(grant);
        this.grants.sort(CommonsPlugin.getInstance().getHandler().findModule(RankModule.class).getGrantComparator());

        MethodUtil.executeLater(() -> {
            if (playerData.getPlayer() != null) {
                playerData.getPlayer().setDisplayName(this.getRank().getColor() + playerData.getPlayer().getName());
            }
        }, 20);

        return grant;
    }

    /**
     * Get the current rank of the player
     *
     * @return the current active rank, or the default rank.
     */
    public Rank getRank() {
        final Optional<Grant> grant = grants.stream()
                .filter(Grant::isActive)
                .findFirst();

        final Rank rank;

        if (!grant.isPresent()) {
            rank = CommonsPlugin.getInstance().getHandler().findModule(RankModule.class).findDefaultRank().findFirst().orElse(null);
        } else {
            rank = grant.get().getRank();
        }

        return rank;
    }

    @Override
    public String getSavePath() {
        return "grants";
    }

    @Override
    public JsonObject toJson() {
        return new JsonBuilder()
                .addProperty("grants", grants.stream().map(Grant::toJson).collect(Collectors.toList()).toString()).get();
    }

    @Override
    public Data fromJson(JsonObject object) {
        final JsonArray array = MongoAPI.get().getParser().parse(object.get("grants").getAsString()).getAsJsonArray();

        array.forEach(element -> grants.add(new Grant(element.getAsJsonObject())));

        return this;
    }
}