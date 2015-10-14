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

public class SayCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("say")) {
            if (sender.hasPermission("kore.chat.say")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You must supply a message!"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Example: &b/say hello world!"));
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

                        String messageFormat = Settings.SAYCOMMAND_MESSAGE;
                        messageFormat = StringParser.parseChatVariables(player, messageFormat, message);
                        messageFormat = StringParser.addColor(messageFormat);

                        DoodLogger.logMessage(messageFormat);

                        String messageHover = Settings.SAYCOMMAND_HOVER;
                        messageHover = StringParser.parseChatVariables(player, messageHover, message);
                        messageHover = StringParser.addColor(messageHover);

                        FancyMessage say = new FancyMessage(messageFormat);
                        say.link(message);

                        if (!Settings.SAYCOMMAND_HOVER.equals("")) {
                            say.tooltip(messageHover);
                        }

                        if (!Bukkit.getOnlinePlayers().isEmpty()) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                say.send(p);
                            }
                        }
                    } else {
                        String messageFormat = Settings.SAYCOMMAND_MESSAGE;
                        messageFormat = StringParser.parseChatVariablesForConsole(messageFormat, message);
                        messageFormat = StringParser.addColor(messageFormat);

                        DoodLogger.logMessage(messageFormat);

                        String messageHover = Settings.SAYCOMMAND_HOVER;
                        messageHover = StringParser.parseChatVariablesForConsole(messageHover, message);
                        messageHover = StringParser.addColor(messageHover);

                        FancyMessage say = new FancyMessage(messageFormat);
                        say.link(message);

                        if (!Settings.SAYCOMMAND_HOVER.equals("")) {
                            say.tooltip(messageHover);
                        }

                        if (!Bukkit.getOnlinePlayers().isEmpty()) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                say.send(p);
                            }
                        }
                    }
                }
            } else {
                Methods.sendNoPermsMsg((Player) sender, "kore.chat.say");
                return false;
            }
        }
        return false;
    }
}