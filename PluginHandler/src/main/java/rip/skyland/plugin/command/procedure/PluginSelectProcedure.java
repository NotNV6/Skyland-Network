package rip.skyland.plugin.command.procedure;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.procedure.ChatProcedure;
import rip.skyland.commons.util.Locale;
import rip.skyland.plugin.plugin.PluginModule;

public class PluginSelectProcedure extends ChatProcedure {

    private final Player player;

    public PluginSelectProcedure(Player player) {
        super(player);

        this.player = player;
    }

    @Override
    public void start() {
        super.start();

        player.sendMessage(Locale.PRIMARY_COLOR + "Select the plugin you want to disable or enable.");
    }

    @Override
    public void finish(String result) {

        final PluginModule pluginModule = CommonsPlugin.getInstance().getHandler().findModule(PluginModule.class);
        final Plugin plugin = Bukkit.getPluginManager().getPlugin(result);

        if (plugin == null) {
            player.sendMessage(ChatColor.RED + "That plugin is not available.");
            return;
        }

        if (plugin.isEnabled()) {
            pluginModule.disablePlugin(plugin);
        } else {
            pluginModule.enablePlugin(plugin);
        }

        player.sendMessage(Locale.PRIMARY_COLOR + "You have " + (plugin.isEnabled() ? "enabled" : "disabled") + " the " + Locale.SECONDARY_COLOR + plugin.getName() + Locale.PRIMARY_COLOR + " plugin.");
        super.finish(result);
    }
}
