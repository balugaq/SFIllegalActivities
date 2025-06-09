package me.cworldstar.sfdrugs.implementations.items.armorupgrades;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.events.ArmorUpgradeListeners;
import me.cworldstar.sfdrugs.utils.Speak;
import me.cworldstar.sfdrugs.utils.Texts;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public abstract class ArmorUpgrade {
    public ItemStack Armor;
    public ApplicationType ApplicationType;
    public SFDrugs plugin;
    public int internalLoreLine;
    public ArmorUpgradeType ArmorUpgradeType;
    public ItemStack ArmorUpgradeItem;

    public static boolean validateArmorUpgrade(SFDrugs p, ItemStack i) {
        return (i.hasItemMeta() && i.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(p, "Upgrade"), PersistentDataType.STRING));
    }

    public static ArmorUpgrade fromItemStack(ItemStack i) {
        for (ArmorUpgrade au : ArmorUpgradeListeners.ArmorUpgrades) {
            if (au.ArmorUpgradeItem.equals(i)) {
                return au;
            }
        }
        return null;
    }

    public abstract void register();

    public void reapply(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        for (ItemStack i : p.getInventory().getArmorContents()) {

            if (ArmorUpgrade.validateArmorUpgrade(this.plugin, i)) {

                this.applySilently(this, i);

            }

        }

        // this should be called on player join.


    }

    public void applySilently(ArmorUpgrade Z, ItemStack armorPiece) {

        this.Armor = armorPiece;

    }

    public void apply(InventoryClickEvent e, ItemStack CircuitToApply, ItemStack ArmorPiece, SFDrugs plugin) {
        CircuitToApply.setAmount(0);
        ItemMeta a = ArmorPiece.getItemMeta();
        if (a.hasLore()) {
            List<String> oldLore = a.getLore();
            oldLore.add("");
            oldLore.addAll(this.getLoreText());
            this.internalLoreLine = oldLore.size() - 1;
        } else {
            List<String> oldLore = new ArrayList<>();
            oldLore.add("");
            oldLore.addAll(this.getLoreText());
            this.internalLoreLine = oldLore.size() - 1;
            a.setLore(oldLore);
        }
        ArmorPiece.setItemMeta(a);
        this.Armor = ArmorPiece;

        this.onUpgradeApplied(ArmorPiece, ArmorPiece.getItemMeta().getPersistentDataContainer());
        Player p = (Player) e.getWhoClicked();
        p.sendTitle(Speak.format(Texts.au_1), Speak.format(Texts.au_2), 10, 70, 20);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 2.0F, 2.0F);
    }

    public ItemStack getItemStack() {
        return this.ArmorUpgradeItem;
    }

    public abstract List<String> getLoreText();

    public abstract void onUpgradeApplied(ItemStack Armor, PersistentDataContainer e);

    public abstract void onWearerDamaged(EntityDamageEvent e);

    public abstract void onArmorDamaged(PlayerItemDamageEvent e);

    public abstract void update(ItemStack armor, Object updateText);
}
