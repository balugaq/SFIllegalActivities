package me.cworldstar.sfdrugs.implementations.generators;

import me.cworldstar.sfdrugs.SFDrugs;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public interface DarkMatter {
    long maxIntegrity = 100;

    SFDrugs getPlugin();

    void onExplode(Block b);

    void onBreakdown(Block b, double integrity);

    default long getDarkMatter(Location BlockLocation) {
        Block b = BlockLocation.getBlock();
        if (b.hasMetadata("dark-matter-machine")) {
            return b.getMetadata("dark-matter-amount").get(0).asLong();
        }
        return 0;
    }

    default long getDarkMatter(Block Machine) {
        Block b = Machine;
        if (b.hasMetadata("dark-matter-machine")) {
            return b.getMetadata("dark-matter-amount").get(0).asLong();
        }
        return 0;
    }


    default boolean hasDarkMatter(Block b) {
        if (b.getMetadata("dark-matter-machine") != null) {
            return this.getDarkMatter(b) > 0;
        }
        return false;
    }

    //TODO: temperature system
    default boolean shouldMeltDown(Block b) {
        if (!this.hasDarkMatter(b)) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    if (getDarkMatter(b) > 0) {
                        this.cancel();
                    } else {
                        onBreakdown(b, maxIntegrity);
                    }
                }
            };
            return true;
        }
        return false;
    }


    default boolean isDarkMatterMachine(Block b) {
        return b.getMetadata("dark-matter-machine") != null;
    }

    default void addDarkMatter(Location BlockLocation, Long DarkMatter) {
        Block b = BlockLocation.getBlock();
        if (b.hasMetadata("dark-matter-machine")) {
            b.setMetadata("dark-matter-amount", new FixedMetadataValue(this.getPlugin(), this.getDarkMatter(BlockLocation) + DarkMatter));
        }
    }

    default void addDarkMatter(Block Machine, Long DarkMatter) {
        Block b = Machine;
        if (b.hasMetadata("dark-matter-machine")) {
            b.setMetadata("dark-matter-amount", new FixedMetadataValue(this.getPlugin(), this.getDarkMatter(Machine) + DarkMatter));
        }
    }

    default void removeDarkMatter(Block Machine, Long DarkMatter) {
        Block b = Machine;
        if (b.hasMetadata("dark-matter-machine")) {
            b.setMetadata("dark-matter-amount", new FixedMetadataValue(this.getPlugin(), this.getDarkMatter(Machine) - DarkMatter));
        }
    }


}
