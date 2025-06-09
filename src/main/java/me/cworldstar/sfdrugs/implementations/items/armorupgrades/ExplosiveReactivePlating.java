package me.cworldstar.sfdrugs.implementations.items.armorupgrades;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.events.ArmorUpgradeListeners;
import me.cworldstar.sfdrugs.utils.Speak;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ExplosiveReactivePlating extends ArmorUpgrade {

    public ExplosiveReactivePlating(SFDrugs plugin) {
        ItemStack L = new ItemStack(Material.DIAMOND, 1);
        ItemMeta x = L.getItemMeta();
        x.setDisplayName(Speak.format("&6Explosive-Reactive Plating Upgrade"));
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Speak.format("&f> &fThis is a &aCircuit&r&f Upgrade! You must"));
        lore.add(Speak.format("&f> use this on an &eElectric&r&f armor piece."));
        lore.add(Speak.format(""));
        lore.add(Speak.format("&f> &cChestplate Only!"));
        x.setLore(lore);
        x.getPersistentDataContainer().set(new NamespacedKey(plugin, "Upgrade"), PersistentDataType.STRING, "11");
        L.setItemMeta(x);
        this.ArmorUpgradeItem = L;
        this.plugin = plugin;
        this.ArmorUpgradeType = me.cworldstar.sfdrugs.implementations.items.armorupgrades.ArmorUpgradeType.HEALTH_DAMAGED;
        this.ApplicationType = me.cworldstar.sfdrugs.implementations.items.armorupgrades.ApplicationType.CHESTPLATE_ONLY;
        this.register();
    }

    @Override
    public void update(ItemStack armor, Object updateText) {
        ItemMeta x = armor.getItemMeta();
        this.plugin.getLogger().warning("updating");
        this.plugin.getLogger().warning(((Integer) updateText).toString());
        this.plugin.getLogger().warning(this.getLoreText().get(this.getLoreText().size() - 1));
        List<String> lore = x.getLore();
        lore.set(this.internalLoreLine, String.format(this.getLoreText().get(this.getLoreText().size() - 1), updateText));
        x.setLore(lore);
        armor.setItemMeta(x);
    }

    @Override
    public void onWearerDamaged(EntityDamageEvent e) {
        PersistentDataContainer DataContainer = this.Armor.getItemMeta().getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(this.plugin, "Charges");
        int charges = DataContainer.get(key, PersistentDataType.INTEGER);
        if (charges > 0) {
            e.setDamage(0);
            e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 10.0F, false, false);
            Damageable d = ((Damageable) this.Armor.getItemMeta());
            d.setDamage(d.getDamage() - (d.getDamage() / 32));
            DataContainer.set(key, PersistentDataType.INTEGER, charges - 1);
            this.update(this.Armor, charges);
            e.setCancelled(true);
        }
    }

    @Override
    public List<String> getLoreText() {
        List<String> Lore = new ArrayList<>();
        Lore.add(Speak.format("&f> &6Explosive-Reactive Plating Applied."));
        Lore.add(Speak.format("&f> &6Charges: &7%d&f / &832"));
        return Lore;
    }

    @Override
    public void onArmorDamaged(PlayerItemDamageEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgradeApplied(ItemStack Armor, PersistentDataContainer e) {
        // TODO Auto-generated method stub
        this.plugin.getLogger().warning("onUpgradeApplied");
        ArmorUpgradeListeners.ArmorUpgrades.add(this);
        PersistentDataContainer DataContainer = e;
        DataContainer.set(new NamespacedKey(this.plugin, "Upgrade"), PersistentDataType.STRING, this.getClass().getName());
        DataContainer.set(new NamespacedKey(this.plugin, "Charges"), PersistentDataType.INTEGER, 32);
        this.update(Armor, 32);
        this.Armor = Armor;
    }

    @Override
    public void register() {
        ArmorUpgradeListeners.ArmorUpgrades.add(this);
    }
}


