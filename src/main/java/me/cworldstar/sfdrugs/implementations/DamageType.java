package me.cworldstar.sfdrugs.implementations;

import me.cworldstar.sfdrugs.utils.Speak;
import me.cworldstar.sfdrugs.utils.Texts;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public enum DamageType {
    UNSTABLE_OBJECT(Texts.dt_1),
    LASER_PROJECTILE(Texts.dt_2),
    ANTIMATTER_DETONATION(Texts.dt_3);

    private final String DAMAGETYPE_DISPLAY_NAME;

    DamageType(String name) {
        this.DAMAGETYPE_DISPLAY_NAME = Speak.format(name);

    }

    public Entity damager(LivingEntity toDamage) {
        World thisWorld = toDamage.getWorld();
        ArmorStand thisDamager = ((ArmorStand) thisWorld.spawnEntity(toDamage.getLocation().add(new Location(thisWorld, 0, 20, 0)), EntityType.ARMOR_STAND));
        thisDamager.setCustomName(Speak.format(this.DAMAGETYPE_DISPLAY_NAME));
        thisDamager.setVisible(false);
        thisDamager.setCustomNameVisible(false);
        thisDamager.setAI(false);
        thisDamager.setCanPickupItems(false);
        thisDamager.setGravity(false);

        return thisDamager;
    }

}
