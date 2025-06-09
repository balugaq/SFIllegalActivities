package me.cworldstar.sfdrugs.implementations;

import io.github.mooy1.infinitylib.machines.AbstractMachineBlock;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.generators.DarkMatter;
import me.cworldstar.sfdrugs.utils.Items;
import me.cworldstar.sfdrugs.utils.MiscUtils;
import me.cworldstar.sfdrugs.utils.Speak;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class NaniteSynthesizer extends AbstractMachineBlock implements EnergyNetComponent, DarkMatter {

    private static final long DarkMatterConsumeRate = 100;
    private int processedTicks;

    public NaniteSynthesizer(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public boolean process(Block b, BlockMenu menu) {
        // TODO Auto-generated method stub
        int maxX = SFDrugs.config().getInt("naniteSynthesizer.maxX");
        int maxZ = SFDrugs.config().getInt("naniteSynthesizer.maxZ");
        if (maxX >= MiscUtils.PositiveInteger(b.getLocation().getBlockX()) && maxZ >= MiscUtils.PositiveInteger(b.getLocation().getBlockZ())) {
            //if ( this.hasDarkMatter(b) && this.getDarkMatter(b) >= NaniteSynthesizer.DarkMatterConsumeRate) {
            this.processedTicks += 10;
            if (this.processedTicks >= 1000) {
                menu.pushItem(Items.NANITES, this.getOutputSlots());
                return true;
            }
            //}

        }

        //this.shouldMeltDown(b);

        return false;
    }


    @Override
    public int getStatusSlot() {
        // TODO Auto-generated method stub
        return 4;
    }

    @Override
    public void setup(BlockMenuPreset preset) {
        // TODO Auto-generated method stub
        this.processedTicks = 0;
        preset.drawBackground(MiscUtils.IntegerRange(0, 8));
        preset.drawBackground(new int[]{9, 17});
        preset.addMenuClickHandler(40, ChestMenuUtils.getEmptyClickHandler());
    }

    @Override
    public int[] getInputSlots() {
        // TODO Auto-generated method stub
        return new int[]{
                10
        };
    }

    @Override
    public int[] getOutputSlots() {
        // TODO Auto-generated method stub
        return new int[]{
                11
        };
    }

    @Override
    public SFDrugs getPlugin() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onExplode(Block b) {
        // TODO Auto-generated method stub
        Location l = b.getLocation();
        b.getWorld().createExplosion(l, this.processedTicks * 10);
    }

    @Override
    public void onBreakdown(Block b, double integrity) {
        // TODO Auto-generated method stub
        ArmorStand MessageSender = (ArmorStand) b.getWorld().spawnEntity(b.getLocation(), EntityType.ARMOR_STAND);
        MessageSender.setVisible(false);
        MessageSender.setCustomName(Speak.format("&6[ Nanite Synthesizer ]"));
        Speak.AOEMessage(MessageSender, "&6[ Nanite Synthesizer ]: WARNING! MACHINE BREAKING DOWN. Current Integrity: " + integrity + ".", 20);
        MessageSender.remove();
    }

}
