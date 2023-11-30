package dev.j3rzy.aott.abilities;

import dev.j3rzy.aott.enums.Stats;
import dev.j3rzy.aott.item.Ability;
import dev.j3rzy.aott.players.Players;
import dev.j3rzy.aott.utils.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Explosive;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;

import static dev.j3rzy.aott.utils.WorldUtils.getMobsInRadius;

public class WitherImpact extends Ability {
    private static final String name = "Wither Impact";
    private static final List<String> description = List.of(new String[]{
            ChatColor.GRAY + "Teleport " + ChatColor.GREEN + "10 blocks " + ChatColor.GRAY + "ahead of",
            ChatColor.GRAY + "you. Then implode dealing",
            ChatColor.RED + "10,000 " + ChatColor.GRAY + "damage to nearby",
            ChatColor.GRAY + "enemies. Also applies the wither",
            ChatColor.GRAY + "shield scroll ability reducing",
            ChatColor.GRAY + "damage taken and granting an",
            ChatColor.GRAY + "absorption shield for " + ChatColor.YELLOW + "5",
            ChatColor.GRAY + "seconds."
    });
    private static final double manaCost = 150;
    private static final List<Action> triggers = List.of(new Action[]{
            Action.RIGHT_CLICK_AIR,
            Action.RIGHT_CLICK_BLOCK
    });
    private final WitherShield witherShield;

    public WitherImpact() {
        super(name, description, manaCost, 0, triggers);
        witherShield = new WitherShield();
    }

    @Override
    public boolean haveAbility(ItemStack item) {
        if (item != null && item.getItemMeta() != null && item.getItemMeta().getLore() != null)
            for (String line : item.getItemMeta().getLore())
                if (line.contains("Ability: " + name)) return true;
        return false;
    }

    @Override
    public void onTrigger(PlayerInteractEvent event) {
        int explosionRadius = 6;

        Player p = event.getPlayer();

        Location loc = PlayerUtils.teleportXBlocksForward(p, 10);

        p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, loc, 1);
        p.playSound(p, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.0F, 1.0F);

        List<Entity> mobsInRadius = getMobsInRadius(loc.getBlock(), explosionRadius);

        for (Entity entity : mobsInRadius) {
            if (entity.isInvulnerable()) continue;
            ((LivingEntity)entity).damage(10000, p);
        }

        witherShield.onTrigger(event);
    }
}
