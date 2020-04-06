package rip.skyland.plugin.plugin;

import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import rip.skyland.commons.module.Module;
import rip.skyland.plugin.PluginHandlerPlugin;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PluginModule extends Module {

    @Override
    public void enable() {
        super.enable();
    }

    /**
     * Enable a Plugin with the name of the plugin
     *
     * @param pluginName the name
     */
    public void enablePlugin(String pluginName) throws NullPointerException {
        try {
            this.enablePlugin(Bukkit.getPluginManager().getPlugin(pluginName));
        } catch (NullPointerException exception) {
            throw new IllegalArgumentException("plugin " + pluginName + " not found in the current plugin list.");
        }
    }

    /**
     * Enable a Plugin with the Plugin object
     *
     * @param plugin the plugin object
     */
    @SneakyThrows
    public void enablePlugin(Plugin plugin) {
        Bukkit.getPluginManager().enablePlugin(plugin);
    }

    /**
     * Disable a Plugin with the name of the plugin
     *
     * @param pluginName the name
     */
    public void disablePlugin(String pluginName) throws NullPointerException {
        try {
            this.disablePlugin(Bukkit.getPluginManager().getPlugin(pluginName));
        } catch (NullPointerException exception) {
            throw new IllegalArgumentException("plugin " + pluginName + " not found in the current plugin list.");
        }
    }

    /**
     * Disable a Plugin with the Plugin object
     *
     * @param plugin the plugin object
     */
    @SneakyThrows
    public void disablePlugin(Plugin plugin) {
        if(plugin.getName().equals(JavaPlugin.getPlugin(PluginHandlerPlugin.class).getName())) {
            return;
        }

        final List<Plugin> dependentPlugins = Arrays.stream(Bukkit.getPluginManager().getPlugins())
                .filter(plugin1 -> plugin1.getDescription().getDepend().stream().anyMatch(string -> string.equals(plugin.getName())))
                .collect(Collectors.toList());

        if(!dependentPlugins.isEmpty()) {
            dependentPlugins.forEach(this::disablePlugin);
        }

        Bukkit.getPluginManager().disablePlugin(plugin);
    }
}