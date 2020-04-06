package rip.skyland.ranks.commands.grant;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rip.skyland.commons.command.annotation.Command;
import rip.skyland.commons.command.annotation.Parameter;
import rip.skyland.commons.command.annotation.Subcommand;
import rip.skyland.commons.player.PlayerData;
import rip.skyland.ranks.commands.grant.menu.GrantMenu;
import rip.skyland.ranks.grants.Grant;
import rip.skyland.ranks.player.GrantData;
import rip.skyland.ranks.ranks.Rank;

public class GrantCommand {

    @Command(label = "grant", permission = "admin")
    public void execute(Player player, PlayerData target) {
        new GrantMenu(player, target).openMenu();
    }

    @Subcommand(parentLabel="grant", label="console", permission="admin")
    public void execute(CommandSender sender, PlayerData target, Rank rank) {
        if (rank.isDefaultRank()) {
            sender.sendMessage(ChatColor.RED + "Please provide a valid rank.");
            return;
        }

        target.findData(GrantData.class).addGrant(new Grant(rank, null));
    }

}