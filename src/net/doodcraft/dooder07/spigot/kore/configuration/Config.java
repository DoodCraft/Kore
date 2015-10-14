package net.doodcraft.dooder07.spigot.kore.configuration;

import net.doodcraft.dooder07.spigot.kore.DoodLogger;
import net.doodcraft.dooder07.spigot.kore.KorePlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Config {
    private KorePlugin plugin;

    private File file;
    private FileConfiguration config;
    public static HashMap<Integer, String> books = new HashMap<>();

    public Config(File file) {
        this.plugin = KorePlugin.plugin;
        this.file = new File(plugin.getDataFolder() + File.separator + file);
        this.config = YamlConfiguration.loadConfiguration(this.file);
        reload();
    }

    public void create() {
        if (!file.getParentFile().exists()) {
            try {
                file.getParentFile().mkdir();
            } catch (Exception ex) {
                DoodLogger.printError("Kore", "Failed to create configuration directory!", ex);
            }
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception ex) {
                DoodLogger.printError("Kore", "Failed to create file '" + file + "'!", ex);
            }
        }

        File booksConfig = new File(plugin.getDataFolder(), "books.txt");
        if (!booksConfig.exists()) {
            plugin.saveResource("books.txt", true);
        }

        try {
            Scanner scanner = new Scanner(booksConfig);
            int line = 0;
            while (scanner.hasNextLine()){
                line = line + 1;
                books.put(line, scanner.nextLine());
            }
            scanner.close();
        } catch (Exception ex) {
            DoodLogger.printError("Kore", "books.txt not found!", ex);
        }
    }

    public FileConfiguration get() {
        return config;
    }

    public void reload() {
        create();
        try {
            config.load(file);
        } catch (Exception ex) {
            DoodLogger.printError("Kore", "Failed to reload file '" + file + "'!", ex);
        }
    }

    public void save() {
        try {
            config.options().copyDefaults(true);
            config.save(file);
        } catch (Exception ex) {
            DoodLogger.printError("Kore", "Failed to save file '" + file + "'!", ex);
        }
    }
}