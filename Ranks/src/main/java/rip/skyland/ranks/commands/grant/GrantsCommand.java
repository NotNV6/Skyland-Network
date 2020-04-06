package rip.skyland.ranks.commands.grant;

import org.bukkit.entity.Player;
import rip.skyland.commons.command.annotation.Command;
import rip.skyland.commons.player.PlayerData;
import rip.skyland.ranks.commands.grant.menu.GrantsMenu;

public class GrantsCommand {

    @Command(label="grants", permission="admin")
    public void execute(Player player, PlayerData target) {
        new GrantsMenu(player, target).openMenu();
    }

}
