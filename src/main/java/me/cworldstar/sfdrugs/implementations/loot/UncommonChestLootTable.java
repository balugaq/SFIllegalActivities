package me.cworldstar.sfdrugs.implementations.loot;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.utils.Constants;
import me.cworldstar.sfdrugs.utils.Items;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class UncommonChestLootTable implements LootTable {
    private final SFDrugs plugin;
    private final Collection<ItemStack> lootTable = new ArrayList<>();

    public UncommonChestLootTable(SFDrugs plugin) {
        this.plugin = plugin;

    }

    @Override
    public NamespacedKey getKey() {
        // TODO Auto-generated method stub
        return new NamespacedKey(plugin, Constants.SfDrugsCorporationEnemy);
    }

    @Override
    public Collection<ItemStack> populateLoot(Random random, LootContext context) {
        int num = random.nextInt(15);
        int items = random.nextInt(5);
        for (int i = 0; i < items; i++) {
            switch (num) {
                case 1:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.BASIC_CIRCUIT_BOARD, 12));
                    break;
                case 2:
                    this.lootTable.add(new CustomItemStack(Items.DRIED_PLANT, 4));
                    break;
                case 3:
                    this.lootTable.add(new ItemStack(Material.IRON_INGOT, 16));
                    break;
                case 4:
                    this.lootTable.add(new ItemStack(Material.IRON_INGOT, 12));
                    break;
                case 5:
                    this.lootTable.add(new ItemStack(Material.IRON_INGOT, 14));
                    break;
                case 6:
                    this.lootTable.add(new ItemStack(Material.IRON_INGOT, 18));
                    break;
                case 7:
                    this.lootTable.add(new ItemStack(Material.DIAMOND, 4));
                    break;
                case 8:
                    this.lootTable.add(new ItemStack(Material.DIAMOND, 2));
                    break;
                case 9:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.BASIC_CIRCUIT_BOARD, 8));
                    break;
                case 10:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.BLANK_RUNE, 8));
                    break;
                case 11:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.BLANK_RUNE, 10));
                    break;
                case 12:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.REINFORCED_ALLOY_INGOT, 1));
                    break;
                case 13:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.ANDROID_MEMORY_CORE, 1));
                    break;
                case 14:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.RADIANT_BACKPACK, 1));
                    break;
                case 15:
                    this.lootTable.add(
                            new CustomItemStack(Items.IRRADIATED_SIRTHIUM_RAW, 1)
                    );
                    break;
                default:
                    this.lootTable.add(new CustomItemStack(Items.CYANIDE, 2));
            }
            num = random.nextInt(15);
        }
        return this.lootTable;
    }

    @Override
    public void fillInventory(Inventory inventory, Random random, LootContext context) {
    }
}
