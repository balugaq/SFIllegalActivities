package me.cworldstar.sfdrugs.implementations.items;

import java.util.Random;

import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.utils.Speak;

public class Hook extends SimpleSlimefunItem<ItemUseHandler> {

	private SFDrugs plugin;
	public Hook(SFDrugs plugin,ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(itemGroup, item, recipeType, recipe);
		this.plugin = plugin;
	}
	@Override
	public ItemUseHandler getItemHandler() {
		return (PlayerRightClickEvent e) -> {
			if(e.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this.plugin,"Cooldown"),PersistentDataType.INTEGER) == null) {
				e.getItem().getItemMeta().getPersistentDataContainer().set(new NamespacedKey(this.plugin,"Cooldown"),PersistentDataType.INTEGER, 0);	
			}
			else if(e.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this.plugin,"Cooldown"),PersistentDataType.INTEGER) == 0) {
				e.getItem().getItemMeta().getPersistentDataContainer().set(new NamespacedKey(this.plugin,"Cooldown"),PersistentDataType.INTEGER, 30);
				new BukkitRunnable() {
					@Override
					public void run() {
						PersistentDataContainer d = e.getItem().getItemMeta().getPersistentDataContainer();
						NamespacedKey key = new NamespacedKey(plugin,"Cooldown");
						if(d.get(key,PersistentDataType.INTEGER) == 0) {
							this.cancel();
						} else {
							d.set(key,PersistentDataType.INTEGER, d.get(key, PersistentDataType.INTEGER) - 1);
						}
					}
				}.runTaskTimerAsynchronously(this.plugin, 0, 20L);
				Player z = e.getPlayer();
				new Speak(z,z.getNearbyEntities(15.0, 15.0, 15.0),"&cCome over here!");
				z.getLastDamageCause().getEntity().teleport(z.getLocation().add(new Random().nextInt(3), 0, new Random().nextInt(3)));
				z.getWorld().playSound(z.getLastDamageCause().getEntity().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.5F, 0.5F);
				z.setInvulnerable(true);
				z.getWorld().createExplosion(z.getLastDamageCause().getEntity().getLocation(), 3,true,false);
				z.setInvulnerable(false);
			}
		};
	}
	
}
