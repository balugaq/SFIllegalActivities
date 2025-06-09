package me.cworldstar.sfdrugs.events;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.events.ArmorEquipEvent;
import me.cworldstar.sfdrugs.implementations.events.ArmorEquipEvent.EquipMethod;
import me.cworldstar.sfdrugs.implementations.events.ArmorType;
import me.cworldstar.sfdrugs.implementations.events.InventoryTickHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

public class PlayerAddedEvent implements Listener {
    private final SFDrugs plugin;
    private final Map<Player, InventoryTickHandler> storage = new HashMap<>();

    public PlayerAddedEvent(SFDrugs plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerAdded(PlayerJoinEvent e) {
        // Assume player is not instance of OfflinePlayer
        this.storage.put(e.getPlayer(), new InventoryTickHandler(plugin, e.getPlayer()));
        if (e.getPlayer().getEquipment().getHelmet() != null) {
            Bukkit.getServer().getPluginManager().callEvent(new ArmorEquipEvent(e.getPlayer(), EquipMethod.PICK_DROP, ArmorType.matchType(e.getPlayer().getEquipment().getHelmet()), e.getPlayer().getEquipment().getHelmet(), e.getPlayer().getEquipment().getHelmet()));
        }
    }

    @EventHandler
    public void onPlayerRemoving(PlayerQuitEvent e) {
        if (this.storage.get(e.getPlayer()) != null) {
            this.storage.get(e.getPlayer()).cancel();
        }
    }

    @EventHandler
    public void onPlayerRemoving(PlayerKickEvent e) {
        if (this.storage.get(e.getPlayer()) != null) {
            this.storage.get(e.getPlayer()).cancel();
        }
    }

}
