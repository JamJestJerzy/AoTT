package dev.j3rzy.aott.item;

import dev.j3rzy.aott.enums.GemstoneSlots;
import dev.j3rzy.aott.enums.Stats;
import dev.j3rzy.aott.item.Gemstone;
import org.bukkit.ChatColor;

public class GemstoneSlot {
    private final GemstoneSlots slotType;
    private boolean unlocked;
    private Gemstone gemstone = null;

    public GemstoneSlot(GemstoneSlots slotType, boolean unlocked, Gemstone gemstone) {
        this.slotType = slotType;
        this.unlocked = unlocked;
        this.gemstone = gemstone;
    }

    public GemstoneSlots getSlotType() {
        return slotType;
    }

    public Gemstone getGamestone() {
        return gemstone;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void unlock() {
        unlocked = true;
    }

    public void setGamestone(Gemstone gemstone) {
        this.gemstone = gemstone;
    }

    public String getIcon() {
        if (gemstone == null || !unlocked) return ChatColor.DARK_GRAY + "[" + ((unlocked) ? ChatColor.GRAY : ChatColor.DARK_GRAY) + slotType.icon + ChatColor.DARK_GRAY + "]";
        return gemstone.getGemstoneTier().color + "[" + gemstone.getGemestone().color + slotType.icon + gemstone.getGemstoneTier().color + "]";
    }

    public Stat getStatBoost(Stats stat) {
        if (gemstone == null) return null;
        if (gemstone.getGemestone().stat != stat) return null;
        return new Stat(gemstone.getGemestone().stat, gemstone.getGemestone().amount);
    }
}
