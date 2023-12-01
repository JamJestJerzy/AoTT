package dev.j3rzy.aott.enums;

public enum Type {
    SWORD("SWORD"),
    BOW("BOW"),
    WAND("WAND"),
    NOTHING("");

    /**
     * Type's name
     */
    public final String name;

    Type(String name) { this.name = name; }
}