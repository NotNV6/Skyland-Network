package rip.skyland.essentials.redis.staff;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.cache.CacheModule;
import rip.skyland.commons.util.Locale;
import rip.skyland.essentials.cache.LeaveCache;
import rip.skyland.essentials.redis.StaffRedisHandler;

import java.util.UUID;

public class StaffConnectivityRedisHandler extends StaffRedisHandler {

    @Override
    public void incomingMessage(String channel, JsonObject object) {
        final String playerName = object.get("playerName").getAsString();
        final String serverName = object.get("serverName").getAsString();

        final UUID playerUuid = UUID.fromString("playerUuid");
        final LeaveCache cache = CommonsPlugin.getInstance().getHandler().findModule(CacheModule.class).findCache(LeaveCache.class);

        if(cache.contains(playerUuid)) {
            channel = "staff-swap-server";
        }

        switch (channel) {
            case "staff-join": {
                cache.add(playerUuid);
                this.broadcastMessage(Locale.PRIMARY_COLOR + "[Staff] " + Locale.SECONDARY_COLOR + playerName + Locale.PRIMARY_COLOR + " has joined " + Locale.SECONDARY_COLOR + serverName);
            } break;

            case "staff-leave": {
                this.broadcastMessage(Locale.PRIMARY_COLOR + "[Staff] " + Locale.SECONDARY_COLOR + playerName + Locale.PRIMARY_COLOR + " has left the netork.");
            } break;

            case "staff-swap-server": {
                this.broadcastMessage(Locale.PRIMARY_COLOR + "[Staff] " + Locale.SECONDARY_COLOR + playerName + Locale.PRIMARY_COLOR + " has switched servers " + Locale.SECONDARY_COLOR + "(to " + serverName + ")");
            } break;
        }
    }

    @Override
    public String[] getChannels() {
        return new String[]{"staff-join", "staff-leave", "staff-swap-server"};
    }
}