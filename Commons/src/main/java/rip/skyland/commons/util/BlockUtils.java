package rip.skyland.commons.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;
import rip.skyland.commons.CommonsPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@UtilityClass
public class BlockUtils {

    /**
     * Paste blocks with a delay
     * Used to safely place lots of blocks without a significant tps loss.
     *
     * @param blocks the blocks and locations you want to paste
     */
    public void pasteBlocks(Map<Location, Material> blocks) {
        final List<Location> locations = new ArrayList<>(blocks.keySet());
        final List<Material> materials = new ArrayList<>(blocks.values());

        new BukkitRunnable() {

            @Override
            public void run() {
                for (int i = 0; i < Math.min(1000, blocks.size() / 10); i++) {
                    if (i > locations.size() || i > materials.size()) {
                        this.cancel();
                        return;
                    }

                    final Location location = locations.get(i);
                    final Material material = materials.get(i);

                    location.getBlock().setType(material);
                }
            }
        }.runTaskTimer(CommonsPlugin.getInstance(), 5L, 5L);
    }
}