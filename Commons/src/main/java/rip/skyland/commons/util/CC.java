package rip.skyland.commons.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CC {

    /**
     * Translate a string
     *
     * @param string the string
     * @return the translated string
     */
    public String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Translate a String List
     *
     * @param string the string list
     * @return the translated string list
     */
    public List<String> translate(List<String> string) {
        return string.stream()
                .map(CC::translate)
                .collect(Collectors.toList());
    }

    /**
     * Translate a String Array
     *
     * @param strings the string array
     * @return the translated string array
     */
    public String[] translate(String... strings) {
        return translate(Arrays.asList(strings)).toArray(new String[strings.length]);
    }
}
