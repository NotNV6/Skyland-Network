package rip.skyland.ranks.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.player.PlayerData;
import rip.skyland.commons.player.PlayerDataModule;
import rip.skyland.ranks.grants.Grant;
import rip.skyland.ranks.player.GrantData;
import rip.skyland.ranks.player.PermissionData;
import rip.skyland.ranks.ranks.Rank;
import rip.skyland.ranks.ranks.RankModule;
import rip.skyland.ranks.util.PermissionUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DataRegisterListener implements Listener {

    private final PlayerDataModule playerDataModule = CommonsPlugin.getInstance().getHandler().findModule(PlayerDataModule.class);

    @EventHandler
    public void onPreLogin(final AsyncPlayerPreLoginEvent event) {
        final PlayerData playerData = playerDataModule.findPlayerData(event.getUniqueId());

        if (playerData != null) {
            Arrays.asList(new GrantData(playerData), new PermissionData()).forEach(playerData::addData);

            final GrantData data = playerData.findData(GrantData.class);
            final RankModule rankModule = CommonsPlugin.getInstance().getHandler().findModule(RankModule.class);

            if (!data.getGrants().stream().map(Grant::getRank).collect(Collectors.toList()).containsAll(rankModule.getRanks().stream().filter(Rank::isDefaultRank).collect(Collectors.toList()))) {
                rankModule.getRanks().stream()
                        .filter(rank -> rank.isDefaultRank() && data.getGrants().stream().noneMatch(grant -> grant.getRank().equals(rank)))
                        .forEach(rank -> data.addGrant(new Grant(rank, playerData.getUuid())));
            }
        }
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final PlayerData playerData = playerDataModule.findPlayerData(player.getUniqueId());

        if (playerData != null) {
            PermissionUtil.updatePermissions(player);

            if (!playerData.isNewProfile() && !player.getName().equals(playerData.getName())) {
                player.sendMessage(ChatColor.AQUA + "You have joined with a new name " + ChatColor.WHITE + "(" + player.getName() + ") " + ChatColor.AQUA + "formerly known as " + ChatColor.WHITE + playerData.getName());
                playerData.setName(player.getName());
            }
        }
    }
}