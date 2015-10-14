package net.doodcraft.dooder07.spigot.kore.messaging;

import net.doodcraft.dooder07.spigot.kore.KorePlugin;
import net.doodcraft.dooder07.spigot.kore.DoodLogger;
import net.doodcraft.dooder07.spigot.kore.configuration.Settings;
import net.doodcraft.dooder07.spigot.kore.lib.FancyMessage;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AfkHandler implements Listener, CommandExecutor {
    public static Map<String, Integer> violationCount = new HashMap<>();
    public static Map<String, Integer> taskList = new HashMap<>();
    public static Map<UUID, String> afkList = new HashMap<>();
    public static Map<String, Location> locationMap = new HashMap<>();
    public static ArrayList<String> usedAfk = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("afk")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (player.hasPermission("kore.chat.afk")) {
                    if (!AfkHandler.afkList.containsKey(player.getUniqueId())) {
                        if (args.length == 0) {
                            AfkHandler.broadcastAfk(true, player, Settings.ISNOWAFK_MESSAGE, "&bNo Given Reason");
                            AfkHandler.afkList.put(player.getUniqueId(), player.getName());
                            AfkHandler.locationMap.put(player.getName(), player.getLocation());
                            player.setSleepingIgnored(true);

                            if (!AfkHandler.usedAfk.contains(player.getName())) {
                                player.sendMessage(StringParser.addColor("&7You can also provide a reason! Simply type a message after the command. It will appear when you hover over the message."));
                                AfkHandler.usedAfk.add(player.getName());
                            }
                            return true;
                        } else {
                            String message = StringUtils.join(args, " ");
                            AfkHandler.broadcastAfk(true, player, Settings.ISNOWAFK_MESSAGE, "&b" + message);
                            AfkHandler.afkList.put(player.getUniqueId(), player.getName());
                            AfkHandler.locationMap.put(player.getName(), player.getLocation());
                            player.setSleepingIgnored(true);
                            return true;
                        }
                    } else {
                        String alreadyAfk = "&cYou are already afk. This would be pointless.";
                        alreadyAfk = StringParser.addColor(alreadyAfk);
                        player.sendMessage(alreadyAfk);
                        return false;
                    }
                }
            }
            DoodLogger.logMessage("&cConsole can't be AFK.");
            return false;
        }
        return false;
    }

    public static void addOnlinePlayers() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!afkList.containsKey(p.getUniqueId())) {
                Location location = p.getLocation();

                AfkHandler.locationMap.put(p.getName(), location);
                AfkHandler.startLocationWatcher(p);
            }
        }
    }

    public static void removeOnlinePlayers() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            AfkHandler.stopLocationWatcher(p);
        }
    }

    public static void stopLocationWatcher(Player player) {
        String name = player.getName();

        if (violationCount.containsKey(name)) {
            violationCount.remove(name);
        }
        if (afkList.containsKey(player.getUniqueId())) {
            afkList.remove(player.getUniqueId());
        }
        if (locationMap.containsKey(name)) {
            locationMap.remove(name);
        }
        if (taskList.get(name) != null) {
            Bukkit.getServer().getScheduler().cancelTask(taskList.get(player.getName()));
            taskList.remove(player.getName());
        }
        if (usedAfk.contains(name)) {
            usedAfk.remove(name);
        }
    }

    public static void startLocationWatcher(Player player) {
        violationCount.put(player.getName(), -1);

        int taskId = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(KorePlugin.plugin, new Runnable() {
            @Override
            public void run() {
                int flag = Settings.AFK_POINTS;
                int kick = Settings.AFK_KICKPOINTS;
                int points = violationCount.get(player.getName());
                Location oldLocation = locationMap.get(player.getName());
                Location newLocation = player.getLocation();

                locationMap.put(player.getName(), newLocation);

                if (player.hasPermission("kore.chat.afkbypass")) {
                    if (!oldLocation.equals(newLocation)) {
                        noLongerAfk(player);
                    }
                    return;
                }

                if (!(afkList.containsKey(player.getUniqueId())) && points == flag) {
                    broadcastAfk(true, player, Settings.ISNOWAFK_MESSAGE, "&bIdling");
                    afkList.put(player.getUniqueId(), player.getName());
                    player.setSleepingIgnored(true);
                }

                if (points == kick) {
                    String message = "&cYou were kicked for being AFK.";
                    message = StringParser.addColor(message);
                    player.sendMessage(message);
                    player.kickPlayer("Away From Keyboard");
                    return;
                }

                if (oldLocation.equals(newLocation)) {
                    int newPoints = points + 1;
                    violationCount.put(player.getName(), newPoints);
                } else {
                    Block block = player.getLocation().getBlock();
                    if (block.isLiquid()) {
                        int newPoints = points + 1;
                        violationCount.put(player.getName(), newPoints);
                    } else {
                        noLongerAfk(player);
                    }
                }
            }
        }, 1L, 200L);

        taskList.put(player.getName(), taskId);
    }

    public static void noLongerAfk(Player player) {
        if (afkList.containsKey(player.getUniqueId())) {
            afkList.remove(player.getUniqueId());
            violationCount.put(player.getName(), 0);
            player.setSleepingIgnored(false);
            broadcastAfk(false, player, Settings.NOLONGERAFK_MESSAGE, null);
        } else {
            player.setSleepingIgnored(false);
            violationCount.put(player.getName(), 0);
        }
    }

    public static void broadcastAfk(Boolean bool, Player player, String format, String message) {
        if (bool) {
            String displayName = player.getDisplayName();
            String prefix = Settings.AFK_PREFIX + "&r";
            prefix = StringParser.addColor(prefix);

            player.setDisplayName(prefix + displayName);

            format = StringParser.parseChatVariables(player, format, "");
            format = StringParser.addColor(format);

            String formatHover = Settings.ISNOWAFK_HOVER;
            formatHover = StringParser.parseChatVariables(player, formatHover, message);
            formatHover = StringParser.addColor(formatHover);

            FancyMessage msg = new FancyMessage(format);
            msg.tooltip(formatHover);

            DoodLogger.logMessage(format + " &3| " + formatHover);

            if (!Bukkit.getOnlinePlayers().isEmpty()) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    msg.send(p);
                }
            }
        } else {
            player.setDisplayName(ChatVariables.getDisplayName(player));

            format = StringParser.parseChatVariables(player, format, "");
            format = StringParser.addColor(format);

            FancyMessage msg = new FancyMessage(format);

            DoodLogger.logMessage(format);

            if (!Bukkit.getOnlinePlayers().isEmpty()) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    msg.send(p);
                }
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        noLongerAfk(player);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        noLongerAfk(player);
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        noLongerAfk(player);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        String[] args = message.split(" ");

        if (args[0].equalsIgnoreCase("/afk")) {
            return;
        } else {
            noLongerAfk(player);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();

        locationMap.put(player.getName(), location);

        startLocationWatcher(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        afkList.remove(player.getUniqueId());
        violationCount.remove(player.getName());
        locationMap.remove(player.getName());

        try {
            stopLocationWatcher(player);
        } catch (Exception ex) {
            DoodLogger.printError("Kore", "&cError removing player from AFK watcher.", ex);
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        locationMap.put(event.getPlayer().getName(), event.getTo());
    }
}
