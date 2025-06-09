package me.cworldstar.sfdrugs.utils;


import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class WorldEater extends SlimefunItem implements Radioactive, DamageableItem, Rechargeable {
    public WorldEater(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Radioactivity getRadioactivity() {
        return Radioactivity.VERY_DEADLY;
    }

    public float getMaxItemCharge(ItemStack item) {
        return 12000F;
    }

    @Override
    public void preRegister() {
        ToolUseHandler One = this::getToolHandler;
        addItemHandler(One);

        ItemUseHandler Two = this::getRightClickHandler;
        addItemHandler(Two);
    }

    public void getToolHandler(BlockBreakEvent e, ItemStack tool, int IDK, List<ItemStack> IDKEither) {
        Player p = e.getPlayer();
        if (this.removeItemCharge(tool, 2F)) {
            Block b = e.getBlock();
            b.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, b.getLocation(), 8);
            b.getWorld().playSound(b.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.2F, 1F);
                    /*if(p.isSneaking()) {
                    	int currentItemStack = 0;
                    	for(ItemStack i : drops) {
                    		if(i.getItemMeta().getDisplayName().contains("Ore")) {
                    			drops.set(i,i.)
                    		}
                    		currentItemStack += 1;
                    	}
                    }*/
            b.breakNaturally(tool);
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[ SFDrugs ]: &dYour tool has run out of power!"));
            e.setCancelled(true);
        }
    }

    public void getRightClickHandler(PlayerRightClickEvent e) {
        if (e.getItem().containsEnchantment(Enchantment.SILK_TOUCH)) {
            e.getItem().removeEnchantment(Enchantment.SILK_TOUCH);
            new Speak(e.getPlayer(), "&e=> You have disabled silk touch.");
        } else {
            e.getItem().addEnchantment(Enchantment.SILK_TOUCH, 1);
            new Speak(e.getPlayer(), "&e=> You have enabled silk touch.");
        }
    }

    @Override
    public boolean isDamageable() {
        // TODO Auto-generated method stub
        return false;
    }
}
