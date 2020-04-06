package rip.skyland.ranks.util;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.player.PlayerData;
import rip.skyland.commons.player.PlayerDataModule;
import rip.skyland.ranks.RanksPlugin;
import rip.skyland.ranks.grants.Grant;
import rip.skyland.ranks.player.GrantData;
import rip.skyland.ranks.player.PermissionData;
import rip.skyland.ranks.ranks.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class PermissionUtil {

    /**
     * Update the permissions of a player
     *
     * @param player the player
     */
    public void updatePermissions(Player player) {
        final PlayerData playerData = CommonsPlugin.getInstance().getHandler().findModule(PlayerDataModule.class).findPlayerData(player.getUniqueId());
        final List<String> permissions = new ArrayList<>(playerData.findData(PermissionData.class).getPermissions());

        playerData.findData(GrantData.class).getGrants().stream()
                .map(Grant::getRank)
                .map(Rank::getPermissions)
                .collect(Collectors.toList()).forEach(permissions::addAll);

        // clear permissions
        player.getEffectivePermissions().stream()
                .filter(attachment -> attachment.getAttachment() != null)
                .forEach(attachment -> player.removeAttachment(attachment.getAttachment()));

        // set all the permissions of the arraylist
        permissions.forEach(permission -> new PermissionAttachment(RanksPlugin.getInstance(), player).setPermission(permission, true));
    }
}