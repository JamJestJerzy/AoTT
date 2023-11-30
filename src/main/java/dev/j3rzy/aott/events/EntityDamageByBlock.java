package dev.j3rzy.aott.events;

import dev.j3rzy.aott.players.Players;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;

public class EntityDamageByBlock implements Listener {
    @EventHandler
    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
        Entity damaged = event.getEntity();

        if (damaged instanceof Player p) {
            double damage = event.getDamage();
            event.setDamage(0);
            dev.j3rzy.aott.player.Player player = Players.INSTANCE.getPlayer(p);
            player.dealDamage(damage);
        }
    }
}
