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

public class RareChestLootTable implements LootTable {
    private final SFDrugs plugin;
    private final Collection<ItemStack> lootTable = new ArrayList<>();

    public RareChestLootTable(SFDrugs plugin) {
        this.plugin = plugin;

    }

    @Override
    public NamespacedKey getKey() {
        // TODO Auto-generated method stub
        return new NamespacedKey(plugin, Constants.SfDrugsCorporationEnemy);
    }

    @Override
    public Collection<ItemStack> populateLoot(Random random, LootContext context) {
        int num = random.nextInt(30);
        int items = random.nextInt(5);
        for (int i = 0; i < items; i++) {
            switch (num) {
                case 1:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.RAINBOW_RUNE, 4));
                    break;
                case 2:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.LIGHTNING_RUNE, 8));
                    break;
                case 3:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.FIRE_RUNE, 8));
                    break;
                case 4:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.EARTH_RUNE, 8));
                    break;
                case 5:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.AIR_RUNE, 8));
                    break;
                case 10, 11:
                    this.lootTable.add(new CustomItemStack(Items.MONEY, 5));
                    break;
                case 12:
                    this.lootTable.add(new CustomItemStack(Items.MONEY, 8));
                    break;
                case 13:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.BASIC_CIRCUIT_BOARD, 64));
                    break;
                case 14, 17, 16, 15:
                    this.lootTable.add(new ItemStack(Material.DIAMOND_BLOCK, 2));
                    break;
                case 18, 20, 19:
                    this.lootTable.add(new ItemStack(Material.NETHERITE_INGOT, 4));
                    break;
                case 21:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.BASIC_CIRCUIT_BOARD, 32));
                    break;
                case 22:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.BASIC_CIRCUIT_BOARD, 48));
                    break;
                case 23:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.BASIC_CIRCUIT_BOARD, 50));
                    break;
                case 24:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.BASIC_CIRCUIT_BOARD, 58));
                    break;
                case 25:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.BLISTERING_INGOT_3, 1));
                    break;
                case 26:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.BLISTERING_INGOT_2, 2));
                    break;
                case 27:
                    this.lootTable.add(new CustomItemStack(SlimefunItems.BLISTERING_INGOT, 4));
                    break;
                case 28:
                    this.lootTable.add(new ItemStack(Material.NETHER_STAR, 1));
                    break;
                case 29:
                    this.lootTable.add(new ItemStack(Material.NETHER_STAR, 1));
                    break;
                case 30:
                    this.lootTable.add(
                            new CustomItemStack(Items.MONEY_STAMP, 1)
                    );
                    break;
                case 9, 8, 7, 6:
                default:
                    this.lootTable.add(new CustomItemStack(Items.MONEY, 2));
            }
            num = random.nextInt(30);
        }
        return this.lootTable;
    }

    @Override
    public void fillInventory(Inventory inventory, Random random, LootContext context) {
    }

}
