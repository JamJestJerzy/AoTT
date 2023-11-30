package dev.j3rzy.aott.events;

import dev.j3rzy.aott.enums.Stats;
import dev.j3rzy.aott.player.Player;
import dev.j3rzy.aott.players.Players;
import dev.j3rzy.aott.utils.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Scheduled {
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

    public static void onceASecond() {
        new java.util.Timer().schedule(new java.util.TimerTask(){@Override public void run(){
            onceASecond();
        }},1000);
    }

    public static void onceOneForthOfASecond() {
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                onceOneForthOfASecond();
            }
        }, 250);

        /* Display stats above hotbar */
        for (Player player : Players.INSTANCE.getPlayers()) {
            String health = ((player.getAbsorptionAmount() > 0) ? ChatColor.GOLD : ChatColor.RED) + "" + (int) (player.getHealth() + player.getAbsorptionAmount()) + "/" + (int) player.getStat(Stats.HEALTH).getMaxValue() + Stats.HEALTH.symbol;
            String defense = ChatColor.GREEN + "" + (int) player.getStat(Stats.DEFENSE).getValue() + Stats.DEFENSE.symbol + " " + Stats.DEFENSE.name;
            String intelligence = ChatColor.AQUA + "" + (int) player.getMana() + "/" + (int) player.getStat(Stats.INTELLIGENCE).getMaxValue() + Stats.INTELLIGENCE.symbol;

            String message = health + "     " + defense + "     " + intelligence;
            PlayerUtils.sendActionBarMessage(player.getPlayer(), message);
        }
        /* Display stats above hotbar */

        /* Update stats from items held in hand
        for (Player player : Players.INSTANCE.getPlayers()) {
            org.bukkit.entity.Player p = player.getPlayer();
            ItemStack heldItem = p.getItemInHand();
            ItemMeta meta = heldItem.getItemMeta();

            if (meta == null || meta.getLore() == null) continue;

            List<String> stats = new ArrayList<>();

            for (String line : meta.getLore()) {
                line = ChatColor.stripColor(line);
                String[] splitLine = line.split(": ");
                if (splitLine.length == 2) {
                    String statName = splitLine[0];
                    double statValue;

                    try {
                        statValue = Double.parseDouble(splitLine[1]);
                    } catch (NumberFormatException e) {
                        // Handle parsing errors if needed
                        continue;
                    }

                    // Match the stat name with player's stats and update accordingly
                    Stats playerStat = Stats.getStatByName(statName); // Implement this method in your Stats enum
                    if (playerStat != null) {
                        player.updateStat(playerStat, statValue);
                    }
                }
            }
        }
        Update stats from items held in hand */

        for (Player player : Players.INSTANCE.getPlayers()) {
            org.bukkit.entity.Player bukkitPlayer = player.getPlayer();
            bukkitPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getMaxHealth()/5);
        }
    }
}
