package net.doodcraft.dooder07.spigot.kore.commands;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import net.doodcraft.dooder07.spigot.kore.DoodLogger;
import net.doodcraft.dooder07.spigot.kore.Methods;
import net.doodcraft.dooder07.spigot.kore.compat.Compatibility;
import net.doodcraft.dooder07.spigot.kore.messaging.StringParser;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.UUID;

public class Gamemode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("gm")) {
            if (sender.hasPermission("kore.general.gm")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;

                    if (Compatibility.plotSquared != null) {
                        Location loc = player.getLocation();
                        @SuppressWarnings("deprecation") PlotAPI api = new PlotAPI(null);

                        if (api.isInPlot(player)) {
                            Plot plot = api.getPlot(loc);
                            HashSet<UUID> owners = plot.getOwners();
                            HashSet<UUID> trusted = plot.getTrusted();

                            if (plot.getFlag("gamemode") != null) {
                                if (owners.contains(player.getUniqueId()) || trusted.contains(player.getUniqueId())) {
                                    player.sendMessage(StringParser.addColor("&7You bypassed the gamemode set for this plot."));
                                    if (player.getGameMode().equals(GameMode.CREATIVE)) {
                                        player.setGameMode(GameMode.SURVIVAL);
                                        player.sendMessage(StringParser.addColor("&7Your gamemode was updated: &eSurvival"));
                                        return true;
                                    }
                                    if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                                        player.setGameMode(GameMode.CREATIVE);
                                        player.sendMessage(StringParser.addColor("&7Your gamemode was updated: &eCreative"));
                                        return true;
                                    }
                                    if (player.getGameMode().equals(GameMode.ADVENTURE)) {
                                        player.setGameMode(GameMode.CREATIVE);
                                        player.sendMessage(StringParser.addColor("&7Your gamemode was updated: &eCreative"));
                                        return true;
                                    }
                                    if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                                        player.setGameMode(GameMode.CREATIVE);
                                        player.sendMessage(StringParser.addColor("&7Your gamemode was updated: &eCreative"));
                                        return true;
                                    }
                                } else {
                                    player.sendMessage(StringParser.addColor("&cThis plot has a gamemode. Only trusted players can use this."));
                                    return false;
                                }
                            } else {
                                if (player.getGameMode().equals(GameMode.CREATIVE)) {
                                    player.setGameMode(GameMode.SURVIVAL);
                                    player.sendMessage(StringParser.addColor("&7Your gamemode was updated: &eSurvival"));
                                    return true;
                                }
                                if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                                    player.setGameMode(GameMode.CREATIVE);
                                    player.sendMessage(StringParser.addColor("&7Your gamemode was updated: &eCreative"));
                                    return true;
                                }
                                if (player.getGameMode().equals(GameMode.ADVENTURE)) {
                                    player.setGameMode(GameMode.CREATIVE);
                                    player.sendMessage(StringParser.addColor("&7Your gamemode was updated: &eCreative"));
                                    return true;
                                }
                                if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                                    player.setGameMode(GameMode.CREATIVE);
                                    player.sendMessage(StringParser.addColor("&7Your gamemode was updated: &eCreative"));
                                    return true;
                                }
                            }
                        } else {
                            if (player.getGameMode().equals(GameMode.CREATIVE)) {
                                player.setGameMode(GameMode.SURVIVAL);
                                player.sendMessage(StringParser.addColor("&7Your gamemode was updated: &eSurvival"));
                                return true;
                            }
                            if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                                player.setGameMode(GameMode.CREATIVE);
                                player.sendMessage(StringParser.addColor("&7Your gamemode was updated: &eCreative"));
                                return true;
                            }
                            if (player.getGameMode().equals(GameMode.ADVENTURE)) {
                                player.setGameMode(GameMode.CREATIVE);
                                player.sendMessage(StringParser.addColor("&7Your gamemode was updated: &eCreative"));
                                return true;
                            }
                            if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                                player.setGameMode(GameMode.CREATIVE);
                                player.sendMessage(StringParser.addColor("&7Your gamemode was updated: &eCreative"));
                                return true;
                            }
                        }
                    } else {
                        if (player.getGameMode().equals(GameMode.CREATIVE)) {
                            player.setGameMode(GameMode.SURVIVAL);
                            player.sendMessage(StringParser.addColor("&7Your gamemode was updated: &eSurvival"));
                            return true;
                        }
                        if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                            player.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(StringParser.addColor("&7Your gamemode was updated: &eCreative"));
                            return true;
                        }
                        if (player.getGameMode().equals(GameMode.ADVENTURE)) {
                            player.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(StringParser.addColor("&7Your gamemode was updated: &eCreative"));
                            return true;
                        }
                        if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                            player.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(StringParser.addColor("&7Your gamemode was updated: &eCreative"));
                            return true;
                        }
                    }
                } else {
                    DoodLogger.logMessage("&cConsole can't use /gm");
                    return false;
                }
            } else {
                Methods.sendNoPermsMsg((Player) sender, "kore.general.gm");
            }
        }
        return false;
    }
}
