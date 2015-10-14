package net.doodcraft.dooder07.spigot.kore.configuration;

import net.doodcraft.dooder07.spigot.kore.KorePlugin;

import java.util.List;

public class Settings {
    // CONFIG
    public static Boolean LOGGING_COLORFUL = KorePlugin.plugin.getConfig().getBoolean("Logging.Colorful");
    public static Boolean MOTD_ENABLED = KorePlugin.plugin.getConfig().getBoolean("Motd.Enabled");

    public static int AFK_POINTS = KorePlugin.plugin.getConfig().getInt("Chat.Afk.FlagPoints");
    public static int AFK_KICKPOINTS = KorePlugin.plugin.getConfig().getInt("Chat.Afk.KickPoints");

    public static List<String> BUNGEE_SERVERS = ConfigManager.defaultConfig.get().getStringList("BungeeCord.Servers");

    public static Boolean ONJOIN_LIGHTNING_ENABLED = ConfigManager.defaultConfig.get().getBoolean("Effectz.OnJoin.Lightning.Enabled");
    public static int ONJOIN_LIGHTNING_AMOUNT = ConfigManager.defaultConfig.get().getInt("Effectz.OnJoin.Lightning.Amount");
    public static Boolean ONJOIN_PARTICLES_ENABLED = ConfigManager.defaultConfig.get().getBoolean("Effectz.OnJoin.Particles.Enabled");
    public static List<String> ONJOIN_PARTICLES_LIST = ConfigManager.defaultConfig.get().getStringList("Effectz.OnJoin.Particles.List");
    public static Boolean ONJOIN_SOUNDS_ENABLED = ConfigManager.defaultConfig.get().getBoolean("Effectz.OnJoin.Sounds.Enabled");
    public static List<String> ONJOIN_SOUNDS_LIST = ConfigManager.defaultConfig.get().getStringList("Effectz.OnJoin.Sounds.List");
    public static Boolean ONJOIN_POTIONS_ENABLED = ConfigManager.defaultConfig.get().getBoolean("Effectz.OnJoin.Potions.Enabled");
    public static List<String> ONJOIN_POTIONS_LIST = ConfigManager.defaultConfig.get().getStringList("Effectz.OnJoin.Potions.List");

    public static Boolean ONQUIT_LIGHTNING_ENABLED = ConfigManager.defaultConfig.get().getBoolean("Effectz.OnQuit.Lightning.Enabled");
    public static int ONQUIT_LIGHTNING_AMOUNT = ConfigManager.defaultConfig.get().getInt("Effectz.OnQuit.Lightning.Amount");
    public static Boolean ONQUIT_PARTICLES_ENABLED = ConfigManager.defaultConfig.get().getBoolean("Effectz.OnQuit.Particles.Enabled");
    public static List<String> ONQUIT_PARTICLES_LIST = ConfigManager.defaultConfig.get().getStringList("Effectz.OnQuit.Particles.List");
    public static Boolean ONQUIT_SOUNDS_ENABLED = ConfigManager.defaultConfig.get().getBoolean("Effectz.OnQuit.Sounds.Enabled");
    public static List<String> ONQUIT_SOUNDS_LIST = ConfigManager.defaultConfig.get().getStringList("Effectz.OnQuit.Sounds.List");

