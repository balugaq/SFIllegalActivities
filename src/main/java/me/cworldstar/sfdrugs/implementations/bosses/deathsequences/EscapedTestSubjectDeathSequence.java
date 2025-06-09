package me.cworldstar.sfdrugs.implementations.bosses.deathsequences;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.DamageType;
import me.cworldstar.sfdrugs.utils.Speak;
import me.cworldstar.sfdrugs.utils.Texts;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class EscapedTestSubjectDeathSequence {
    public EscapedTestSubjectDeathSequence(SFDrugs plugin, LivingEntity mob) {
        for (Entity e : mob.getNearbyEntities(20, 20, 20)) {
            if (e instanceof LivingEntity) {
                new Speak(mob, mob.getNearbyEntities(20, 20, 20), Texts.etsds_1);
                new Speak(mob, mob.getNearbyEntities(20, 20, 20), Texts.etsds_2);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (mob.isDead()) {
                            this.cancel();
                        }
                        if (mob.getLocation().getYaw() < 360 && !this.isCancelled()) {
                            mob.setRotation(mob.getLocation().getYaw() + 1.0F, mob.getLocation().getPitch());
                        } else {
                            mob.setRotation(0.0F, mob.getLocation().getPitch());
                        }
                    }
                }.runTaskTimer(plugin, 0, 2L); // Rotate
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        mob.getWorld().createExplosion(mob.getLocation(), 20.0F, true, true, DamageType.ANTIMATTER_DETONATION.damager(mob));
                        for (Entity e2 : mob.getNearbyEntities(7.0, 7.0, 7.0)) {
                            if (e2 instanceof LivingEntity) {
                                ((LivingEntity) e2).damage(50.0, DamageType.ANTIMATTER_DETONATION.damager((LivingEntity) e2));
                                mob.setInvulnerable(false);
                                mob.remove();
                            }
                        }
                    }
                }.runTaskLater(plugin, 100L); // 5 seconds
            }
        }

    }
}
