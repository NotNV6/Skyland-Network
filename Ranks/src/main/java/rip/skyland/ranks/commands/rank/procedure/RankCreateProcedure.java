package rip.skyland.ranks.commands.rank.procedure;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.procedure.ChatProcedure;
import rip.skyland.commons.util.Locale;
import rip.skyland.ranks.ranks.Rank;
import rip.skyland.ranks.ranks.RankModule;

import java.util.UUID;

public class RankCreateProcedure extends ChatProcedure {

    private final Player player;

    public RankCreateProcedure(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public void start() {
        super.start();

        player.sendMessage(Locale.PRIMARY_COLOR + "Please select the name for the rank.");
    }

    @Override
    public void finish(String result) {
        final RankModule rankModule = CommonsPlugin.getInstance().getHandler().findModule(RankModule.class);

        if (rankModule.findRank(result) != null) {
            player.sendMessage(ChatColor.RED + "A rank with that name already exists!");
            return;
        }

        super.finish(result);
        new Rank(UUID.randomUUID(), result);
    }
}