    public static Boolean TP_LIGHTNING_FROM_ENABLED = ConfigManager.defaultConfig.get().getBoolean("Effectz.OnTeleport.Lightning.From.Enabled");
    public static int TP_LIGHTNING_FROM_AMOUNT = ConfigManager.defaultConfig.get().getInt("Effectz.OnTeleport.Lightning.From.Amount");
    public static Boolean TP_LIGHTNING_TO_ENABLED = ConfigManager.defaultConfig.get().getBoolean("Effectz.OnTeleport.Lightning.To.Enabled");
    public static int TP_LIGHTNING_TO_AMOUNT = ConfigManager.defaultConfig.get().getInt("Effectz.OnTeleport.Lightning.To.Amount");
    public static Boolean TP_PARTICLES_ENABLED = ConfigManager.defaultConfig.get().getBoolean("Effectz.OnTeleport.Particles.Enabled");
    public static List<String> TP_PARTICLES_FROM_LIST = ConfigManager.defaultConfig.get().getStringList("Effectz.OnTeleport.Particles.From.List");
    public static List<String> TP_PARTICLES_TO_LIST = ConfigManager.defaultConfig.get().getStringList("Effectz.OnTeleport.Particles.To.List");
    public static Boolean TP_SOUNDS_ENABLED = ConfigManager.defaultConfig.get().getBoolean("Effectz.OnTeleport.Sounds.Enabled");
    public static List<String> TP_SOUNDS_FROM_LIST = ConfigManager.defaultConfig.get().getStringList("Effectz.OnTeleport.Sounds.From.List");
    public static List<String> TP_SOUNDS_TO_LIST = ConfigManager.defaultConfig.get().getStringList("Effectz.OnTeleport.Sounds.To.List");
    public static Boolean TP_POTIONS_ENABLED = ConfigManager.defaultConfig.get().getBoolean("Effectz.OnTeleport.Potions.Enabled");
    public static List<String> TP_POTIONS_LIST = ConfigManager.defaultConfig.get().getStringList("Effectz.OnTeleport.Potions.List");

    // LOCAL
    public static String NOPERMISSION_MESSAGE = ConfigManager.defaultLocal.get().getString("Messages.NoPermission.Message");
    public static String NOPERMISSION_HOVER = ConfigManager.defaultLocal.get().getString("Messages.NoPermission.Hover");

    public static String JOIN_MESSAGE = ConfigManager.defaultLocal.get().getString("Messages.Events.Join.Message");
    public static String JOIN_HOVER = ConfigManager.defaultLocal.get().getString("Messages.Events.Join.Hover");
    public static String QUIT_MESSAGE = ConfigManager.defaultLocal.get().getString("Messages.Events.Quit.Message");
    public static String QUIT_HOVER = ConfigManager.defaultLocal.get().getString("Messages.Events.Quit.Hover");
    public static String NEWPLAYER_MESSAGE = ConfigManager.defaultLocal.get().getString("Messages.Events.Welcome.NewPlayer.Message");
    public static String NEWPLAYER_TITLE = ConfigManager.defaultLocal.get().getString("Messages.Events.Welcome.NewPlayer.Title");
    public static String NEWPLAYER_SUBTITLE = ConfigManager.defaultLocal.get().getString("Messages.Events.Welcome.NewPlayer.SubTitle");
    public static String RETURNINGPLAYER_MESSAGE = ConfigManager.defaultLocal.get().getString("Messages.Events.Welcome.ReturningPlayer.Message");
    public static String RETURNINGPLAYER_TITLE = ConfigManager.defaultLocal.get().getString("Messages.Events.Welcome.ReturningPlayer.Title");
    public static String RETURNINGPLAYER_SUBTITLE = ConfigManager.defaultLocal.get().getString("Messages.Events.Welcome.ReturningPlayer.SubTitle");

    public static String AFK_PREFIX = ConfigManager.defaultLocal.get().getString("Messages.Afk.Prefix");
    public static String ISNOWAFK_MESSAGE = ConfigManager.defaultLocal.get().getString("Messages.Afk.IsNowAFK.Message");
    public static String ISNOWAFK_HOVER = ConfigManager.defaultLocal.get().getString("Messages.Afk.IsNowAfk.Hover");
    public static String NOLONGERAFK_MESSAGE = ConfigManager.defaultLocal.get().getString("Messages.Afk.NoLongerAfk.Message");

    public static String NAME_FORMAT = ConfigManager.defaultLocal.get().getString("Chat.Formats.PlayerChat.Name.Format");
    public static String NAME_HOVER = ConfigManager.defaultLocal.get().getString("Chat.Formats.PlayerChat.Name.Hover");
    public static String NAME_CLICK = ConfigManager.defaultLocal.get().getString("Chat.Formats.PlayerChat.Name.Click");
    public static String MESSAGE_FORMAT = ConfigManager.defaultLocal.get().getString("Chat.Formats.PlayerChat.Message.Format");
    public static String MESSAGE_HOVER = ConfigManager.defaultLocal.get().getString("Chat.Formats.PlayerChat.Message.Hover");

