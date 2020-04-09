package rip.skyland.essentials.commands;

import org.bukkit.entity.Player;
import rip.skyland.commons.command.annotation.Command;
import rip.skyland.commons.redis.RedisAPI;
import rip.skyland.commons.util.Locale;
import rip.skyland.commons.util.json.JsonBuilder;

public class StaffChatCommand {

    @Command(label = "staffchat", aliases = "sc", permission = "admin")
    public void execute(final Player player, final String[] message) {
        RedisAPI.get().write("staff-chat", new JsonBuilder()
                .addProperty("message", String.join(" ", message))
                .addProperty("playerName", player.getDisplayName())
                .addProperty("server", Locale.SERVER_NAME).get());
    }
}