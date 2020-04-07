package rip.skyland.commons.menu;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.menu.button.ButtonListener;
import rip.skyland.commons.module.Module;
import rip.skyland.commons.util.CC;

import java.util.ArrayList;
import java.util.List;

public class MenuHandler extends Module {

    @Getter
    private List<Menu> menus = new ArrayList<>();

    public void enable() {
        super.enable();

        Bukkit.getPluginManager().registerEvents(new ButtonListener(), CommonsPlugin.getInstance());
    }

    /**
     * Find a menu by a player and a title
     *
     * @param player the player
     * @param title  the title
     * @return the menu
     */
    public Menu getByTitleAndPlayer(Player player, String title) {
        return this.menus.stream()
                .filter(menu -> CC.translate(menu.getTitle()).equalsIgnoreCase(CC.translate(title)) && menu.getPlayer().equals(player))
                .findFirst().orElse(null);
    }

    /**
     * Find a menu by a player
     *
     * @param player the player
     * @return the menu
     */
    public Menu getMenuByPlayer(Player player) {
        return menus.stream().filter(menu -> menu.getPlayer().equals(player))
                .findFirst().orElse(null);
    }

    /**
     * Get a menu by a title
     *
     * @param title the title
     * @return the menu
     */
    public Menu getByTitle(String title) {
        return menus.stream()
                .filter(menu -> menu.getTitle().equals(title))
                .findFirst().orElse(null);
    }
}