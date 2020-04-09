package rip.skyland.essentials;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.cache.CacheModule;
import rip.skyland.commons.command.CommandModule;
import rip.skyland.commons.module.ModuleRegistrar;
import rip.skyland.commons.redis.RedisAPI;
import rip.skyland.essentials.cache.LeaveCache;
import rip.skyland.essentials.commands.ModModeCommand;
import rip.skyland.essentials.commands.ReportCommand;
import rip.skyland.essentials.commands.StaffChatCommand;
import rip.skyland.essentials.listener.ConnectivityListener;
import rip.skyland.essentials.modmode.ModModeModule;
import rip.skyland.essentials.redis.staff.RequestRedisHandler;
import rip.skyland.essentials.redis.staff.StaffChatRedisHandler;
import rip.skyland.essentials.redis.staff.StaffConnectivityRedisHandler;

public class EssentialsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        // setup modules
        final ModuleRegistrar registrar = CommonsPlugin.getInstance().getHandler();

        registrar.registerModule(new ModModeModule());

        // setup redis stuff
        if (RedisAPI.get() != null) {
            final RedisAPI redisAPI = RedisAPI.get();

            redisAPI.registerHandler(new StaffChatRedisHandler());
            redisAPI.registerHandler(new StaffConnectivityRedisHandler());
            redisAPI.registerHandler(new RequestRedisHandler());
        }

        // setup cache handler
        final CacheModule cacheModule = registrar.findModule(CacheModule.class);
        cacheModule.addCache(new LeaveCache());

        // register commands
        final CommandModule commandModule = registrar.findModule(CommandModule.class);

        commandModule.registerCommand(
                new StaffChatCommand(),
                new ReportCommand(),
                new ModModeCommand()
        );

        // register listeners
        registrar.registerListener(ConnectivityListener.class);
    }
}