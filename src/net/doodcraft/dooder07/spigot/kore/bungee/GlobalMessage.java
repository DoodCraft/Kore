package net.doodcraft.dooder07.spigot.kore.bungee;

import net.doodcraft.dooder07.spigot.kore.Methods;
import net.doodcraft.dooder07.spigot.kore.DoodLogger;
import net.doodcraft.dooder07.spigot.kore.messaging.StringParser;
import net.doodcraft.dooder07.spigot.kore.configuration.Settings;
import net.doodcraft.dooder07.spigot.kore.lib.FancyMessage;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GlobalMessage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("g")) {
            String outputChannel = "GlobalMessage";
            String usePermission = "kore.global.use";
            String hearPermission = "kore.global.hear";

            if (sender.hasPermission(usePermission)) {
                if (args.length == 0) {
                    sender.sendMessage(StringParser.addColor("&7You must supply a message!"));
                    sender.sendMessage(StringParser.addColor("&7Example: &b/g hello world!"));
                    return false;
                } else {
                    String message = StringUtils.join(args, " ");

                    if (sender instanceof Player) {
                        Player player = (Player) sender;

                        String nameFormat = Settings.GLOBAL_NAMEFORMAT;
                        nameFormat = StringParser.parseChatVariables(player, nameFormat, message);
                        nameFormat = StringParser.addColor(nameFormat);

                        String nameHover = Settings.GLOBAL_NAMEHOVER;
                        nameHover = StringParser.parseChatVariables(player, nameHover, message);
                        nameHover = StringParser.addColor(nameHover);

                        String nameClick = Settings.GLOBAL_NAMECLICK;
                        nameClick = StringParser.parseChatVariables(player, nameClick, message);

                        String messageFormat = Settings.GLOBAL_MESSAGEFORMAT;
                        messageFormat = StringParser.parseChatVariables(player, messageFormat, message);
                        messageFormat = StringParser.addColor(messageFormat);

                        String messageHover = Settings.GLOBAL_MESSAGEHOVER;
                        messageHover = StringParser.parseChatVariables(player, messageHover, message);
                        messageHover = StringParser.addColor(messageHover);

                        FancyMessage msg = new FancyMessage(nameFormat);

                        if (!nameHover.equals("")) {
                            msg.tooltip(nameHover);
                        }
                        if (!nameClick.equals("")) {
                            msg.suggest(nameClick);
                        }

                        msg.then(messageFormat);
                        msg.link(message);

                        if (!messageHover.equals("")) {
                            msg.tooltip(messageHover);
                        }

                        DoodLogger.logMessage(nameFormat + messageFormat);

                        if (!Bukkit.getOnlinePlayers().isEmpty()) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.hasPermission(hearPermission)) {
                                    msg.send(p);
                                }
                            }
                        }

                        BungeeMethods.sendGlobalMessage(outputChannel, hearPermission, nameFormat, nameHover, nameClick, messageFormat, messageHover, message);
                    } else {
                        if (Bukkit.getOnlinePlayers().isEmpty()) {
                            DoodLogger.log("Kore", "&cThe server is empty -- this command will fail.");
                            return false;
                        }

                        String nameFormat = Settings.GLOBAL_NAMEFORMAT;
                        nameFormat = StringParser.parseChatVariablesForConsole(nameFormat, message);
                        nameFormat = StringParser.addColor(nameFormat);

                        String nameHover = Settings.GLOBAL_NAMEHOVER;
                        nameHover = StringParser.parseChatVariablesForConsole(nameHover, message);
                        nameHover = StringParser.addColor(nameHover);

                        String nameClick = Settings.GLOBAL_NAMECLICK;
                        nameClick = StringParser.parseChatVariablesForConsole(nameClick, message);

                        String messageFormat = Settings.GLOBAL_MESSAGEFORMAT;
                        messageFormat = StringParser.parseChatVariablesForConsole(messageFormat, message);
                        messageFormat = StringParser.addColor(messageFormat);

                        String messageHover = Settings.GLOBAL_MESSAGEHOVER;
                        messageHover = StringParser.parseChatVariablesForConsole(messageHover, message);
                        messageHover = StringParser.addColor(messageHover);

                        FancyMessage msg = new FancyMessage(nameFormat);

                        if (!nameHover.equals("")) {
                            msg.tooltip(nameHover);
                        }
                        if (!nameClick.equals("")) {
                            msg.suggest(nameClick);
                        }

                        msg.then(messageFormat);
                        msg.link(message);

                        if (!messageHover.equals("")) {
                            msg.tooltip(messageHover);
                        }

                        DoodLogger.logMessage(nameFormat + messageFormat);

                        if (!Bukkit.getOnlinePlayers().isEmpty()) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.hasPermission(hearPermission)) {
                                    msg.send(p);
                                }
                            }
                        }

                        BungeeMethods.sendGlobalMessage(outputChannel, hearPermission, nameFormat, nameHover, nameClick, messageFormat, messageHover, message);
                    }
                }
            } else {
                Methods.sendNoPermsMsg((Player) sender, usePermission);
                return false;
            }
        }

        if (label.equalsIgnoreCase("s")) {
            String outputChannel = "StaffMessage";
            String usePermission = "kore.staff.use";
            String hearPermission = "kore.staff.hear";

            if (sender.hasPermission(usePermission)) {
                if (args.length == 0) {
                    sender.sendMessage(StringParser.addColor("&7You must supply a message!"));
                    sender.sendMessage(StringParser.addColor("&7Example: &b/s hello world!"));
                    return false;
                } else {
                    String message = StringUtils.join(args, " ");

                    if (sender instanceof Player) {
                        Player player = (Player) sender;

                        String nameFormat = Settings.STAFF_NAMEFORMAT;
                        nameFormat = StringParser.parseChatVariables(player, nameFormat, message);
                        nameFormat = StringParser.addColor(nameFormat);

                        String nameHover = Settings.STAFF_NAMEHOVER;
                        nameHover = StringParser.parseChatVariables(player, nameHover, message);
                        nameHover = StringParser.addColor(nameHover);

                        String nameClick = Settings.STAFF_NAMECLICK;
                        nameClick = StringParser.parseChatVariables(player, nameClick, message);

                        String messageFormat = Settings.STAFF_MESSAGEFORMAT;
                        messageFormat = StringParser.parseChatVariables(player, messageFormat, message);
                        messageFormat = StringParser.addColor(messageFormat);

                        String messageHover = Settings.STAFF_MESSAGEHOVER;
                        messageHover = StringParser.parseChatVariables(player, messageHover, message);
                        messageHover = StringParser.addColor(messageHover);

                        FancyMessage msg = new FancyMessage(nameFormat);

                        if (!nameHover.equals("")) {
                            msg.tooltip(nameHover);
                        }
                        if (!nameClick.equals("")) {
                            msg.suggest(nameClick);
                        }

                        msg.then(messageFormat);
                        msg.link(message);

                        if (!messageHover.equals("")) {
                            msg.tooltip(messageHover);
                        }

                        DoodLogger.logMessage(nameFormat + messageFormat);

                        if (!Bukkit.getOnlinePlayers().isEmpty()) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.hasPermission(hearPermission)) {
                                    msg.send(p);
                                }
                            }
                        }

                        BungeeMethods.sendGlobalMessage(outputChannel, hearPermission, nameFormat, nameHover, nameClick, messageFormat, messageHover, message);
                    } else {
                        if (Bukkit.getOnlinePlayers().isEmpty()) {
                            DoodLogger.log("Kore", "&cThe server is empty -- this command will fail.");
                            return false;
                        }

                        String nameFormat = Settings.STAFF_NAMEFORMAT;
                        nameFormat = StringParser.parseChatVariablesForConsole(nameFormat, message);
                        nameFormat = StringParser.addColor(nameFormat);

                        String nameHover = Settings.STAFF_NAMEHOVER;
                        nameHover = StringParser.parseChatVariablesForConsole(nameHover, message);
                        nameHover = StringParser.addColor(nameHover);

                        String nameClick = Settings.STAFF_NAMECLICK;
                        nameClick = StringParser.parseChatVariablesForConsole(nameClick, message);

                        String messageFormat = Settings.STAFF_MESSAGEFORMAT;
                        messageFormat = StringParser.parseChatVariablesForConsole(messageFormat, message);
                        messageFormat = StringParser.addColor(messageFormat);

                        String messageHover = Settings.STAFF_MESSAGEHOVER;
                        messageHover = StringParser.parseChatVariablesForConsole(messageHover, message);
                        messageHover = StringParser.addColor(messageHover);

                        FancyMessage msg = new FancyMessage(nameFormat);

                        if (!nameHover.equals("")) {
                            msg.tooltip(nameHover);
                        }
                        if (!nameClick.equals("")) {
                            msg.suggest(nameClick);
                        }

                        msg.then(messageFormat);
                        msg.link(message);

                        if (!messageHover.equals("")) {
                            msg.tooltip(messageHover);
                        }

                        DoodLogger.logMessage(nameFormat + messageFormat);

                        if (!Bukkit.getOnlinePlayers().isEmpty()) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.hasPermission(hearPermission)) {
                                    msg.send(p);
                                }
                            }
                        }

                        BungeeMethods.sendGlobalMessage(outputChannel, hearPermission, nameFormat, nameHover, nameClick, messageFormat, messageHover, message);
                    }
                }
            } else {
                Methods.sendNoPermsMsg((Player) sender, usePermission);
                return false;
            }
        }
        return false;
    }
}