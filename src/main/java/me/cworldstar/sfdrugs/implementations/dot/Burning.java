package me.cworldstar.sfdrugs.implementations.dot;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.utils.Speak;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Burning {
    private static final List<PotionEffect> PotionEffects = new ArrayList<>();

    static {
        Collection<PotionEffect> PotionEffectCollection = new ArrayList<>();
        PotionEffectCollection.add(new PotionEffect(PotionEffectType.WITHER, 120, 2));
        PotionEffectCollection.add(new PotionEffect(PotionEffectType.GLOWING, 240, 1));
        PotionEffects.addAll(PotionEffectCollection);
    }

    /**
     * Takes a LivingEntity p, and the APlugin extension.
     * Causes Disintegration.
     *
     * @param p
     * @param sfdrugs
     * @author cworldstar
     */
    public Burning(LivingEntity p, SFDrugs sfdrugs) {
        for (PotionEffect potion : Burning.PotionEffects) {
            p.addPotionEffect(potion);
        }
        p.setFireTicks(60);
        new Speak(p, "&e&lYou are disintegrating. Consider running away.");
        new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (ticks >= 240) {
                    this.cancel();
                }
                p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 10, 1.0, 2.0, 1.0);
                for (PotionEffect potion : Burning.PotionEffects) {
                    p.addPotionEffect(potion);
                }
                ticks += 20;
            }
        }.runTaskTimer(sfdrugs, 0, 20);
    }
}
