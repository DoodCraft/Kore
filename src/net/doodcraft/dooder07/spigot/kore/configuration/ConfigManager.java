package net.doodcraft.dooder07.spigot.kore.configuration;

import net.doodcraft.dooder07.spigot.kore.KorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.ArrayList;

public class ConfigManager {

    public static Config defaultConfig;
    public static Config defaultLocal;
    static KorePlugin plugin;

    public ConfigManager(KorePlugin plugin) {
        ConfigManager.plugin = plugin;
        defaultConfig = new Config(new File("config.yml"));
        defaultLocal = new Config(new File("local.yml"));
        configCheck(ConfigType.DEFAULT_CONFIG);
        configCheck(ConfigType.DEFAULT_LOCAL);
    }

    public static void configCheck(ConfigType type) {
        FileConfiguration config;
        switch (type) {
            case DEFAULT_CONFIG:
                config = defaultConfig.get();

                config.addDefault("Logging.Colorful", true);
                config.addDefault("Motd.Enabled", false);

                config.addDefault("Chat.Afk.FlagPoints", 21);
                config.addDefault("Chat.Afk.KickPoints", 60);

                ArrayList<String> servers = new ArrayList<>();
                servers.add("Hub");
                servers.add("Survival");
                servers.add("Creative");

                config.addDefault("BungeeCord.Servers", servers);

                ArrayList<String> joinParticles = new ArrayList<>();
                joinParticles.add("FIREWORKS_SPARK 0.2 80 1.0");
                joinParticles.add("PORTAL 0.1 60 1.0");

                ArrayList<String> joinSounds = new ArrayList<>();
                joinSounds.add("LEVEL_UP 2.0 2.0");

                ArrayList<String> joinPotions = new ArrayList<>();
                joinPotions.add("CONFUSION 140 255 true");

                ArrayList<String> quitParticles = new ArrayList<>();
                quitParticles.add("EXPLOSION_LARGE 0.1 5 0.8");
                quitParticles.add("PORTAL 0.1 30 1.0");

                ArrayList<String> quitSounds = new ArrayList<>();
                quitSounds.add("ORB_PICKUP 2.0 2.0");

                ArrayList<String> fromParticles = new ArrayList<>();
                fromParticles.add("EXPLOSION_LARGE 0.1 5 0.8");
                fromParticles.add("PORTAL 0.1 30 1.0");

                ArrayList<String> fromSounds = new ArrayList<>();
                fromSounds.add("GHAST_FIREBALL 2.0 2.0");

                ArrayList<String> toParticles = new ArrayList<>();
                toParticles.add("FIREWORKS_SPARK 0.2 80 1.0");
                toParticles.add("PORTAL 0.1 60 1.0");

                ArrayList<String> toSounds = new ArrayList<>();
                toSounds.add("GHAST_FIREBALL 2.0 2.0");

                ArrayList<String> teleportPotions = new ArrayList<>();
                teleportPotions.add("CONFUSION 140 255 true");

                config.addDefault("Effectz.OnJoin.Lightning.Enabled", false);
                config.addDefault("Effectz.OnJoin.Lightning.Amount", 1);
                config.addDefault("Effectz.OnJoin.Particles.Enabled", true);
                config.addDefault("Effectz.OnJoin.Particles.List", joinParticles);
                config.addDefault("Effectz.OnJoin.Sounds.Enabled", true);
                config.addDefault("Effectz.OnJoin.Sounds.List", joinSounds);
                config.addDefault("Effectz.OnJoin.Potions.Enabled", true);
                config.addDefault("Effectz.OnJoin.Potions.List", joinPotions);

                config.addDefault("Effectz.OnQuit.Lightning.Enabled", false);
                config.addDefault("Effectz.OnQuit.Lightning.Amount", 1);
                config.addDefault("Effectz.OnQuit.Particles.Enabled", true);
                config.addDefault("Effectz.OnQuit.Particles.List", quitParticles);
                config.addDefault("Effectz.OnQuit.Sounds.Enabled", true);
                config.addDefault("Effectz.OnQuit.Sounds.List", quitSounds);

                config.addDefault("Effectz.OnTeleport.Lightning.From.Enabled", false);
                config.addDefault("Effectz.OnTeleport.Lightning.From.Amount", 1);
                config.addDefault("Effectz.OnTeleport.Lightning.To.Enabled", false);
                config.addDefault("Effectz.OnTeleport.Lightning.To.Amount", 1);
                config.addDefault("Effectz.OnTeleport.Particles.Enabled", true);
                config.addDefault("Effectz.OnTeleport.Particles.From.List", fromParticles);
                config.addDefault("Effectz.OnTeleport.Particles.To.List", toParticles);
                config.addDefault("Effectz.OnTeleport.Sounds.Enabled", true);
                config.addDefault("Effectz.OnTeleport.Sounds.From.List", fromSounds);
                config.addDefault("Effectz.OnTeleport.Sounds.To.List", toSounds);
                config.addDefault("Effectz.OnTeleport.Potions.Enabled", true);
                config.addDefault("Effectz.OnTeleport.Potions.List", teleportPotions);

                defaultConfig.save();
                break;
            case DEFAULT_LOCAL:
                config = defaultLocal.get();

                config.addDefault("Messages.NoPermission.Message", "&cYou don't have permission <dname>&c!");
                config.addDefault("Messages.NoPermission.Hover", "&8Node: &c<node>");

                config.addDefault("Messages.Events.Join.Message", "<dname> &7joined &e&o<server>");
                config.addDefault("Messages.Events.Join.Hover", "");
                config.addDefault("Messages.Events.Quit.Message", "<dname> &7quit");
                config.addDefault("Messages.Events.Quit.Hover", "");
                config.addDefault("Messages.Events.Welcome.NewPlayer.Message", "");
                config.addDefault("Messages.Events.Welcome.NewPlayer.Title", "&e&o<server>");
                config.addDefault("Messages.Events.Welcome.NewPlayer.SubTitle", "");
                config.addDefault("Messages.Events.Welcome.ReturningPlayer.Message", "");
                config.addDefault("Messages.Events.Welcome.ReturningPlayer.Title", "&e&o<server>");
                config.addDefault("Messages.Events.Welcome.ReturningPlayer.SubTitle", "");

                config.addDefault("Messages.Afk.Prefix", "&e*");
                config.addDefault("Messages.Afk.IsNowAFK.Message", "<dname> &7is now afk.");
                config.addDefault("Messages.Afk.IsNowAfk.Hover", "&8Reason: <reason>");
                config.addDefault("Messages.Afk.NoLongerAfk.Message", "<dname> &7is no longer afk.");

                config.addDefault("Chat.Formats.PlayerChat.Name.Format", "<dname>");
                config.addDefault("Chat.Formats.PlayerChat.Name.Hover", "&8Rank: <prefix>&o<group>");
                config.addDefault("Chat.Formats.PlayerChat.Name.Click", "/mail <name> ");
                config.addDefault("Chat.Formats.PlayerChat.Message.Format", "&8: &r<message>");
                config.addDefault("Chat.Formats.PlayerChat.Message.Hover", "");

                config.addDefault("Chat.Formats.MeCommand.Message", "&7* <dname> &r<message>");
                config.addDefault("Chat.Formats.MeCommand.Hover", "");
                config.addDefault("Chat.Formats.SayCommand.Message", "&8[&d<name>&8] &d<message>");
                config.addDefault("Chat.Formats.SayCommand.Hover", "");

                config.addDefault("Chat.Variables.Kore.DisplayName", "<prefix><name>");
                config.addDefault("Chat.Variables.Kore.ServerName", "");

                for (World world : Bukkit.getWorlds()) {
                    String name = world.getName();
                    config.addDefault("Chat.Variables.Kore.Worlds." + name, "&b" + name);
                }

                config.addDefault("Chat.Variables.ProjectKorra.Powerless", "&fPowerless");
                config.addDefault("Chat.Variables.ProjectKorra.Avatar", "&5Avatar");
                config.addDefault("Chat.Variables.ProjectKorra.Earth", "&aEarth");
                config.addDefault("Chat.Variables.ProjectKorra.Water", "&bWater");
                config.addDefault("Chat.Variables.ProjectKorra.Fire", "&cFire");
                config.addDefault("Chat.Variables.ProjectKorra.Air", "&7Air");
                config.addDefault("Chat.Variables.ProjectKorra.ChiBlocking", "&6Chi");

                config.addDefault("BungeeCord.GlobalChannels.Global.NameFormat", "&8[&2G&8] [&e&o<server>&8]&r <dname>");
                config.addDefault("BungeeCord.GlobalChannels.Global.NameHover", "&8Rank: <prefix>&o<group>");
                config.addDefault("BungeeCord.GlobalChannels.Global.NameClick", "/mail <name> ");
                config.addDefault("BungeeCord.GlobalChannels.Global.MessageFormat", "&8:&r <message>");
                config.addDefault("BungeeCord.GlobalChannels.Global.MessageHover", "");
                config.addDefault("BungeeCord.GlobalChannels.Staff.NameFormat", "&8[&cS&8] [&e&o<server>&8]&r <dname>");
                config.addDefault("BungeeCord.GlobalChannels.Staff.NameHover", "&8Rank: <prefix>&o<group>");
                config.addDefault("BungeeCord.GlobalChannels.Staff.NameClick", "/mail <name> ");
                config.addDefault("BungeeCord.GlobalChannels.Staff.MessageFormat", "&8:&r <message>");
                config.addDefault("BungeeCord.GlobalChannels.Staff.MessageHover", "");

                ArrayList<String> motd = new ArrayList<>();
                motd.add("&8&l&m====================&r&8&l[&r&eMOTD&8&l]&m====================");
                motd.add("");
                motd.add("                          &7Nothing new <name>!");
                motd.add("");
                motd.add("&8&l&m=============================================");
                config.addDefault("Motd.Lines", motd);

                defaultLocal.save();
                break;
        }
    }
}