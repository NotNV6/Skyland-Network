package rip.skyland.ranks.commands.grant.procedure;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.skyland.commons.player.PlayerData;
import rip.skyland.commons.procedure.ChatProcedure;
import rip.skyland.commons.util.Locale;
import rip.skyland.ranks.ranks.Rank;

public class GrantReasonProcedure extends ChatProcedure {

    private final Player player;
    private final Rank rank;
    private final PlayerData target;

    public GrantReasonProcedure(Player player, Rank rank, PlayerData target) {
        super(player);

        this.rank = rank;
        this.player = player;
        this.target = target;
    }

    @Override
    public void start() {
        super.start();
        player.sendMessage(Locale.PRIMARY_COLOR + "Please type a reason for the grant.");
    }

    @Override
    public void finish(String result) {
        super.finish(result);

        new GrantDurationProcedure(player, rank, result, target).start();
    }
}
