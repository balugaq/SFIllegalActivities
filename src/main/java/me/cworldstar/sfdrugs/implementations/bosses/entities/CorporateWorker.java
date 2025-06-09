package me.cworldstar.sfdrugs.implementations.bosses.entities;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.loot.SmallerGangMemberLootTable;
import me.cworldstar.sfdrugs.utils.RandomUtils;
import me.cworldstar.sfdrugs.utils.Speak;
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
        z.setCustomName(ChatColor.translateAlternateColorCodes('&', "&7Corporate Worker"));
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
        z.setMetadata("SFDRUGS_CUSTOM_MOB", new FixedMetadataValue(plugin, "corporate_worker"));
        z.setLootTable(new SmallerGangMemberLootTable(plugin));
        ItemStack ZombieHead = SlimefunUtils.getCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjgxOWM5OTY2ZmQ4OWI0YjM4MTJlMmRmMTdkODk4NzliZTVmMjNmZGU5ZmQ3MTQ2NWQzZjAwMjM2ZGJkMjZmOCJ9fX0=");
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
        // TODO: Create "Personalities" with different dialog.

        String[] list = new String[]{
                "&7Work all day, die at night.",
                "&7I'll pay my soul off eventually.",
                "&7There's no hope.",
                "&7I can't remember the last time I had a break..."
        };
        List<String> dialogs = new ArrayList<>(Arrays.asList(list));
        if (dialogs.get(RandomUtils.nextInt(dialogs.size() - 1)) != null) {
            return "&7[ Corporate Worker ]:&r ".concat(dialogs.get(RandomUtils.nextInt(dialogs.size() - 1)));
        }
        return "&cSomething went wrong. If you see this, please report it.";
    }
}
