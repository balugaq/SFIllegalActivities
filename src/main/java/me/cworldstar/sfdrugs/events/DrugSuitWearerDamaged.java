package me.cworldstar.sfdrugs.events;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.items.DrugSuit;
import me.cworldstar.sfdrugs.utils.Texts;
import org.bukkit.Effect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DrugSuitWearerDamaged implements Listener {
    private final SFDrugs plugin;

    public DrugSuitWearerDamaged(SFDrugs plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onEntityDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player p) {
            ItemStack item = p.getInventory().getChestplate();
            if (SlimefunItem.getByItem(item) != null) {
                if (item.getItemMeta().getDisplayName().contains(Texts.CorporateHazmat)) {
                    DrugSuit T = (DrugSuit) SlimefunItem.getByItem(item);
                    T.PlayerDamaged(e, p, item, e.getFinalDamage() * 10);
                    for (Entity enemies : p.getNearbyEntities(3.0, 3.0, 3.0)) {
                        if (enemies instanceof LivingEntity) {
                            enemies.getWorld().playEffect(enemies.getLocation(), Effect.BONE_MEAL_USE, 12);
                            ((LivingEntity) enemies).damage(e.getDamage() / 2, p);
                        }
                    }
                }
            }
        }

    }
}
