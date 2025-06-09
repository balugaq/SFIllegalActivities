package me.cworldstar.sfdrugs.implementations.bosses.entities;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.loot.SmallerGangMemberLootTable;
import me.cworldstar.sfdrugs.utils.Speak;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SmallerGangMember {
    @SuppressWarnings("deprecation")
    public SmallerGangMember(SFDrugs plugin, Zombie z) {
        z.setCustomName(ChatColor.translateAlternateColorCodes('&', "&c&lRed Wolves Trainee"));
        z.setMaxHealth(100.0);
        z.setHealth(100.0);
        z.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 2));
        z.setAbsorptionAmount(25);
        z.setAdult();
        z.setCanPickupItems(false);
        z.setMetadata("SFDRUGS_CUSTOM_MOB", new FixedMetadataValue(plugin, "red_wolves_trainee"));
        z.setLootTable(new SmallerGangMemberLootTable(plugin));
        ItemStack ZombieHead = SlimefunUtils.getCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjgxOWM5OTY2ZmQ4OWI0YjM4MTJlMmRmMTdkODk4NzliZTVmMjNmZGU5ZmQ3MTQ2NWQzZjAwMjM2ZGJkMjZmOCJ9fX0=");
        ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta BootsMeta = (LeatherArmorMeta) Boots.getItemMeta();
        new Speak(BootsMeta, "&c Red Wolves Boots");
        BootsMeta.setColor(Color.RED);
        BootsMeta.setUnbreakable(true);
        Boots.setItemMeta(BootsMeta);
        ItemStack Leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta LeggingsMeta = (LeatherArmorMeta) Leggings.getItemMeta();
        new Speak(LeggingsMeta, "&c Red Wolves Jeans");
        LeggingsMeta.setColor(Color.RED);
        LeggingsMeta.setUnbreakable(true);
        Leggings.setItemMeta(LeggingsMeta);
        ItemStack Chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta ChestplateMeta = (LeatherArmorMeta) Chestplate.getItemMeta();
        new Speak(ChestplateMeta, "&c Red Wolves Vest");
        ChestplateMeta.setColor(Color.RED);
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
