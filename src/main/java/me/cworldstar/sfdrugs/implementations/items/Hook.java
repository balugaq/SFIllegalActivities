package me.cworldstar.sfdrugs.implementations.items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.utils.Speak;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Hook extends SimpleSlimefunItem<ItemUseHandler> {
    private final Random r = new Random();

    private final SFDrugs plugin;

    public Hook(SFDrugs plugin, ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.plugin = plugin;
    }

    @Override
    public @NotNull ItemUseHandler getItemHandler() {
        return (PlayerRightClickEvent e) -> {
            var pdc = e.getItem().getItemMeta().getPersistentDataContainer();
            var cd = new NamespacedKey(this.plugin, "Cooldown");
            var v = pdc.get(cd, PersistentDataType.INTEGER);
            if (v == null) {
                pdc.set(cd, PersistentDataType.INTEGER, 0);
            } else if (v == 0) {
                pdc.set(cd, PersistentDataType.INTEGER, 30);
                Bukkit.getScheduler().runTaskTimerAsynchronously(this.plugin, task -> {
                    PersistentDataContainer d = e.getItem().getItemMeta().getPersistentDataContainer();
                    var v2 = d.get(cd, PersistentDataType.INTEGER);
                    if (v2 == null || v2 == 0) {
                        task.cancel();
                    } else {
                        d.set(cd, PersistentDataType.INTEGER, v2 - 1);
                    }
                }, 0, 20L);
                Player player = e.getPlayer();
                new Speak(player, player.getNearbyEntities(15.0, 15.0, 15.0), "&cCome over here!");
                player.getLastDamageCause().getEntity().teleport(player.getLocation().add(r.nextInt(3), 0, r.nextInt(3)));
                player.getWorld().playSound(player.getLastDamageCause().getEntity().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.5F, 0.5F);
                player.setInvulnerable(true);
                player.getWorld().createExplosion(player.getLastDamageCause().getEntity().getLocation(), 3, true, false);
                player.setInvulnerable(false);
            }
        };
    }

}
