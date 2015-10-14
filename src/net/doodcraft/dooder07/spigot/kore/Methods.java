package net.doodcraft.dooder07.spigot.kore;

import net.doodcraft.dooder07.spigot.kore.configuration.Settings;
import net.doodcraft.dooder07.spigot.kore.lib.FancyMessage;
import net.doodcraft.dooder07.spigot.kore.messaging.StringParser;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Methods {
    public static void setWorldStatic(World world, boolean isStatic) throws Exception {
        Field field = World.class.getDeclaredField("isClientSide");
        field.setAccessible(true);
        field.set(world, isStatic);
    }

    public static boolean isHidden(Player player) {
        if (player.getGameMode().equals(GameMode.SPECTATOR)) {
            return true;
        }
        return false;
    }

    public static ItemStack createSkull(String owner, String name, ArrayList<String> lore) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(owner);
        skullMeta.setDisplayName(StringParser.addColor(name));
        skullMeta.setLore(lore);
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

    public static void addItem(Player player, ItemStack item, int amount)
    {
        if (player != null && item != null)
        {
            for (int i = 0; i < amount; i++) {
                player.getInventory().addItem(item);
            }
            player.updateInventory();
        }
    }

    public static int hasItem(Inventory inventory, Material mat, int amount) {
        int searchAmount = 0;
        ItemStack[] arrayOfItemStack;
        int j = (arrayOfItemStack = inventory.getContents()).length;

        for (int i = 0; i < j; i++) {
            ItemStack item = arrayOfItemStack[i];
            if ((item != null) && (item.getType().equals(mat))) {
                searchAmount += item.getAmount();
            }
        }
        return searchAmount - amount;
    }

    public static void removeItem(Inventory inventory, Material material, int amount) {
        ItemStack stack = new ItemStack(Material.getMaterial(String.valueOf(material).toUpperCase()), amount);
        inventory.removeItem(stack);
    }

    public static void sendMotd(Player player) {
        for (String messageFormat : Settings.MOTD_LINES) {
            messageFormat = StringParser.parseChatVariables(player, messageFormat, "");
            messageFormat = StringParser.addColor(messageFormat);
            player.sendMessage(messageFormat);
        }
    }

    public static void sendMotdConsole(ConsoleCommandSender sender) {
        for (String messageFormat : Settings.MOTD_LINES) {
            messageFormat = StringParser.parseChatVariablesForConsole(messageFormat, "");
            messageFormat = StringParser.addColor(messageFormat);
            sender.sendMessage(messageFormat);
        }
    }

    public static void sendNoPermsMsg(Player player, String node) {
        String messageFormat = Settings.NOPERMISSION_MESSAGE;
        messageFormat = StringParser.parseChatVariables(player, messageFormat, node);
        messageFormat = StringParser.addColor(messageFormat);

        String messageHover = Settings.NOPERMISSION_HOVER;
        messageHover = StringParser.parseChatVariables(player, messageHover, node);
        messageHover = StringParser.addColor(messageHover);

        FancyMessage noPermission = new FancyMessage(messageFormat);
        noPermission.tooltip(messageHover);
        noPermission.send(player);
    }
}