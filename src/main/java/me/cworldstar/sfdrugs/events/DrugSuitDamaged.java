package me.cworldstar.sfdrugs.events;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.items.DrugSuit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class DrugSuitDamaged implements Listener {
    private final SFDrugs plugin;

    public DrugSuitDamaged(SFDrugs plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onPlayerItemDamage(PlayerItemDamageEvent e) {
        ItemStack item = e.getItem();
        if (SlimefunItem.getByItem(item) != null) {
            if (item.getItemMeta().getDisplayName().contains("Corporate Hazmat")) {
                DrugSuit T = (DrugSuit) SlimefunItem.getByItem(item);
                T.Damage(e, item, e.getDamage());
            }
        }

    }
}
