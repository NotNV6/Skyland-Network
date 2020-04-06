package rip.skyland.ranks.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.command.annotation.Command;
import rip.skyland.commons.player.PlayerDataModule;
import rip.skyland.ranks.player.GrantData;
import rip.skyland.ranks.ranks.Rank;
import rip.skyland.ranks.ranks.RankModule;

import java.util.stream.Collectors;

public class ListCommand {

    @Command(label = "list", aliases = {"who", "players"})
    public void executeList(final Player player) {
        final PlayerDataModule playerDataModule = CommonsPlugin.getInstance().getHandler().findModule(PlayerDataModule.class);
        final RankModule rankModule = CommonsPlugin.getInstance().getHandler().findModule(RankModule.class);

        final String players = playerDataModule.getPlayerData().stream()
                .map(playerData -> playerData.findData(GrantData.class).getRank().getColor() + playerData.getPlayer().getName())
                .collect(Collectors.joining(ChatColor.WHITE + ", "));

        final String ranks = rankModule.getRanks().stream()
                .map(Rank::getDisplayName)
                .collect(Collectors.joining(ChatColor.WHITE + ", "));

        player.sendMessage(ranks);
        player.sendMessage(ChatColor.WHITE + "(" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + ") [" + players + "]");
    }
}