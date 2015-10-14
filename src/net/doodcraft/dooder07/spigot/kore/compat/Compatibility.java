package net.doodcraft.dooder07.spigot.kore.compat;

import net.doodcraft.dooder07.spigot.kore.DoodLogger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Compatibility {
    public static Plugin vault;
    public static Plugin factions;
    public static Plugin projectKorra;
    public static Plugin plotSquared;

    public static void checkHooks() {
        Plugin hookVault = Bukkit.getPluginManager().getPlugin("Vault");
        Plugin hookFactions = Bukkit.getPluginManager().getPlugin("Factions");
        Plugin hookProjectKorra = Bukkit.getPluginManager().getPlugin("ProjectKorra");
        Plugin hookPlotSquared = Bukkit.getPluginManager().getPlugin("PlotSquared");

        if (hookVault != null) {
            // TODO: Check version before loading for true compatibility.
            vault = hookVault;
            Vault.setupChat();
            Vault.setupEconomy();
            Vault.setupPermissions();
            DoodLogger.log("Kore", "&9:: &aPlugin 'Vault' found and hooked!");
        }

        if (hookFactions != null) {
            // TODO: Check version before loading for true compatibility.
            factions = hookFactions;
            DoodLogger.log("Kore", "&9:: &aPlugin 'Factions' found and hooked!");
        }

        if (hookProjectKorra != null) {
            // TODO: Check version before loading for true compatibility.
            projectKorra = hookProjectKorra;
            DoodLogger.log("Kore", "&9:: &aPlugin 'ProjectKorra' found and hooked!");
        }

        if (hookPlotSquared != null) {
            // TODO: Check version before loading for true compatibility.
            plotSquared = hookPlotSquared;
            DoodLogger.log("Kore", "&9:: &aPlugin 'PlotSquared' found and hooked!");
        }
    }
}