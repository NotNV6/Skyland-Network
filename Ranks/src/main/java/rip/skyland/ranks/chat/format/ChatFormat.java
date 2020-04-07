package rip.skyland.ranks.chat.format;

import org.bukkit.entity.Player;
import rip.skyland.ranks.chat.format.impl.DefaultChatFormat;

import java.util.List;

public interface ChatFormat {

    /**
     * Get the list of added additions
     *
     * @return the addition list
     */
    List<FormatAddition> getFormatAdditions();

    /**
     * Format a message with the current ChatFormat
     *
     * @param player  the player
     * @param message the message
     * @return the formatted string
     */
    String format(Player player, String message);

    /**
     * Add a new FormatAddition object to the chat format
     *
     * @param addition the addition
     * @return the current instance
     */
    ChatFormat add(FormatAddition addition);

    /**
     * Add a new addition to to the chat
     *
     * @param addition the addition
     * @param position the position for the addition
     * @return the current instance
     */
    ChatFormat add(String addition, FormatAddition.AdditionPosition position);

}
