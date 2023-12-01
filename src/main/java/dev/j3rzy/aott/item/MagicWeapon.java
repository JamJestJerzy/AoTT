package dev.j3rzy.aott.item;

import dev.j3rzy.aott.enums.Rarity;
import dev.j3rzy.aott.enums.Stats;
import dev.j3rzy.aott.enums.Type;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MagicWeapon extends Item {
    private final Material physicalItem;
    private final Type type;
    private final Rarity rarity;
    private final String name;
    private final List<String> description;
    private final List<Stat> stats;
    private final List<GemstoneSlot> gemstoneSlots;
    private final boolean canBeReforged;
    private final Ability ability;

    public MagicWeapon(Material physicalItem, Type type, Rarity rarity, String name, List<String> description, List<Stat> stats, List<GemstoneSlot> gemstoneSlots, boolean canBeReforged, Ability ability) {
        super(physicalItem, type, rarity, name, description, stats, gemstoneSlots, canBeReforged);
        this.physicalItem = physicalItem;
        this.type = type;
        this.rarity = rarity;
        this.name = name;
        this.description = description;
        this.stats = stats;
        this.gemstoneSlots = gemstoneSlots;
        this.canBeReforged = canBeReforged;
        this.ability = ability;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(physicalItem);
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        assert itemMeta != null;

        itemMeta.setDisplayName(rarity.color + name);

        for (Stat stat : stats) {
            /* Resetting stat value before applying gemstone boosts */
            stat.resetValue();

            String gemstoneStatBoost = "";
            int totalStatBoost = 0;
            Set<GemstoneSlot> appliedSlots = new HashSet<>();

            for (GemstoneSlot gemstoneSlot : gemstoneSlots) {
                if (gemstoneSlot.getStatBoost(stat.stat) != null && !appliedSlots.contains(gemstoneSlot)) {
                    int boostValue = (int) gemstoneSlot.getStatBoost(stat.stat).value;
                    totalStatBoost += boostValue;
                    stat.setValue(stat.value + boostValue);
                    appliedSlots.add(gemstoneSlot);
                }
            }

            /* Applying gemstones */
            lore.add(ChatColor.GRAY + stat.stat.name + ": " + stat.stat.valueColor +
                    ((stat.stat == Stats.GEAR_SCORE) ? "" : "+") + (int) stat.value +
                    ((totalStatBoost > 0) ? ChatColor.LIGHT_PURPLE + " (+" + totalStatBoost + ")" : ""));
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

        lore.add(ChatColor.RESET+""+ChatColor.GOLD+"Ability: "+ability.name+" "+ChatColor.YELLOW+ChatColor.BOLD+ability.triggers.get(0).name().replaceFirst("^(.*)_[^_]*$", "$1").replace("_", " "));
        for (String line : ability.description) lore.add(ChatColor.RESET+line);
        lore.add(ChatColor.RESET+""+ChatColor.DARK_GRAY+"Mana Cost: "+ChatColor.DARK_AQUA+(int)ability.manaCost);
        if (ability.cooldown > 0) lore.add(ChatColor.RESET+""+ChatColor.DARK_GRAY+"Cooldown: "+ChatColor.GREEN+(int)ability.cooldown+"s");

        lore.add("");

        if (canBeReforged) lore.add(ChatColor.RESET+""+ChatColor.DARK_GRAY+"This item can be reforged!");
        lore.add(ChatColor.RESET+""+rarity.color+ChatColor.BOLD+rarity.name+" "+type.name);
        itemMeta.setLore(lore);
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        return item;
    }

    public Ability getAbility() {
        return ability;
    }
}