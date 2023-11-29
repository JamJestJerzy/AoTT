package dev.j3rzy.aott.events;

import dev.j3rzy.aott.enums.Stats;
import dev.j3rzy.aott.players.Players;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();

        if (damaged instanceof Player p) {
            event.setCancelled(true);
            dev.j3rzy.aott.player.Player player = Players.INSTANCE.getPlayer(p);
            player.dealDamage(event.getDamage());
            player.dealDamage(1);
            System.out.println(player.getStat(Stats.HEALTH).currentValue);
        }
    }
}
