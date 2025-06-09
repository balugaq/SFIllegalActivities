package me.cworldstar.sfdrugs.implementations.bosses.entities;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.utils.RandomUtils;
import me.cworldstar.sfdrugs.utils.Speak;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Sundowner extends NormalEntity {

    public Sundowner(SFDrugs plugin, Mob b) {
        Sundowner x = this;
        this.applyEntityEdits(b);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (b.isDead()) {
                    this.cancel();
                } else if (b.getTarget() == null) {
                    new Speak(b, b.getNearbyEntities(20, 20, 20), RandomUtils.selectRandom(x.getIdleDialog()));
                } else if (b.getTarget() != null && b.getTarget() instanceof Player) {
                    new Speak(b, b.getNearbyEntities(20, 20, 20), RandomUtils.selectRandom(x.getAttackingDialog((Player) b.getTarget())));

                }
            }
        }.runTaskTimer(plugin, 0, 200L);
    }

    @Override
    public void applyEntityEdits(Mob b) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Skill> getSkills() {
        // TODO Auto-generated method stub
        List<Skill> Skills = new ArrayList<>();

        return Skills;
    }

    @Override
    public List<String> getIdleDialog() {
        // TODO Auto-generated method stub
        List<String> IdleDialog = new ArrayList<>();
        IdleDialog.add(null);

        return null;
    }

    @Override
    public List<String> getAttackingDialog(Player e) {
        // TODO Auto-generated method stub
        List<String> AttackingDialog = new ArrayList<>();
        AttackingDialog.add(Speak.format("&8&l[ &e&lSundowner &8&l ]:&r &cKids are cruel, ".concat(e.getDisplayName()).concat(". And I'm very much in touch with my inner child!")));
        AttackingDialog.add(Speak.format("&8&l[ &e&lSundowner &8&l ]:&r &cI'm fucking invincible!"));
        return null;
    }

}
