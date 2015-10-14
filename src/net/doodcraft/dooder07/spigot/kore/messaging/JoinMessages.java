package net.doodcraft.dooder07.spigot.kore.messaging;

import net.doodcraft.dooder07.spigot.kore.messaging.ChatVariables;
import net.doodcraft.dooder07.spigot.kore.Methods;
import net.doodcraft.dooder07.spigot.kore.DoodLogger;
import net.doodcraft.dooder07.spigot.kore.messaging.StringParser;
import net.doodcraft.dooder07.spigot.kore.configuration.Settings;
import net.doodcraft.dooder07.spigot.kore.lib.FancyMessage;
import net.doodcraft.dooder07.spigot.kore.lib.TitleLib;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinMessages implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.setDisplayName(ChatVariables.getDisplayName(player));

        String messageFormat = Settings.JOIN_MESSAGE;
        messageFormat = StringParser.parseChatVariables(player, messageFormat, "");
        messageFormat = StringParser.addColor(messageFormat);

        String messageHover = Settings.JOIN_HOVER;
        messageHover = StringParser.parseChatVariables(player, messageHover, "");
        messageHover = StringParser.addColor(messageHover);

        FancyMessage chat = new FancyMessage(messageFormat);

        if (!messageHover.equals("")) {
            chat.tooltip(messageHover);
        }

        DoodLogger.logMessage(messageFormat);

        event.setJoinMessage(null);

        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!p.equals(player)) {
                    chat.send(p);
                }
            }
        }

        if (Settings.MOTD_ENABLED) {
            Methods.sendMotd(player);
        }

        if (!player.hasPlayedBefore()) {
            String welcome = Settings.NEWPLAYER_MESSAGE;

            if (!welcome.equals("")) {
                String welcomeFormat = welcome;
                welcomeFormat = StringParser.parseChatVariables(player, welcomeFormat, "");
                welcomeFormat = StringParser.addColor(welcomeFormat);

                player.sendMessage(welcomeFormat);
            }

            String title = Settings.NEWPLAYER_TITLE;
            title = StringParser.parseChatVariables(player, title, "");
            title = StringParser.addColor(title);

            String subTitle = Settings.NEWPLAYER_SUBTITLE;
            subTitle = StringParser.parseChatVariables(player, subTitle, "");
            subTitle = StringParser.addColor(subTitle);

            TitleLib welcomeTitle = new TitleLib(title, subTitle, 15, 100, 15);
            welcomeTitle.clearTitle(player);
            welcomeTitle.setTimingsToTicks();
            welcomeTitle.send(player);
        } else {
            String welcomeFormat = Settings.RETURNINGPLAYER_MESSAGE;

            if (!welcomeFormat.equals("") && welcomeFormat != null) {
                welcomeFormat = StringParser.parseChatVariables(player, welcomeFormat, "");
                welcomeFormat = StringParser.addColor(welcomeFormat);

                player.sendMessage(welcomeFormat);
            }

            String title = Settings.RETURNINGPLAYER_TITLE;
            title = StringParser.parseChatVariables(player, title, "");
            title = StringParser.addColor(title);

            String subTitle = Settings.RETURNINGPLAYER_SUBTITLE;
            subTitle = StringParser.parseChatVariables(player, subTitle, "");
            subTitle = StringParser.addColor(subTitle);

            TitleLib welcome = new TitleLib(title, subTitle, 15, 100, 15);
            welcome.clearTitle(player);
            welcome.setTimingsToTicks();
            welcome.send(player);
        }
    }
}