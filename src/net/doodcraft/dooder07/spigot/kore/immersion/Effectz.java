package net.doodcraft.dooder07.spigot.kore.immersion;

import net.doodcraft.dooder07.spigot.kore.Methods;
import net.doodcraft.dooder07.spigot.kore.configuration.Settings;
import net.doodcraft.dooder07.spigot.kore.lib.ParticleLib;
import net.doodcraft.dooder07.spigot.kore.lib.ParticleLib.ParticleType;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Effectz implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        World world = player.getLocation().getWorld();
        Location location = player.getLocation();

        if (Settings.ONJOIN_LIGHTNING_ENABLED && !Methods.isHidden(player)) {
            for (int i = 0; i < Settings.ONJOIN_LIGHTNING_AMOUNT + 1; i++) {
                world.strikeLightningEffect(location);
            }
        }

        if (Settings.ONJOIN_PARTICLES_ENABLED && !Methods.isHidden(player)) {
            for (String particle : Settings.ONJOIN_PARTICLES_LIST) {
                String[] args = particle.split(" ");
                ParticleLib effect = new ParticleLib(ParticleType.valueOf(String.valueOf(args[0]).toUpperCase()), Double.valueOf(args[1]), Integer.valueOf(args[2]), Double.valueOf(args[3]));
                effect.sendToLocation(location);
            }
        }

        if (Settings.ONJOIN_SOUNDS_ENABLED && !Methods.isHidden(player)) {
            for (String sound : Settings.ONJOIN_SOUNDS_LIST) {
                String[] args = sound.split(" ");
                world.playSound(location, Sound.valueOf(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2]));
            }
        }

        if (Settings.ONJOIN_POTIONS_ENABLED && !Methods.isHidden(player)) {
            for (String potion : Settings.ONJOIN_POTIONS_LIST) {
                String[] args = potion.split(" ");
                player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Boolean.parseBoolean(args[3])));
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        World world = player.getLocation().getWorld();
        Location location = player.getLocation();

        if (Settings.ONQUIT_LIGHTNING_ENABLED && !Methods.isHidden(player)) {
            for (int i = 0; i < Settings.ONQUIT_LIGHTNING_AMOUNT + 1; i++) {
                world.strikeLightningEffect(location);
            }
        }

        if (Settings.ONQUIT_PARTICLES_ENABLED && !Methods.isHidden(player)) {
            for (String particle : Settings.ONQUIT_PARTICLES_LIST) {
                String[] args = particle.split(" ");
                ParticleLib effect = new ParticleLib(ParticleType.valueOf(String.valueOf(args[0]).toUpperCase()), Double.valueOf(args[1]), Integer.valueOf(args[2]), Double.valueOf(args[3]));
                effect.sendToLocation(location);
            }
        }

        if (Settings.ONQUIT_SOUNDS_ENABLED && !Methods.isHidden(player)) {
            for (String sound : Settings.ONQUIT_SOUNDS_LIST) {
                String[] args = sound.split(" ");
                world.playSound(location, Sound.valueOf(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2]));
            }
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Location toLocation = event.getTo();
        Location fromLocation = event.getFrom();
        PlayerTeleportEvent.TeleportCause cause = event.getCause();

        if (cause != PlayerTeleportEvent.TeleportCause.UNKNOWN) {
            if (Settings.TP_LIGHTNING_FROM_ENABLED && !Methods.isHidden(player)) {
                for (int i = 0; i < Settings.TP_LIGHTNING_FROM_AMOUNT + 1; i++) {
                    fromLocation.getWorld().strikeLightningEffect(fromLocation);
                }
            }

            if (Settings.TP_LIGHTNING_TO_ENABLED && !Methods.isHidden(player)) {
                for (int i = 0; i < Settings.TP_LIGHTNING_TO_AMOUNT + 1; i++) {
                    toLocation.getWorld().strikeLightningEffect(toLocation);
                }
            }

            if (Settings.TP_PARTICLES_ENABLED && !Methods.isHidden(player)) {
                for (String particle : Settings.TP_PARTICLES_FROM_LIST) {
                    String[] args = particle.split(" ");
                    ParticleLib effect = new ParticleLib(ParticleType.valueOf(String.valueOf(args[0]).toUpperCase()), Double.valueOf(args[1]), Integer.valueOf(args[2]), Double.valueOf(args[3]));
                    effect.sendToLocation(fromLocation);
                }
                for (String particle : Settings.TP_PARTICLES_TO_LIST) {
                    String[] args = particle.split(" ");
                    ParticleLib effect = new ParticleLib(ParticleType.valueOf(String.valueOf(args[0]).toUpperCase()), Double.valueOf(args[1]), Integer.valueOf(args[2]), Double.valueOf(args[3]));
                    effect.sendToLocation(toLocation);
                }
            }

            if (Settings.TP_SOUNDS_ENABLED && !Methods.isHidden(player)) {
                for (String sound : Settings.TP_SOUNDS_FROM_LIST) {
                    String[] args = sound.split(" ");
                    fromLocation.getWorld().playSound(fromLocation, Sound.valueOf(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2]));
                }
                for (String sound : Settings.TP_SOUNDS_TO_LIST) {
                    String[] args = sound.split(" ");
                    toLocation.getWorld().playSound(toLocation, Sound.valueOf(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2]));
                }
            }

            if (Settings.TP_POTIONS_ENABLED && !Methods.isHidden(player)) {
                for (String potion : Settings.TP_POTIONS_LIST) {
                    String[] args = potion.split(" ");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Boolean.parseBoolean(args[3])));
                }
            }
        }
    }
}