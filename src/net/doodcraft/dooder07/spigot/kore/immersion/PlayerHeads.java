package net.doodcraft.dooder07.spigot.kore.immersion;

import net.doodcraft.dooder07.spigot.kore.Methods;
import net.doodcraft.dooder07.spigot.kore.messaging.StringParser;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerHeads implements Listener, CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("head")) {
            if (sender.hasPermission("kore.general.head")) {
                if (sender instanceof Player)
                {
                    Player player = (Player) sender;

                    if (args.length == 0) {
                        player.sendMessage(StringParser.addColor("&7Usage: &b/head <playername> [displayname]"));
                        return false;
                    } else if (args.length == 1) {
                        Methods.addItem(player, Methods.createSkull(args[0], "&r" + args[0] + "'s Head", null), 1);
                        player.sendMessage(StringParser.addColor("&7Skull created! &ePlayerName: &b" + args[0]));
                        return true;
                    } else if (args.length == 2) {
                        Methods.addItem(player, Methods.createSkull(args[0], "&r" + args[1], null), 1);
                        player.sendMessage(StringParser.addColor("&7Skull created! &ePlayerName: &b" + args[0] + " &eDisplayName: &b" + args[1]));
                        return true;
                    } else if (args.length > 2) {
                        player.sendMessage(StringParser.addColor("&7Usage: &b/head <playername> [item name]"));
                        return false;
                    }
                } else {
                    sender.sendMessage(StringParser.addColor("&cConsole can't spawn player heads!"));
                    return false;
                }
            }
        }
        return false;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (block.getType() == Material.SKULL) {
            Skull skull = (Skull) block.getState();
            String owner = skull.getOwner();

            if (owner.equalsIgnoreCase("MHF_Spider")) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                ItemStack drop = Methods.createSkull("MHF_Spider", "&rSpider Head", null);
                player.getWorld().dropItemNaturally(block.getLocation(), drop);
                return;
            }
            if (owner.equalsIgnoreCase("MHF_Slime")) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                ItemStack drop = Methods.createSkull("MHF_Slime", "&rSlime Head", null);
                player.getWorld().dropItemNaturally(block.getLocation(), drop);
                return;
            }
            if (owner.equalsIgnoreCase("MHF_Ghast")) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                ItemStack drop = Methods.createSkull("MHF_Ghast", "&rGhast Head", null);
                player.getWorld().dropItemNaturally(block.getLocation(), drop);
                return;
            }
            if (owner.equalsIgnoreCase("MHF_Blaze")) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                ItemStack drop = Methods.createSkull("MHF_Blaze", "&rBlaze Head", null);
                player.getWorld().dropItemNaturally(block.getLocation(), drop);
                return;
            }
            if (owner.equalsIgnoreCase("MHF_CaveSpider")) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                ItemStack drop = Methods.createSkull("MHF_CaveSpider", "&rCaveSpider Head", null);
                player.getWorld().dropItemNaturally(block.getLocation(), drop);
                return;
            }
            if (owner.equalsIgnoreCase("MHF_Enderman")) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                ItemStack drop = Methods.createSkull("MHF_Enderman", "&rEnderman Head", null);
                player.getWorld().dropItemNaturally(block.getLocation(), drop);
                return;
            }
            if (owner.equalsIgnoreCase("MHF_PigZombie")) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                ItemStack drop = Methods.createSkull("MHF_PigZombie", "&rZombie Pigman Head", null);
                player.getWorld().dropItemNaturally(block.getLocation(), drop);
                return;
            }
            if (owner.equalsIgnoreCase("MHF_LavaSlime")) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                ItemStack drop = Methods.createSkull("MHF_LavaSlime", "&rLava Slime Head", null);
                player.getWorld().dropItemNaturally(block.getLocation(), drop);
                return;
            }
            if (owner.equalsIgnoreCase("MHF_Squid")) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                ItemStack drop = Methods.createSkull("MHF_Squid", "&rSquid Head", null);
                player.getWorld().dropItemNaturally(block.getLocation(), drop);
                return;
            }
            if (owner.equalsIgnoreCase("MHF_Pig")) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                ItemStack drop = Methods.createSkull("MHF_Pig", "&rPig Head", null);
                player.getWorld().dropItemNaturally(block.getLocation(), drop);
                return;
            }
            if (owner.equalsIgnoreCase("MHF_Chicken")) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                ItemStack drop = Methods.createSkull("MHF_Chicken", "&rChicken Head", null);
                player.getWorld().dropItemNaturally(block.getLocation(), drop);
                return;
            }
            if (owner.equalsIgnoreCase("MHF_Sheep")) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                ItemStack drop = Methods.createSkull("MHF_Sheep", "&rSheep Head", null);
                player.getWorld().dropItemNaturally(block.getLocation(), drop);
                return;
            }
            if (owner.equalsIgnoreCase("MHF_MushroomCow")) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                ItemStack drop = Methods.createSkull("MHF_MushroomCow", "&rMushroom Cow Head", null);
                player.getWorld().dropItemNaturally(block.getLocation(), drop);
                return;
            }
            if (owner.equalsIgnoreCase("MHF_Cow")) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                ItemStack drop = Methods.createSkull("MHF_Cow", "&rCow Head", null);
                player.getWorld().dropItemNaturally(block.getLocation(), drop);
                return;
            }
            if (owner.equalsIgnoreCase("MHF_Villager")) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                ItemStack drop = Methods.createSkull("MHF_Villager", "&rVillager Head", null);
                player.getWorld().dropItemNaturally(block.getLocation(), drop);
                return;
            }
            if (owner.equalsIgnoreCase("MHF_Golem")) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                ItemStack drop = Methods.createSkull("MHF_Golem", "&rGolem Head", null);
                player.getWorld().dropItemNaturally(block.getLocation(), drop);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.hasBlock() && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (block.getType() == Material.SKULL) {
                Skull skull = (Skull) block.getState();
                String owner = skull.getOwner();
                if (owner != null) {
                    // Mobs
                    if (owner.equalsIgnoreCase("MHF_Spider")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bSpider&7&o Head"));
                        event.setCancelled(true);
                        return;
                    }
                    if (owner.equalsIgnoreCase("MHF_Slime")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bSlime&7&o Head"));
                        event.setCancelled(true);
                        return;
                    }
                    if (owner.equalsIgnoreCase("MHF_Ghast")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bGhast&7&o Head"));
                        event.setCancelled(true);
                        return;
                    }
                    if (owner.equalsIgnoreCase("MHF_Blaze")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bBlaze&7&o Head"));
                        event.setCancelled(true);
                        return;
                    }
                    if (owner.equalsIgnoreCase("MHF_CaveSpider")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bCaveSpider&7&o Head"));
                        event.setCancelled(true);
                        return;
                    }
                    if (owner.equalsIgnoreCase("MHF_Enderman")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bEnderman&7&o Head"));
                        event.setCancelled(true);
                        return;
                    }
                    if (owner.equalsIgnoreCase("MHF_PigZombie")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bPigZombie&7&o Head"));
                        event.setCancelled(true);
                        return;
                    }
                    if (owner.equalsIgnoreCase("MHF_LavaSlime")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bLavaSlime&7&o Head"));
                        event.setCancelled(true);
                        return;
                    }
                    if (owner.equalsIgnoreCase("MHF_Squid")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bSquid&7&o Head"));
                        event.setCancelled(true);
                        return;
                    }
                    if (owner.equalsIgnoreCase("MHF_Pig")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bPig&7&o Head"));
                        event.setCancelled(true);
                        return;
                    }
                    if (owner.equalsIgnoreCase("MHF_Chicken")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bChicken&7&o Head"));
                        event.setCancelled(true);
                        return;
                    }
                    if (owner.equalsIgnoreCase("MHF_Sheep")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bSheep&7&o Head"));
                        event.setCancelled(true);
                        return;
                    }
                    if (owner.equalsIgnoreCase("MHF_MushroomCow")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bMushroomCow&7&o Head"));
                        event.setCancelled(true);
                        return;
                    }
                    if (owner.equalsIgnoreCase("MHF_Cow")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bCow&7&o Head"));
                        event.setCancelled(true);
                        return;
                    }
                    if (owner.equalsIgnoreCase("MHF_Villager")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bVillager&7&o Head"));
                        event.setCancelled(true);
                        return;
                    }
                    if (owner.equalsIgnoreCase("MHF_Golem")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bGolem&7&o Head"));
                        event.setCancelled(true);
                        return;
                    }

                    // Etc
                    if (owner.equalsIgnoreCase("Camera")) {
                        player.sendMessage(StringParser.addColor("&7&oThat's a &bCamera"));
                        event.setCancelled(true);
                        return;
                    }

                    player.sendMessage(StringParser.addColor("&7&oThat's &r&e" + owner + "&7&o's Head"));
                    event.setCancelled(true);
                } else {
                    player.sendMessage(StringParser.addColor("&7&oThat's a mob skull"));
                    event.setCancelled(true);
                }
            }
        }
    }
}
