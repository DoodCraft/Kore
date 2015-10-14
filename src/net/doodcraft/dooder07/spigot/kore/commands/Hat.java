package net.doodcraft.dooder07.spigot.kore.commands;

import net.doodcraft.dooder07.spigot.kore.Methods;
import net.doodcraft.dooder07.spigot.kore.messaging.StringParser;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Hat implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;

        if (sender instanceof Player) {
            player = (Player) sender;
        }

        if (command.getName().equalsIgnoreCase("hat")) {
            if (player != null) {
                if (player.hasPermission("kore.command.hat")) {
                    ItemStack item = player.getItemInHand();
                    ItemStack helmet = player.getInventory().getHelmet();

                    if (!item.getType().equals(Material.AIR) && item.getType().isBlock()) {
                        if (player.getInventory().getHelmet() == player.getItemInHand()) {
                            player.sendMessage(StringParser.addColor("&7You're already wearing that!"));
                        } else {
                            if (player.getInventory().getHelmet().getType() != Material.AIR) {
                                Methods.addItem(player, player.getInventory().getHelmet(), player.getInventory().getHelmet().getAmount());
                                player.getInventory().getHelmet().setType(Material.AIR);
                            }
                            Methods.removeItem(player.getInventory(), item.getType(), 1);
                            player.getInventory().setHelmet(item);
                            player.sendMessage(StringParser.addColor("&7Nice hat!"));
                        }
                    } else {
                        player.sendMessage(StringParser.addColor("&7Hold a block to wear it as a hat!"));
                    }
                }
            } else {
                sender.sendMessage(StringParser.addColor("&cConsole can't wear hats."));
            }
        }
        return false;
    }
}
