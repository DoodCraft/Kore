package net.doodcraft.dooder07.spigot.kore.messaging;

import net.doodcraft.dooder07.spigot.kore.DoodLogger;
import net.doodcraft.dooder07.spigot.kore.configuration.Settings;
import net.doodcraft.dooder07.spigot.kore.lib.FancyMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatHandler implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (player.hasPermission("kore.chat.formatting")) {
            message = StringParser.addColor(message);
        } else {
            message = StringParser.removeColor(message);
        }

        player.setDisplayName(ChatVariables.getDisplayName(player));

        String nameFormat = Settings.NAME_FORMAT;
        nameFormat = StringParser.parseChatVariables(player, nameFormat, message);
        nameFormat = StringParser.addColor(nameFormat);

        String nameHover = Settings.NAME_HOVER;
        nameHover = StringParser.parseChatVariables(player, nameHover, message);
        nameHover = StringParser.addColor(nameHover);

        String nameClick = Settings.NAME_CLICK;
        nameClick = StringParser.parseChatVariables(player, nameClick, message);

        String messageFormat = Settings.MESSAGE_FORMAT;
        messageFormat = StringParser.parseChatVariables(player, messageFormat, message);
        messageFormat = StringParser.addColor(messageFormat);

        String messageHover = Settings.MESSAGE_HOVER;
        messageHover = StringParser.parseChatVariables(player, messageHover, message);
        messageHover = StringParser.addColor(messageHover);

        FancyMessage chat = new FancyMessage(nameFormat);

        if (!Settings.NAME_HOVER.equals("")) {
            chat.tooltip(nameHover);
        }
        if (!Settings.NAME_CLICK.equals("")) {
            chat.suggest(nameClick);
        }

        chat.then(messageFormat);
        chat.link(event.getMessage());

        if (!Settings.MESSAGE_HOVER.equals("")) {
            chat.tooltip(messageHover);
        }

        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                chat.send(p);
            }
        }

        DoodLogger.logMessage(nameFormat + messageFormat);
        event.setCancelled(true);
    }
}