package me.cworldstar.sfdrugs.events;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.DamageType;
import me.cworldstar.sfdrugs.implementations.dot.Burning;
import me.cworldstar.sfdrugs.implementations.dot.Decay;
import me.cworldstar.sfdrugs.utils.Constants;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class LaserProjectileHit implements Listener {
    private final SFDrugs plugin;

    public LaserProjectileHit(SFDrugs plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onProjectileHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Projectile & e.getDamager().hasMetadata(Constants.SfDrugsIsLaserProjectile)) {
            if (e.getEntity() instanceof LivingEntity Entity) {
                Entity.damage(10, DamageType.LASER_PROJECTILE.damager(Entity));
                new Decay(Entity, plugin);
            }
        }
    }

    @EventHandler
    public void onProjectileHitBlock(ProjectileHitEvent e) {
        if (e.getEntity().hasMetadata(Constants.SfDrugsIsLaserProjectile) && e.getHitBlock() != null) {
            e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), e.getEntity().getMetadata(Constants.SfDrugsIsLaserProjectile).get(0).asFloat(), true, true);
            for (Entity Entities : e.getEntity().getNearbyEntities(2, 2, 2)) {
                if (Entities instanceof LivingEntity) {
                    ((LivingEntity) Entities).damage(8, DamageType.LASER_PROJECTILE.damager((LivingEntity) Entities));
                    new Burning((LivingEntity) Entities, plugin);
                }
            }
        }
    }
}
