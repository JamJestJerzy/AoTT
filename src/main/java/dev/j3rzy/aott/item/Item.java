package dev.j3rzy.aott.item;

import dev.j3rzy.aott.enums.Rarity;
import dev.j3rzy.aott.enums.Stats;
import dev.j3rzy.aott.enums.Type;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Item {
    private final Material physicalItem;
    private final Type type;
    private final Rarity rarity;
    private final String name;
    private final List<String> description;
    private final List<Stat> stats;
    private final List<GemstoneSlot> gemstoneSlots;
    private final boolean canBeReforged;

    public Item(Material physicalItem, Type type, Rarity rarity, String name, List<String> description, List<Stat> stats, List<GemstoneSlot> gemstoneSlots, boolean canBeReforged) {
        this.physicalItem = physicalItem;
        this.type = type;
        this.rarity = rarity;
        this.name = name;
        this.description = description;
        this.stats = stats;
        this.gemstoneSlots = gemstoneSlots;
        this.canBeReforged = canBeReforged;
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(physicalItem);
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        assert itemMeta != null;

        itemMeta.setDisplayName(rarity.color + name);

        List<Stat> boostedStats = getGemstoneBoostedStats();
        Map<Stats, Integer> totalStatBoosts = new HashMap<>();

        for (Stat stat : boostedStats) {
            String statLine = ChatColor.GRAY + stat.stat.name + ": " + stat.stat.valueColor +
                    ((stat.stat == Stats.GEAR_SCORE) ? "" : "+") + (int) stat.value;

            // Accumulate total boost for each stat
            for (GemstoneSlot gemstoneSlot : gemstoneSlots) {
                if (gemstoneSlot.getStatBoost(stat.stat) != null) {
                    int boostValue = (int) gemstoneSlot.getStatBoost(stat.stat).value;
                    if (boostValue > 0) {
                        totalStatBoosts.put(stat.stat, totalStatBoosts.getOrDefault(stat.stat, 0) + boostValue);
                    }
                }
            }

            // Check if the stat was boosted and display the total boost
            if (totalStatBoosts.containsKey(stat.stat)) {
                int totalBoost = totalStatBoosts.get(stat.stat);
                statLine += ChatColor.LIGHT_PURPLE + " (+" + totalBoost + ")";
            }

            lore.add(statLine);
        }

        if (!gemstoneSlots.isEmpty()) {
            StringBuilder gemstonesSlots = new StringBuilder();
            for (GemstoneSlot gemstoneSlot : gemstoneSlots) {
                gemstonesSlots.append(" ").append(gemstoneSlot.getIcon());
            }
            lore.add(gemstonesSlots.toString());
        }

        lore.add("");

        if (!description.isEmpty()) {
            for (String line : description) lore.add(ChatColor.RESET+line);
            lore.add("");
        }

        if (canBeReforged) lore.add(ChatColor.RESET+""+ChatColor.DARK_GRAY+"This item can be reforged!");
        lore.add(ChatColor.RESET+""+rarity.color+ChatColor.BOLD+rarity.name+" "+type.name);
        itemMeta.setLore(lore);
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        return item;
    }

    public boolean CanBeReforged() {
        return canBeReforged;
    }

    public Type getType() {
        return type;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public String getName() {
        return name;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public Material getPhysicalItem() {
        return physicalItem;
    }

    public List<GemstoneSlot> getGemstoneSlots() {
        return gemstoneSlots;
    }

    public List<Stat> getGemstoneBoostedStats() {
        List<Stat> boostedStats = new ArrayList<>(stats); // Create a copy of stats to preserve original values

        for (Stat stat : boostedStats) {
            /* Resetting stat value before applying gemstone boosts */
            stat.resetValue();

            for (GemstoneSlot gemstoneSlot : gemstoneSlots) {
                if (gemstoneSlot.getStatBoost(stat.stat) != null) {
                    int boostValue = (int) gemstoneSlot.getStatBoost(stat.stat).value;
                    stat.setValue(stat.value + boostValue);
                }
            }
        }

        return boostedStats;
    }
}
