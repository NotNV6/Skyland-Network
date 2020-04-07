package rip.skyland.ranks.ranks;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.util.json.JsonBuilder;
import rip.skyland.commons.util.json.SimpleJsonSerialization;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Rank extends SimpleJsonSerialization {

    private final RankModule rankModule = CommonsPlugin.getInstance().getHandler().findModule(RankModule.class);

    private final UUID uuid;
    private final String rankName;
    private final List<String> permissions = new ArrayList<>();

    private ChatColor color = ChatColor.WHITE;

    private String displayName = "";
    private String prefix = "";

    private int weight = 0;

    private boolean defaultRank = false;
    private boolean staffRank = false;

    /**
     * Constructor for creating a new Rank object
     *
     * @param uuid     the uuid of the rank
     * @param rankName the name of the rank
     */
    public Rank(UUID uuid, String rankName) {
        super(null);

        this.uuid = uuid;
        this.rankName = rankName;
        this.displayName = rankName;

        rankModule.addRank(this);
    }

    /**
     * Constructor for creating a new Rank object from a JsonObject
     *
     * @param object the object
     */
    public Rank(JsonObject object) {
        super(object);

        this.uuid = UUID.fromString(object.get("uuid").getAsString());
        this.rankName = object.get("rankName").getAsString();
        this.displayName = object.get("displayName").getAsString();
        this.prefix = object.get("prefix").getAsString();
        this.weight = object.get("weight").getAsInt();
        this.defaultRank = object.get("defaultRank").getAsBoolean();
        this.staffRank = object.get("staffRank").getAsBoolean();
        this.color = ChatColor.valueOf(object.get("color").getAsString());

        rankModule.addRank(this);
    }

    @Override
    public JsonObject toJson() {
        return new JsonBuilder()
                .addProperty("uuid", uuid.toString())
                .addProperty("rankName", rankName)
                .addProperty("displayName", displayName)
                .addProperty("prefix", prefix)
                .addProperty("weight", weight)
                .addProperty("defaultRank", defaultRank)
                .addProperty("staffRank", staffRank)
                .addProperty("color", color.name()).get();
    }
}