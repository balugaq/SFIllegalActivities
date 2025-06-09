package me.cworldstar.sfdrugs.implementations.items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.loot.RareChestLootTable;
import me.cworldstar.sfdrugs.utils.RandomUtils;
import org.bukkit.Effect;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public class RareChest extends SimpleSlimefunItem<ItemUseHandler> implements ALootChest {
    private final SFDrugs plugin;
    private RareChestLootTable lootTable;

    public RareChest(SFDrugs plugin, ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.plugin = plugin;
        this.lootTable = new RareChestLootTable(this.plugin);
    }

    @Override
    public ItemUseHandler open() {
        return (PlayerRightClickEvent e) -> {
            List<ItemStack> drops = new ArrayList<>(this.lootTable.populateLoot(RandomUtils.getRandom(), new LootContext.Builder(e.getPlayer().getLocation()).build()));
            for (ItemStack drop : drops) {
                e.getPlayer().getInventory().addItem(drop);
            }
            e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.EXTINGUISH, 0);
            e.getItem().setAmount(e.getItem().getAmount() - 1);
            this.lootTable = new RareChestLootTable(this.plugin);
        };
    }

    @Override
    public LootTable getLootTable() {
        // TODO Auto-generated method stub
        return this.lootTable;
    }

    @Override
    public ItemUseHandler getItemHandler() {
        // TODO Auto-generated method stub
        return this.open();
    }

}
