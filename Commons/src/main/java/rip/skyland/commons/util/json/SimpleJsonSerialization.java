package rip.skyland.commons.util.json;

import com.google.gson.JsonObject;

public abstract class SimpleJsonSerialization {

    /**
     * Constructor for loading a JsonObject
     *
     * @param object
     */
    public SimpleJsonSerialization(JsonObject object) { }

    /**
     * Method for transfering a class extending SimpleJsonSerialization to a JsonObject
     *
     * @return the JsonObject
     */
    public abstract JsonObject toJson();

}
