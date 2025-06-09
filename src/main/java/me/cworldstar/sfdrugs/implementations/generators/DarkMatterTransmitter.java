package me.cworldstar.sfdrugs.implementations.generators;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.utils.Constants;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class DarkMatterTransmitter extends SlimefunItem implements DarkMatter, Listener {

    public SFDrugs plugin;

    public DarkMatterTransmitter(SFDrugs plugin, ItemGroup category, SlimefunItemStack item, RecipeType recipeType,
                                 ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        // TODO Auto-generated constructor stub
        if (item.getItem() instanceof Block) {
            ((Block) item).setMetadata(Constants.dark_matter_transmitter, new FixedMetadataValue(plugin, true));
        }
    }

    public void tickTransmit() {

    }

    @Override
    public void preRegister() {
        this.addItemHandler(new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(BlockPlaceEvent e) {
                // TODO Auto-generated method stub
                //Block PlacedBlock = this.

            }
        });
    }


    @Override
    public SFDrugs getPlugin() {
        // TODO Auto-generated method stub
        return null;
    }

    public Long getDarkMatterCapacity() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void onExplode(Block b) {
        // TODO Auto-generated method stub

    }


    @Override
    public void onBreakdown(Block b, double integrity) {
        // TODO Auto-generated method stub

    }


}
 