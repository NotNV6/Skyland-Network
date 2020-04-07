package rip.skyland.commons.menu;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.menu.button.Button;
import rip.skyland.commons.util.CC;

import java.util.List;


@Getter
public abstract class Menu {

    private final Player player;
    private final MenuHandler menuHandler = CommonsPlugin.getInstance().getHandler().findModule(MenuHandler.class);

    public Menu(Player player) {
        this.player = player;
    }

    /**
     * Get the title of the menu
     *
     * @return the title
     */
    public abstract String getTitle();

    /**
     * Get the buttons of the menu
     *
     * @return the buttons
     */
    public abstract List<Button> getButtons();

    /**
     * Called upon menu close
     */
    public void onClose() {
        menuHandler.getMenus().remove(this);
    }

    /**
     * Get the size of the menu
     *
     * @return the size
     */
    public abstract int size();

    public void openMenu() {
        final Inventory inventory = Bukkit.createInventory(null, size(), CC.translate(getTitle()));

        this.getButtons().forEach(button -> inventory.setItem(button.getIndex(), button.getItem()));
        this.menuHandler.getMenus().add(this);

        player.openInventory(inventory);
    }
}
