package rip.skyland.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.command.CommandModule;
import rip.skyland.commons.module.ModuleRegistrar;
import rip.skyland.plugin.command.PluginCommand;
import rip.skyland.plugin.plugin.PluginModule;

public class PluginHandlerPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        final ModuleRegistrar registrar = CommonsPlugin.getInstance().getHandler();
        final CommandModule commandModule = registrar.findModule(CommandModule.class);

        registrar.registerModule(new PluginModule());

        commandModule.registerCommand(new PluginCommand());
    }

}
