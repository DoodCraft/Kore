package net.doodcraft.dooder07.spigot.kore.lib;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InventoryMenu {

    private Inventory inventory;

    private String name;

    private int slots;

    private List<ItemStack> items = new ArrayList<ItemStack>();

    private HashMap<Integer, ItemStack> itemSlot = new HashMap<Integer, ItemStack>();

    /**
     * @param inventoryName - Name the inventory
     */
    public InventoryMenu(String inventoryName) {
        this.name = inventoryName;
    }

    /**
     * @param item
     * @return The class
     */
    public InventoryMenu addItem(ItemStack item) {
        items.add(item);
        return this;
    }

    /**
     * @param item - Add an item
     * @return The class
     */
    public InventoryMenu addItem(ItemStack[] item) {
        for (ItemStack i : item) {
            this.items.add(i);
        }
        return this;
    }

    /**
     * @return a clear inventory
     */
    public InventoryMenu clear() {
        this.items.clear();
        this.itemSlot.clear();
        return this;
    }

    /**
     * @param item
     * @return A boolean
     */
    public boolean contains(ItemStack item) {
        if (this.items.contains(item) || this.itemSlot.values().contains(item))
            return true;
        return false;
    }

    /**
     * @param item
     * @return A boolean
     */
    public boolean contains(Material material) {
        List<Material> materials = new ArrayList<Material>();
        for (ItemStack item : items) {
            materials.add(item.getType());
        }
        for (ItemStack item : itemSlot.values()) {
            materials.add(item.getType());
        }

        return materials.contains(material);
    }

    /**
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The slots
     */
    public int getSize() {
        return this.slots;
    }

    /**
     * @param player
     */
    public void openMenuFor(Player player) {
        this.inventory = Bukkit.createInventory(null, slots, name);
        for (Integer slot : this.itemSlot.keySet()) {
            inventory.setItem(slot, this.itemSlot.get(slot));
        }
        for (ItemStack i : this.items) {
            inventory.addItem(i);
        }
        player.openInventory(this.inventory);
    }

    /**
     * @param ItemStack item
     */
    public void removeItem(ItemStack item) {
        if (!itemSlot.values().contains(item) || items.contains(item))
            return;

        if (itemSlot.values().contains(item)) {
            for (int slot : itemSlot.keySet()) {
                if (itemSlot.get(slot).equals(item)) {
                    itemSlot.remove(slot);
                }
            }
        } else if (items.contains(item)) {
            items.remove(item);
        }
    }

    /**
     * @param slot
     * @param item
     * @return The class
     */
    public InventoryMenu setItem(int slot, ItemStack item) {
        itemSlot.put(slot, item);
        return this;
    }

    /**
     * @param slots
     * @return The class
     */
    public InventoryMenu setSlots(int slots) {
        this.slots = slots;
        return this;
    }
}