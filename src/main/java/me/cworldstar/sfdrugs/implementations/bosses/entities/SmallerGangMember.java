package me.cworldstar.sfdrugs.implementations.bosses.entities;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.loot.SmallerGangMemberLootTable;
import me.cworldstar.sfdrugs.utils.Constants;
import me.cworldstar.sfdrugs.utils.Speak;
import me.cworldstar.sfdrugs.utils.Texts;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static me.cworldstar.sfdrugs.utils.Texts.*;

public class SmallerGangMember {
    public SmallerGangMember(SFDrugs plugin, Zombie z) {
        z.setCustomName(ChatColor.translateAlternateColorCodes('&', Texts.sgm_1));
        z.setMaxHealth(100.0);
        z.setHealth(100.0);
        z.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 2));
        z.setAbsorptionAmount(25);
        z.setAdult();
        z.setCanPickupItems(false);
        z.setMetadata(Constants.SfDrugsCustomMob, new FixedMetadataValue(plugin, Constants.red_wolves_trainee));
        z.setLootTable(new SmallerGangMemberLootTable(plugin));
        ItemStack ZombieHead = SlimefunUtils.getCustomHead(Constants.sgm_head_1);
        ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta BootsMeta = (LeatherArmorMeta) Boots.getItemMeta();
        new Speak(BootsMeta, sgm_2);
        BootsMeta.setColor(Color.RED);
        BootsMeta.setUnbreakable(true);
        Boots.setItemMeta(BootsMeta);
        ItemStack Leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta LeggingsMeta = (LeatherArmorMeta) Leggings.getItemMeta();
        new Speak(LeggingsMeta, ets_3_gm_3_sgm_3);
        LeggingsMeta.setColor(Color.RED);
        LeggingsMeta.setUnbreakable(true);
        Leggings.setItemMeta(LeggingsMeta);
        ItemStack Chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta ChestplateMeta = (LeatherArmorMeta) Chestplate.getItemMeta();
        new Speak(ChestplateMeta, ets_2_gm_2_sgm_4);
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
