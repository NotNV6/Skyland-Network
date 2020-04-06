package rip.skyland.commons.menu.pagination;

import org.bukkit.Material;
import org.bukkit.Sound;
import rip.skyland.commons.menu.button.Button;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class PaginationButton extends Button {

    public PaginationButton(String displayName, PaginatedMenu menu, boolean next) {
        super(next ? 8 : 0, Material.CARPET, displayName, Collections.emptyList(), 0, player -> {
            if(next && menu.getCurrentPage() < Objects.requireNonNull(menu.getPaginatedButtons().stream().sorted(Comparator.comparingInt(Button::getIndex)).reduce((first, second) -> second).orElse(null)).getIndex()) {
                menu.setCurrentPage(menu.getCurrentPage()+1);
                player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            } else if(!next && menu.getCurrentPage() > 1) {
                menu.setCurrentPage(menu.getCurrentPage()-1);
                player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            } else {
                player.playSound(player.getLocation(), Sound.DIG_GRASS, 1F, 1F);
            }

            menu.updatePage();
        });
    }
}
