package dev.j3rzy.aott.items;

import dev.j3rzy.aott.item.Item;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Items {
    public static Items INSTANCE = new Items();
    private final List<Item> items = new ArrayList<>();

    public Items() {
        addItems();
    }

    private void addItems() {
        items.add(new AspectOfTheEnd());
        items.add(new Fish());
        items.add(new Hyperion());
        items.add(new Stick());
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getItem(Item item) {
        for (Item i : items) if (i == item) return i;
        return null;
    }

    public Item getItem(String name) {
        for (Item i : items) if (Objects.equals(i.getName(), name)) return i;
        return null;
    }

    public Item getItem(List<String> description) {
        for (Item i : items) if (Objects.equals(i.getDescription(), description)) return i;
        return null;
    }

    public Item getItem(ItemStack itemStack) {
        for (Item i : items) if (itemStack.isSimilar(i.getItem())) return i;
        return null;
    }
}
