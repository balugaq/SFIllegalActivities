package me.cworldstar.sfdrugs.implementations.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PlayerInventoryItemAddedEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final List<ItemStack> item;
    private final Inventory inventory;
    private boolean cancelled;

    public PlayerInventoryItemAddedEvent(Player who, Inventory i, List<ItemStack> items) {
        super(who);
        this.item = items;
        this.inventory = i;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        // TODO Auto-generated method stub
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        // TODO Auto-generated method stub
        this.cancelled = true;
        this.cancel();
    }

    public void cancel() {
        this.cancelled = true;
    }

    @Override
    public HandlerList getHandlers() {
        // TODO Auto-generated method stub
        return PlayerInventoryItemAddedEvent.handlers;
    }

    public List<ItemStack> getItem() {
        return this.item;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

}
