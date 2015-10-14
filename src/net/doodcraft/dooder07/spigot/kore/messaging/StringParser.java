package net.doodcraft.dooder07.spigot.kore.messaging;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class StringParser {
    public static String addColor(String message) {
        ArrayList<String> arguments = new ArrayList<>();
        for (String arg : message.split(" ")) {
            String color = ChatColor.getLastColors(arg);
            arguments.add(color + arg);
        }
        message = StringUtils.join(arguments, " ");
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }

    public static String removeColor(String message) {
        message = ChatColor.stripColor(message);
        return message;
    }

    public static String parseChatVariables(Player player, String format, String message) {
        String name = player.getName();
        String displayName = player.getDisplayName();
        String group = ChatVariables.getPlayerGroup(player.getWorld(), player);
        String prefix = ChatVariables.getPlayerPrefix(player.getWorld(), player);
        String suffix = ChatVariables.getPlayerSuffix(player.getWorld(), player);
        String server = ChatVariables.getServerName();
        String world = ChatVariables.getWorldName(player.getWorld().getName(), player);
        String worldTime = ChatVariables.getWorldTime(player);
        String faction = ChatVariables.getFactionName(player);
        String factionRolePrefix = ChatVariables.getFactionRolePrefix(player);
        String element = ChatVariables.getElement(player);

        String parsed = format;
        parsed = parsed.replace("<message>", message);
        parsed = parsed.replace("<command>", message);
        parsed = parsed.replace("<reason>", message);
        parsed = parsed.replace("<node>", message);
        parsed = parsed.replaceAll("<name>", name);
        parsed = parsed.replaceAll("<dname>", displayName);
        parsed = parsed.replaceAll("<group>", group);
        parsed = parsed.replaceAll("<prefix>", prefix);
        parsed = parsed.replaceAll("<suffix>", suffix);
        parsed = parsed.replaceAll("<server>", server);
        parsed = parsed.replaceAll("<world>", world);
        parsed = parsed.replaceAll("<world_time>", worldTime);
        parsed = parsed.replaceAll("<factions_name>", faction);
        parsed = parsed.replaceAll("<factions_role>", factionRolePrefix);
        parsed = parsed.replaceAll("<element>", element);

        return parsed;
    }

    public static String parseChatVariablesForConsole(String format, String message) {
        String name = "CONSOLE";
        String displayName = "&dCONSOLE&r";
        String group = "&rN/A";
        String prefix = "";
        String suffix = "";
        String server = ChatVariables.getServerName();
        String world = "&rN/A";
        String worldTime = "&rN/A";
        String faction = "&rN/A";
        String factionRolePrefix = "";
        String element = "&rN/A";

        String parsed = format;
        parsed = parsed.replace("<message>", message);
        parsed = parsed.replace("<command>", message);
        parsed = parsed.replace("<reason>", message);
        parsed = parsed.replace("<node>", message);
        parsed = parsed.replaceAll("<name>", name);
        parsed = parsed.replaceAll("<dname>", displayName);
        parsed = parsed.replaceAll("<group>", group);
        parsed = parsed.replaceAll("<prefix>", prefix);
        parsed = parsed.replaceAll("<suffix>", suffix);
        parsed = parsed.replaceAll("<server>", server);
        parsed = parsed.replaceAll("<world>", world);
        parsed = parsed.replaceAll("<world_time>", worldTime);
        parsed = parsed.replaceAll("<factions_name>", faction);
        parsed = parsed.replaceAll("<factions_role>", factionRolePrefix);
        parsed = parsed.replaceAll("<element>", element);

        return parsed;
    }
}