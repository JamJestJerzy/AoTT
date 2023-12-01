package dev.j3rzy.aott.events;

import dev.j3rzy.aott.player.Player;
import dev.j3rzy.aott.players.Players;
import dev.j3rzy.aott.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.scheduler.BukkitScheduler;

import static dev.j3rzy.aott.utils.Utils.plugin;

public class Scheduled {
    BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

    /**
     * Excecuted once every 2 seconds
     */
    public static void onceEveryTwoSeconds() {
        new java.util.Timer().schedule(new java.util.TimerTask(){@Override public void run(){
            onceEveryTwoSeconds();
        }},2000);

        /* Regen mana and health */
        for (Player player : Players.INSTANCE.getPlayers()) {
            player.heal((player.getMaxHealth() * 0.01) + 1.5);
            player.regenMana(player.getMaxMana() * 0.02);
        }
        /* Regen mana and health */
    }

    /**
     * Executed once every second
     */
    public static void onceASecond() {
        new java.util.Timer().schedule(new java.util.TimerTask(){@Override public void run(){
            onceASecond();
        }},1000);
    }

    /**
     * Executed once 1/4th of a second
     */
    public static void onceOneForthOfASecond() {
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                onceOneForthOfASecond();
            }
        }, 250);

        /* Display stats above hotbar */
        for (Player player : Players.INSTANCE.getPlayers()) {
            PlayerUtils.updateActionBarStats(player); // Shows stats above hotbar
            player.getPlayer().setFoodLevel(20);
        }
        /* Display stats above hotbar */

        for (Player player : Players.INSTANCE.getPlayers()) {
            org.bukkit.entity.Player bukkitPlayer = player.getPlayer();
            bukkitPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getMaxHealth()/5);
        }
    }

    /**
     * Executed once per gameTick
     */
    int taskId = scheduler.scheduleSyncRepeatingTask(plugin, () -> {

    }, 0L, 1L);
}
