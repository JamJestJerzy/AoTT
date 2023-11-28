package dev.j3rzy.aott.item;

import dev.j3rzy.aott.enums.Rarity;
import dev.j3rzy.aott.enums.Stats;
import dev.j3rzy.aott.enums.Type;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MagicWeapon {
    public final Material physicalItem;
    public final Type type;
    public final Rarity rarity;
    public final String name;
    public final List<Stat> stats;
    public final Ability ability;

    public MagicWeapon(Material physicalItem, Type type, Rarity rarity, String name, List<Stat> stats, Ability ability) {
        this.physicalItem = physicalItem;
        this.type = type;
        this.rarity = rarity;
        this.name = name;
        this.stats = stats;
        this.ability = ability;
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(physicalItem);
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        assert itemMeta != null;

        itemMeta.setDisplayName(rarity.color + name);


        for (Stat stat : stats) lore.add(ChatColor.GRAY+stat.stat.name+": "+ChatColor.RED+((stat.stat == Stats.GEAR_SCORE)?"":"+")+(int)stat.value);

        lore.add("");

        lore.add(ChatColor.RESET+""+ChatColor.GOLD+"Ability: "+ability.name+" "+ChatColor.YELLOW+ChatColor.BOLD+ability.triggers.get(0).name().replaceFirst("^(.*)_[^_]*$", "$1").replace("_", " "));
        for (String line : ability.description) lore.add(ChatColor.RESET+line);
        lore.add(ChatColor.RESET+""+ChatColor.DARK_GRAY+"Mana Cost: "+ChatColor.DARK_AQUA+(int)ability.manaCost);
        if (ability.cooldown > 0) lore.add(ChatColor.RESET+""+ChatColor.DARK_GRAY+"Cooldown: "+ChatColor.GREEN+(int)ability.cooldown+"s");

        lore.add("");
        lore.add(ChatColor.RESET+""+ChatColor.DARK_GRAY+"This item can be reforged!");
        lore.add(ChatColor.RESET+""+rarity.color+ChatColor.BOLD+rarity.name+" "+type.name);
        itemMeta.setLore(lore);
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return item;
    }
}