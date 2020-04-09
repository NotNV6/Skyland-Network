package rip.skyland.essentials.modmode.cache;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public class CachedPlayerData {

    private final ItemStack[] contents;
    private final ItemStack[] armor;

    private final GameMode gameMode;

    private final Location location;

    private final double health;
    private final int foodLevel;

    /**
     * Constructor for creating a new InventoryStructure instance for a player
     * This will automatically set all fields with the player's properties.
     *
     * @param player the player
     */
    public CachedPlayerData(Player player) {
        this(player.getInventory().getContents(), player.getInventory().getArmorContents(), player.getGameMode(), player.getLocation(), player.getHealth(), player.getFoodLevel());
    }

    /**
     * Constructor for creating a new InventoryStructure instance
     * This constructor requires all data parameters.
     *
     * @param contents  the item contents
     * @param armor     the armor the player is wearing
     * @param health    the health level
     * @param foodLevel the food level
     */
    public CachedPlayerData(ItemStack[] contents, ItemStack[] armor, GameMode gameMode, Location location, double health, int foodLevel) {
        this.contents = contents;
        this.armor = armor;
        this.gameMode = gameMode;
        this.location = location;
        this.health = health;
        this.foodLevel = foodLevel;
    }

    /**
     * Apply the data to a player
     *
     * @param player the player
     */
    public void applyData(Player player) {
        // set the items
        player.getInventory().setContents(contents);
        player.getInventory().setArmorContents(armor);

        // set health and food
        player.setHealth(health);
        player.setFoodLevel(foodLevel);

        // set gamemode
        player.setGameMode(gameMode);

        // set location
        player.teleport(location);
    }
}