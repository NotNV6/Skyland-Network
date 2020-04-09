package rip.skyland.essentials.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.player.PlayerData;
import rip.skyland.commons.player.PlayerDataModule;
import rip.skyland.commons.util.Locale;
import rip.skyland.essentials.modmode.data.ModModeData;

public class ModModeListener implements Listener {

    private final PlayerDataModule playerDataModule = CommonsPlugin.getInstance().getHandler().findModule(PlayerDataModule.class);

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.hasItem()) {
            final ItemStack item = event.getItem();
            final Player player = event.getPlayer();

            final PlayerData playerData = playerDataModule.findPlayerData(player.getUniqueId());
            final ModModeData modModeData = playerData.findData(ModModeData.class);

            if (item.getData().getData() == 69) {
                if (modModeData.isVanished()) {
                    modModeData.vanishPlayer(player);
                } else {
                    modModeData.unvanishPlayer(player);
                }

                player.sendMessage(Locale.PRIMARY_COLOR + "You have been " + Locale.SECONDARY_COLOR + (modModeData.isVanished() ? "vanished" : "unvanished"));
            }
        }
    }
}