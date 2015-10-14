package net.doodcraft.dooder07.spigot.kore;

import net.doodcraft.dooder07.spigot.kore.bungee.BungeeCordListener;
import net.doodcraft.dooder07.spigot.kore.bungee.CommandFactory;
import net.doodcraft.dooder07.spigot.kore.bungee.GlobalMessage;
import net.doodcraft.dooder07.spigot.kore.commands.Gamemode;
import net.doodcraft.dooder07.spigot.kore.commands.Hat;
import net.doodcraft.dooder07.spigot.kore.commands.Teleport;
import net.doodcraft.dooder07.spigot.kore.compat.Compatibility;
import net.doodcraft.dooder07.spigot.kore.configuration.ConfigManager;
import net.doodcraft.dooder07.spigot.kore.immersion.Effectz;
import net.doodcraft.dooder07.spigot.kore.immersion.Misc;
import net.doodcraft.dooder07.spigot.kore.immersion.PlayerHeads;
import net.doodcraft.dooder07.spigot.kore.immersion.Survival;
import net.doodcraft.dooder07.spigot.kore.messaging.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class KorePlugin extends JavaPlugin {
    public static KorePlugin plugin;

    @Override
    public void onEnable() {
        Map<String, Long> enableTime = new HashMap<>();
        enableTime.put("start", System.currentTimeMillis());

        plugin = this;

        new ConfigManager(this);

        registerListeners();
        setExecutors();
        initialise();

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (Exception ex) {
            DoodLogger.printError("&2Metrics", "&cError enabling metrics for Kore!", ex);
        }

        Long time = (System.currentTimeMillis() - enableTime.get("start"));
        DoodLogger.log("Kore", "&9:: &eKore is now enabled! &bTook " + time + "ms");
        enableTime.clear();
    }

    @Override
    public void onDisable() {
        AfkHandler.removeOnlinePlayers();
    }

    public void registerListeners() {
        registerEvents(plugin, new Teleport());
        registerEvents(plugin, new PlayerHeads());
        registerEvents(plugin, new Survival());
        registerEvents(plugin, new Misc());
        registerEvents(plugin, new AfkHandler());
        registerEvents(plugin, new ChatHandler());
        registerEvents(plugin, new JoinMessages());
        registerEvents(plugin, new QuitMessages());
        registerEvents(plugin, new Effectz());
    }

    public void setExecutors() {
        getCommand("hat").setExecutor(new Hat());
        getCommand("tpa").setExecutor(new Teleport());
        getCommand("accept").setExecutor(new Teleport());
        getCommand("deny").setExecutor(new Teleport());
        getCommand("kore").setExecutor(new KoreCommand());
        getCommand("compel").setExecutor(new CompelCommand());
        getCommand("head").setExecutor(new PlayerHeads());
        getCommand("gm").setExecutor(new Gamemode());
        getCommand("afk").setExecutor(new AfkHandler());
        getCommand("me").setExecutor(new MeCommand());
        getCommand("say").setExecutor(new SayCommand());
        getCommand("g").setExecutor(new GlobalMessage());
        getCommand("s").setExecutor(new GlobalMessage());
    }

    public void initialise() {
        Compatibility.checkHooks();
        CommandFactory.setupAbstractCommands();
        AfkHandler.addOnlinePlayers();
        getServer().getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", new BungeeCordListener());
    }

    public static void registerEvents(Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }
}