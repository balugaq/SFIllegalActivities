package me.cworldstar.sfdrugs.implementations.bosses.entities.CorporateLeader;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.bosses.entities.BossBarHandler;
import me.cworldstar.sfdrugs.implementations.bosses.entities.BossEntity;
import me.cworldstar.sfdrugs.implementations.bosses.entities.CorporateWorker;
import me.cworldstar.sfdrugs.implementations.bosses.entities.EntityDialog;
import me.cworldstar.sfdrugs.implementations.bosses.entities.EntityDialog.Personality;
import me.cworldstar.sfdrugs.implementations.bosses.entities.Skill;
import me.cworldstar.sfdrugs.implementations.loot.CorporateLeaderLootTable;
import me.cworldstar.sfdrugs.utils.Constants;
import me.cworldstar.sfdrugs.utils.RandomUtils;
import me.cworldstar.sfdrugs.utils.Speak;
import me.cworldstar.sfdrugs.utils.Texts;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class CorporateLeader extends BossEntity {

    public CorporateLeader(World world, Location location) {
        super(EntityType.ZOMBIE, world, location, Constants.corporate_leader);
        SFDrugs plugin = SFDrugs.getPlugin(SFDrugs.class);
        this.addSkillToEntity(this.SummonCorporateWorkers(world));
        this.setArmor(null, null, null, null);
        Zombie entity = (Zombie) this.getEntity();
        CorporateLeader Entity = this;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                } else if (entity.getTarget() == null) {
                    new Speak(entity, entity.getNearbyEntities(20, 20, 20), RandomUtils.selectRandom(CorporateLeader.getIdleDialog()));
                } else if (entity.getTarget() != null && entity.getTarget() instanceof Player) {
                    new Speak(entity, entity.getNearbyEntities(20, 20, 20), RandomUtils.selectRandom(CorporateLeader.getAttackingDialog((Player) entity.getTarget())));
                    Entity.useRandomSkill();

                }
            }
        }.runTaskTimer(plugin, 0, 200L);
        this.applyEntityEdits(plugin, entity);
    }

    protected static List<String> getIdleDialog() {
        // TODO Auto-generated method stub
        return null;
    }

    protected static List<String> getAttackingDialog(Player target) {
        List<String> AttackingDialog = new ArrayList<>();
        AttackingDialog.add(Speak.format(Texts.cl_1));
        return AttackingDialog;
    }

    @Override
    public EntityDialog registerDialogs() {
        EntityDialog DialogManager = new EntityDialog(Texts.cl_2, Personality.NEUTRAL);
        // Neutral personality dialog
        DialogManager.registerAllDialogs(Personality.NEUTRAL, new String[]{
                Texts.cl_3,
                Texts.cl_4,
                Texts.cl_5,
                Texts.cl_6,

        });
        return DialogManager;
    }

    public void applyEntityEdits(SFDrugs plugin, Zombie z) {
        z.setCustomName(ChatColor.translateAlternateColorCodes('&', Texts.cl_7_bbh_1));
        z.setCanPickupItems(false);
        z.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(3000);
        z.setHealth(z.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        z.setRemoveWhenFarAway(false);
        z.setMetadata(Constants.SfDrugsCustomMob, new FixedMetadataValue(plugin, Constants.corporate_executive));
        z.setAdult();
        z.setCanPickupItems(false);
        z.setLootTable(new CorporateLeaderLootTable(plugin));
        new BossBarHandler(z);
    }

    public Skill SummonCorporateWorkers(World w) {

        LivingEntity e = this.getEntity();
        SFDrugs plugin = SFDrugs.getPlugin(SFDrugs.class);

        return new Skill(Skill.SkillType.ACTIVE, () -> {
            // TODO Auto-generated method stub
            for (int i = 0; i <= RandomUtils.nextInt(4); i++) {
                Zombie z = (Zombie) w.spawnEntity(e.getLocation().add(RandomUtils.RandomLocation(w, 5, 0, 5)), EntityType.ZOMBIE);
                CorporateWorker Worker = new CorporateWorker(plugin, z);
            }
        });
    }

    public Skill SelfHeal(World w) {
        LivingEntity e = this.getEntity();
        SFDrugs plugin = SFDrugs.getPlugin(SFDrugs.class);

        return new Skill(Skill.SkillType.SEQUENCED, new BukkitRunnable() {
            final
            int times_ran = 0;
            final
            Speak speak = new Speak(e, e.getNearbyEntities(20, 20, 20), Speak.format(Texts.cl_8));

            @Override
            public void run() {
                if (times_ran < 5) {
                    e.setHealth(e.getHealth() + (e.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() * 0.05));
                }
            }
        });

    }

    @Override
    public void addDialog(String dialog) {
        // TODO Auto-generated method stub

    }

}
