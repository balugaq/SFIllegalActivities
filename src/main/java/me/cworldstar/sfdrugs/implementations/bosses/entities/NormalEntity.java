package me.cworldstar.sfdrugs.implementations.bosses.entities;

import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class NormalEntity {
    public abstract void applyEntityEdits(Mob b);

    public abstract List<Skill> getSkills();

    public abstract List<String> getIdleDialog();

    public abstract List<String> getAttackingDialog(Player e);
}
