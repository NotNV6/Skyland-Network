package rip.skyland.commons.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public class WoolColor {

    /**
     * Convert a ChatColor to a color byte
     *
     * @param color the color
     * @return the byte
     */
    public byte getWoolColor(ChatColor color) {
        switch(color) {
            case GOLD:
                return 1;

            case DARK_PURPLE:
                return 2;

            case BLUE: case AQUA:
                return 3;

            case YELLOW:
                return 4;

            case GREEN:
                return 5;

            case LIGHT_PURPLE:
                return 6;

            case DARK_GRAY:
                return 7;

            case GRAY:
                return 8;

            case DARK_AQUA:
                return 9;

            case DARK_BLUE:
                return 11;

            case DARK_GREEN:
                return 13;

            case RED: case DARK_RED:
                return 14;

            case BLACK:
                return 15;

            default:
                return 0;
        }
    }
}
