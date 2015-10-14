package net.doodcraft.dooder07.spigot.kore;

import net.doodcraft.dooder07.spigot.kore.configuration.Settings;
import net.doodcraft.dooder07.spigot.kore.messaging.StringParser;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class DoodLogger {
    public static void log(String prefix, String message) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        message = StringParser.addColor("&8[&3" + prefix + "&8] " + message);
        if (!Settings.LOGGING_COLORFUL) {
            message = StringParser.removeColor(message);
        }
        console.sendMessage(message);
    }

    public static void logMessage(String message) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        message = StringParser.addColor(message);
        if (!Settings.LOGGING_COLORFUL) {
            message = StringParser.removeColor(message);
        }
        console.sendMessage(message);
    }

    public static void printError(String prefix, String warning, Throwable ex) {
        log(prefix, " ");
        log(prefix, "&cThere was an internal error! Report this to have it fixed.");
        log(prefix, "&c" + warning);
        log(prefix, " ");
        log(prefix, "                    &c======= Copy and Paste =======");
        log(prefix, " ");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);

        for (String l : sw.toString().replace("\r", "").split("\n")) {
            log(prefix, l);
            pw.close();

            try {
                sw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        log(prefix, " ");
        log(prefix, "                    &c======= Copy and Paste =======");
        log(prefix, " ");
    }
}