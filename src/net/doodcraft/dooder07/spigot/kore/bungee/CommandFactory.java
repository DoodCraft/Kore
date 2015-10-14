package net.doodcraft.dooder07.spigot.kore.bungee;

import net.doodcraft.dooder07.spigot.kore.DoodLogger;
import net.doodcraft.dooder07.spigot.kore.configuration.Settings;

import java.util.List;

public class CommandFactory {
    public static void setupAbstractCommands() {
        List<String> servers = Settings.BUNGEE_SERVERS;

        for (String command : servers) {
            addAbstractCommand("server", command);
        }
    }

    public static void addAbstractCommand(String type, String label) {
        if (type.equals("server")) {
            ServerCommand command = new ServerCommand(label);
            command.register();
            DoodLogger.log("Kore", "&9:: &5Registered abstract command: /" + label);
        }
    }
}
