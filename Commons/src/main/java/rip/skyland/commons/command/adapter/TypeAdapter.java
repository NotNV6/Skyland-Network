package rip.skyland.commons.command.adapter;

import org.bukkit.command.CommandSender;
import rip.skyland.commons.util.CC;

import java.util.Collections;
import java.util.List;

public interface TypeAdapter<T> {

    T convert(CommandSender sender, String source) throws Exception;

    default List<String> tabComplete(CommandSender sender, String source) {
        return Collections.emptyList();
    }

    default void handleException(CommandSender sender, String source) {
        sender.sendMessage(CC.translate("&cError while parsing '" + source + "' with that type."));
    }

    Class<T> getType();

}
