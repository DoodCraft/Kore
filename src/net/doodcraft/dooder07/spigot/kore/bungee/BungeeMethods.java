package net.doodcraft.dooder07.spigot.kore.bungee;

import com.google.common.collect.Iterables;
import net.doodcraft.dooder07.spigot.kore.KorePlugin;
import net.doodcraft.dooder07.spigot.kore.DoodLogger;
import org.bukkit.Bukkit;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class BungeeMethods {
    public static void sendGlobalMessage(String outChannel, String hearPermission, String nameFormat, String nameHover, String nameClick, String messageFormat, String messageHover, String message) {
        Bukkit.getMessenger().registerOutgoingPluginChannel(KorePlugin.plugin, "BungeeCord");
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Forward");
            out.writeUTF("ONLINE");
            out.writeUTF("Kore");
        } catch (Exception ex) {
            DoodLogger.printError("Kore", "&cError initializing global Bungee message!", ex);
        }

        ByteArrayOutputStream msgBytes = new ByteArrayOutputStream();
        DataOutputStream msgOut = new DataOutputStream(msgBytes);

        try {
            msgOut.writeUTF(outChannel);
            msgOut.writeUTF(hearPermission);
            msgOut.writeUTF(nameFormat);
            msgOut.writeUTF(nameHover);
            msgOut.writeUTF(nameClick);
            msgOut.writeUTF(messageFormat);
            msgOut.writeUTF(messageHover);
            msgOut.writeUTF(message);
            out.writeShort(msgBytes.toByteArray().length);
            out.write(msgBytes.toByteArray());
        } catch (Exception ex) {
            DoodLogger.printError("Kore", "&cError sending global Bungee message!", ex);
        }

        Iterables.getLast(Bukkit.getOnlinePlayers()).sendPluginMessage(KorePlugin.plugin, "BungeeCord", b.toByteArray());
    }
}
