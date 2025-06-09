package me.cworldstar.sfdrugs.implementations.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectionType;
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectiveArmor;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.utils.Constants;
import org.bukkit.Axis;
import org.bukkit.Effect;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

public class RobotArmor extends CustomArmorPiece implements ProtectiveArmor, Radioactive, Rechargeable, DamageableItem {
    public final SFDrugs plugin;

    public RobotArmor(SFDrugs plugin, ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType,
                      ItemStack[] recipe, PotionEffect[] effects) {
        super(plugin, itemGroup, item, recipeType, recipe, effects);
        this.plugin = plugin;
    }

    public static boolean IsNotAffected(LivingEntity Enemy) {
        return (!Enemy.hasMetadata(Constants.AfflictedBySfDrugsRobotArmor));
    }

    @Override
    public ProtectionType[] getProtectionTypes() {
        return new ProtectionType[]{ProtectionType.RADIATION};
    }

    @Override
    public boolean isFullSetRequired() {
        return false;
    }

    public boolean PlayerIsWearingFullSet(Player p) {
        Inventory I = p.getInventory();
        int Wearing = 0;
        for (int i = 5; i <= 8; i++) {
            if (I.getItem(i).getItemMeta().getDisplayName().contains(Constants.CorporateSecurityRobot)) {
                Wearing += 1;
            }
        }
        return (Wearing >= 4);
    }

    @Override
    public NamespacedKey getArmorSetId() {
        // TODO Auto-generated method stub
        return new NamespacedKey(this.plugin, "SFDRUGS_CORPORATE_ROBOT_SET");
    }

    @Override
    public float getMaxItemCharge(ItemStack item) {
        // TODO Auto-generated method stub
        return 19000F;
    }

    public void PlayerDamaged(EntityDamageByEntityEvent e, Player p, ItemStack item, double dmg) {
        if (dmg > 0 & this.getItemCharge(item) - (float) dmg > 0) {
            if (this.removeItemCharge(item, (float) dmg)) {
                p.getWorld().playEffect(p.getLocation(), Effect.ELECTRIC_SPARK, Axis.Y);
                p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 0.4F, 0.5F);
                e.setDamage(dmg / 4);
                p.setMetadata(Constants.AfflictedBySfDrugsRobotArmor, new FixedMetadataValue(this.plugin, true));
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        p.removeMetadata(Constants.AfflictedBySfDrugsRobotArmor, plugin);
                    }
                }.runTaskLater(this.plugin, 20L);
            }
        }
    }

    public void EntityDamaged(EntityDamageByEntityEvent e, Mob p, ItemStack item, double dmg) {
        if (dmg > 0 & this.getItemCharge(item) - (float) dmg > 0) {
            if (this.removeItemCharge(item, (float) dmg)) {
                p.getWorld().playEffect(p.getLocation(), Effect.ELECTRIC_SPARK, Axis.Y);
                p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 0.4F, 0.5F);
                e.setDamage(dmg / 4);
                p.setMetadata(Constants.AfflictedBySfDrugsRobotArmor, new FixedMetadataValue(this.plugin, true));
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        p.removeMetadata(Constants.AfflictedBySfDrugsRobotArmor, plugin);
                    }
                }.runTaskLater(this.plugin, 20L);

            }
        }
    }

    public void ArmorDamaged(PlayerItemDamageEvent e, ItemStack item, int durabilityDamage) {
        if (durabilityDamage > 0 & (this.getItemCharge(item) - (float) durabilityDamage > 0)) {
            if (this.removeItemCharge(item, (float) durabilityDamage)) {
                e.setCancelled(true);
            }
        }
    }

    @Override
    public boolean isDamageable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Radioactivity getRadioactivity() {
        // TODO Auto-generated method stub
        return Radioactivity.VERY_DEADLY;
    }
}