package rip.skyland.commons.menu.button;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.menu.Menu;
import rip.skyland.commons.menu.MenuHandler;
import rip.skyland.commons.menu.pagination.PaginatedMenu;

import java.util.List;
import java.util.Objects;

public class ButtonListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        final MenuHandler menuHandler = CommonsPlugin.getInstance().getHandler().findModule(MenuHandler.class);
        final Menu menu = menuHandler.getByTitleAndPlayer((Player) event.getWhoClicked(), event.getInventory().getTitle());

        if (menu != null) {
            if (!(event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null)) {
                event.setCancelled(true);
                List<Button> buttons = menu instanceof PaginatedMenu ? ((PaginatedMenu) menu).getPaginatedButtons() : menu.getButtons();
                Objects.requireNonNull(buttons.stream().filter(button -> button.getItem().equals(event.getCurrentItem())).findFirst().orElse(null)).getClick().accept((Player) event.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        final MenuHandler menuHandler = CommonsPlugin.getInstance().getHandler().findModule(MenuHandler.class);
        final Menu menu = menuHandler.getByTitleAndPlayer((Player) event.getPlayer(), event.getInventory().getTitle());

        if (menu != null) {
            menu.onClose();
        }
    }
}