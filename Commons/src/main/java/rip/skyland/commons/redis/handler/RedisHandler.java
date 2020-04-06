package rip.skyland.commons.redis.handler;

import com.google.gson.JsonObject;
import rip.skyland.commons.redis.RedisAPI;
import redis.clients.jedis.JedisPubSub;

public abstract class RedisHandler extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        RedisAPI redisAPI = RedisAPI.get();
        incomingMessage(channel, redisAPI.getJsonParser().parse(message).getAsJsonObject());
    }

    /**
     * Called upon message sent.
     *
     * @param channel the channel where the message has been sent in
     * @param object  the message which was sent
     */
    public abstract void incomingMessage(String channel, JsonObject object);

    /**
     * Get the channels which are available for the handler.
     *
     * @return the array of channels
     */
    public abstract String[] getChannels();

}
