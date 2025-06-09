package me.cworldstar.sfdrugs.implementations.events;

import me.cworldstar.sfdrugs.SFDrugs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class InventoryTickHandler {
    private final SFDrugs plugin;
    private final Player player;
    private final BukkitTask itemAddedRunnable;
    private final BukkitTask itemRemovingRunnable;
    private List<ItemStack> oldInventory;

    /**
     * InventoryTickHandler for PlayerInventoryItemAddedEvent.
     * Takes a Plugin and a Player.
     * Has getRunnable() and getPlayer() tasks for cancelling.
     * <p>
     * This is stupid.
     *
     * @param plugin
     * @param p
     * @author cworldstar
     * @since today
     */
    public InventoryTickHandler(SFDrugs plugin, Player p) {
        this.plugin = plugin;
        this.player = p;
        this.oldInventory = new ArrayList<>(Arrays.asList(p.getInventory().getStorageContents()));
        this.itemAddedRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (!new HashSet<>(oldInventory).containsAll(new ArrayList<>(Arrays.asList(p.getInventory().getStorageContents())))) {
                    List<ItemStack> NewItems = new ArrayList<>(Arrays.asList(p.getInventory().getStorageContents()));
                    NewItems.removeAll(oldInventory);
                    if (!NewItems.isEmpty()) {
                        Bukkit.getServer().getPluginManager().callEvent(new PlayerInventoryItemAddedEvent(p, p.getInventory(), NewItems));
                        oldInventory = new ArrayList<>(Arrays.asList(p.getInventory().getContents()));
                    }
                }
            }

        }.runTaskTimer(plugin, 0, 1L);
        this.itemRemovingRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (!new ArrayList<>(Arrays.asList(p.getInventory().getStorageContents())).containsAll(oldInventory)) {
                    List<ItemStack> NewItems = oldInventory;
                    NewItems.removeAll(new ArrayList<>(Arrays.asList(p.getInventory().getStorageContents())));
                    if (!NewItems.isEmpty()) {
                        Bukkit.getServer().getPluginManager().callEvent(new PlayerInventoryItemRemovingEvent(p, p.getInventory(), NewItems));
                        oldInventory = new ArrayList<>(Arrays.asList(p.getInventory().getContents()));
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 1L);
    }

    public BukkitTask getItemAddedRunnable() {
        return this.itemAddedRunnable;
    }

    public BukkitTask getItemRemovingRunnable() {
        return this.itemRemovingRunnable;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void cancel() {
        this.itemAddedRunnable.cancel();
        this.itemRemovingRunnable.cancel();
    }
}
