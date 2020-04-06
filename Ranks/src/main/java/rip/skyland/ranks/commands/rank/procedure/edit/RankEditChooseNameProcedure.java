package rip.skyland.ranks.commands.rank.procedure.edit;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.procedure.ChatProcedure;
import rip.skyland.ranks.ranks.Rank;
import rip.skyland.ranks.ranks.RankModule;

public class RankEditChooseNameProcedure extends ChatProcedure {

    private final Player player;

    public RankEditChooseNameProcedure(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public void start() {
        super.start();
        player.sendMessage(ChatColor.AQUA + "Please select a rank you want to edit.");
    }

    @Override
    public void finish(String result) {
        final RankModule rankModule = CommonsPlugin.getInstance().getHandler().findModule(RankModule.class);
        final Rank rank = rankModule.findRank(result);

        if(rank == null) {
            player.sendMessage(ChatColor.RED + "No rank with that name found!");
            return;
        }

        super.finish(result);
        new RankEditSelectionProcedure(player, rank).start();
    }
}