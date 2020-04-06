package rip.skyland.commons.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import rip.skyland.commons.CommonsPlugin;

@UtilityClass
public class MethodUtil {

    public void executeLater(Callback callback, int ticks) {
        Bukkit.getScheduler().runTaskLater(CommonsPlugin.getInstance(), callback::accept, ticks);
    }

}
