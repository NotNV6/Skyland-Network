package rip.skyland.ranks.commands.grant.menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.menu.button.Button;
import rip.skyland.commons.menu.pagination.PaginatedMenu;
import rip.skyland.commons.player.PlayerData;
import rip.skyland.commons.util.Locale;
import rip.skyland.commons.util.WoolColor;
import rip.skyland.ranks.commands.grant.procedure.GrantReasonProcedure;
import rip.skyland.ranks.ranks.Rank;
import rip.skyland.ranks.ranks.RankModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrantMenu extends PaginatedMenu {

    private final PlayerData target;

    private final RankModule rankModule = CommonsPlugin.getInstance().getHandler().findModule(RankModule.class);

    public GrantMenu(Player player, PlayerData target) {
        super(player);

        this.target = target;
    }

    @Override
    public String getTitle() {
        return "Select a Rank";
    }

    @Override
    public List<Button> getButtons() {
        final List<Button> buttons = new ArrayList<>();

        for (int i = 0; i < rankModule.getRanks().size(); i++) {
            final Rank rank = rankModule.getRanks().get(i);

            buttons.add(new Button(i, Material.WOOL, Locale.PRIMARY_COLOR + (rank.isDefaultRank() ? "[Default] " : rank.isStaffRank() ? "[Staff] " : "") + rank.getDisplayName(),
                    Arrays.asList(
                            "",
                            Locale.PRIMARY_COLOR + "Click to grant this rank to " + (target.getPlayer() == null ? target.getName() : target.getPlayer().getDisplayName()),
                            ""
                    ), WoolColor.getWoolColor(rank.getColor()), player -> {

                if(rank.isDefaultRank()) {
                    player.sendMessage(ChatColor.RED + "That rank is not grantable.");
                    return;
                }

                new GrantReasonProcedure(player, rank, target).start();
                player.closeInventory();
            }));
        }

        return buttons;
    }

    @Override
    public void onClose() {
    }

    @Override
    public int size() {
        return 9;
    }
}