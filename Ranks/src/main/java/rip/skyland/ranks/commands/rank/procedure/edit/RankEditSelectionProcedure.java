package rip.skyland.ranks.commands.rank.procedure.edit;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.skyland.commons.procedure.ChatProcedure;
import rip.skyland.ranks.ranks.Rank;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RankEditSelectionProcedure extends ChatProcedure {

    private final Player player;
    private final Rank rank;


    public RankEditSelectionProcedure(Player player, Rank rank) {
        super(player);

        this.player = player;
        this.rank = rank;
    }

    @Override
    public void start() {
        super.start();
        player.sendMessage(ChatColor.AQUA + "Please select the type you want to edit: " +
                ChatColor.WHITE + Arrays.stream(EditSelectionType.values())
                .map(Enum::name)
                .map(String::toLowerCase)
                .collect(Collectors.joining(", ")));
    }

    @Override
    public void finish(String result) {
        final EditSelectionType type = Arrays.stream(EditSelectionType.values())
                .filter(enumeration -> enumeration.name().equalsIgnoreCase(result))
                .findFirst().orElse(null);

        if (type == null) {
            player.sendMessage(ChatColor.RED + "That type could not be found, please choose one of those: " +
                    ChatColor.RED + Arrays.stream(EditSelectionType.values())
                    .map(Enum::name)
                    .map(String::toLowerCase)
                    .collect(Collectors.joining(", ")));

            return;
        }

        super.finish(result);
        new RankEditValueProcedure(player, rank, type).start();
    }
}