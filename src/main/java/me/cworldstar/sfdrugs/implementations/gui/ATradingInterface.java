package me.cworldstar.sfdrugs.implementations.gui;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.TradingRecipe;
import me.cworldstar.sfdrugs.implementations.traders.ATrader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.logging.Level;


/**
 * ATradingInterface. Used by traders. ATraders will use this automatically to generate trading interfaces.
 *
 * @param InventorySize Size
 * @param ItemStack     BorderItem
 * @param ATrader       Trader
 * @author cworldstar
 */

public class ATradingInterface implements Listener {

    private final Inventory TradingTerminalInterface;
    private final ItemStack BorderItem;
    private final InventorySize GUISize;
    private final ATrader Trader;
    private final ItemStack OutputItem;
    private final ItemStack InputItem;
    private final int[] left = new int[]{16, 15, 14, 25, 24, 23, 34, 33, 32};
    private final int[] right = new int[]{10, 11, 12, 19, 20, 21, 28, 29, 30};

    public ATradingInterface(InventorySize GUISize, ItemStack BorderItem, ATrader Trader) {
        SFDrugs plugin = SFDrugs.getPlugin(SFDrugs.class);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.GUISize = GUISize;
        this.TradingTerminalInterface = Bukkit.createInventory(null, GUISize.size, ChatColor.AQUA + Trader.getTraderId());
        this.BorderItem = BorderItem;
        this.InputItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        this.OutputItem = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = this.BorderItem.getItemMeta();
        meta.setDisplayName(" ");
        meta.getPersistentDataContainer().set(SFDrugs.createKey("tti_border"), PersistentDataType.INTEGER, 1);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        this.BorderItem.setItemMeta(meta);

        ItemMeta inputItemMeta = this.InputItem.getItemMeta();
        inputItemMeta.setDisplayName("Input Item");
        inputItemMeta.getPersistentDataContainer().set(SFDrugs.createKey("tti_input"), PersistentDataType.INTEGER, 1);
        inputItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        this.InputItem.setItemMeta(inputItemMeta);

        ItemMeta outputItemMeta = this.OutputItem.getItemMeta();
        outputItemMeta.setDisplayName("Output Item");
        outputItemMeta.getPersistentDataContainer().set(SFDrugs.createKey("tti_output"), PersistentDataType.INTEGER, 1);
        outputItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        this.OutputItem.setItemMeta(outputItemMeta);

        this.Trader = Trader;
        this.PopulateInventory();

    }

    public void Display(Player p) {
        p.openInventory(this.TradingTerminalInterface);
    }

    public void PopulateInventory() {


        int[] sides = new int[]{
                9,
                17,
                18,
                26,
                27,
                35
        };

        for (int i : this.right) {
            this.TradingTerminalInterface.setItem(i, this.InputItem);
        }

        for (int b : this.left) {
            this.TradingTerminalInterface.setItem(b, this.OutputItem);
        }

        for (int l : sides) {
            this.TradingTerminalInterface.setItem(l, this.BorderItem);
        }

        for (int k = 0; k < 9; k++) {
            this.TradingTerminalInterface.setItem(k, this.BorderItem);
        }
        for (int j = 36; j < 45; j++) {
            this.TradingTerminalInterface.setItem(j, this.BorderItem);
        }
        for (int o = 4; o < 45; o += 9) {
            this.TradingTerminalInterface.setItem(o, this.BorderItem);
        }
    }

