package me.cworldstar.sfdrugs.utils;

import me.cworldstar.sfdrugs.SFDrugs;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Speak {
    public static SFDrugs plugin;
    public static NamespacedKey key;

    public Speak(SFDrugs plugin) {
        Speak.plugin = plugin;
    }

    public Speak(Player p, String text) {
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_VILLAGER_TRADE, 0.6F, 0.2F);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    }

    public Speak(Entity Origin, List<Entity> Entities, String text) {
        for (Entity e : Entities) {
            Origin.getWorld().playSound(Origin.getLocation(), Sound.ENTITY_VILLAGER_TRADE, 0.6F, 0.2F);
            e.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
        }
    }

    public Speak(Entity Origin, String text, int Surrounding) {
        for (Entity e : Origin.getNearbyEntities(Surrounding, 100, Surrounding)) {
            Origin.getWorld().playSound(Origin.getLocation(), Sound.ENTITY_VILLAGER_TRADE, 0.6F, 0.2F);
            e.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
        }
    }

    public Speak(LivingEntity e, String text) {
        e.getWorld().playSound(e.getLocation(), Sound.ENTITY_VILLAGER_TRADE, 0.6F, 0.2F);
        e.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    }

    public Speak(ItemMeta i, String text) {
        i.setDisplayName(text);
    }

    public Speak() {
    }

    public static String format(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static NamespacedKey NamespacedKey(String str) {
        return new NamespacedKey(plugin, str);
    }

    public static void AOEMessage(Entity Origin, String text, int Surrounding) {
        // TODO Auto-generated method stub
        new Speak(Origin, text, Surrounding);
    }
}
