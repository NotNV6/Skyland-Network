package rip.skyland.commons.util;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;
import rip.skyland.commons.CommonsPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Easily create itemstacks, without messing your hands.
 * <i>Note that if you do use this in one of your projects, leave this notice.</i>
 * <i>Please do credit me if you do use this in one of your projects.</i>
 * @author NonameSL
 */
public class ItemBuilder implements Cloneable {

    private final ItemStack itemStack;

    /**
     * Constructor for creating a new ItemBuilder with only the Material type
     *
     * @param material the material type
     */
    public ItemBuilder(Material material) {
        this(material, 1);
    }

    /**
     * Constructor for creating a new ItemBuilder instantly with an ItemStack
     *
     * @param itemStack the item stack
     */
    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Constructor for creating a new ItemBuilder with the amount and Material type
     *
     * @param material the material type
     * @param amount the amount
     */
    public ItemBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
    }

    /**
     * Constructor for creating a new ItemBuilder with the amount, material type and durability byte.
     *
     * @param material the material type
     * @param amount the amount
     * @param durability the durability byte
     */
    public ItemBuilder(Material material, int amount, byte durability) {
        itemStack = new ItemStack(material, amount, durability);
    }

    /**
     * Set the durability value of the ItemStack
     *
     * @param dur the durability value
     * @return the current ItemBuilder instance
     */
    public ItemBuilder setDurability(short dur) {
        itemStack.setDurability(dur);
        itemStack.setData(new MaterialData(69));
        return this;
    }

    /**
     * Set the MaterialData value of the ItemStack
     *
     * @param data the bytes of the data
     * @return the current ItemBuilder instance
     */
    public ItemBuilder setData(byte data) {
        itemStack.setData(new MaterialData(this.itemStack.getType().ordinal(), data));

        return this;
    }

    /**
     * Set the name of the ItemStack
     *
     * @param name the name
     * @return the current ItemBuilder instance
     */
    public ItemBuilder setName(String name) {
        ItemMeta im = itemStack.getItemMeta();

        im.setDisplayName(CC.translate(name));
        itemStack.setItemMeta(im);

        return this;
    }

    /**
     * Add an unsafe enchantment to the ItemStack
     *
     * @param ench the enchantment
     * @param level the level of the enchantment
     * @return the current ItemBuilder instance
     */
    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
        itemStack.addUnsafeEnchantment(ench, level);
        return this;
    }

    /**
     * Remove an enchantment from the ItemBuilder
     *
     * @param ench the enchantment to remove
     * @return the current ItemBuilder instance
     */
    public ItemBuilder removeEnchantment(Enchantment ench) {
        itemStack.removeEnchantment(ench);
        return this;
    }

    /**
     * Set the SkullOwner data of the material
     * This only works if the item type is a SKULL_ITEM
     *
     * @param owner the name of the owner
     * @return the current ItemBuilder instance
     */
    public ItemBuilder setSkullOwner(String owner) {
        SkullMeta im = (SkullMeta) itemStack.getItemMeta();
        im.setOwner(owner);
        itemStack.setItemMeta(im);

        return this;
    }

    /**
     * Add a normal enchantment to the ItemStack
     *
     * @param ench the enchantment
     * @param level the level of the enchantment
     * @return the current ItemBuilder instance
     */
    public ItemBuilder addEnchant(Enchantment ench, int level) {
        ItemMeta im = itemStack.getItemMeta();
        im.addEnchant(ench, level, true);
        itemStack.setItemMeta(im);
        return this;
    }

    /**
     * Make the item unbreakable
     *
     * @return the current ItemBuilder instance
     */
    public ItemBuilder setInfinityDurability() {
        itemStack.setDurability(Short.MAX_VALUE);
        return this;
    }

    /**
     * Set the lore of the ItemMeta
     *
     * @param lore the lore
     * @return the current ItemBuilder instance
     */
    public ItemBuilder setLore(String... lore) {
        ItemMeta im = itemStack.getItemMeta();

        im.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(im);

        return this;
    }

    /**
     * Set the lore of the ItemMeta
     *
     * @param lore the lore
     * @return the current ItemBuilder instance
     */
    public ItemBuilder setLore(List<String> lore) {
        ItemMeta im = itemStack.getItemMeta();

        im.setLore(CC.translate(lore));
        itemStack.setItemMeta(im);

        return this;
    }


    /**
     * Set the color of the material
     * Only works if the Material is a INK_SACK
     *
     * @param color the color
     * @return the current ItemBuilder instance
     */
    @SuppressWarnings("deprecation")
    public ItemBuilder setDyeColor(DyeColor color) {
        this.itemStack.setDurability(color.getData());
        return this;
    }

    /**
     * Set the color of the material
     * Only works if the material is WOOL
     *
     * @param color the color
     * @return the current ItemBuilder instance
     */
    @Deprecated
    public ItemBuilder setWoolColor(DyeColor color) {
        if (!itemStack.getType().equals(Material.WOOL)) {
            return this;
        }

        this.itemStack.setDurability(color.getData());

        return this;
    }

    /**
     * Set the color of the material
     * Only works if the material is LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS or LEATHER_BOOTS
     *
     * @param color the color
     * @return the current ItemBuilder instance
     */
    public ItemBuilder setLeatherArmorColor(Color color) {
        LeatherArmorMeta im = (LeatherArmorMeta) itemStack.getItemMeta();
        im.setColor(color);
        itemStack.setItemMeta(im);
        return this;
    }

    /**
     * Set the click action of the Item
     *
     * @param action the click action
     * @return the current ItemBuilder instance
     */
    public ItemBuilder setAction(Consumer<Player> action) {
        if(action != null) {
            Bukkit.getPluginManager().registerEvents(new Listener() {
                @EventHandler
                public void onClick(PlayerInteractEvent event) {
                    ItemStack item = event.getItem();
                    if (event.hasItem() && item.equals(toItemStack())) {
                        action.accept(event.getPlayer());
                        event.setCancelled(true);
                    }
                }
            }, CommonsPlugin.getInstance());
        }

        return this;
    }

    public ItemStack toItemStack() {
        return itemStack;
    }
}