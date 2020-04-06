package rip.skyland.essentials.commands;

import org.bukkit.entity.Player;
import rip.skyland.commons.command.annotation.Command;
import rip.skyland.commons.redis.RedisAPI;
import rip.skyland.commons.util.json.JsonBuilder;

public class ReportCommand {

    @Command(label = "report")
    public void execute(final Player player, final Player target, final String reason) {
        RedisAPI.get().write("report", new JsonBuilder()
                .addProperty("reporter", player.getDisplayName())
                .addProperty("reported", target.getDisplayName())
                .addProperty("reason", reason).get());
    }

    @Command(label = "request", aliases = {"helpop"})
    public void execute(final Player player, final String reason) {
        RedisAPI.get().write("report", new JsonBuilder()
                .addProperty("reporter", player.getDisplayName())
                .addProperty("reason", reason).get());
    }
}