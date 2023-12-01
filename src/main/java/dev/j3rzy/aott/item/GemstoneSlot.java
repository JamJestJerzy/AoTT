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

    /**
     *
     * @return slot type (e.g. COMBAT)
     */
    public GemstoneSlots getSlotType() {
        return slotType;
    }

    /**
     *
     * @return gamestone applied in slot
     */
    public Gemstone getGamestone() {
        return gemstone;
    }

    /**
     *
     * @return whatever slot is unlocked or not
     */
    public boolean isUnlocked() {
        return unlocked;
    }

    /**
     * Unlocks slot
     */
    public void unlock() {
        unlocked = true;
    }

    /**
     * Puts given gemstone in slot
     * @param gemstone gemstone to put in slot
     */
    public void setGamestone(Gemstone gemstone) {
        this.gemstone = gemstone;
    }

    /**
     *
     * @return slot's icon to use in item lore
     */
    public String getIcon() {
        if (gemstone == null || !unlocked) return ChatColor.DARK_GRAY + "[" + ((unlocked) ? ChatColor.GRAY : ChatColor.DARK_GRAY) + slotType.icon + ChatColor.DARK_GRAY + "]";
        return gemstone.getGemstoneTier().color + "[" + gemstone.getGemestone().color + slotType.icon + gemstone.getGemstoneTier().color + "]";
    }

    /**
     *
     * @param stat stat to get boost of
     * @return Stat including Stat and amount
     */
    public Stat getStatBoost(Stats stat) {
        if (gemstone == null) return null;
        if (gemstone.getGemestone().stat != stat) return null;
        return new Stat(gemstone.getGemestone().stat, gemstone.getGemestone().amount);
    }
}
