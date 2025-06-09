package me.cworldstar.sfdrugs.implementations.items.armorsets;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class AArmorSet implements Listener {
    public static List<AArmorPiece> ArmorPieces = new ArrayList<>();
    public static List<PotionEffect> PotionEffects = new ArrayList<>();

    public AArmorSet(ItemStack[] ArmorPieces) {

    }

    public boolean PlayerIsWearingFullSet(Player p) {
        Inventory I = p.getInventory();
        int Wearing = 0;
        for (int i = 5; i <= 8; i++) {
            SlimefunItem IsItemArmorPiece = SlimefunItem.getByItem(I.getItem(i));
            if (IsItemArmorPiece instanceof AArmorPiece) {
                Wearing += 1;
            }
        }
        return (Wearing >= 4);
    }


}
