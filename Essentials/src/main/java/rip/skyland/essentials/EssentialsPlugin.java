package rip.skyland.essentials;

import org.bukkit.plugin.java.JavaPlugin;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.command.CommandModule;
import rip.skyland.commons.redis.RedisAPI;
import rip.skyland.essentials.commands.ReportCommand;
import rip.skyland.essentials.commands.StaffChatCommand;
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
        }

        // register commands
        final CommandModule commandModule = CommonsPlugin.getInstance().getHandler().findModule(CommandModule.class);

        commandModule.registerCommand(new StaffChatCommand(), new ReportCommand());
    }
}