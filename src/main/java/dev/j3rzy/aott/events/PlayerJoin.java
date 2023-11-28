package dev.j3rzy.aott.events;

import dev.j3rzy.aott.players.Players;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Players.INSTANCE.addPlayer(new dev.j3rzy.aott.player.Player(player));
    }
}
