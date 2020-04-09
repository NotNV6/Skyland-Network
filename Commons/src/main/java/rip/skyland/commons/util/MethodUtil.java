package rip.skyland.commons.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import rip.skyland.commons.CommonsPlugin;

@UtilityClass
public class MethodUtil {

    /**
     * Execute a callback later
     *
     * @param callback the callback to get executed
     * @param ticks the amount of ticks to wait
     */
    public void executeLater(Callback callback, int ticks) {
        Bukkit.getScheduler().runTaskLater(CommonsPlugin.getInstance(), callback::accept, ticks);
    }

}
