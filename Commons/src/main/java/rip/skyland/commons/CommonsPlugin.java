package rip.skyland.commons;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import rip.skyland.commons.cache.CacheModule;
import rip.skyland.commons.command.CommandModule;
import rip.skyland.commons.menu.MenuHandler;
import rip.skyland.commons.module.Module;
import rip.skyland.commons.module.ModuleRegistrar;
import rip.skyland.commons.mongo.MongoAPI;
import rip.skyland.commons.player.PlayerDataModule;
import rip.skyland.commons.procedure.ChatProcedureModule;


@Getter
public class CommonsPlugin extends JavaPlugin {

    @Getter
    private static CommonsPlugin instance;

    private ModuleRegistrar handler;

    @Override
    public void onEnable() {
        instance = this;

        this.handler = new ModuleRegistrar(new CacheModule(), new ChatProcedureModule(), new MenuHandler());

        final FileConfiguration config = this.getConfig();

        config.options().copyDefaults(true);
        this.saveDefaultConfig();

        if (this.getConfig().getBoolean("mongo.enabled")) {
            final String mongoPath = "mongo.";

            handler.registerModule(new MongoAPI(config.getString(mongoPath + "host"), config.getInt(mongoPath + "port")));
            handler.registerModule(new PlayerDataModule());
        }

        handler.registerModule(new CommandModule("cancer"));
    }

    @Override
    public void onDisable() {
        this.handler.getModules().forEach(Module::disable);
    }
}