package net.doodcraft.dooder07.spigot.kore.bungee;

import net.doodcraft.dooder07.spigot.kore.DoodLogger;
import net.doodcraft.dooder07.spigot.kore.lib.FancyMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class BungeeCordListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player bitch, byte[] msg) {
        if (!channel.equals("BungeeCord")) {
            return;
        }

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(msg));

        try {
            String subChannel = in.readUTF();
            if (subChannel.equals("Kore")) {
                short len = in.readShort();
                byte[] msgBytes = new byte[len];
                in.readFully(msgBytes);

                DataInputStream msgIn = new DataInputStream(new ByteArrayInputStream(msgBytes));
                String inputChannel = msgIn.readUTF();

                if (inputChannel.equals("GlobalMessage")) {
                    String hearPermission = msgIn.readUTF();
                    String nameFormat = msgIn.readUTF();
                    String nameHover = msgIn.readUTF();
                    String nameClick = msgIn.readUTF();
                    String messageFormat = msgIn.readUTF();
                    String messageHover = msgIn.readUTF();
                    String message = msgIn.readUTF();

                    FancyMessage globalMessage = new FancyMessage(nameFormat);

                    if (!nameHover.equals("")) {
                        globalMessage.tooltip(nameHover);
                    }
                    if (!nameClick.equals("")) {
                        globalMessage.suggest(nameClick);
                    }

                    globalMessage.then(messageFormat);
                    globalMessage.link(message);

                    if (!messageHover.equals("")) {
                        globalMessage.tooltip(messageHover);
                    }

                    DoodLogger.logMessage(nameFormat + messageFormat);

                    if (!Bukkit.getOnlinePlayers().isEmpty()) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission(hearPermission)) {
                                globalMessage.send(p);
                            }
                        }
                    }
                }

                if (inputChannel.equals("StaffMessage")) {
                    String hearPermission = msgIn.readUTF();
                    String nameFormat = msgIn.readUTF();
                    String nameHover = msgIn.readUTF();
                    String nameClick = msgIn.readUTF();
                    String messageFormat = msgIn.readUTF();
                    String messageHover = msgIn.readUTF();
                    String message = msgIn.readUTF();

                    FancyMessage staffMessage = new FancyMessage(nameFormat);

                    if (!nameHover.equals("")) {
                        staffMessage.tooltip(nameHover);
                    }
                    if (!nameClick.equals("")) {
                        staffMessage.suggest(nameClick);
                    }

                    staffMessage.then(messageFormat);
                    staffMessage.link(message);

                    if (!messageHover.equals("")) {
                        staffMessage.tooltip(messageHover);
                    }

                    DoodLogger.logMessage(nameFormat + messageFormat);

                    if (!Bukkit.getOnlinePlayers().isEmpty()) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission(hearPermission)) {
                                staffMessage.send(p);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            DoodLogger.printError("Kore", "&cError recieving global Bungee message!", ex);
        }
    }
}
