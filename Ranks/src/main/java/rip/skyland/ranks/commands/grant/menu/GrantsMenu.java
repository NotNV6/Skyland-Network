package rip.skyland.ranks.commands.grant.menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import rip.skyland.commons.menu.button.Button;
import rip.skyland.commons.menu.pagination.PaginatedMenu;
import rip.skyland.commons.player.PlayerData;
import rip.skyland.commons.util.CC;
import rip.skyland.commons.util.TimeUtils;
import rip.skyland.commons.util.WoolColor;
import rip.skyland.ranks.grants.Grant;
import rip.skyland.ranks.player.GrantData;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GrantsMenu extends PaginatedMenu {

    private final PlayerData target;

    public GrantsMenu(Player player, PlayerData target) {
        super(player);

        this.target = target;
    }

    @Override
    public String getTitle() {
        return "Grants of " + target.getName();
    }

    @Override
    public List<Button> getButtons() {
        final List<Button> buttons = new ArrayList<>();
        final GrantData grantData = target.findData(GrantData.class);

        for (int i = 0; i < grantData.getGrants().size(); i++) {
            final Grant grant = grantData.getGrants().get(i);
            final ZonedDateTime dateTime = Instant.ofEpochMilli(grant.getStartTime())
                    .atZone(ZoneId.of("America/New_York"));

            buttons.add(new Button(i, Material.WOOL, grant.getRank().getDisplayName() + ChatColor.WHITE + " #0",
                    Arrays.asList(
                            "",
                            "&bReason: &f" + grant.getReason(),
                            "&bIssued on: &f" + grant.getServer(),
                            "",
                            "&bIssue date: &f" + dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            "&bExpires in: &f" + (grant.getEndTime() == -1L ? "Never" : TimeUtils.formatWords(grant.getEndTime() - System.currentTimeMillis()))
                    ), WoolColor.getWoolColor(grant.getRank().getColor()), player -> {
                grant.setActive(!grant.isActive());

                player.sendMessage(ChatColor.AQUA + "You have changed the activity of grant " + CC.translate(grant.getRank().getDisplayName()) + ChatColor.WHITE + " #0");

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