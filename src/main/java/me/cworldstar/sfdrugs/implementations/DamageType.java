package me.cworldstar.sfdrugs.implementations;

import me.cworldstar.sfdrugs.utils.Speak;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public enum DamageType {
    UNSTABLE_OBJECT("&f&l&k|||&r &f&lUnstable Object &k|||&r"),
    LASER_PROJECTILE("&6&l&k|||&r &e&lLaser Projectile &k|||&r"),
    ANTIMATTER_DETONATION("&d&l&k|||&r &5&lAntimatter Detonation&r &d&l&k|||&r");

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
