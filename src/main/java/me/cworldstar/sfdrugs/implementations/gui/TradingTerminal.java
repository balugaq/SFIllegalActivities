package me.cworldstar.sfdrugs.implementations.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class TradingTerminal implements Listener {

    private static final Map<Integer, ?> Hooks = new HashMap<>();
    final Inventory TradingTerminalInterface = Bukkit.createInventory(null, 45);


    public TradingTerminal() {
        for (int i = 0; i <= 9; i++) {
            TradingTerminalInterface.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS));
        }
        for (int i = 36; i <= 45; i++) {
            TradingTerminalInterface.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS));
        }

    }

}
