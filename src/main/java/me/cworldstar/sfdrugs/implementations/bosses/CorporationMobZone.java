package me.cworldstar.sfdrugs.implementations.bosses;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.bosses.entities.CorporationEnemy;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

import java.util.ArrayList;
import java.util.Random;

public class CorporationMobZone {

    public ArrayList<Entity> CorporationEnemies;

    public CorporationMobZone(SFDrugs p, World world, Location location) {
        for (int i = 0; i < 2; i++) {
            Zombie z = (Zombie) world.spawnEntity(getRandomSafeLocation(location, 0), EntityType.ZOMBIE);
            new CorporationEnemy(p, z);
        }
    }

    private Location getRandomSafeLocation(Location location, int Iterations) {
        int x = new Random().nextInt(6);
        int y = new Random().nextInt(6);
        int z = new Random().nextInt(6);

        Location newLocation = location.add(x, y, z);
        if (newLocation.getBlock().getBlockData().getMaterial() != Material.AIR) {
            return getRandomSafeLocation(newLocation, Iterations + 1);
        } else if (Iterations > 10) {
            return null;
        } else {
            return newLocation;
        }
    }
}
