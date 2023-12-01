package dev.j3rzy.aott.enums;

public enum GemstoneSlots {
    RUBY("\u2764"),
    AMETHYST("\u2748"),
    JADE("\u2618"),
    SAPPHIRE("\u270e"),
    AMBER("\u2E15"),
    TOPAZ("\u2727"),
    JASPER("\u2741"),

    COMBAT("\u2694"),
    OFFENSIVE("\u2620"),
    DEFFENSIVE("\u2624"),
    MINING("\u2726"),
    UNIVERSAL("\u2742");

    /**
     * Icon gamestone slot have
     */
    public final String icon;

    GemstoneSlots(String icon) {
        this.icon = icon;
    }
}
