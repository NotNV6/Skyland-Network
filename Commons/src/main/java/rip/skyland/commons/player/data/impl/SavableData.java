package rip.skyland.commons.player.data.impl;

import com.google.gson.JsonObject;
import rip.skyland.commons.player.data.Data;

public abstract class SavableData extends Data {

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

    /**
     * Transfer a JsonObject to a SavableData object
     *
     * @param object the JsonObject
     * @return the SavableData
     */
    public abstract SavableData fromJson(JsonObject object);

}