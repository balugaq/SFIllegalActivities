package me.cworldstar.sfdrugs.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.events.ArmorEquipEvent;
import me.cworldstar.sfdrugs.implementations.gui.ATradingInterface;
import me.cworldstar.sfdrugs.implementations.gui.ATradingInterface.InventorySize;
import me.cworldstar.sfdrugs.implementations.powerarmor.PowerArmor;
import me.cworldstar.sfdrugs.implementations.powerarmor.PowerArmorCore;
import me.cworldstar.sfdrugs.implementations.traders.ATrader;
import me.cworldstar.sfdrugs.utils.Items;

public class PowerArmorListener implements Listener {
	
	
	private SFDrugs plugin;

	public PowerArmorListener(SFDrugs plugin) {
		
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerEquip(ArmorEquipEvent e) {
		ItemStack chestplate = e.getNewArmorPiece();
		if(!chestplate.equals(null)) {
			if(SlimefunItem.getByItem(chestplate) instanceof PowerArmor) {
				
			}
		}
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerRightClick(PlayerInteractEvent e) {
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(SlimefunItem.getByItem(e.getPlayer().getInventory().getItemInMainHand()) instanceof PowerArmor) {
				e.setCancelled(true);
				//TODO: power armor core UI
				ATradingInterface TestInterface = new ATradingInterface(InventorySize.LARGE, (ItemStack) Items.LEGENDARY_CHEST, ATrader.TraderFromString("hooker_zombie"));
				TestInterface.Display(e.getPlayer());
				
			}
			

		}
	}

}
