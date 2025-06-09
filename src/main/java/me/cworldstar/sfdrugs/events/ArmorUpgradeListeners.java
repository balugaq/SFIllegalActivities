package me.cworldstar.sfdrugs.events;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.events.ArmorType;
import me.cworldstar.sfdrugs.implementations.items.armorupgrades.ApplicationType;
import me.cworldstar.sfdrugs.implementations.items.armorupgrades.ArmorUpgrade;
import me.cworldstar.sfdrugs.implementations.items.armorupgrades.ArmorUpgradeType;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class ArmorUpgradeListeners implements Listener {
    public static final List<ArmorUpgrade> ArmorUpgrades = new ArrayList<>();
    public final SFDrugs plugin;

    public ArmorUpgradeListeners(SFDrugs plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onPlayerDamageEvent(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && e.getFinalDamage() > 0) {
            this.plugin.getLogger().log(Level.WARNING, "first");
            for (ItemStack InventoryItem : ((Player) e.getEntity()).getInventory().getArmorContents()) {
                if (InventoryItem != null) {
                    this.plugin.getLogger().log(Level.WARNING, Boolean.toString((ArmorType.matchType(InventoryItem) != null)));
                    this.plugin.getLogger().log(Level.WARNING, Boolean.toString(InventoryItem.hasItemMeta()));
                    this.plugin.getLogger().log(Level.WARNING, Boolean.toString(InventoryItem.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this.plugin, "Upgrade"), PersistentDataType.STRING) != null));
                    if (ArmorType.matchType(InventoryItem) != null && InventoryItem.hasItemMeta() && InventoryItem.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this.plugin, "Upgrade"), PersistentDataType.STRING) != null) {
                        this.plugin.getLogger().log(Level.WARNING, Boolean.toString(InventoryItem.hasItemMeta()));
                        this.plugin.getLogger().log(Level.WARNING, "second");
                        for (ArmorUpgrade i : ArmorUpgrades) {
                            if (i.ArmorUpgradeType == ArmorUpgradeType.HEALTH_DAMAGED && Objects.equals(InventoryItem.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this.plugin, "Upgrade"), PersistentDataType.STRING), i.getClass().getName())) {
                                this.plugin.getLogger().log(Level.WARNING, "third");
                                i.onWearerDamaged(e);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onPlayerClickInventory(InventoryClickEvent e) {
        this.plugin.getLogger().log(Level.WARNING, "right click handled");
        if (!e.getCursor().hasItemMeta()) {
            e.getCursor().setItemMeta(Bukkit.getItemFactory().getItemMeta(e.getCursor().getType()));
        }
        if (e.getCursor().hasItemMeta() && e.getCursor().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this.plugin, "Upgrade"), PersistentDataType.STRING) != null) {
            this.plugin.getLogger().log(Level.WARNING, e.getCursor().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this.plugin, "Upgrade"), PersistentDataType.STRING));
            this.plugin.getLogger().log(Level.WARNING, "applying");
            for (ArmorUpgrade i : ArmorUpgrades) {
                if (i.ArmorUpgradeItem.equals(e.getCursor()) && ArmorType.matchType(e.getCurrentItem()) != null) {
                    switch (ArmorType.matchType(e.getCurrentItem())) {
                        case HELMET, LEGGINGS:
                            if (i.ApplicationType == ApplicationType.HELMET_ONLY || i.ApplicationType == ApplicationType.ANY) {
                                i.apply(e, e.getCursor(), e.getCurrentItem(), this.plugin);
                            }
                            break;
                        case CHESTPLATE:
                            if (i.ApplicationType == ApplicationType.CHESTPLATE_ONLY || i.ApplicationType == ApplicationType.ANY) {
                                i.apply(e, e.getCursor(), e.getCurrentItem(), this.plugin);
                            }
                            break;
                        case BOOTS:
                            if (i.ApplicationType == ApplicationType.BOOTS_ONLY || i.ApplicationType == ApplicationType.ANY) {
                                i.apply(e, e.getCursor(), e.getCurrentItem(), this.plugin);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onPlayerArmorDamageEvent(PlayerItemDamageEvent e) {
        if (ArmorType.matchType(e.getItem()) != null) {
            for (ArmorUpgrade i : ArmorUpgrades) {
                if (i.ArmorUpgradeType == ArmorUpgradeType.ARMOR_DAMAGED && !e.isCancelled() && i.Armor.equals(e.getItem()))
                    i.onArmorDamaged(e);
            }
        }
    }

}
