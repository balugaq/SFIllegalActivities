package me.cworldstar.sfdrugs.implementations.items;


import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectionType;
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectiveArmor;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.implementation.items.armor.SlimefunArmorPiece;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.utils.Constants;
import me.cworldstar.sfdrugs.utils.Speak;
import me.cworldstar.sfdrugs.utils.Texts;
import org.bukkit.Axis;
import org.bukkit.Effect;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.text.MessageFormat;

public class DrugSuit extends SlimefunArmorPiece implements ProtectiveArmor, Rechargeable {
    public final SFDrugs plugin;

    public DrugSuit(SFDrugs plugin, ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType,
                    ItemStack[] recipe, PotionEffect[] effects) {
        super(itemGroup, item, recipeType, recipe, effects);
        this.plugin = plugin;
    }

    @Override
    public ProtectionType[] getProtectionTypes() {
        return new ProtectionType[]{ProtectionType.RADIATION};
    }

    @Override
    public boolean isFullSetRequired() {
        return true;
    }

    public boolean PlayerIsWearingFullSet(Player p) {
        Inventory I = p.getInventory();
        int Wearing = 0;
        for (int i = 5; i <= 8; i++) {
            if (I.getItem(i).getItemMeta().getDisplayName().contains(Texts.CorporateHazmat)) {
                Wearing += 1;
            }
        }
        return (Wearing >= 4);
    }

    @Override
    public NamespacedKey getArmorSetId() {
        // TODO Auto-generated method stub
        return new NamespacedKey(this.plugin, Constants.SfDrugsDrugSet);
    }

    @Override
    public float getMaxItemCharge(ItemStack item) {
        // TODO Auto-generated method stub
        return 1200F;
    }

    public boolean Damage(PlayerItemDamageEvent e, ItemStack Item, int dmg) {
        if (dmg > 0 & this.getItemCharge(Item) - (float) dmg > 0) {
            if (this.removeItemCharge(Item, (float) dmg)) {
                e.setDamage(0);
                return true;
            }
        }
        return false;
    }

    public void PlayerDamaged(EntityDamageByEntityEvent e, Player p, ItemStack item, double dmg) {
        if (dmg > 0 & this.getItemCharge(item) - (float) dmg > 0) {
            if (this.removeItemCharge(item, (float) dmg)) {
                p.getWorld().playEffect(p.getLocation(), Effect.ELECTRIC_SPARK, Axis.Y);
                p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 0.4F, 0.5F);
                new Speak(p, MessageFormat.format(Texts.ds_1, dmg / 2));
                e.setDamage(dmg / 2);
            }
        }
    }
}
