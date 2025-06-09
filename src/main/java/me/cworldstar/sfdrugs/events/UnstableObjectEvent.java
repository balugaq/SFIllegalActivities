package me.cworldstar.sfdrugs.events;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.DamageType;
import me.cworldstar.sfdrugs.implementations.events.PlayerInventoryItemAddedEvent;
import me.cworldstar.sfdrugs.implementations.events.PlayerInventoryItemRemovingEvent;
import me.cworldstar.sfdrugs.implementations.items.UnstableObject;
import me.cworldstar.sfdrugs.utils.Constants;
import me.cworldstar.sfdrugs.utils.LoreHandler;
import me.cworldstar.sfdrugs.utils.Speak;
import me.cworldstar.sfdrugs.utils.Texts;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

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
                if (oldLore.get(oldLore.size() - 1).contains(Texts.cd)) {
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
            if (SlimefunItem.getByItem(item) instanceof UnstableObject item2) {
                new Speak(e.getPlayer(), Texts.uoe1);
                plugin.getLogger().warning(item2.unstable.toString());
                var u = new NamespacedKey(plugin, Constants.Unstable);
                item.getItemMeta().getPersistentDataContainer().set(u, PersistentDataType.DOUBLE, UnstableObject.getCooldown(item2.unstable));

                Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                    if (!e.getPlayer().getInventory().contains(item) || !e.getPlayer().isOnline()) {
                        e.cancel();
                    }
                }, 0, 1L);
                Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                    if (!e.isCancelled()) {
                        ItemMeta meta = item.getItemMeta();
                        double cooldown = meta.getPersistentDataContainer().get(u, PersistentDataType.DOUBLE);

                        List<String> oldLore = meta.getLore();
                        if (!oldLore.get(oldLore.size() - 1).contains(Texts.cd)) {
                            oldLore.add("");
                            oldLore.add(LoreHandler.UnstableObjectCooldownTimer(cooldown));
                        } else {
                            oldLore.add(oldLore.size() - 1, LoreHandler.UnstableObjectCooldownTimer(cooldown - 0.1));
                        }
                        meta.setLore(oldLore);
                        meta.getPersistentDataContainer().set(u, PersistentDataType.DOUBLE, cooldown - 0.1);
                        item.setItemMeta(meta);
                    }
                }, 0, 2L);
                switch (item2.getUnstableAmount()) {
                    case SLIGHTLY_UNSTABLE:
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            if (e.getPlayer().getInventory().contains(item) && !e.isCancelled() && e.getPlayer().isOnline()) {
                                new Speak(e.getPlayer(), Texts.uoe2);
                                item.setAmount(0);
                            }
                        }, 300L);
                        break;
                    case UNSTABLE:
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            if (e.getPlayer().getInventory().contains(item) && !e.isCancelled() && e.getPlayer().isOnline()) {
                                new Speak(e.getPlayer(), Texts.uoe3);
                                item.setAmount(0);
                                e.getPlayer().getWorld().createExplosion(e.getPlayer().getLocation(), 4F, true, true, DamageType.UNSTABLE_OBJECT.damager(e.getPlayer()));
                                e.getPlayer().damage(30.0, DamageType.UNSTABLE_OBJECT.damager(e.getPlayer()));
                            }
                        }, 300L);
                        break;
                    case HIGHLY_UNSTABLE:
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            if (e.getPlayer().getInventory().contains(item) && !e.isCancelled() && e.getPlayer().isOnline()) {
                                new Speak(e.getPlayer(), Texts.uoe4);
                                item.setAmount(0);
                                e.getPlayer().getWorld().createExplosion(e.getPlayer().getLocation(), 10F, true, true, DamageType.UNSTABLE_OBJECT.damager(e.getPlayer()));
                                e.getPlayer().damage(45.0, DamageType.UNSTABLE_OBJECT.damager(e.getPlayer()));
                            }
                        }, 200L);
                        break;
                    case STABLE:
                    default:
                        break;
                }
            }
        }
    }
}
