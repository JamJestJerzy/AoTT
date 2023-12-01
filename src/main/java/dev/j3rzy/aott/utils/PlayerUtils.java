package dev.j3rzy.aott.utils;

import dev.j3rzy.aott.enums.Stats;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class PlayerUtils {
    /**
     *
     * @param player player to send action bar message to
     * @param message message to send
     */
    public static void sendActionBarMessage(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

    /**
     *
     * @param player Player to update action bar of
     */
    public static void updateActionBarStats(dev.j3rzy.aott.player.Player player) {
        String health = ((player.getAbsorptionAmount() > 0) ? ChatColor.GOLD : ChatColor.RED) + "" + (int) (player.getHealth() + player.getAbsorptionAmount()) + "/" + (int) player.getStat(Stats.HEALTH).getMaxValue() + Stats.HEALTH.symbol;
        String defense = ChatColor.GREEN + "" + (int) player.getStat(Stats.DEFENSE).getValue() + Stats.DEFENSE.symbol + " " + Stats.DEFENSE.name;
        String intelligence = ChatColor.AQUA + "" + (int) player.getMana() + "/" + (int) player.getStat(Stats.INTELLIGENCE).getMaxValue() + Stats.INTELLIGENCE.symbol;

        String message = health + "     " + defense + "     " + intelligence;
        PlayerUtils.sendActionBarMessage(player.getPlayer(), message);
    }

    /**
     *
     * @param player Player to teleport
     * @param X amount of blocks to teleport
     * @return Location player got teleported to
     */
    public static Location teleportXBlocksForward(Player player, int X) {
        Set<Material> transparent = new HashSet<>();

        {
            transparent.add(Material.AIR);
            transparent.add(Material.WATER);
            transparent.add(Material.LAVA);
            transparent.add(Material.SUGAR_CANE);
            transparent.add(Material.GRASS);
            transparent.add(Material.TALL_GRASS);
            transparent.add(Material.SEAGRASS);
            transparent.add(Material.TALL_SEAGRASS);
            transparent.add(Material.KELP);
            transparent.add(Material.VINE);
        }

        Block block = player.getTargetBlock(transparent, X);
        Location location = new Location(block.getWorld(), block.getX(), block.getY(), block.getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
        if (!location.getBlock().isPassable()) {
            int i = 0;
            while (!location.getBlock().isPassable()) {
                if (i > X) {
                    location = player.getLocation();
                    break;
                }
                block = player.getTargetBlock(null, X-i);
                location = new Location(block.getWorld(), block.getX(), block.getY(), block.getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
                i++;
            }
        }

        if (location != player.getLocation()) {
            player.teleport(location, PlayerTeleportEvent.TeleportCause.COMMAND);
            player.setFallDistance(0);
            player.setVelocity(new Vector(0,0,0));
        }
        return location;
    }
}
