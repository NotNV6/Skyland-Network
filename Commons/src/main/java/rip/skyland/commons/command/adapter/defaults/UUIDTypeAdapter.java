package rip.skyland.commons.command.adapter.defaults;

import org.bukkit.command.CommandSender;
import rip.skyland.commons.command.adapter.TypeAdapter;

import java.util.UUID;

public class UUIDTypeAdapter implements TypeAdapter<UUID> {

    @Override
    public UUID convert(CommandSender sender, String source) {
        return UUID.fromString(source);
    }

    @Override
    public Class<UUID> getType() {
        return UUID.class;
    }
}
