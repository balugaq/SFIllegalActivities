package me.cworldstar.sfdrugs.utils;

import me.cworldstar.sfdrugs.implementations.items.UnstableObject;
import me.cworldstar.sfdrugs.implementations.items.UnstableObject.Unstable;

public final class LoreHandler {
    private static final Speak Formatter = new Speak();

    public LoreHandler() {
    }

    public static String UnstableObjectCooldownTimer(Unstable unstable) {
        return Speak.format("&f&l" + Texts.cd + " " + UnstableObject.getCooldown(unstable));
    }

    public static String UnstableObjectCooldownTimer(double d) {
        return Speak.format("&f&l" + Texts.cd + " " + d);
    }

    public Speak getFormatter() {
        return LoreHandler.Formatter;
    }
}
