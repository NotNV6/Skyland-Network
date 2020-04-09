package rip.skyland.commons.menu.pagination;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import rip.skyland.commons.CommonsPlugin;
import rip.skyland.commons.menu.Menu;
import rip.skyland.commons.menu.MenuHandler;
import rip.skyland.commons.menu.button.Button;
import rip.skyland.commons.util.CC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public abstract class PaginatedMenu extends Menu {

    private Player player;

    @Setter
    private int currentPage = 1;

    private List<Button> navigationButtons;

    /**
     * Constructor for creating a new instance of the PaginatedMenu
     *
     * @param player the player
     */
    public PaginatedMenu(Player player) {
        super(player);
        this.player = player;

        this.navigationButtons = Arrays.asList(
                new PaginationButton("&7Previous Page", this, false),
                new PaginationButton("&7Next Page", this, true));
    }

    /**
     * The size of the paginated menu
     * This is the menu size + 9
     *
     * @return the size
     */
    public int paginatedSize() {
        return size() + 9;
    }

    /**
     * Get the buttons of the menu
     *
     * @return the button list
     */
    public List<Button> getPaginatedButtons() {
        List<Button> buttons = new ArrayList<>();

        this.getButtons().forEach(button -> buttons.add(new Button(button.getIndex() + 9, button.getType(), button.getDisplayName(), button.getLore(), button.getDurability(), button.getClick())));
        buttons.addAll(navigationButtons);

        return buttons;
    }

    /**
     * Open the menu
     */
    public void openMenu() {
        this.updatePage();
    }

    /**
     * Update the menu
     */
    public void updatePage() {
        final Inventory inventory = Bukkit.createInventory(null, paginatedSize(), CC.translate(getTitle()));
        final MenuHandler menuHandler = CommonsPlugin.getInstance().getHandler().findModule(MenuHandler.class);

        this.onClose();
        player.closeInventory();

        navigationButtons.forEach(button -> inventory.setItem(button.getIndex(), button.getItem()));

        getPaginatedButtons().stream().filter(button -> (button.getIndex() < paginatedSize() * currentPage) && (button.getIndex() + 1 > paginatedSize() * (currentPage - 1)))
                .forEach(button -> inventory.setItem(button.getIndex() - (button instanceof PaginationButton ? 0 : paginatedSize() * (currentPage - 1)) + (currentPage == 1 ? 0 :
                                button instanceof PaginationButton ? 0 : 9),
                        button.getItem()));

        menuHandler.getMenus().add(this);
        player.openInventory(inventory);
    }
}