package me.cworldstar.sfdrugs.events;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.items.RobotArmor;
import me.cworldstar.sfdrugs.implementations.items.RobotArmorSet;
import me.cworldstar.sfdrugs.utils.Constants;
import org.bukkit.Effect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class RobotArmorDamaged implements Listener {
    private final SFDrugs plugin;

    public RobotArmorDamaged(SFDrugs plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private void HandleZombie(EntityDamageByEntityEvent e, Mob p) {
        /**
         *
         * New Implementation of RobotArmorSet.
         * Still bugs w/ security robot.
         *
         * @author cworldstar
         */
        if (RobotArmorSet.WearingMostArmorSet(p)) {
            RobotArmorSet.RemoveSetItemCharge(RobotArmorSet.ToRobotArmor(p.getEquipment().getArmorContents()), e.getDamage(), e);
            for (ItemStack ArmorPiece : p.getEquipment().getArmorContents()) {
                if (SlimefunItem.getByItem(ArmorPiece) instanceof RobotArmor) {
                    ((RobotArmor) SlimefunItem.getByItem(ArmorPiece)).EntityDamaged(e, p, ArmorPiece, e.getFinalDamage());
                    for (Entity enemies : p.getNearbyEntities(3.0, 3.0, 3.0)) {
                        if (enemies instanceof LivingEntity) {
                            if (RobotArmor.IsNotAffected((LivingEntity) enemies) && (!enemies.equals(p))) {
                                enemies.getWorld().playEffect(enemies.getLocation(), Effect.BONE_MEAL_USE, 12);
                                ((LivingEntity) enemies).damage(e.getDamage() / 2, p);
                                if (!enemies.hasMetadata(Constants.AfflictedBySfDrugsRobotArmor)) {
                                    enemies.setMetadata(Constants.AfflictedBySfDrugsRobotArmor, new FixedMetadataValue(plugin, true));
                                    new BukkitRunnable() {

                                        @Override
                                        public void run() {
                                            // TODO Auto-generated method stub
                                            enemies.removeMetadata(Constants.AfflictedBySfDrugsRobotArmor, plugin);
                                        }

                                    }.runTaskLater(plugin, 60L);
                                }
                            }
                        }
                    }
                    break;
                }
            }

        }
    }

    private void HandlePlayer(EntityDamageByEntityEvent e, Player p) {
        if (p.getEquipment().getChestplate() != null) {
            if (RobotArmorSet.WearingMostArmorSet(p)) {
                RobotArmorSet.RemoveSetItemCharge(RobotArmorSet.ToRobotArmor(p.getInventory().getArmorContents()), e.getDamage(), e);
                for (ItemStack ArmorPiece : p.getEquipment().getArmorContents()) {
                    if (SlimefunItem.getByItem(ArmorPiece) instanceof RobotArmor) {
                        ((RobotArmor) SlimefunItem.getByItem(ArmorPiece)).PlayerDamaged(e, p, ArmorPiece, e.getFinalDamage());
                        for (Entity enemies : p.getNearbyEntities(3.0, 3.0, 3.0)) {
                            if (enemies instanceof LivingEntity) {
                                if (RobotArmor.IsNotAffected((LivingEntity) enemies) && (!enemies.equals(p))) {
                                    enemies.getWorld().playEffect(enemies.getLocation(), Effect.BONE_MEAL_USE, 12);
                                    ((LivingEntity) enemies).damage(e.getDamage() / 2, p);
                                }
                            }
                        }
                        break;
                    }
                }

            }
        }
    }

    @EventHandler
    private void onEntityDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() != null) {
            if (!e.getEntity().hasMetadata(Constants.AfflictedBySfDrugsRobotArmor) && (e.getEntity() instanceof Player || e.getEntity() instanceof Mob)) {
                if (e.getEntity() instanceof Player p) {
                    this.HandlePlayer(e, p);
                } else {
                    Mob p = (Mob) e.getEntity();
                    this.HandleZombie(e, p);
                }

            }
        }
    }

    @EventHandler
    private void onPlayerItemDamage(PlayerItemDamageEvent e) {
        ItemStack item = e.getItem();
        if (SlimefunItem.getByItem(item) != null) {
            if (item.getItemMeta().getDisplayName().contains(Constants.CorporateSecurityRobot)) {
                RobotArmor T = (RobotArmor) SlimefunItem.getByItem(item);
                T.ArmorDamaged(e, item, e.getDamage());
            }
        }

    }
}
