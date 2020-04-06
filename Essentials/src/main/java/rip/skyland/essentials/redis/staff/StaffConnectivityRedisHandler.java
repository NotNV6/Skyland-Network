package rip.skyland.essentials.redis.staff;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import rip.skyland.essentials.redis.StaffRedisHandler;

public class StaffConnectivityRedisHandler extends StaffRedisHandler {

    @Override
    public void incomingMessage(String channel, JsonObject object) {
        final String playerName = object.get("playerName").getAsString();
        final String serverName = object.get("serverName").getAsString();

        if (channel.equals("staff-join")) {
            this.broadcastMessage(ChatColor.AQUA + "[Staff] " + ChatColor.WHITE + playerName + ChatColor.AQUA + " has joined " + ChatColor.WHITE + serverName);
        } else if (channel.equals("staff-leave")) {
            this.broadcastMessage(ChatColor.AQUA + "[Staff] " + ChatColor.WHITE + playerName + ChatColor.AQUA + " has left the netork.");
        } else if (channel.equals("staff-swap-server")) {
            final String serverFrom = object.get("serverFrom").getAsString();

            this.broadcastMessage(ChatColor.AQUA + "[Staff] " + ChatColor.WHITE + playerName + ChatColor.AQUA + " has joined " + ChatColor.WHITE + serverName + ChatColor.AQUA + " from " + ChatColor.WHITE + serverFrom);
        }
    }

    @Override
    public String[] getChannels() {
        return new String[]{"staff-join", "staff-leave", "staff-swap-server"};
    }
}