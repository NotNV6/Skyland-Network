package rip.skyland.commons.command.adapter.defaults;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rip.skyland.commons.command.adapter.TypeAdapter;

public class PlayerTypeAdapter implements TypeAdapter<Player> {

    @Override
    public Player convert(CommandSender sender, String source) {
        return Bukkit.getPlayer(source);
    }

    @Override
    public Class<Player> getType() {
        return Player.class;
    }
}
