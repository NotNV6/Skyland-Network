package rip.skyland.essentials.redis.staff;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import rip.skyland.commons.util.CC;
import rip.skyland.commons.util.Locale;
import rip.skyland.essentials.redis.StaffRedisHandler;

public class RequestRedisHandler extends StaffRedisHandler {


    @Override
    public void incomingMessage(String channel, JsonObject object) {
        final String reporter = object.get("reporter").getAsString();
        final String reason = object.get("reason").getAsString();

        if (channel.equals("request")) {
            this.broadcastMessage(Locale.PRIMARY_COLOR + "[Request] " + Locale.SECONDARY_COLOR + CC.translate(reporter) + Locale.PRIMARY_COLOR + " has requested assistance");
            this.broadcastMessage(Locale.PRIMARY_COLOR + "  Reason: " + Locale.SECONDARY_COLOR + reason);
        } else if (channel.equals("report")) {
            final String reported = object.get("reported").getAsString();

            this.broadcastMessage(Locale.PRIMARY_COLOR + "[Report] " + Locale.SECONDARY_COLOR + CC.translate(reporter) + Locale.PRIMARY_COLOR + " has reported " + Locale.SECONDARY_COLOR + CC.translate(reported));
            this.broadcastMessage(Locale.PRIMARY_COLOR + "  Reason: " + Locale.SECONDARY_COLOR + reason);
        }
    }

    @Override
    public String[] getChannels() {
        return new String[]{"request", "report"};
    }
}