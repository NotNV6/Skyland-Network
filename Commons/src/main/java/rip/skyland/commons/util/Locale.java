package rip.skyland.commons.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import rip.skyland.commons.CommonsPlugin;

@UtilityClass
public class Locale {

    /** the primary color for the network */
    public final ChatColor PRIMARY_COLOR = ChatColor.AQUA;

    /** the secondary color for the network */
    public final ChatColor SECONDARY_COLOR = ChatColor.WHITE;

    /** the tertiary color for the network */
    public final ChatColor TERTIARY_COLOR = ChatColor.DARK_AQUA;

    /** the name of the server */
    public final String SERVER_NAME = CommonsPlugin.getInstance().getConfig().getString("server.name");

    /** timeout for logout timer in ticks */
    public final int LOGOUT_TIMEOUT = 10;

}