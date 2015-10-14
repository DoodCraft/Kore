package net.doodcraft.dooder07.spigot.kore.bungee;

import net.doodcraft.dooder07.spigot.kore.KorePlugin;
import net.doodcraft.dooder07.spigot.kore.DoodLogger;
import net.doodcraft.dooder07.spigot.kore.configuration.Settings;
import net.doodcraft.dooder07.spigot.kore.lib.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;

public class ServerCommand extends AbstractCommand {
    public ServerCommand(String command) {
        super(command);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.getMessenger().registerOutgoingPluginChannel(KorePlugin.plugin, "BungeeCord");
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        List<String> servers = Settings.BUNGEE_SERVERS;

        if (sender instanceof Player) {
            Player player = (Player) sender;
            for (String server : servers) {
                if (label.equalsIgnoreCase(server)) {
                    try {
                        out.writeUTF("Connect");
                        out.writeUTF(server);
                    } catch (Exception ex) {
                        DoodLogger.printError("Kore", "&cError sending player to target server!", ex);
                    }
                    player.sendPluginMessage(KorePlugin.plugin, "BungeeCord", b.toByteArray());
                    return true;
                }
            }
        } else {
            DoodLogger.log("Kore", "&cConsole can't switch servers!");
        }
        return false;
    }
}
