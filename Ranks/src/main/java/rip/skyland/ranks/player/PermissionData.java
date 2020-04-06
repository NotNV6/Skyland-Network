package rip.skyland.ranks.player;

import com.google.gson.JsonObject;
import lombok.EqualsAndHashCode;
import rip.skyland.commons.mongo.MongoAPI;
import rip.skyland.commons.player.data.Data;
import rip.skyland.commons.util.json.JsonBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
public class PermissionData extends Data {

    private final List<String> permissions = new ArrayList<>();

    @Override
    public String getSavePath() {
        return "permissions";
    }

    @Override
    public JsonObject toJson() {
        return new JsonBuilder()
                .addProperty("permissions", permissions.toString()).get();
    }

    @Override
    public Data fromJson(JsonObject object) {
        MongoAPI.get().getParser().parse(object.get("permissions").getAsString()).getAsJsonArray().forEach(element -> permissions.add(element.getAsString()));

        return this;
    }
}