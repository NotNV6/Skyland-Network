package rip.skyland.commons.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class PlayerUtil {

    /**
     * Get all online players online with a permission
     *
     * @param permission the permission
     * @return the online players
     */
    public List<Player> getPlayers(String permission) {
        return Bukkit.getOnlinePlayers().stream()
                .filter(player -> player.hasPermission(permission))
                .collect(Collectors.toList());
    }
}