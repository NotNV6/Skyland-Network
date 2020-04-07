package rip.skyland.ranks.commands.grant.procedure;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.skyland.commons.player.PlayerData;
import rip.skyland.commons.procedure.ChatProcedure;
import rip.skyland.commons.util.Locale;
import rip.skyland.commons.util.TimeUtils;
import rip.skyland.ranks.grants.Grant;
import rip.skyland.ranks.player.GrantData;
import rip.skyland.ranks.ranks.Rank;

public class GrantDurationProcedure extends ChatProcedure {

    private final Player player;
    private final Rank rank;
    private final String reason;
    private final PlayerData target;

    public GrantDurationProcedure(Player player, Rank rank, String reason, PlayerData target) {
        super(player);

        this.rank = rank;
        this.player = player;
        this.reason = reason;
        this.target = target;
    }

    @Override
    public void start() {
        super.start();
        player.sendMessage(Locale.PRIMARY_COLOR + "Please type the duration the grant has to last." + Locale.SECONDARY_COLOR + " (type perm or permanent for a permanent grant)");
    }

    @Override
    public void finish(String result) {
        final long duration;
        final Grant grant = new Grant(rank, player.getUniqueId());

        if (result.equalsIgnoreCase("perm") || result.equalsIgnoreCase("permanent")) {
            duration = -1L;
        } else {
            duration = TimeUtils.format(result);
        }

        grant.setReason(reason);
        grant.setEndTime(duration == -1L ? -1L : System.currentTimeMillis() + duration);

        target.findData(GrantData.class).addGrant(grant);

        super.finish(result);
    }
}