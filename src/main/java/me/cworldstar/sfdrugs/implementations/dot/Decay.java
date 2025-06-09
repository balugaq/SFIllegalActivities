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

public class Decay {
    private static final List<PotionEffect> DecayPotionEffects = new ArrayList<>();

    static {
        Collection<PotionEffect> PotionEffectCollection = new ArrayList<>();
        PotionEffectCollection.add(new PotionEffect(PotionEffectType.WITHER, 120, 2));
        PotionEffectCollection.add(new PotionEffect(PotionEffectType.WEAKNESS, 120, 1));
        PotionEffectCollection.add(new PotionEffect(PotionEffectType.HUNGER, 120, 1));

        DecayPotionEffects.addAll(PotionEffectCollection);
    }

    private boolean ShouldEnd = false;

    /**
     * Takes a LivingEntity p, and the APlugin extension.
     * Causes Decay.
     *
     * @param p
     * @param sfdrugs
     * @author cworldstar
     */
    public Decay(LivingEntity p, SFDrugs sfdrugs) {
        for (PotionEffect potion : Decay.DecayPotionEffects) {
            p.addPotionEffect(potion);
        }
        new Speak(p, "&7&lYou are decaying. Consider running away.");
        new BukkitRunnable() {
            @Override
            public void run() {
                if (ShouldEnd) {
                    this.cancel();
                }
                p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 10, 1.0, 2.0, 1.0);
                for (PotionEffect potion : Decay.DecayPotionEffects) {
                    p.addPotionEffect(potion);
                }
            }
        }.runTaskTimer(sfdrugs, 0, 20);
        new BukkitRunnable() {
            @Override
            public void run() {
                ShouldEnd = true;
                this.cancel();
            }
        }.runTaskLater(sfdrugs, 120);
    }
}
