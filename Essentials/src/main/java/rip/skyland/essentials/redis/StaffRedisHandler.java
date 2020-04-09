package rip.skyland.essentials.redis;

import rip.skyland.commons.redis.handler.RedisHandler;
import rip.skyland.commons.util.CC;
import rip.skyland.commons.util.PlayerUtil;

public abstract class StaffRedisHandler extends RedisHandler {

    /**
     * Sends a message to all online staff members.
     *
     * @param message the message
     */
    public void broadcastMessage(String message) {
        PlayerUtil.getPlayers("staff").forEach(player -> player.sendMessage(CC.translate(message)));
    }
}