package rip.skyland.commons.command.adapter;

import org.bukkit.command.CommandSender;
import rip.skyland.commons.util.CC;

import java.util.Collections;
import java.util.List;

public interface TypeAdapter<T> {

    /**
     * Convert a String to the type
     *
     * @param sender the executor
     * @param source the string to get converted
     * @return the converted type
     * @throws Exception calls the handleException method
     */
    T convert(CommandSender sender, String source) throws Exception;

    /**
     * Tab complete the String to the type
     *
     * @param sender the executor
     * @param source the string
     * @return the returned tab complete string list
     */
    default List<String> tabComplete(CommandSender sender, String source) {
        return Collections.emptyList();
    }

    /**
     * Handle a thrown exception
     *
     * @param sender the executor
     * @param source the provided string
     */
    default void handleException(CommandSender sender, String source) {
        sender.sendMessage(CC.translate("&cError while parsing '" + source + "' with that type."));
    }

    Class<T> getType();

}
