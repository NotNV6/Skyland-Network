package rip.skyland.essentials.modmode;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.module.Module;
import rip.skyland.commons.player.PlayerData;
import rip.skyland.commons.player.PlayerDataModule;
import rip.skyland.essentials.modmode.cache.CachedPlayerData;
import rip.skyland.essentials.modmode.data.ModModeData;

public class ModModeModule extends Module {

    @Override
    public void enable() {
        super.enable();
    }

    /**
     * Enable mod mode for a player
     *
     * @param player the player
     */
    public void enableModMode(Player player) {
        final PlayerDataModule playerDataModule = CommonsPlugin.getInstance().getHandler().findModule(PlayerDataModule.class);
        final PlayerData playerData = playerDataModule.findPlayerData(player.getUniqueId());

        if (playerData != null) {
            final ModModeData data = playerData.findData(ModModeData.class);

            data.setCachedPlayerData(new CachedPlayerData(player));
            data.setCanDestroyBlocks(player.hasPermission("admin"));
            data.setModModeEnabled(true);

            player.setGameMode(GameMode.CREATIVE);
        }
    }

    public void disableModMode(Player player) {
        final PlayerDataModule playerDataModule = CommonsPlugin.getInstance().getHandler().findModule(PlayerDataModule.class);
        final PlayerData playerData = playerDataModule.findPlayerData(player.getUniqueId());

        if (playerData != null) {
            final ModModeData data = playerData.findData(ModModeData.class);
            final CachedPlayerData cachedPlayerData = data.getCachedPlayerData();

            cachedPlayerData.applyData(player);

            data.setModModeEnabled(false);
            data.setCanDestroyBlocks(player.hasPermission("admin"));
            data.setCachedPlayerData(null);
        }
    }
}