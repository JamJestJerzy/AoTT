package dev.j3rzy.aott.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
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
    public static void sendActionBarMessage(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

    /*
    * Teleports player given amount of blocks in direction he is facing.
    * Returns location player got teleported to.
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
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
            player.setFallDistance(0);
            player.setVelocity(new Vector(0,0,0));
        }
        return location;
    }
}
