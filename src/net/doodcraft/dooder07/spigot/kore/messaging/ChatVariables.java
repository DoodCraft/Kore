package net.doodcraft.dooder07.spigot.kore.messaging;

import com.massivecraft.factions.entity.MPlayer;
import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.GeneralMethods;
import net.doodcraft.dooder07.spigot.kore.compat.Compatibility;
import net.doodcraft.dooder07.spigot.kore.compat.Vault;
import net.doodcraft.dooder07.spigot.kore.configuration.Settings;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ChatVariables {
    // KORE
    public static String getDisplayName(Player player) {
        String displayName = Settings.KORE_DISPLAYNAME;
        displayName = StringParser.parseChatVariables(player, displayName, "");
        displayName = StringParser.addColor(displayName);
        return displayName;
    }

    public static String getServerName() {
        String name = Settings.KORE_SERVERNAME;
        if (name.equals("")) {
            name = Bukkit.getServerName();
        }
        return name;
    }

    public static String getWorldName(String world, Player player) {
        String worldName;

        if (Settings.getWorldName(world) != null) {
            worldName = Settings.getWorldName(world);
            worldName = StringParser.addColor(worldName);
            return worldName;
        } else {
            worldName = player.getWorld().getName();
            return worldName;
        }
    }

    public static String getWorldTime(Player player) {
        long gameTime = player.getWorld().getTime();
        long hours = gameTime / 1000 + 6;
        long minutes = (gameTime % 1000) * 60 / 1000;
        String meridiem = "AM";

        if (hours >= 12) {
            hours -= 12;
            meridiem = "PM";
        }
        if (hours >= 12) {
            hours -= 12;
            meridiem = "AM";
        }
        if (hours == 0) {
            hours = 12;
        }

        String mm = "0" + minutes;
        mm = mm.substring(mm.length() - 2, mm.length());
        return hours + ":" + mm + " " + meridiem;
    }

    // PERMISSIONS
    public static String getPlayerGroup(World world, Player player) {
        String group = "";
        if (Compatibility.vault != null) {
            group = Vault.permission.getPrimaryGroup(world.getName(), player);
            return group;
        }
        return group;
    }

    public static String getPlayerPrefix(World world, Player player) {
        String prefix = "";
        if (Compatibility.vault != null) {
            prefix = Vault.chat.getPlayerPrefix(world.getName(), player);
            return prefix;
        }
        return prefix;
    }

    public static String getPlayerSuffix(World world, Player player) {
        String suffix = "";
        if (Compatibility.vault != null) {
            suffix = Vault.chat.getPlayerSuffix(world.getName(), player);
            return suffix;
        }
        return suffix;
    }

    // PROJECTKORRA
    public static String getElement(Player player) {
        String name = player.getName();
        String element = "&";

        if (Compatibility.projectKorra != null) {
            BendingPlayer bPlayer = GeneralMethods.getBendingPlayer(name);

            if (bPlayer != null) {
                if (bPlayer.hasElement(Element.Earth)) {
                    element = StringParser.addColor(Settings.PROJECTKORRA_EARTH);
                }
                if (bPlayer.hasElement(Element.Water)) {
                    element = StringParser.addColor(Settings.PROJECTKORRA_WATER);
                }
                if (bPlayer.hasElement(Element.Fire)) {
                    element = StringParser.addColor(Settings.PROJECTKORRA_FIRE);
                }
                if (bPlayer.hasElement(Element.Air)) {
                    element = StringParser.addColor(Settings.PROJECTKORRA_AIR);
                }
                if (bPlayer.hasElement(Element.Chi)) {
                    element = StringParser.addColor(Settings.PROJECTKORRA_CHIBLOCKING);
                }
                if (bPlayer.getElements().size() > 1) {
                    element = StringParser.addColor(Settings.PROJECTKORRA_AVATAR);
                }
            } else {
                element = StringParser.addColor(Settings.PROJECTKORRA_POWERLESS);
            }
        }
        return element;
    }

    // FACTIONS
    public static String getFactionName(Player player) {
        String faction = null;

        if (Compatibility.factions != null) {
            faction = MPlayer.get(player).getFaction().getName();
        }
        return faction;
    }

    public static String getFactionRolePrefix(Player player) {
        String role = null;

        if (Compatibility.factions != null) {
            role = MPlayer.get(player).getRole().getPrefix();
        }
        return role;
    }
}