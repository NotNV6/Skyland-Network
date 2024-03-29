package rip.skyland.ranks.commands.rank.procedure.edit;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.skyland.commons.procedure.ChatProcedure;
import rip.skyland.commons.util.CC;
import rip.skyland.commons.util.Locale;
import rip.skyland.ranks.ranks.Rank;

public class RankEditValueProcedure extends ChatProcedure {

    private final Player player;
    private final Rank rank;
    private final EditSelectionType type;

    public RankEditValueProcedure(Player player, Rank rank, EditSelectionType type) {
        super(player);

        this.player = player;
        this.rank = rank;
        this.type = type;
    }

    @Override
    public void start() {
        super.start();

        player.sendMessage(Locale.PRIMARY_COLOR + "Please type the value you want to give to the " + Locale.SECONDARY_COLOR + type.name().toLowerCase() + Locale.PRIMARY_COLOR + " type.");
    }

    @Override
    public void finish(String result) {
        switch (type) {

            case PREFIX: {
                rank.setPrefix(result);
            }
            break;

            case DISPLAY_NAME: {
                rank.setDisplayName(result);
            }
            break;

            case WEIGHT: {
                try {
                    rank.setWeight(Integer.parseInt(result));
                } catch (Exception exception) {
                    player.sendMessage(ChatColor.RED + result + " is not an integer.");
                }
            }
            break;

            case COLOR: {
                try {
                    rank.setColor(ChatColor.valueOf(result));
                } catch (Exception exception) {
                    player.sendMessage(ChatColor.RED + result + " is not a boolean.");
                }
            }
            break;

            case STAFF_RANK: {
                try {
                    rank.setStaffRank(Boolean.parseBoolean(result));
                } catch (Exception exception) {
                    player.sendMessage(ChatColor.RED + result + " is not a boolean.");
                }
            }
            break;

            case DEFAULT_RANK: {
                try {
                    rank.setDefaultRank(Boolean.parseBoolean(result));
                } catch (Exception exception) {
                    player.sendMessage(ChatColor.RED + result + " is not a boolean.");
                }
            }
            break;
        }

        super.finish(result);
        player.sendMessage(Locale.PRIMARY_COLOR + "You have set the " + Locale.SECONDARY_COLOR + type.name().toLowerCase() + Locale.PRIMARY_COLOR + " value to " + Locale.SECONDARY_COLOR + CC.translate(result));
    }
}