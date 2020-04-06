package rip.skyland.essentials.redis;

import org.bukkit.Bukkit;
import rip.skyland.commons.redis.handler.RedisHandler;
import rip.skyland.commons.util.CC;

public abstract class StaffRedisHandler extends RedisHandler {

    /**
     * Sends a message to all online staff members.
     *
     * @param message the message
     */
    public void broadcastMessage(String message) {
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> player.hasPermission("staff"))
                .forEach(player -> player.sendMessage(CC.translate(message)));
    }

}
