package rip.skyland.plugin.plugin;

import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import rip.skyland.commons.module.Module;
import rip.skyland.plugin.PluginHandlerPlugin;

import java.lang.reflect.Method;
import java.util.ArrayList;
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
        if (plugin.getName().equals(JavaPlugin.getPlugin(PluginHandlerPlugin.class).getName())) {
            return;
        }

        if (!this.findRelatedPlugins(plugin).isEmpty()) {
            this.findRelatedPlugins(plugin).forEach(this::disablePlugin);
        }

        Bukkit.getPluginManager().disablePlugin(plugin);
    }

    /**
     * Find all plugins which have a relation to a plugin
     *
     * @param plugin the plugin to check
     * @return the related plugins
     */
    public List<Plugin> findRelatedPlugins(Plugin plugin) {
        return Arrays.stream(Bukkit.getPluginManager().getPlugins())
                .filter(plugin1 -> {
                    final List<String> depend = new ArrayList<>();
                    depend.addAll(plugin1.getDescription().getDepend());
                    depend.addAll(plugin1.getDescription().getSoftDepend());

                    return depend.stream().anyMatch(string -> plugin.getName().equals(string));
                }).collect(Collectors.toList());

    }
}