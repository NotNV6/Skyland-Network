package rip.skyland.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.command.annotation.Command;
import rip.skyland.commons.command.annotation.Parameter;
import rip.skyland.commons.player.PlayerData;
import rip.skyland.commons.player.PlayerDataModule;
import rip.skyland.commons.util.Locale;
import rip.skyland.essentials.modmode.ModModeModule;
import rip.skyland.essentials.modmode.data.ModModeData;

public class ModModeCommand {

    @Command(label = "mod", aliases = {"hackermode", "h", "modmode"}, permission = "staff")
    public void execute(Player player, @Parameter(name = "target", value = "SELF") Player target) {
        final ModModeModule modModeModule = CommonsPlugin.getInstance().getHandler().findModule(ModModeModule.class);
        final PlayerDataModule playerDataModule = CommonsPlugin.getInstance().getHandler().findModule(PlayerDataModule.class);
        final PlayerData playerData = playerDataModule.findPlayerData(target.getUniqueId());

        if (playerData != null) {
            final ModModeData modModeData = playerData.findData(ModModeData.class);

            if (modModeData == null) {
                player.sendMessage(ChatColor.RED + "That player isn't a staff member or their profile is incomplete.");
                return;
            }

            if (modModeData.isModModeEnabled()) {
                modModeModule.disableModMode(player);
            } else {
                modModeModule.enableModMode(player);
            }

            player.sendMessage(Locale.PRIMARY_COLOR + "You have " + (modModeData.isModModeEnabled() ? "enabled" : "disabled") + Locale.SECONDARY_COLOR + target.getDisplayName() + "'s " + Locale.PRIMARY_COLOR + " mod mode.");
        }
    }
}