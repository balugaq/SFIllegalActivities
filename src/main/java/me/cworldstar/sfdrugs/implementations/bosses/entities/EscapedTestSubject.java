package me.cworldstar.sfdrugs.implementations.bosses.entities;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.bosses.deathsequences.EscapedTestSubjectDeathSequence;
import me.cworldstar.sfdrugs.utils.Speak;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class EscapedTestSubject {
    public EscapedTestSubject(SFDrugs plugin, Zombie z) {
        z.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a&l&k|||&r &2&l⚠ Escaped Corporate Test Subject ⚠&r &a&l&k|||&r"));
        z.setMaxHealth(2000.0);
        z.setHealth(2000.0);
        z.setRemoveWhenFarAway(false);
        z.setAdult();
        z.setMetadata("SFDRUGS_CUSTOM_MOB", new FixedMetadataValue(plugin, "escaped_test_subject"));
        z.setCanPickupItems(false);
        BossBar EnemyBossBar = Bukkit.getServer().createBossBar(ChatColor.translateAlternateColorCodes('&', "&a&l&k|||&r &2&l⚠ Escaped Corporate Test Subject ⚠&r &a&l&k|||&r"), BarColor.GREEN, BarStyle.SEGMENTED_12);
        EnemyBossBar.setVisible(true);
        EnemyBossBar.setProgress(1.0);
        List<Player> Players = new ArrayList<>();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (z.isDead()) {
                    this.cancel();
                }
                if (Double.parseDouble(new DecimalFormat("#.#").format(z.getHealth() / z.getMaxHealth())) <= 0.1) {
                    z.setInvulnerable(true);
                    z.setAI(false);
                    z.setGravity(false);
                    new EscapedTestSubjectDeathSequence(plugin, z);
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 1L);

        ItemStack ZombieHead = SlimefunUtils.getCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWNjNGI1YjdmNjE2OTQyOGNlZTcyZDFkZTNlYjkzNTc5M2I3ZDZmNGI2YzFhMjNjMzRhMjZkZGE2MDYwMWNjOSJ9fX0=");
        ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta BootsMeta = (LeatherArmorMeta) Boots.getItemMeta();
        new Speak(BootsMeta, "&c Red Wolves Vest");
        BootsMeta.setColor(Color.LIME);
        BootsMeta.setUnbreakable(true);
        Boots.setItemMeta(BootsMeta);

        //TODO: Add a "Thorns" effect to EscapedTestSubject and a "Regeneration" effect. Attacks should apply "Decaying" unless player is wearing armored hazmat.
        //TODO: Increase zombie speed and strength. Make it slightly faster than the player and give it a warden-like ranged projectile attack.


        ItemStack Leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta LeggingsMeta = (LeatherArmorMeta) Leggings.getItemMeta();
        new Speak(LeggingsMeta, "&c Red Wolves Jeans");
        LeggingsMeta.setColor(Color.LIME);
        LeggingsMeta.setUnbreakable(true);
        Leggings.setItemMeta(LeggingsMeta);


        ItemStack Chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta ChestplateMeta = (LeatherArmorMeta) Chestplate.getItemMeta();
        new Speak(ChestplateMeta, "&c Red Wolves Vest");
        ChestplateMeta.setColor(Color.LIME);
        ChestplateMeta.setUnbreakable(true);
        Chestplate.setItemMeta(ChestplateMeta);
        z.getEquipment().setArmorContents(new ItemStack[]{
                Boots,
                Leggings,
                Chestplate,
                ZombieHead
        });
    }
}