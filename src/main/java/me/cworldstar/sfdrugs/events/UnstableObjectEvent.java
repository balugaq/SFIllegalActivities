package me.cworldstar.sfdrugs.events;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.DamageType;
import me.cworldstar.sfdrugs.implementations.events.PlayerInventoryItemAddedEvent;
import me.cworldstar.sfdrugs.implementations.events.PlayerInventoryItemRemovingEvent;
import me.cworldstar.sfdrugs.implementations.items.UnstableObject;
import me.cworldstar.sfdrugs.utils.LoreHandler;
import me.cworldstar.sfdrugs.utils.Speak;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class UnstableObjectEvent implements Listener {

    private final SFDrugs plugin;

    public UnstableObjectEvent(SFDrugs plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInventoryItemRemoved(PlayerInventoryItemRemovingEvent e) {
        for (ItemStack item : e.getItem()) {
            if (SlimefunItem.getByItem(item) != null && SlimefunItem.getByItem(item) instanceof UnstableObject) {
                ItemMeta meta = item.getItemMeta();
                List<String> oldLore = meta.getLore();
                if (oldLore.get(oldLore.size() - 1).contains("Cooldown:")) {
                    oldLore.remove(oldLore.size() - 1);
                    meta.setLore(oldLore);
                    item.setItemMeta(meta);
                }
            }
        }
    }


    @EventHandler
    public void onPlayerInventoryItemAdded(PlayerInventoryItemAddedEvent e) {
        for (ItemStack item : e.getItem()) {
            if (SlimefunItem.getByItem(item) != null && SlimefunItem.getByItem(item) instanceof UnstableObject TheUnstableObject) {
                new Speak(e.getPlayer(), "&e&l ⚠ You have picked up an unstable object. Either dispose of it or use it. ⚠");
                plugin.getLogger().warning(TheUnstableObject.unstable.toString());
                item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "Unstable"), PersistentDataType.DOUBLE, UnstableObject.getCooldown(TheUnstableObject.unstable));

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!e.getPlayer().getInventory().contains(item) || !e.getPlayer().isOnline()) {
                            e.cancel();
                        }
                    }
                }.runTaskTimer(plugin, 0, 1L);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!e.isCancelled()) {
                            ItemMeta meta = item.getItemMeta();
                            double cooldown = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "Unstable"), PersistentDataType.DOUBLE);

                            List<String> oldLore = meta.getLore();
                            if (!oldLore.get(oldLore.size() - 1).contains("Cooldown:")) {
                                oldLore.add("");
                                oldLore.add(LoreHandler.UnstableObjectCooldownTimer(cooldown));
                            } else {
                                oldLore.add(oldLore.size() - 1, LoreHandler.UnstableObjectCooldownTimer(cooldown - 0.1));
                            }
                            meta.setLore(oldLore);
                            item.setItemMeta(meta);
                            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "Unstable"), PersistentDataType.DOUBLE, cooldown - 0.1);
                        }
                    }
                }.runTaskTimer(plugin, 0, 2L);
                switch (item2.getUnstableAmount()) {
                    case SLIGHTLY_UNSTABLE:
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (e.getPlayer().getInventory().contains(item) && !e.isCancelled() && e.getPlayer().isOnline()) {
                                    new Speak(e.getPlayer(), "&eYou took too long to dispose of a slightly unstable object. It has disappeared.");
                                    item.setAmount(0);
                                }
                            }

                        }.runTaskLater(plugin, 300L);
                        break;
                    case UNSTABLE:
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (e.getPlayer().getInventory().contains(item) && !e.isCancelled() && e.getPlayer().isOnline()) {
                                    new Speak(e.getPlayer(), "&e&lYou took too long to dispose of an unstable object. It blew up.");
                                    item.setAmount(0);
                                    e.getPlayer().getWorld().createExplosion(e.getPlayer().getLocation(), 4F, true, true, DamageType.UNSTABLE_OBJECT.damager(e.getPlayer()));
                                    e.getPlayer().damage(30.0, DamageType.UNSTABLE_OBJECT.damager(e.getPlayer()));
                                }
                            }

                        }.runTaskLater(plugin, 300L);
                        break;
                    case HIGHLY_UNSTABLE:
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (e.getPlayer().getInventory().contains(item) && !e.isCancelled() && e.getPlayer().isOnline()) {
                                    new Speak(e.getPlayer(), "&c&lYou took too long to dispose of a highly unstable object. It blew up.");
                                    item.setAmount(0);
                                    e.getPlayer().getWorld().createExplosion(e.getPlayer().getLocation(), 10F, true, true, DamageType.UNSTABLE_OBJECT.damager(e.getPlayer()));
                                    e.getPlayer().damage(45.0, DamageType.UNSTABLE_OBJECT.damager(e.getPlayer()));
                                }
                            }

                        }.runTaskLater(plugin, 200L);
                        break;
                    case STABLE:
                    default:
                        break;
                }
            }
        }
    }
}
