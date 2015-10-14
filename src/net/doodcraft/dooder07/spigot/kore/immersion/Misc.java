package net.doodcraft.dooder07.spigot.kore.immersion;

import net.doodcraft.dooder07.spigot.kore.configuration.Config;
import net.doodcraft.dooder07.spigot.kore.messaging.StringParser;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Random;

public class Misc implements Listener {
    Random rand = new Random();

    @EventHandler
    public void onChange(SignChangeEvent event) {
        String lineOne = event.getLine(0);
        String lineTwo = event.getLine(1);
        String lineThree = event.getLine(2);
        String lineFour = event.getLine(3);
        event.setLine(0, StringParser.addColor(lineOne));
        event.setLine(1, StringParser.addColor(lineTwo));
        event.setLine(2, StringParser.addColor(lineThree));
        event.setLine(3, StringParser.addColor(lineFour));
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getLocation();

        if (loc.getBlock().equals(Material.PORTAL) && event.getAction() == Action.LEFT_CLICK_BLOCK) {
            loc.getBlock().setType(Material.AIR);
        }

        if (event.hasBlock() && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();

            if (block.getType() == Material.BOOKSHELF) {
                if (!player.isSneaking()) {
                    int chance = rand.nextInt(Config.books.size()) + 1;
                    String line = Config.books.get(chance);
                    player.sendMessage(StringParser.addColor("&7&oYou pick up a book:"));
                    player.sendMessage(StringParser.addColor("&7\"&6" + line + "&7\""));
                    event.setCancelled(true);
                }
            }
        }
    }
}
