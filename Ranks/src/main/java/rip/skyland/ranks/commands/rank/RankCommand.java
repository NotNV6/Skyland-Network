package rip.skyland.ranks.commands.rank;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.command.annotation.Command;
import rip.skyland.commons.command.annotation.Subcommand;
import rip.skyland.commons.util.CC;
import rip.skyland.ranks.commands.rank.procedure.RankCreateProcedure;
import rip.skyland.ranks.commands.rank.procedure.RankDeleteProcedure;
import rip.skyland.ranks.commands.rank.procedure.edit.RankEditChooseNameProcedure;
import rip.skyland.ranks.ranks.RankModule;

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

    @Subcommand(parentLabel = "rank", label = "list", permission = "admin")
    public void executeList(final Player player) {
        final RankModule rankModule = CommonsPlugin.getInstance().getHandler().findModule(RankModule.class);

        player.sendMessage("");

        rankModule.getRanks().stream()
                .map(rank -> ChatColor.BLUE + "* " + CC.translate(rank.getDisplayName()) + ChatColor.AQUA + " (W: " + rank.getWeight() + ") ")
                .forEach(player::sendMessage);

        player.sendMessage("");

    }
}