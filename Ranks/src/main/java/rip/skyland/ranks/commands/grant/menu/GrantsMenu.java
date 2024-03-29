package rip.skyland.ranks.commands.grant.menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import rip.skyland.commons.menu.button.Button;
import rip.skyland.commons.menu.pagination.PaginatedMenu;
import rip.skyland.commons.player.PlayerData;
import rip.skyland.commons.util.CC;
import rip.skyland.commons.util.Locale;
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

            final List<String> lore = new ArrayList<>(Arrays.asList(
                    "",
                    Locale.PRIMARY_COLOR + "Rank: " + Locale.SECONDARY_COLOR + grant.getRank().getDisplayName(),
                    Locale.PRIMARY_COLOR + "Reason: " + Locale.SECONDARY_COLOR + grant.getReason(),
                    Locale.PRIMARY_COLOR + "Issued on: " + Locale.SECONDARY_COLOR + grant.getServer(),
                    "",
                    Locale.PRIMARY_COLOR + "Expires in: " + Locale.SECONDARY_COLOR + (!grant.isActive() ? "Expired" : grant.getEndTime() == -1L ? "Never" : TimeUtils.formatWords(grant.getEndTime() - System.currentTimeMillis()))
            ));

            if (!grant.isActive()) {
                lore.add(Locale.PRIMARY_COLOR + "Expired at: &f" + Instant.ofEpochMilli(grant.getExpirationTime()).atZone(ZoneId.of("America/New_York")).format(DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss")));
            }

            lore.add("");

            buttons.add(new Button(i, Material.WOOL, Locale.PRIMARY_COLOR + dateTime.format(DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss")), lore, WoolColor.getWoolColor(grant.getRank().getColor()), player -> {
                grant.setActive(!grant.isActive());

                player.sendMessage(Locale.PRIMARY_COLOR + "You have changed the activity of a grant");

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