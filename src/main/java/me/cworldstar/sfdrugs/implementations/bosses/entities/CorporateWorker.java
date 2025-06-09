package me.cworldstar.sfdrugs.implementations.bosses.entities;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.loot.SmallerGangMemberLootTable;
import me.cworldstar.sfdrugs.utils.Constants;
import me.cworldstar.sfdrugs.utils.RandomUtils;
import me.cworldstar.sfdrugs.utils.Speak;
import me.cworldstar.sfdrugs.utils.Texts;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CorporateWorker {
    public CorporateWorker(SFDrugs plugin, Zombie z) {
        z.setCustomName(ChatColor.translateAlternateColorCodes('&', Texts.cw_1));
        z.setMaxHealth(75.0);
        z.setHealth(75.0);
        z.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999, 0));
        z.setAbsorptionAmount(100);
        z.setAdult();
        z.setCanPickupItems(false);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (z.isDead()) {
                    this.cancel();
                } else if (z.getTarget() == null) {
                    new Speak(z, z.getNearbyEntities(20, 20, 20), CorporateWorker.randomDialog());
                }
            }
        }.runTaskTimer(plugin, 0, 200L);
        z.setMetadata(Constants.SfDrugsCustomMob, new FixedMetadataValue(plugin, Constants.corporate_worker));
        z.setLootTable(new SmallerGangMemberLootTable(plugin));
        ItemStack ZombieHead = SlimefunUtils.getCustomHead(Constants.cw_head_1);
        ItemStack Boots = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemMeta BootsMeta = Boots.getItemMeta();
        BootsMeta.setUnbreakable(true);
        Boots.setItemMeta(BootsMeta);
        ItemStack Leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemMeta LeggingsMeta = Leggings.getItemMeta();
        LeggingsMeta.setUnbreakable(true);
        Leggings.setItemMeta(LeggingsMeta);
        ItemStack Chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta ChestplateMeta = Chestplate.getItemMeta();
        ChestplateMeta.setUnbreakable(true);
        Chestplate.setItemMeta(ChestplateMeta);
        z.getEquipment().setArmorContents(new ItemStack[]{
                Boots,
                Leggings,
                Chestplate,
                ZombieHead
        });
    }

    private static String randomDialog() {
        String[] list = new String[]{
                Texts.cw_2,
                Texts.cw_3,
                Texts.cw_4,
                Texts.cw_5
        };
        List<String> dialogs = new ArrayList<>(Arrays.asList(list));
        if (dialogs.get(RandomUtils.nextInt(dialogs.size() - 1)) != null) {
            return Texts.cw_6.concat(dialogs.get(RandomUtils.nextInt(dialogs.size() - 1)));
        }
        return Texts.error_cw_1;
    }
}
