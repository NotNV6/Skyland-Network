package rip.skyland.commons.util.json;

import com.google.gson.JsonObject;
import lombok.experimental.UtilityClass;
import org.bson.Document;
import rip.skyland.commons.mongo.MongoAPI;

@UtilityClass
public class JsonUtil {

    public JsonObject getJsonObject(Document document) {
        return MongoAPI.get().getParser().parse(document.toJson()).getAsJsonObject();
    }

}