    // pain
    @EventHandler(priority = EventPriority.HIGHEST)
    private void onPlayerClickInventory(InventoryClickEvent e) {


        if (e.getClick().isShiftClick() && e.getClickedInventory() != this.TradingTerminalInterface && this.TradingTerminalInterface.getViewers().contains(e.getWhoClicked())) {
            ItemStack shiftClickItem = e.getCurrentItem();
            ItemMeta ItemToPlaceMeta = shiftClickItem.getItemMeta();
            PersistentDataContainer ItemToPlaceDataContainer = ItemToPlaceMeta.getPersistentDataContainer();
            ItemToPlaceDataContainer.set(SFDrugs.createKey("tti_input"), PersistentDataType.INTEGER, 1);
            shiftClickItem.setItemMeta(ItemToPlaceMeta);
            this.TradingTerminalInterface.setItem(this.TradingTerminalInterface.first(this.InputItem), new CustomItemStack(shiftClickItem, shiftClickItem.getAmount()));
            e.getWhoClicked().getInventory().setItem(e.getRawSlot(), null);
            ArrayList<ItemStack> ItemsInHaveSlot = new ArrayList<>();
            for (int a1 : this.right) {
                if (this.TradingTerminalInterface.getItem(a1) != null && !this.TradingTerminalInterface.getItem(a1).isSimilar(this.InputItem)) {
                    ItemsInHaveSlot.add(this.TradingTerminalInterface.getItem(a1));
                }
            }
            e.getWhoClicked().getInventory().setItem(e.getRawSlot(), new ItemStack(Material.AIR));
            // display output
            ArrayList<TradingRecipe> usableRecipes = this.Trader.getRecipes(ItemsInHaveSlot);
            for (TradingRecipe recipe : usableRecipes) {
                SFDrugs.log(Level.WARNING, recipe.toString());
                ItemStack For = recipe.getFor();
                ItemMeta ForMeta = For.getItemMeta();
                PersistentDataContainer ForItemContainer = ForMeta.getPersistentDataContainer();
                ForItemContainer.set(SFDrugs.createKey("tti_output"), PersistentDataType.INTEGER, 1);
                For.setItemMeta(ForMeta);
                int FirstPlaceable = this.TradingTerminalInterface.first(this.OutputItem);
                if (FirstPlaceable != -1) {
                    this.TradingTerminalInterface.setItem(this.TradingTerminalInterface.first(this.OutputItem), For);
                } else {
                    SFDrugs.log(Level.WARNING, "\u001B[31m" + recipe.getFor().getItemMeta().getDisplayName() + " could not be displayed." + "\n" + "This is not an error.");
                }
            }
        }

        if (e.getClickedInventory() != null && e.getClickedInventory().equals(this.TradingTerminalInterface)) {
            e.setCancelled(true);
            // if item exists
            if (e.getCurrentItem() != null) {
                // if item is border, cancel.
                ItemStack ClickedItem = e.getCurrentItem();
                ItemMeta ClickedItemMeta = ClickedItem.getItemMeta();
                PersistentDataContainer DataContainer = ClickedItemMeta.getPersistentDataContainer();
                if (DataContainer.has(SFDrugs.createKey("tti_border"), PersistentDataType.INTEGER)) {
                    e.setCancelled(true);
                } else if (DataContainer.has(SFDrugs.createKey("tti_input"), PersistentDataType.INTEGER) && !e.getCursor().isSimilar(new ItemStack(Material.AIR))) {
                    ItemStack ItemToPlace = e.getCursor();

                    if (ItemToPlace != null) {
                        if (!ClickedItem.isSimilar(this.InputItem)) {
                            if (ClickedItem != null) {
                                e.getWhoClicked().getInventory().addItem(ClickedItem);
                                e.getClickedInventory().setItem(e.getRawSlot(), this.InputItem);
                                ArrayList<ItemStack> ItemsInHaveSlot = new ArrayList<>();
                                for (int a1 : this.right) {
                                    if (this.TradingTerminalInterface.getItem(a1) != null && !this.TradingTerminalInterface.getItem(a1).isSimilar(this.InputItem)) {
                                        ItemsInHaveSlot.add(this.TradingTerminalInterface.getItem(a1));
                                    }
                                }

                                ArrayList<TradingRecipe> usableRecipes = this.Trader.getRecipes(ItemsInHaveSlot);
                                e.getWhoClicked().setItemOnCursor(new ItemStack(Material.AIR));
                                // display output
                                for (TradingRecipe recipe : usableRecipes) {
                                    SFDrugs.log(Level.WARNING, recipe.toString());
                                    ItemStack For = recipe.getFor();
                                    ItemMeta ForMeta = For.getItemMeta();
                                    PersistentDataContainer ForItemContainer = ForMeta.getPersistentDataContainer();
                                    ForItemContainer.set(SFDrugs.createKey("tti_output"), PersistentDataType.INTEGER, 1);
                                    For.setItemMeta(ForMeta);
                                    int FirstPlaceable = this.TradingTerminalInterface.first(this.OutputItem);
                                    if (FirstPlaceable != -1) {
                                        this.TradingTerminalInterface.setItem(this.TradingTerminalInterface.first(this.OutputItem), For);
                                    } else {
                                        SFDrugs.log(Level.WARNING, "\u001B[31m" + recipe.getFor().getItemMeta().getDisplayName() + " could not be displayed." + "\n" + "This is not an error." + "\u001B[0m");
                                    }
                                }
                                e.setCancelled(true);
                            }
                            e.setCancelled(true);
                        } else {
                            ItemMeta ItemToPlaceMeta = ItemToPlace.getItemMeta();
                            PersistentDataContainer ItemToPlaceDataContainer = ItemToPlaceMeta.getPersistentDataContainer();
                            ItemToPlaceDataContainer.set(SFDrugs.createKey("tti_input"), PersistentDataType.INTEGER, 1);
                            ItemToPlace.setItemMeta(ItemToPlaceMeta);
                            int slot = e.getRawSlot();
                            e.getClickedInventory().setItem(slot, new CustomItemStack(ItemToPlace, ItemToPlace.getAmount()));

                            ArrayList<ItemStack> ItemsInHaveSlot = new ArrayList<>();
                            for (int a1 : this.right) {
                                if (this.TradingTerminalInterface.getItem(a1) != null && !this.TradingTerminalInterface.getItem(a1).isSimilar(this.InputItem)) {
                                    ItemsInHaveSlot.add(this.TradingTerminalInterface.getItem(a1));
                                }
                            }

                            ArrayList<TradingRecipe> usableRecipes = this.Trader.getRecipes(ItemsInHaveSlot);
                            e.getWhoClicked().setItemOnCursor(new ItemStack(Material.AIR));
                            // display output
                            for (TradingRecipe recipe : usableRecipes) {
                                SFDrugs.log(Level.WARNING, recipe.toString());
                                ItemStack For = new CustomItemStack(recipe.getFor(), recipe.getFor().getAmount());
                                ItemMeta ForMeta = For.getItemMeta();
                                PersistentDataContainer ForItemContainer = ForMeta.getPersistentDataContainer();
                                ForItemContainer.set(SFDrugs.createKey("tti_output"), PersistentDataType.INTEGER, 1);
                                For.setItemMeta(ForMeta);
                                int FirstPlaceable = this.TradingTerminalInterface.first(this.OutputItem);
                                if (FirstPlaceable != -1) {
                                    this.TradingTerminalInterface.setItem(this.TradingTerminalInterface.first(this.OutputItem), For);
                                } else {
                                    SFDrugs.log(Level.WARNING, "\u001B[31m" + recipe.getFor().getItemMeta().getDisplayName() + " could not be displayed." + "\n" + "This is not an error." + "\u001B[0m");
                                }
                            }
                        }

                    }

                    e.setCancelled(true);
                } else if (DataContainer.has(SFDrugs.createKey("tti_output"), PersistentDataType.INTEGER)) {
                    e.setCancelled(true);
                    if (!e.getCurrentItem().isSimilar(this.OutputItem)) {
                        TradingRecipe recipe = this.Trader.GetRecipeFromOutput(e.getCurrentItem());
                        if (recipe != null) {

                            for (int i : this.right) {
                                if (this.TradingTerminalInterface.getItem(i) != null) {
                                    CustomItemStack ComparableItem;
                                    if (SlimefunItem.getByItem(this.TradingTerminalInterface.getItem(i)) != null) {
                                        ComparableItem = new CustomItemStack(SlimefunItem.getByItem(this.TradingTerminalInterface.getItem(i)).getItem(), this.TradingTerminalInterface.getItem(i).getAmount());
                                    } else {
                                        ComparableItem = new CustomItemStack(new ItemStack(this.TradingTerminalInterface.getItem(i).getType()), this.TradingTerminalInterface.getItem(i).getAmount());
                                    }
                                    SFDrugs.log(Level.WARNING, ComparableItem.getItemMeta().getDisplayName());
                                    SFDrugs.log(Level.WARNING, recipe.getFor().getItemMeta().getDisplayName());
                                    if (recipe.getFor().isSimilar(ComparableItem)) {
                                        int amount = this.TradingTerminalInterface.getItem(i).getAmount();
                                        if (amount >= recipe.getFor().getAmount()) {
                                            this.TradingTerminalInterface.setItem(i, new CustomItemStack(recipe.getFor(), amount - recipe.getFor().getAmount()));
                                            e.getWhoClicked().getInventory().addItem(new CustomItemStack(SlimefunItem.getByItem(e.getCurrentItem()).getItem(), e.getCurrentItem().getAmount()));
                                        }
                                    }

                                }
                            }
                        }
                    }
                    for (int b : this.left) {
                        this.TradingTerminalInterface.setItem(b, this.OutputItem);
                    }
                    e.setCancelled(true);
                }
                e.setCancelled(true);
            }
            e.setCancelled(true);
			/*
				if(e.getCurrentItem().isSimilar(this.BorderItem)) {
					e.setCancelled(true);
				} else if(e.getCurrentItem().isSimilar(Items.MONEY)) {
					e.getWhoClicked().getWorld().playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_VILLAGER_TRADE, 0, 0);
					e.getWhoClicked().closeInventory();
				} else if (e.getCurrentItem().hasItemMeta()) {

				} else {
					// assume that it's something the player is selling

				}
			*/
        }
    }

    public enum InventorySize {
        LARGE(45);

        private final int size;

        InventorySize(int i) {
            this.size = i;
        }

    }
}
