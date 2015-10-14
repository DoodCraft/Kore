package net.doodcraft.dooder07.spigot.kore.commands;

import net.doodcraft.dooder07.spigot.kore.KorePlugin;
import net.doodcraft.dooder07.spigot.kore.Methods;
import net.doodcraft.dooder07.spigot.kore.lib.FancyMessage;
import net.doodcraft.dooder07.spigot.kore.lib.ParticleLib;
import net.doodcraft.dooder07.spigot.kore.messaging.StringParser;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Teleport implements Listener, CommandExecutor {
    static Map<UUID, Long> tpaCooldown = new HashMap<UUID, Long>();
    static Map<UUID, UUID> currentTpaRequest = new HashMap<UUID, UUID>();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = null;

        if (sender instanceof Player)
        {
            player = (Player) sender;
        }

        if (command.getName().equalsIgnoreCase("tpa"))
        {
            if (player != null)
            {
                if (player.hasPermission("kore.command.tpa"))
                {
                    if (args.length > 0)
                    {
                        final Player target = Bukkit.getServer().getPlayer(args[0]);

                        if (!player.hasPermission("kore.teleport.bypasscooldown"))
                        {
                            if (tpaCooldown.containsKey(player.getUniqueId()))
                            {
                                int cooldown = 20;
                                long diff = (System.currentTimeMillis() - tpaCooldown.get(player.getUniqueId())) / 1000;

                                if (diff < cooldown)
                                {
                                    String error = ChatColor.translateAlternateColorCodes('&', "&7There is still a &b<seconds> &7second cooldown to use this again.");
                                    error = error.replaceAll("<seconds>", String.valueOf(cooldown - (System.currentTimeMillis() - tpaCooldown.get(player.getUniqueId())) / 1000));
                                    player.sendMessage(error);
                                    return false;
                                }
                            }
                        }

                        boolean requiresQuartz = false;
                        if (!requiresQuartz) {
                            if (target == null) {
                                player.sendMessage(StringParser.addColor("&7" + target + " &7could not be found."));
                                return false;
                            }

                            if (target == player) {
                                player.sendMessage(StringParser.addColor("&7You can't teleport to yourself."));
                                return false;
                            }

                            sendtpaRequest(player, target);
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(KorePlugin.plugin, () -> killtpaRequest(target.getUniqueId()), 300L);
                            tpaCooldown.put(player.getUniqueId(), System.currentTimeMillis());
                        } else {
                            if (Methods.hasItem(player.getInventory(), Material.QUARTZ, 0) >= 1 || player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR)
                            {
                                if (target == null)
                                {
                                    FancyMessage error = new FancyMessage(ChatColor.translateAlternateColorCodes('&', "&cThe quartz did not react.."));
                                    error.tooltip(ChatColor.translateAlternateColorCodes('&', "&7" + target + " &7could not be found."));
                                    error.send(player);
                                    return false;
                                }

                                if (target == player)
                                {
                                    FancyMessage error = new FancyMessage(ChatColor.translateAlternateColorCodes('&', "&cThe quartz did not react.."));
                                    error.tooltip(ChatColor.translateAlternateColorCodes('&', "&7That would be a waste.."));
                                    error.send(player);
                                    return false;
                                }

                                sendtpaRequest(player, target);
                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(KorePlugin.plugin, () -> killtpaRequest(target.getUniqueId()), 300L);
                                tpaCooldown.put(player.getUniqueId(), System.currentTimeMillis());
                            }
                            else
                            {
                                FancyMessage poor = new FancyMessage(ChatColor.translateAlternateColorCodes('&', "&cThis power requires quartz."));
                                poor.tooltip(ChatColor.translateAlternateColorCodes('&', "&7Quartz can be found in the nether."));
                                poor.send(player);
                                return false;
                            }
                        }
                    }
                    else
                    {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Request a teleport to another player's location."));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: &b/tpa playername"));
                        return false;
                    }
                }
                else
                {
                    Methods.sendNoPermsMsg(player, "kore.command.tpa");
                    return false;
                }
            }
            else
            {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cConsole cannot teleport."));
                return false;
            }
        }

        if (command.getName().equalsIgnoreCase("accept"))
        {
            if (player != null)
            {
                if (player.hasPermission("kore.command.tpa"))
                {
                    if (currentTpaRequest.containsKey(player.getUniqueId()))
                    {
                        Player requester = Bukkit.getServer().getPlayer(currentTpaRequest.get(player.getUniqueId()));

                        if (requester != null)
                        {
                            boolean requiresQuartz = false;
                            if (!requiresQuartz) {
                                currentTpaRequest.remove(player.getUniqueId());
                                FancyMessage format = new FancyMessage(ChatColor.translateAlternateColorCodes('&', "Teleporting..."));
                                format.tooltip(ChatColor.translateAlternateColorCodes('&', "&8Target: &7" + player.getDisplayName()));
                                format.send(requester);
                                requester.teleport(player);
                                return true;
                            } else {
                                if (Methods.hasItem(requester.getInventory(), Material.QUARTZ, 0) >= 1 || requester.getGameMode() == GameMode.CREATIVE || requester.getGameMode() == GameMode.SPECTATOR)
                                {
                                    currentTpaRequest.remove(player.getUniqueId());
                                    FancyMessage format = new FancyMessage(ChatColor.translateAlternateColorCodes('&', "Teleporting..."));
                                    format.tooltip(ChatColor.translateAlternateColorCodes('&', "&8Target: &7" + player.getDisplayName()));
                                    format.send(requester);
                                    Methods.removeItem(requester.getInventory(), Material.QUARTZ, 1);
                                    requester.teleport(player);
                                    return true;
                                }
                                else
                                {
                                    currentTpaRequest.remove(player.getUniqueId());
                                    FancyMessage theyhadnoitem = new FancyMessage(ChatColor.translateAlternateColorCodes('&', "&cThere was a small gust of wind, but nothing happened."));
                                    theyhadnoitem.tooltip(ChatColor.translateAlternateColorCodes('&', "&7" + requester.getDisplayName() + "&7's teleport must have failed. &oDid they have quartz?"));
                                    theyhadnoitem.send(player);
                                    FancyMessage toopoor = new FancyMessage(ChatColor.translateAlternateColorCodes('&', "&cThere was a small gust of wind, but nothing happened.."));
                                    toopoor.tooltip(ChatColor.translateAlternateColorCodes('&', "&7" + player.getDisplayName() + " &7accepted your teleport request, but you had no quartz."));
                                    toopoor.send(requester);
                                    return false;
                                }
                            }
                        }
                        else
                        {
                            player.sendMessage(StringParser.addColor("&7The person who requested a teleport no longer exists.."));
                            return false;
                        }
                    }
                    else
                    {
                        player.sendMessage(StringParser.addColor("&7Nobody's sent you a teleport request."));
                        return false;
                    }
                }
                else
                {
                    Methods.sendNoPermsMsg(player, "kore.command.tpa");
                    return false;
                }
            }
            else
            {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cConsole cannot teleport."));
                return false;
            }
        }

        if (command.getName().equalsIgnoreCase("deny"))
        {
            if (player != null)
            {
                if (player.hasPermission("kore.command.tpa"))
                {
                    if (currentTpaRequest.containsKey(player.getUniqueId()))
                    {
                        Player reject = Bukkit.getServer().getPlayer(currentTpaRequest.get(player.getUniqueId()));

                        if (reject != null)
                        {
                            boolean requiresQuartz = false;
                            if (!requiresQuartz) {
                                currentTpaRequest.remove(player.getUniqueId());
                                player.sendMessage(StringParser.addColor("&7You denied " + reject.getDisplayName() + "&7's request."));
                                reject.sendMessage(StringParser.addColor("&7" + player.getDisplayName() + " &7denied your request."));
                                return false;
                            } else {
                                if (Methods.hasItem(reject.getInventory(), Material.QUARTZ, 0) >= 1)
                                {
                                    currentTpaRequest.remove(player.getUniqueId());

                                    FancyMessage rejection = new FancyMessage(ChatColor.translateAlternateColorCodes('&', "&cThere was a small gust of wind, but nothing happened."));
                                    rejection.tooltip(ChatColor.translateAlternateColorCodes('&', "&7You denied " + reject.getDisplayName() + "&7's request."));
                                    rejection.send(player);

                                    FancyMessage rejected = new FancyMessage(ChatColor.translateAlternateColorCodes('&', "&cThere was a small gust of wind, but nothing happened.."));
                                    rejected.tooltip(ChatColor.translateAlternateColorCodes('&', "&7" +  player.getDisplayName() + " &7denied your request. The quartz shatters in your hands."));
                                    rejected.send(reject);

                                    Methods.removeItem(reject.getInventory(), Material.QUARTZ, 1);

                                    return false;
                                }
                                else
                                {

                                    currentTpaRequest.remove(player.getUniqueId());

                                    FancyMessage rejection = new FancyMessage(ChatColor.translateAlternateColorCodes('&', "&cThere was a small gust of wind, but nothing happened."));
                                    rejection.tooltip(ChatColor.translateAlternateColorCodes('&', "&7You denied " + reject.getDisplayName() + "&7's request."));
                                    rejection.send(player);

                                    FancyMessage rejected = new FancyMessage(ChatColor.translateAlternateColorCodes('&', "&cA sudden backflow of quartz energy harms you!"));
                                    rejected.tooltip(ChatColor.translateAlternateColorCodes('&', "&7Your last teleport request was denied, and you had no quartz!"));
                                    rejected.send(reject);

                                    ParticleLib quartzbackflow = new ParticleLib(ParticleLib.ParticleType.EXPLOSION_LARGE, 0.1D, 5, 0.8D);
                                    quartzbackflow.sendToLocation(reject.getLocation());

                                    reject.getLocation().getWorld().playSound(reject.getLocation(), Sound.CREEPER_HISS, 2, 2);
                                    reject.getLocation().getWorld().playSound(reject.getLocation(), Sound.EXPLODE, (float) 1.3, 2);

                                    reject.damage(8);

                                    return false;
                                }
                            }
                        }
                        else
                        {
                            player.sendMessage(StringParser.addColor("&7The person who requested a teleport no longer exists.."));
                            return false;
                        }
                    }
                    else
                    {
                        player.sendMessage(StringParser.addColor("&7Nobody's sent you a teleport request."));
                        return false;
                    }
                }
                else
                {
                    Methods.sendNoPermsMsg(player, "kore.command.tpa");
                    return false;
                }
            }
            else
            {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cConsole cannot teleport."));
                return false;
            }
        }
        return false;
    }

    public boolean killtpaRequest(UUID uuid)
    {
        if (currentTpaRequest.containsKey(uuid))
        {
            Player player = Bukkit.getServer().getPlayer(currentTpaRequest.get(uuid));

            if (player != null)
            {
                FancyMessage timedout = new FancyMessage(ChatColor.translateAlternateColorCodes('&', "&7Your teleport request timed out."));
                timedout.send(player);
            }

            currentTpaRequest.remove(uuid);

            return true;
        }

        return false;
    }

    public void sendtpaRequest(Player sender, Player recipient)
    {
        FancyMessage sending = new FancyMessage(ChatColor.translateAlternateColorCodes('&', "&7Sending a teleport request to " + recipient.getDisplayName() + "&7."));
        sending.send(sender);

        FancyMessage receiving = new FancyMessage(ChatColor.translateAlternateColorCodes('&', "&7" + sender.getDisplayName() + " &7sent you a teleport request. "));
        receiving.then(ChatColor.translateAlternateColorCodes('&', "&8[&aAccept&8] "));
        receiving.command("/accept");
        receiving.then(ChatColor.translateAlternateColorCodes('&', "&8[&cDeny&8] "));
        receiving.command("/deny");
        receiving.send(recipient);

        currentTpaRequest.put(recipient.getUniqueId(), sender.getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        killtpaRequest(event.getPlayer().getUniqueId());
    }
}
