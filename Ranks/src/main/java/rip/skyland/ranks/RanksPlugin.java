package rip.skyland.ranks;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.command.CommandModule;
import rip.skyland.commons.module.ModuleRegistrar;
import rip.skyland.commons.player.PlayerDataModule;
import rip.skyland.ranks.chat.ChatModule;
import rip.skyland.ranks.chat.format.impl.DefaultChatFormat;
import rip.skyland.ranks.chat.impl.SimpleChatHandler;
import rip.skyland.ranks.commands.ListCommand;
import rip.skyland.ranks.commands.grant.GrantCommand;
import rip.skyland.ranks.commands.grant.GrantsCommand;
import rip.skyland.ranks.commands.rank.RankCommand;
import rip.skyland.ranks.commands.rank.adapter.RankTypeAdapter;
import rip.skyland.ranks.grants.GrantModule;
import rip.skyland.ranks.listener.ChatHandlerListener;
import rip.skyland.ranks.listener.DataRegisterListener;
import rip.skyland.ranks.player.GrantData;
import rip.skyland.ranks.player.PermissionData;
import rip.skyland.ranks.ranks.RankModule;

import java.util.Arrays;

public class RanksPlugin extends JavaPlugin {

    @Getter
    private static RanksPlugin instance;

    public void onEnable() {
        instance = this;

        final ModuleRegistrar registrar = CommonsPlugin.getInstance().getHandler();
        final PlayerDataModule playerDataModule = registrar.findModule(PlayerDataModule.class);
        final ChatModule chatModule = new ChatModule(new DefaultChatFormat());

        // register modules
        registrar.registerModule(new GrantModule());
        registrar.registerModule(new RankModule());

        // chat module
        registrar.registerModule(chatModule);
        chatModule.setChatHandler(new SimpleChatHandler());

        // register player data
        playerDataModule.getRegisteredData().addAll(Arrays.asList(
                new GrantData(null),
                new PermissionData())
        );

        // register listeners
        registrar.registerListener(DataRegisterListener.class);
        registrar.registerListener(ChatHandlerListener.class);

        // register commands
        final CommandModule commandModule = registrar.findModule(CommandModule.class);

        commandModule.getTypeAdapters().add(new RankTypeAdapter());
        commandModule.registerCommand(
                new RankCommand(),
                new GrantCommand(),
                new GrantsCommand(),
                new ListCommand()
        );
    }
}