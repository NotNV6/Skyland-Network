package rip.skyland.ranks.commands.rank.procedure;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.procedure.ChatProcedure;
import rip.skyland.ranks.ranks.Rank;
import rip.skyland.ranks.ranks.RankModule;

public class RankDeleteProcedure extends ChatProcedure {

    private final Player player;

    public RankDeleteProcedure(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public void start() {
        super.start();

        player.sendMessage(ChatColor.AQUA + "Please select the name of the rank you want to delete.");
    }

    @Override
    public void finish(String result) {
        final RankModule rankModule = CommonsPlugin.getInstance().getHandler().findModule(RankModule.class);
        final Rank rank = rankModule.findRank(result);

        if(rank == null) {
            player.sendMessage(ChatColor.WHITE + "No rank with that name found!");
            return;
        }

        super.finish(result);
        rankModule.removeRank(rank);
    }
}
