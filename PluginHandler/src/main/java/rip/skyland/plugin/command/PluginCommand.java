package rip.skyland.plugin.command;

import org.bukkit.entity.Player;
import rip.skyland.commons.command.annotation.Command;
import rip.skyland.plugin.command.procedure.PluginSelectProcedure;

public class PluginCommand {

    @Command(label = "plugin", permission = "admin")
    public void execute(Player player) {
        new PluginSelectProcedure(player).start();
    }
}