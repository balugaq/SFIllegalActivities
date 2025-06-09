package me.cworldstar.sfdrugs.implementations.world.radiation;

import me.cworldstar.sfdrugs.SFDrugs;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class RadiationListener implements Listener {
    public static HashMap<Radiation, Block> RadiatedBlocks = new HashMap<>();
    private final SFDrugs plugin;
    private BukkitRunnable radiationRunnable;


    public RadiationListener(SFDrugs plugin) {
        this.plugin = plugin;
    }
}
