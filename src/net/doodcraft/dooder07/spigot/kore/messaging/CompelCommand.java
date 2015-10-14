package net.doodcraft.dooder07.spigot.kore.messaging;

import net.doodcraft.dooder07.spigot.kore.Methods;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CompelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("compel")) {
            if (sender.hasPermission("kore.general.compel")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: &b/compel Dooder07 Hello!"));
                    return false;
                } else if (args.length == 1) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: &b/compel " + args[0] + " /me is being compelled to say this!"));
                    return false;
                } else {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (args[0].equals("*")) {
                        ArrayList<String> arguments = new ArrayList<>();
                        for (String arg : args) {
                            arguments.add(arg);
                        }
                        arguments.remove(0);
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            String message = StringUtils.join(arguments, " ");
                            p.chat(message);
                        }
                        return true;
                    } else if (target == null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player could not be found!"));
                        return false;
                    } else {
                        ArrayList<String> arguments = new ArrayList<String>();
                        for (String arg : args) {
                            arguments.add(arg);
                        }
                        arguments.remove(0);
                        String message = StringUtils.join(arguments, " ");
                        target.chat(message);
                        return true;
                    }
                }
            } else {
                Methods.sendNoPermsMsg((Player) sender, "kore.general.compel");
                return false;
            }
        }
        return false;
    }
}
