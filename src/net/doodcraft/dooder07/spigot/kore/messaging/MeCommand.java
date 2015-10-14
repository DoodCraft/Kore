package net.doodcraft.dooder07.spigot.kore.messaging;

import net.doodcraft.dooder07.spigot.kore.Methods;
import net.doodcraft.dooder07.spigot.kore.DoodLogger;
import net.doodcraft.dooder07.spigot.kore.messaging.StringParser;
import net.doodcraft.dooder07.spigot.kore.configuration.Settings;
import net.doodcraft.dooder07.spigot.kore.lib.FancyMessage;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("me")) {
            if (sender.hasPermission("kore.chat.me")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: &b/me crais every time"));
                    return false;
                } else {
                    String message = StringUtils.join(args, " ");

                    if (sender.hasPermission("kore.chat.formatting")) {
                        message = StringParser.addColor(message);
                    } else {
                        message = StringParser.removeColor(message);
                    }

                    if (sender instanceof Player) {
                        Player player = (Player) sender;

                        String messageFormat = Settings.MECOMMAND_MESSAGE;
                        messageFormat = StringParser.parseChatVariables(player, messageFormat, message);
                        messageFormat = StringParser.addColor(messageFormat);

                        DoodLogger.logMessage(messageFormat);

                        String messageHover = Settings.MECOMMAND_HOVER;
                        messageHover = StringParser.parseChatVariables(player, messageHover, message);
                        messageHover = StringParser.addColor(messageHover);

                        FancyMessage me = new FancyMessage(messageFormat);
                        me.link(message);

                        if (!Settings.MECOMMAND_HOVER.equals("")) {
                            me.tooltip(messageHover);
                        }

                        if (!Bukkit.getOnlinePlayers().isEmpty()) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                me.send(p);
                            }
                        }
                    } else {
                        String messageFormat = Settings.MECOMMAND_MESSAGE;
                        messageFormat = StringParser.parseChatVariablesForConsole(messageFormat, message);
                        messageFormat = StringParser.addColor(messageFormat);

                        DoodLogger.logMessage(messageFormat);

                        String messageHover = Settings.MECOMMAND_HOVER;
                        messageHover = StringParser.parseChatVariablesForConsole(messageHover, message);
                        messageHover = StringParser.addColor(messageHover);

                        FancyMessage me = new FancyMessage(messageFormat);
                        me.link(message);

                        if (!Settings.MECOMMAND_HOVER.equals("")) {
                            me.tooltip(messageHover);
                        }

                        if (!Bukkit.getOnlinePlayers().isEmpty()) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                me.send(p);
                            }
                        }
                    }
                }
            } else {
                Methods.sendNoPermsMsg((Player) sender, "kore.chat.me");
                return false;
            }
        }
        return false;
    }
}