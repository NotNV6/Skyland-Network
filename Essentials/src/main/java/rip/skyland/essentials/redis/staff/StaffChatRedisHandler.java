package rip.skyland.essentials.redis.staff;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import rip.skyland.essentials.redis.StaffRedisHandler;

public class StaffChatRedisHandler extends StaffRedisHandler {

    @Override
    public void incomingMessage(String channel, JsonObject object) {
        final String playerName = object.get("playerName").getAsString();
        final String serverName = object.get("serverName").getAsString();
        final String message = object.get("message").getAsString();

        this.broadcastMessage(ChatColor.GRAY + "[" + serverName + "] " + ChatColor.AQUA + playerName + ": " + ChatColor.WHITE + message);
    }

    @Override
    public String[] getChannels() {
        return new String[] { "staff-chat" };
    }
}
