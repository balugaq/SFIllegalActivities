package me.cworldstar.sfdrugs.events;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.loot.CorporationEnemyLootTable;
import me.cworldstar.sfdrugs.implementations.loot.EscapedTestSubjectLootTable;
import me.cworldstar.sfdrugs.implementations.loot.SmallerGangMemberLootTable;
import me.cworldstar.sfdrugs.utils.Items;
import me.cworldstar.sfdrugs.utils.RandomUtils;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.loot.LootContext;

public class CustomMobDeathEvent implements Listener {
    private final SFDrugs plugin;

    public CustomMobDeathEvent(SFDrugs plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {
        LivingEntity killedMob = e.getEntity();
        if (killedMob.hasMetadata("SFDRUGS_CUSTOM_MOB")) {
            String Identifier = killedMob.getMetadata("SFDRUGS_CUSTOM_MOB").get(0).asString();
            e.getDrops().clear();
            switch (Identifier) {
                case "red_wolves_trainee":
                    e.getDrops().add(new CustomItemStack(Items.UNCOMMON_CHEST, 1));
                    e.getDrops().addAll(new SmallerGangMemberLootTable(this.plugin).populateLoot(RandomUtils.getRandom(), new LootContext.Builder(killedMob.getLocation()).build()));
                    break;
                case "corporate_security_robot":
                    e.getDrops().add(new CustomItemStack(Items.RARE_CHEST, 1));

                    e.getDrops().addAll(new CorporationEnemyLootTable(this.plugin).populateLoot(RandomUtils.getRandom(), new LootContext.Builder(killedMob.getLocation()).build()));
                    break;
                case "escaped_test_subject":
                    //TODO: Loot table for this guy.
                    e.setDroppedExp(7640000);
                    e.getDrops().add(new CustomItemStack(Items.RARE_CHEST, 3));
                    e.getDrops().addAll(new EscapedTestSubjectLootTable(this.plugin).populateLoot(RandomUtils.getRandom(), new LootContext.Builder(killedMob.getLocation()).build()));
                    break;
                case "red_wolves_gangster":
                    e.getDrops().add(new CustomItemStack(Items.UNCOMMON_CHEST, 2));
                    //TODO: Loot table for this guy.

                    break;
                default:
                    break;
            }
        }
    }
}
