package rip.skyland.essentials.redis.staff;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import rip.skyland.commons.util.CC;
import rip.skyland.essentials.redis.StaffRedisHandler;

public class RequestRedisHandler extends StaffRedisHandler {


    @Override
    public void incomingMessage(String channel, JsonObject object) {
        final String reporter = object.get("reporter").getAsString();
        final String reason = object.get("reason").getAsString();

        if (channel.equals("request")) {
            this.broadcastMessage(ChatColor.AQUA + "[Request] " + ChatColor.WHITE + CC.translate(reporter) + ChatColor.AQUA + " has requested assistance");
            this.broadcastMessage(ChatColor.AQUA + "  Reason: " + ChatColor.WHITE + reason);
        } else if (channel.equals("report")) {
            final String reported = object.get("reported").getAsString();

            this.broadcastMessage(ChatColor.AQUA + "[Report] " + ChatColor.WHITE + CC.translate(reporter) + ChatColor.AQUA + " has reported " + ChatColor.WHITE + CC.translate(reported));
            this.broadcastMessage(ChatColor.AQUA + "  Reason: " + ChatColor.WHITE + reason);
        }
    }

    @Override
    public String[] getChannels() {
        return new String[]{"request", "report"};
    }
}