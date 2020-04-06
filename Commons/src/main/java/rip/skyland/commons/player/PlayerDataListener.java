package rip.skyland.commons.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.mongo.MongoAPI;
import rip.skyland.commons.procedure.ChatProcedureModule;

public class PlayerDataListener implements Listener {


    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent event) {
        final PlayerDataModule profileAPI = CommonsPlugin.getInstance().getHandler().findModule(PlayerDataModule.class);

        PlayerData profile = profileAPI.findPlayerData(event.getUniqueId());

        if(profile == null) {
            profile = new PlayerData(event.getUniqueId());
        }
    }

    @EventHandler(priority= EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final ChatProcedureModule chatProcedureModule = CommonsPlugin.getInstance().getHandler().findModule(ChatProcedureModule.class);

        if (chatProcedureModule.getProcedures().containsKey(player)) {
            event.setCancelled(true);

            if (event.getMessage().equalsIgnoreCase("cancel")) {
                chatProcedureModule.getProcedures().remove(player);
                return;
            }

            chatProcedureModule.getProcedures().get(player).finish(event.getMessage());
        }
    }
}