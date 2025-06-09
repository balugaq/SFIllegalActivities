package me.cworldstar.sfdrugs.implementations.traders;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.TradingRecipe;
import me.cworldstar.sfdrugs.implementations.gui.ATradingInterface;
import me.cworldstar.sfdrugs.implementations.gui.ATradingInterface.InventorySize;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Base class for all traders. Handles trading recipes. Use getTradingInterface() for the global trading interface. Otherwise, use newTradingInterface() for many trading interfaces.
 * Unsure whether it will cause issues.
 *
 * @param String TraderIdentity
 * @author cworldstar
 * @see me.cworldstar.sfdrugs.implementations.traders.MysteriousTrader
 * @see me.cworldstar.sfdrugs.implementations.traders.HookerZombie
 */
public class ATrader implements Listener {

    public static final Map<String, ATrader> Traders = new HashMap<>();
    private final ArrayList<TradingRecipe> Recipes = new ArrayList<>();
    private final String TraderId;
    private final ATradingInterface TradingInterface;

    public ATrader(String TraderId) {
        this.TraderId = TraderId;
        this.TradingInterface = new ATradingInterface(InventorySize.LARGE, new ItemStack(Material.BLACK_STAINED_GLASS_PANE), this);
        this.TradingInterface.PopulateInventory();
        ATrader.Traders.put(TraderId, this);
    }

    @Nonnull
    public static ATrader TraderFromEntity(Entity rightClicked) {

        String TraderTag = rightClicked.getPersistentDataContainer().get(SFDrugs.createKey("TraderTag"), PersistentDataType.STRING);
        if (TraderTag != null) {
            return ATrader.Traders.get(TraderTag);
        }
        return null;
    }

    public static ATrader TraderFromString(String string) {
        // TODO Auto-generated method stub
        return ATrader.Traders.get(string);
    }

    public void RegisterTradingRecipe(TradingRecipe t) {
        if (t.getTradingRecipe().Validate()) {
            Recipes.add(t);
        }
    }

    public ATradingInterface getTradingInterface() {
        return this.TradingInterface;
    }

    public String getTraderId() {
        return this.TraderId;
    }

    public TradingRecipe GetRecipeFromItem(ItemStack Item, int Amount) {
        for (TradingRecipe T : Recipes) {
            if (T.getHave().equals(new CustomItemStack(Item, Amount))) {
                return T;
            }
        }
        return null;
    }

    public boolean ItemIsRecipe(ItemStack Item, int Amount) {
        for (TradingRecipe T : Recipes) {
            if (T.getHave().equals(new CustomItemStack(Item, Amount))) {
                Logger.getGlobal().log(Level.INFO, "===========================");
                Logger.getGlobal().log(Level.INFO, Item.toString());
                Logger.getGlobal().log(Level.INFO, Integer.toString(Amount));
                Logger.getGlobal().log(Level.INFO, "===========================");
                return true;
            }
        }
        return false;
    }

    public ArrayList<TradingRecipe> getAllRecipes() {
        return Recipes;
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player p = event.getPlayer();
        if (!event.getRightClicked().hasMetadata("SFDRUGS_IS" + this.TraderId.toUpperCase()) & !(event.getHand() == EquipmentSlot.HAND)) {
            event.setCancelled(true);
        } else if (event.getRightClicked().hasMetadata("SFDRUGS_IS" + this.TraderId.toUpperCase()) & event.getHand() == EquipmentSlot.HAND) {
            // stole this
            float yaw = (float) Math.toDegrees(Math.atan2(
                    p.getLocation().getZ() - event.getRightClicked().getLocation().getZ(), p.getLocation().getX() - event.getRightClicked().getLocation().getX())) - 90;
            event.getRightClicked().setRotation(yaw, event.getRightClicked().getLocation().getPitch());
            p.setMetadata("SFDRUGS_PLAYER_IS_RIGHTCLICKING_TRADER", new FixedMetadataValue(SFDrugs.getPlugin(SFDrugs.class), true));
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.removeMetadata("SFDRUGS_PLAYER_IS_RIGHTCLICKING_TRADER", SFDrugs.getPlugin(SFDrugs.class));
                }
            }.runTaskLater(SFDrugs.getPlugin(SFDrugs.class), 20L);
            ATrader.Traders.get(this.TraderId).getTradingInterface().Display(p);
        }

    }

    public ArrayList<TradingRecipe> getRecipes(ArrayList<ItemStack> itemsInHaveSlot) {
        // TODO Auto-generated method stub
        ArrayList<TradingRecipe> recipes = new ArrayList<>();
        for (ItemStack item : itemsInHaveSlot) {
            if (item.getItemMeta().getPersistentDataContainer().has(SFDrugs.createKey("tti_input"), PersistentDataType.INTEGER)) {
                CustomItemStack ComparableItem;
                if (SlimefunItem.getByItem(item) != null) {
                    ComparableItem = new CustomItemStack(SlimefunItem.getByItem(item).getItem(), item.getAmount());
                } else {
                    ComparableItem = new CustomItemStack(new ItemStack(item.getType(), item.getAmount()));
                }

                for (TradingRecipe r : Recipes) {
                    Logger.getGlobal().log(Level.WARNING, Boolean.toString(r.getHave().isSimilar(ComparableItem)));
                    Logger.getGlobal().log(Level.WARNING, Integer.toString(ComparableItem.getAmount()));
                    Logger.getGlobal().log(Level.WARNING, Integer.toString(r.getHave().getAmount()));

                    if (ComparableItem.isSimilar(r.getHave()) && r.getHave().getAmount() <= ComparableItem.getAmount()) {
                        Logger.getGlobal().log(Level.WARNING, "matches!");
                        item.getItemMeta().getPersistentDataContainer().set(SFDrugs.createKey("tti_input"), PersistentDataType.INTEGER, 1);
                        recipes.add(r);
                    }
                }


            }
        }
        return recipes;
    }

    public TradingRecipe GetRecipeFromOutput(ItemStack currentItem) {
        CustomItemStack ComparableItem = new CustomItemStack(SlimefunItem.getByItem(currentItem).getItem(), currentItem.getAmount());
        for (TradingRecipe recipe : Recipes) {
            if (recipe.getFor().isSimilar(ComparableItem)) {
                return recipe;
            }
        }
        return null;
    }

    public ATradingInterface newTradingInterface() {
        // TODO Auto-generated method stub
        return new ATradingInterface(InventorySize.LARGE, new ItemStack(Material.BLACK_STAINED_GLASS_PANE), this);
    }
}
