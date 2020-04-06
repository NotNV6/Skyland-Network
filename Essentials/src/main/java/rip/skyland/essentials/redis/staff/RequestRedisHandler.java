package rip.skyland.essentials.redis.staff;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import rip.skyland.commons.util.CC;
import rip.skyland.essentials.redis.StaffRedisHandler;

import java.util.Arrays;

public class RequestRedisHandler extends StaffRedisHandler {


    @Override
    public void incomingMessage(String channel, JsonObject object) {
        final String reporter = object.get("reporter").getAsString();
        final String reason = object.get("reason").getAsString();

        if (channel.equals("request")) {
            Arrays.stream(new String[]{
                    ChatColor.AQUA + "[Request] " + ChatColor.WHITE + CC.translate(reporter) + ChatColor.AQUA + " has requested assistance",
                    ChatColor.AQUA + "  Reason: " + ChatColor.WHITE + reason
            }).forEach(this::broadcastMessage);
        } else if (channel.equals("report")) {
            final String reported = object.get("reported").getAsString();

            Arrays.stream(new String[]{
                    ChatColor.AQUA + "[Report] " + ChatColor.WHITE + CC.translate(reporter) + ChatColor.AQUA + " has reported " + ChatColor.WHITE + CC.translate(reported),
                    ChatColor.AQUA + "  Reason: " + ChatColor.WHITE + reason
            }).forEach(this::broadcastMessage);
        }

    }

    @Override
    public String[] getChannels() {
        return new String[]{"request", "report"};
    }
}