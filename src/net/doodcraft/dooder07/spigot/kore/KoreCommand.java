package net.doodcraft.dooder07.spigot.kore;

import net.doodcraft.dooder07.spigot.kore.KorePlugin;
import net.doodcraft.dooder07.spigot.kore.Methods;
import net.doodcraft.dooder07.spigot.kore.DoodLogger;
import net.doodcraft.dooder07.spigot.kore.messaging.StringParser;
import net.doodcraft.dooder07.spigot.kore.configuration.ConfigManager;
import net.doodcraft.dooder07.spigot.kore.lib.FancyMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KoreCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("kore")) {
            if (args.length == 0) {
                String message = "&9:: &6&l&oKore&7 v" + KorePlugin.plugin.getDescription().getVersion();
                message = StringParser.addColor(message);

                String hover = "&8Author: &6Dooder07\n&8Website: &bhttp://doodcraft.net/";
                hover = StringParser.addColor(hover);

                FancyMessage msg = new FancyMessage(message);
                msg.tooltip(hover);

                if (sender instanceof Player) {
                    msg.send(sender);
                } else {
                    DoodLogger.logMessage(message);
                }

                if (sender.hasPermission("kore.admin.reload")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Valid arguments: &breload"));
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("kore.admin.reload")) {
                    try {
                        if (sender instanceof Player) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9:: &aReloading Kore..."));
                        }

                        DoodLogger.log("Kore", "&9:: &aReloading Kore...");

                        ConfigManager.defaultConfig.reload();
                        ConfigManager.defaultLocal.reload();

                        ConfigManager.defaultConfig.save();
                        ConfigManager.defaultLocal.save();

                        if (sender instanceof Player) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9:: &aConfiguration reloaded successfully!"));
                        }

                        DoodLogger.log("Kore", "&9:: &aConfiguration reloaded successfully!");
                        return true;
                    } catch (Exception ex) {
                        if (sender instanceof Player) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9:: &cError during reload!"));
                        }
                        DoodLogger.printError("Kore", "&cError during reload!", ex);
                        return false;
                    }
                } else {
                    Methods.sendNoPermsMsg((Player) sender, "kore.admin.reload");
                    return false;
                }
            }

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid arguments!"));
            if (sender.hasPermission("kore.admin.reload")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Valid arguments: &breload"));
            }
            return true;
        }
        return false;
    }
}