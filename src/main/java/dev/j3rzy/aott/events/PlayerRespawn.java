package dev.j3rzy.aott.events;

import dev.j3rzy.aott.player.Player;
import dev.j3rzy.aott.players.Players;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import static dev.j3rzy.aott.utils.Utils.plugin;

public class PlayerRespawn implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Bukkit.getServer().getScheduler().runTask(plugin, () -> {
            Player player = Players.INSTANCE.getPlayer(event.getPlayer());
            player.heal(player.getMaxHealth() / 2);
        });
    }
}