    public static String MECOMMAND_MESSAGE = ConfigManager.defaultLocal.get().getString("Chat.Formats.MeCommand.Message");
    public static String MECOMMAND_HOVER = ConfigManager.defaultLocal.get().getString("Chat.Formats.MeCommand.Hover");
    public static String SAYCOMMAND_MESSAGE = ConfigManager.defaultLocal.get().getString("Chat.Formats.SayCommand.Message");
    public static String SAYCOMMAND_HOVER = ConfigManager.defaultLocal.get().getString("Chat.Formats.SayCommand.Hover");

    public static String KORE_DISPLAYNAME = ConfigManager.defaultLocal.get().getString("Chat.Variables.Kore.DisplayName");
    public static String KORE_SERVERNAME = ConfigManager.defaultLocal.get().getString("Chat.Variables.Kore.ServerName");

    public static String getWorldName(String world) {
        String name = ConfigManager.defaultLocal.get().getString("Chat.Variables.Kore.Worlds." + world);
        return name;
    }

    public static String PROJECTKORRA_POWERLESS = ConfigManager.defaultLocal.get().getString("Chat.Variables.ProjectKorra.Powerless");
    public static String PROJECTKORRA_AVATAR = ConfigManager.defaultLocal.get().getString("Chat.Variables.ProjectKorra.Avatar");
    public static String PROJECTKORRA_EARTH = ConfigManager.defaultLocal.get().getString("Chat.Variables.ProjectKorra.Earth");
    public static String PROJECTKORRA_WATER = ConfigManager.defaultLocal.get().getString("Chat.Variables.ProjectKorra.Water");
    public static String PROJECTKORRA_FIRE = ConfigManager.defaultLocal.get().getString("Chat.Variables.ProjectKorra.Fire");
    public static String PROJECTKORRA_AIR = ConfigManager.defaultLocal.get().getString("Chat.Variables.ProjectKorra.Air");
    public static String PROJECTKORRA_CHIBLOCKING = ConfigManager.defaultLocal.get().getString("Chat.Variables.ProjectKorra.ChiBlocking");

    public static String GLOBAL_NAMEFORMAT = ConfigManager.defaultLocal.get().getString("BungeeCord.GlobalChannels.Global.NameFormat");
    public static String GLOBAL_NAMEHOVER = ConfigManager.defaultLocal.get().getString("BungeeCord.GlobalChannels.Global.NameHover");
    public static String GLOBAL_NAMECLICK = ConfigManager.defaultLocal.get().getString("BungeeCord.GlobalChannels.Global.NameClick");
    public static String GLOBAL_MESSAGEFORMAT = ConfigManager.defaultLocal.get().getString("BungeeCord.GlobalChannels.Global.MessageFormat");
    public static String GLOBAL_MESSAGEHOVER = ConfigManager.defaultLocal.get().getString("BungeeCord.GlobalChannels.Global.MessageHover");
    public static String STAFF_NAMEFORMAT = ConfigManager.defaultLocal.get().getString("BungeeCord.GlobalChannels.Staff.NameFormat");
    public static String STAFF_NAMEHOVER = ConfigManager.defaultLocal.get().getString("BungeeCord.GlobalChannels.Staff.NameHover");
    public static String STAFF_NAMECLICK = ConfigManager.defaultLocal.get().getString("BungeeCord.GlobalChannels.Staff.NameClick");
    public static String STAFF_MESSAGEFORMAT = ConfigManager.defaultLocal.get().getString("BungeeCord.GlobalChannels.Staff.MessageFormat");
    public static String STAFF_MESSAGEHOVER = ConfigManager.defaultLocal.get().getString("BungeeCord.GlobalChannels.Staff.MessageHover");

    public static List<String> MOTD_LINES = ConfigManager.defaultLocal.get().getStringList("Motd.Lines");
}