package rip.skyland.commons.menu.button;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import rip.skyland.commons.util.CC;

import java.util.List;
import java.util.function.Consumer;

@Getter
public class Button {

    private final Material type;
    private ItemStack item;

    private final int index;
    private final byte durability;

    private final String displayName;

    private final List<String> lore;
    private final Consumer<Player> click;

    @Setter
    private boolean added;

    /**
     * Constructor for creating a new Button
     *
     * @param index the index
     * @param type the material type
     * @param displayName the name
     * @param lore the lore
     * @param durability the durability/item code
     * @param click click action
     */
    public Button(int index, Material type, String displayName, List<String> lore, int durability, Consumer<Player> click) {
        this.index = index;
        this.displayName = displayName;
        this.lore = lore;
        this.durability = (byte) durability;
        this.click = click;
        this.type = type;

        this.updateItem();
    }

    public Button updateItem() {
        this.item = new ItemStack(type, 0, durability);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate(displayName));
        meta.setLore(CC.translate(lore));
        this.item.setItemMeta(meta);

        return this;
    }
}