package dev.j3rzy.aott.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WorldUtils {
    public static List<Entity> getMobsInRadius(Block block, double radius) {
        List<Entity> mobs = new ArrayList<>();
        World world = block.getWorld();
        for (Entity entity : world.getEntities()) {
            if (entity instanceof LivingEntity && !(entity instanceof Player)) {
                Location entityLocation = entity.getLocation();
                Location blockLocation = block.getLocation();
                double distance = entityLocation.distance(blockLocation);
                if (distance <= radius) {
                    mobs.add(entity);
                }
            }
        }
        return mobs;
    }
}
