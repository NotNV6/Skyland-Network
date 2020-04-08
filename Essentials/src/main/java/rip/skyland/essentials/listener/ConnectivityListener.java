package rip.skyland.essentials.listener;

import com.google.gson.JsonObject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.redis.RedisAPI;
import rip.skyland.commons.util.Locale;
import rip.skyland.commons.util.MethodUtil;
import rip.skyland.commons.util.json.JsonBuilder;
import rip.skyland.essentials.cache.LeaveCache;

public class ConnectivityListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (player.hasPermission("staff")) {
            RedisAPI.get().write("staff-join", this.getObject(player));
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        if (player.hasPermission("staff")) {
            final LeaveCache cache = CommonsPlugin.getInstance().getHandler().findModule(LeaveCache.class);

            cache.add(player.getUniqueId());
            MethodUtil.executeLater(() -> {
                if (!cache.contains(player.getUniqueId())) {
                    RedisAPI.get().write("staff-leave", this.getObject(player));
                }
            }, Locale.LOGOUT_TIMEOUT);
        }
    }

    private JsonObject getObject(Player player) {
        return new JsonBuilder()
                .addProperty("playerName", player.getDisplayName())
                .addProperty("serverName", Locale.SERVER_NAME)
                .addProperty("playerUuid", player.getUniqueId().toString()).get();
    }

}
