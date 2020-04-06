package rip.skyland.commons.command.adapter.defaults;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.command.adapter.TypeAdapter;
import rip.skyland.commons.player.PlayerData;
import rip.skyland.commons.player.PlayerDataModule;

public class PlayerDataTypeAdapter implements TypeAdapter<PlayerData> {

    private final PlayerDataModule playerDataModule = CommonsPlugin.getInstance().getHandler().findModule(PlayerDataModule.class);

    @Override
    public PlayerData convert(CommandSender sender, String source) {
        if (playerDataModule.getPlayerData().stream().anyMatch(playerData -> playerData.getName().equalsIgnoreCase(source))) {
            return playerDataModule.getPlayerData().stream()
                    .filter(playerData -> playerData.getName().equalsIgnoreCase(source))
                    .findFirst().orElse(null);
        }

        PlayerData playerData = null;
        Player player;

        if (source.equals("SELF")) {
            player = (Player) sender;
        } else {
            player = Bukkit.getPlayer(source);
        }

        if (player != null) {
            playerData = playerDataModule.findPlayerData(player.getUniqueId());
        }

        return playerData;
    }

    @Override
    public Class<PlayerData> getType() {
        return PlayerData.class;
    }
}