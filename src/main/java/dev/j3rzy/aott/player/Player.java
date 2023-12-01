package dev.j3rzy.aott.player;

import dev.j3rzy.aott.enums.Stats;
import dev.j3rzy.aott.item.Stat;
import dev.j3rzy.aott.utils.MathUtils;
import dev.j3rzy.aott.utils.PlayerUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final org.bukkit.entity.Player player;

    private final List<PlayerStat> stats = new ArrayList<>();
    private double damageReduction;
    private double absorptionAmount;

    public Player(org.bukkit.entity.Player player) {
        this.player = player;

        stats.add(new PlayerStat(Stats.HEALTH, 100,100, true));
        stats.add(new PlayerStat(Stats.DEFENSE, 0, -1, false));
        stats.add(new PlayerStat(Stats.STRENGTH, 0, -1, false));
        stats.add(new PlayerStat(Stats.INTELLIGENCE, 100, 100, true));
        stats.add(new PlayerStat(Stats.CRIT_DAMAGE, 50, -1, false));
        stats.add(new PlayerStat(Stats.CRIT_CHANCE, 30, 100, false));
        stats.add(new PlayerStat(Stats.FEROCITY, 0, 500, false));
        stats.add(new PlayerStat(Stats.BONUS_ATTACK_SPEED, 0, 100, false));
        stats.add(new PlayerStat(Stats.ABILITY_DAMAGE, 0, -1, false));
        stats.add(new PlayerStat(Stats.HEALTH_REGEN, 100, -1, false));
        stats.add(new PlayerStat(Stats.VITALITY, 100, -1, false));
        stats.add(new PlayerStat(Stats.MENDING, 100, -1, false));
        stats.add(new PlayerStat(Stats.TRUE_DEFENSE, 0, -1, false));
        stats.add(new PlayerStat(Stats.SWING_RANGE, 3, -1, false));

        this.absorptionAmount = 0;
        this.damageReduction = 0;
    }

    /**
     *
     * @return bukkitPlayer Class represents
     */
    public org.bukkit.entity.Player getPlayer() {
        return player;
    }

    /**
     *
     * @param statistic stat to update
     * @param newValue new stat's value
     */
    public void updateStat(Stats statistic, double newValue) {
        for (PlayerStat playerStat : stats) if (playerStat.getStat() == statistic) {
            if (playerStat.isRegenerating()) playerStat.setMaxValue(newValue);
            else {
                if (playerStat.getMaxValue() == -1) playerStat.setValue(newValue);
                else playerStat.setValue(Math.min(newValue, playerStat.getMaxValue()));
            }
        }
    }

    /**
     *
     * @param statistic stat to get
     * @return stat's max value
     */
    public double getStatMaxValue(Stats statistic) {
        for (PlayerStat playerStat : stats) if (playerStat.getStat() == statistic) {
            if (playerStat.isRegenerating()) return playerStat.getMaxValue();
            return playerStat.getValue();
        }
        return -1;
    }

    /**
     *
     * @param statistic stat to fetch
     * @return stat object
     */
    public PlayerStat getStat(Stats statistic) {
        for (PlayerStat playerStat : stats) if (playerStat.getStat() == statistic) return playerStat;
        return null;
    }

    /**
     *
     * @return amount of damage reduction (percentage / 100)
     */
    public double getDamageReduction() {return damageReduction; }

    /**
     *
     * @param amount amount of damage reduction to add (percentage / 100)
     */
    public void addDamageReduction(double amount) { damageReduction += amount; }

    /**
     *
     * @param amount amount damage reduction to remove (percentage / 100)
     */
    public void removeDamageReduction(double amount) { damageReduction -= Math.min(amount, damageReduction); }

    /**
     *
     * @param damageReduction amount to set damage reduction to (percentage / 100)
     */
    public void setDamageReduction(double damageReduction) { this.damageReduction = damageReduction; }

    /**
     *
     * @return absorption amount
     */
    public double getAbsorptionAmount() { return absorptionAmount; }

    /**
     *
     * @param amount amount of absorption to add
     */
    public void addAbsorption(double amount) {
        absorptionAmount += amount;
        updateVanillaAbsorption();
    }

    /**
     *
     * @param amount amount of absorption to remove
     */
    public void removeAbsorption(double amount) {
        absorptionAmount = Math.max(0, absorptionAmount - amount);
        updateVanillaAbsorption();
    }

    /**
     *
     * @param absorptionAmount amout to set absorption to
     */
    public void setAbsorptionAmount(double absorptionAmount) {
        this.absorptionAmount = absorptionAmount;
        updateVanillaAbsorption();
    }

    /* Health management */

    /**
     *
     * @param amount amount of damage to deal to player
     * @param cause cause of the damage (event.getCause())
     */
    public void dealDamage(double amount, EntityDamageEvent.DamageCause cause) {
        if (player.isDead()) return;
        PlayerStat health = getStat(Stats.HEALTH);
        double playerDefence = getStat(Stats.DEFENSE).getValue();
        double finalDamage = amount * ((playerDefence > 0) ? (playerDefence / (playerDefence + 100)) : 1) * (1-getDamageReduction());
        if (absorptionAmount > 0) {
            double damageToAbsorptionShield = Math.min(finalDamage, absorptionAmount);
            absorptionAmount -= damageToAbsorptionShield;
            finalDamage -= damageToAbsorptionShield;
        }
        if (finalDamage > 0) health.setValue(Math.max(0, health.getValue() - finalDamage));
        updateVanillaHealth();
        updateVanillaAbsorption();
    }

    /**
     *
     * @param amount amount of hp to heal player
     */
    public void heal(double amount) {
        if (player.isDead()) return;
        PlayerStat health = getStat(Stats.HEALTH);
        if (health.getValue() >= health.getMaxValue()) return;
        health.setValue(Math.min(health.getValue() + amount, health.getMaxValue()));
        updateVanillaHealth();
    }

    /**
     *
     * @return amount of health player have
     */
    public double getHealth() {
        return getStat(Stats.HEALTH).getValue();
    }

    /**
     *
     * @return player's max health
     */
    public double getMaxHealth() {
        return getStat(Stats.HEALTH).getMaxValue();
    }

    /**
     * Updates vanilla health bar with custom amounts
     */
    public void updateVanillaHealth() {
        double scaledHealth = getMaxHealth()/5;
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(MathUtils.roundDownToMultipleOf(5, scaledHealth));
        player.setHealth(MathUtils.roundDownToMultipleOf(5, getHealth()/5));
        PlayerUtils.updateActionBarStats(this);
    }

    /**
     * Updates vanilla absorption amount with custom amounts
     */
    public void updateVanillaAbsorption() {
        player.setAbsorptionAmount(absorptionAmount/5);
        PlayerUtils.updateActionBarStats(this);
    }
    /* Health management */

    /* Mana management */

    /**
     *
     * @param amount amount of mana to consume
     */
    public void useMana(double amount) {
        PlayerStat mana = getStat(Stats.INTELLIGENCE);
        mana.setValue(Math.max(0, mana.getValue() - amount));
    }

    /**
     *
     * @param amount amount of mana to regen
     */
    public void regenMana(double amount) {
        PlayerStat mana = getStat(Stats.INTELLIGENCE);
        if (mana.getValue() >= mana.getMaxValue()) return;
        mana.setValue(Math.min(mana.getValue() + amount, mana.getMaxValue()));
    }

    /**
     *
     * @return amount of mana player have
     */
    public double getMana() {
        return getStat(Stats.INTELLIGENCE).getValue();
    }

    /**
     *
     * @return maximum amout of mana player can have (for regen)
     */
    public double getMaxMana() {
        return getStat(Stats.INTELLIGENCE).getMaxValue();
    }
    /* Mana management */
}