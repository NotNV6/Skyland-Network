package rip.skyland.ranks.commands.rank;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.skyland.commons.command.annotation.Command;
import rip.skyland.commons.command.annotation.Subcommand;
import rip.skyland.ranks.commands.rank.procedure.RankCreateProcedure;
import rip.skyland.ranks.commands.rank.procedure.RankDeleteProcedure;
import rip.skyland.ranks.commands.rank.procedure.edit.RankEditChooseNameProcedure;

public class RankCommand {

    @Command(label = "rank", permission = "admin")
    @Subcommand(parentLabel = "rank", label = "help", permission = "admin")
    public void executeHelp(final Player player) {
        player.sendMessage(new String[]{
                ChatColor.AQUA + "[Rank Help]",
                "",
                ChatColor.BLUE + "*" + ChatColor.AQUA + " /rank create",
                ChatColor.BLUE + "*" + ChatColor.AQUA + " /rank delete",
                ChatColor.BLUE + "*" + ChatColor.AQUA + " /rank edit",
                ChatColor.BLUE + "*" + ChatColor.AQUA + " /rank list",
                ""});
    }

    @Subcommand(parentLabel = "rank", label = "create", permission = "admin")
    public void executeCreate(final Player player) {
        new RankCreateProcedure(player).start();
    }

    @Subcommand(parentLabel = "rank", label = "delete", permission = "admin")
    public void executeDelete(final Player player) {
        new RankDeleteProcedure(player).start();
    }

    @Subcommand(parentLabel = "rank", label = "edit", permission = "admin")
    public void executeEdit(final Player player) {
        new RankEditChooseNameProcedure(player).start();
    }
}