package rip.skyland.essentials.modmode.data;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import rip.skyland.commons.player.data.Data;
import rip.skyland.commons.util.PlayerUtil;
import rip.skyland.essentials.modmode.cache.CachedPlayerData;

@Getter
@Setter
public class ModModeData extends Data {

    private boolean modModeEnabled;
    private boolean canDestroyBlocks;
    private boolean vanished;

    private CachedPlayerData cachedPlayerData;

    /**
     * Vanish a player
     *
     * @param player the player
     */
    public void vanishPlayer(Player player) {
        this.vanished = true;

        Bukkit.getOnlinePlayers().forEach(target -> target.hidePlayer(player));
        PlayerUtil.getPlayers("staff").forEach(target -> target.showPlayer(player));
    }

    /**
     * Unvanish a player
     *
     * @param player the player
     */
    public void unvanishPlayer(Player player) {
        this.vanished = false;

        Bukkit.getOnlinePlayers().forEach(target -> target.showPlayer(player));
    }
}
