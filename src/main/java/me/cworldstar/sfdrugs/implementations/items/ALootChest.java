package me.cworldstar.sfdrugs.implementations.items;

import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.loot.LootTable;

public interface ALootChest {
    ItemUseHandler open();

    LootTable getLootTable();

    ItemUseHandler getItemHandler();
}
