package rip.skyland.ranks.chat.impl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.ranks.chat.ChatHandler;
import rip.skyland.ranks.chat.ChatModule;
import rip.skyland.ranks.chat.format.ChatFormat;

public class SimpleChatHandler implements ChatHandler {

    @Override
    public void handleChat(Player player, String message) {
        final ChatModule chatModule = CommonsPlugin.getInstance().getHandler().findModule(ChatModule.class);
        final ChatFormat chatFormat = chatModule.getFormat();

        final String formattedMessage = chatFormat.format(player, message);

        Bukkit.broadcastMessage(formattedMessage);
    }
}