package rip.skyland.ranks.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.ranks.chat.ChatModule;

public class ChatHandlerListener implements Listener {

    @EventHandler(priority= EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        if(!event.isCancelled()) {
            final ChatModule chatModule = CommonsPlugin.getInstance().getHandler().findModule(ChatModule.class);
            final Player player = event.getPlayer();
            final String message = event.getMessage();

            event.setCancelled(true);

            chatModule.getChatHandler().handleChat(player, message);
        }
    }
}