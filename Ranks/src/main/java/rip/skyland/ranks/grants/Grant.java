package rip.skyland.ranks.grants;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.util.json.JsonBuilder;
import rip.skyland.commons.util.json.SimpleJsonSerialization;
import rip.skyland.ranks.ranks.Rank;
import rip.skyland.ranks.ranks.RankModule;

import java.util.UUID;

@Getter
@Setter
public class Grant extends SimpleJsonSerialization {

    private final Rank rank;

    private final UUID issuerUuid;

    private String server = "Not specified";
    private String reason = "Not specified";

    private long startTime;
    private long endTime = -1L;
    private long expirationTime = -1L;

    private boolean active = true;

    /**
     * Constructor for creating a new Grant object
     *
     * @param rank       the rank
     * @param issuerUuid the issuer
     */
    public Grant(final Rank rank, final UUID issuerUuid) {
        super(null);

        this.startTime = System.currentTimeMillis();
        this.rank = rank;
        this.issuerUuid = issuerUuid;
    }

    /**
     * Constructor for registering a Grant object from a JsonObject
     *
     * @param object the object
     */
    public Grant(final JsonObject object) {
        super(object);

        final RankModule rankModule = CommonsPlugin.getInstance().getHandler().findModule(RankModule.class);

        this.rank = rankModule.findRank(UUID.fromString(object.get("rank").getAsString()));
        this.issuerUuid = object.get("issuerUuid").getAsString().equals("null") ? null : UUID.fromString(object.get("issuerUuid").getAsString());
        this.server = object.get("server").getAsString();
        this.reason = object.get("reason").getAsString();
        this.active = object.get("active").getAsBoolean();
        this.startTime = object.get("startTime").getAsLong();
        this.endTime = object.get("endTime").getAsLong();
    }

    @Override
    public JsonObject toJson() {
        return new JsonBuilder()
                .addProperty("rank", rank.getUuid().toString())
                .addProperty("issuerUuid", issuerUuid == null ? "null" : issuerUuid.toString())
                .addProperty("server", server)
                .addProperty("reason", reason)
                .addProperty("active", active)
                .addProperty("startTime", startTime)
                .addProperty("endTime", endTime).get();
    }

    /**
     * Set whether a grant is active.
     *
     * @param active whether it's active
     */
    public void setActive(boolean active) {
        if(!active) {
            this.expirationTime = System.currentTimeMillis();
        } else {
            this.expirationTime = -1L;
        }

        this.active = active;
    }

    public int getWeight() {
        return this.rank.getWeight();
    }
}