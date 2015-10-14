package net.doodcraft.dooder07.spigot.kore.immersion;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Survival implements Listener {
    Random rand = new Random();

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (player != null) {
            if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
                if (block.getType().equals(Material.LEAVES) || block.getType().equals(Material.LEAVES_2)) {
                    int chance = rand.nextInt(4) + 1;
                    if (chance == 1) {
                        block.getLocation().getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.STICK, 1));
                    }
                }
            }
        }
    }
}
