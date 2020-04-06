package rip.skyland.commons.player.data;

import com.google.gson.JsonObject;

public abstract class Data {

    /**
     * Constructor for initializing a new Data object without initializing anything.
     */
    public Data() {

    }

    /**
     * Get the path where the Data should be saved
     *
     * @return the path
     */
    public abstract String getSavePath();

    /**
     * Transfer a Data object to a JsonObject
     *
     * @return the JsonObject
     */
    public abstract JsonObject toJson();

    public abstract Data fromJson(JsonObject object);

}