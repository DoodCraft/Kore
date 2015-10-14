package net.doodcraft.dooder07.spigot.kore.messaging;

import net.doodcraft.dooder07.spigot.kore.messaging.ChatVariables;
import net.doodcraft.dooder07.spigot.kore.DoodLogger;
import net.doodcraft.dooder07.spigot.kore.messaging.StringParser;
import net.doodcraft.dooder07.spigot.kore.configuration.Settings;
import net.doodcraft.dooder07.spigot.kore.lib.FancyMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitMessages implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        player.setDisplayName(ChatVariables.getDisplayName(player));
        event.setQuitMessage(null);

        String messageFormat = Settings.QUIT_MESSAGE;
        messageFormat = StringParser.parseChatVariables(player, messageFormat, "");
        messageFormat = StringParser.addColor(messageFormat);

        String messageHover = Settings.QUIT_HOVER;
        messageHover = StringParser.parseChatVariables(player, messageHover, "");
        messageHover = StringParser.addColor(messageHover);

        FancyMessage chat = new FancyMessage(messageFormat);

        if (!messageHover.equals("")) {
            chat.tooltip(messageHover);
        }

        DoodLogger.logMessage(messageFormat);

        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                chat.send(p);
            }
        }
    }
}
