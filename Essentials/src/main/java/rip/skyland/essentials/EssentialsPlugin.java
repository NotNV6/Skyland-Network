package rip.skyland.essentials;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.cache.CacheModule;
import rip.skyland.commons.command.CommandModule;
import rip.skyland.commons.redis.RedisAPI;
import rip.skyland.essentials.cache.LeaveCache;
import rip.skyland.essentials.commands.ReportCommand;
import rip.skyland.essentials.commands.StaffChatCommand;
import rip.skyland.essentials.listener.ConnectivityListener;
import rip.skyland.essentials.redis.staff.RequestRedisHandler;
import rip.skyland.essentials.redis.staff.StaffChatRedisHandler;
import rip.skyland.essentials.redis.staff.StaffConnectivityRedisHandler;

public class EssentialsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        // setup redis stuff
        if (RedisAPI.get() != null) {
            final RedisAPI redisAPI = RedisAPI.get();

            redisAPI.registerHandler(new StaffChatRedisHandler());
            redisAPI.registerHandler(new StaffConnectivityRedisHandler());
            redisAPI.registerHandler(new RequestRedisHandler());
        }

        // setup cache handler
        final CacheModule cacheModule = CommonsPlugin.getInstance().getHandler().findModule(CacheModule.class);
        cacheModule.addCache(new LeaveCache());

        // register commands
        final CommandModule commandModule = CommonsPlugin.getInstance().getHandler().findModule(CommandModule.class);

        commandModule.registerCommand(new StaffChatCommand(), new ReportCommand());

        // register listeners
        Bukkit.getPluginManager().registerEvents(new ConnectivityListener(), this);
    }
}