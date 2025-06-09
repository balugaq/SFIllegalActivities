package me.cworldstar.sfdrugs.implementations.bosses.entities;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.bosses.entities.EntityDialog.Personality;
import me.cworldstar.sfdrugs.implementations.loot.CorporationEnemyLootTable;
import me.cworldstar.sfdrugs.implementations.loot.SmallerGangMemberLootTable;
import me.cworldstar.sfdrugs.utils.Constants;
import me.cworldstar.sfdrugs.utils.Speak;
import me.cworldstar.sfdrugs.utils.Texts;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Level;

public class CorporateScout extends BossEntity {


    public CorporateScout(SFDrugs plugin, World w, Location l) {

        super(EntityType.ZOMBIE, w, l, Constants.corporate_scout);
        EntityDialog DialogManager = this.registerDialogs();


        // CorporateScout skills

        CorporateScout scout = this;

        //Dialog handler

        new BukkitRunnable() {

            final Zombie z = (Zombie) scout.getEntity();

            @Override
            public void run() {
                if (scout.getEntity().isDead()) {
                    this.cancel();
                } else if (z.getTarget() == null) {
                    SFDrugs.log(Level.WARNING, DialogManager.toString());
                    new Speak(z, z.getNearbyEntities(20, 20, 20), DialogManager.randomDialog());
                }
            }
        }.runTaskTimer(plugin, 0, 200L);

    }


    /**
     * duplicate class for spawning based on normal mob spawning
     *
     * @see me.cworldstar.sfdrugs.events.GangMemberSpawnEvent
     */
    public CorporateScout(SFDrugs plugin, Zombie entity) {

        super(entity, Constants.corporate_scout);
        EntityDialog DialogManager = this.registerDialogs();
        CorporateScout scout = this;

        //Dialog handler

        new BukkitRunnable() {

            final Zombie z = (Zombie) scout.getEntity();

            @Override
            public void run() {
                if (scout.getEntity().isDead()) {
                    this.cancel();
                } else if (z.getTarget() == null) {
                    SFDrugs.log(Level.WARNING, DialogManager.toString());
                    new Speak(z, z.getNearbyEntities(20, 20, 20), DialogManager.randomDialog());
                }
            }
        }.runTaskTimer(plugin, 0, 200L);
    }

    @Override
    public EntityDialog registerDialogs() {
        EntityDialog DialogManager = new EntityDialog(Texts.cs_1, Personality.RANDOM);
        // Neutral personality dialog
        DialogManager.registerAllDialogs(Personality.NEUTRAL, new String[]{
                Texts.cs_2,
                Texts.cs_3,
                Texts.cs_4
        });
        // Aggressive personality dialog
        DialogManager.registerAllDialogs(Personality.AGGRESSIVE, new String[]{
                Texts.cs_5,
                Texts.cs_6,
                Texts.cs_7
        });
        // Sad personality dialog
        DialogManager.registerAllDialogs(Personality.SAD, new String[]{
                Texts.cs_8,
                Texts.cs_9,
                Texts.cs_10
        });
        // Happy personality dialog
        DialogManager.registerAllDialogs(Personality.HAPPY, new String[]{
                Texts.cs_11,
                Texts.cs_12,
                Texts.cs_13
        });
        return DialogManager;
    }

    @Override
    public void applyEntityEdits(SFDrugs plugin, Zombie z) {
        // TODO Auto-generated method stub
        z.setCustomName(ChatColor.translateAlternateColorCodes('&', Texts.cs_1));
        z.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(75);
        z.setHealth(75.0);
        z.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999, 0));
        z.setAbsorptionAmount(100);
        z.setAdult();
        z.setCanPickupItems(false);
        z.setMetadata(Constants.SfDrugsCustomMob, new FixedMetadataValue(plugin, Constants.corporate_scout));
        z.setLootTable(new CorporationEnemyLootTable(plugin));
        ItemStack ZombieHead = SlimefunUtils.getCustomHead(Constants.cs_head_1);
        ItemStack Boots = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemMeta BootsMeta = Boots.getItemMeta();
        BootsMeta.setUnbreakable(true);
        Boots.setItemMeta(BootsMeta);
        ItemStack Leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemMeta LeggingsMeta = Leggings.getItemMeta();
        LeggingsMeta.setUnbreakable(true);
        Leggings.setItemMeta(LeggingsMeta);
        ItemStack Chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta ChestplateMeta = Chestplate.getItemMeta();
        ChestplateMeta.setUnbreakable(true);
        Chestplate.setItemMeta(ChestplateMeta);
        this.setArmor(
                ZombieHead,
                Chestplate,
                Leggings,
                Boots
        );
    }


    @Override
    public void addDialog(String dialog) {
        // TODO Auto-generated method stub

    }
}
