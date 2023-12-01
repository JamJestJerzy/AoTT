package dev.j3rzy.aott.players;

import dev.j3rzy.aott.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Players {
    public static Players INSTANCE = new Players();
    private final List<Player> players = new ArrayList<>();

    public Players() {}

    /**
     *
     * @param player player to register
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     *
     * @param playerEntity bukkitPlayer to get Player of
     * @return Player object
     */
    public Player getPlayer(org.bukkit.entity.Player playerEntity) {
        for (Player player : players) {
            if (player.getPlayer() == playerEntity) return player;
        }
        return null;
    }

    /**
     *
     * @return registered players
     */
    public List<Player> getPlayers() {
        return players;
    }
}