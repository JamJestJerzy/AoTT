package dev.j3rzy.aott.players;

import dev.j3rzy.aott.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Players {
    public static Players INSTANCE = new Players();
    private final List<Player> players = new ArrayList<>();

    public Players() {}

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getPlayer(org.bukkit.entity.Player playerEntity) {
        for (Player player : players) {
            if (player.getPlayer() == playerEntity) return player;
        }
        return null;
    }

    public List<Player> getPlayers() {
        return players;
    }
}