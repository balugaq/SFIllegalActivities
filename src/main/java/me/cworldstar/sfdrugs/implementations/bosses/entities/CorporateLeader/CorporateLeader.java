package me.cworldstar.sfdrugs.implementations.bosses.entities.CorporateLeader;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.bosses.entities.BossBarHandler;
import me.cworldstar.sfdrugs.implementations.bosses.entities.BossEntity;
import me.cworldstar.sfdrugs.implementations.bosses.entities.CorporateWorker;
import me.cworldstar.sfdrugs.implementations.bosses.entities.EntityDialog;
import me.cworldstar.sfdrugs.implementations.bosses.entities.EntityDialog.Personality;
import me.cworldstar.sfdrugs.implementations.bosses.entities.Skill;
import me.cworldstar.sfdrugs.implementations.loot.CorporateLeaderLootTable;
import me.cworldstar.sfdrugs.utils.RandomUtils;
import me.cworldstar.sfdrugs.utils.Speak;
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
        super(EntityType.ZOMBIE, world, location, "corporate_leader");
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
        AttackingDialog.add(Speak.format("&8&l[ &e&lSundowner &8&l ]:&r &cI'm fucking invincible!"));
        return AttackingDialog;
    }

    @Override
    public EntityDialog registerDialogs() {
        EntityDialog DialogManager = new EntityDialog("&7Corporate Leader", Personality.NEUTRAL);
        // Neutral personality dialog
        DialogManager.registerAllDialogs(Personality.NEUTRAL, new String[]{
                "&7These poor fools...",
                "&7They'll never pay off their debt.",
                "&7I'll never be satisfied.",
                "&7Mysterious trader... Who are you really?",

        });
        return DialogManager;
    }

    public void applyEntityEdits(SFDrugs plugin, Zombie z) {
        z.setCustomName(ChatColor.translateAlternateColorCodes('&', "&d&lCorporate Executive"));
        z.setCanPickupItems(false);
        z.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(3000);
        z.setHealth(z.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        z.setRemoveWhenFarAway(false);
        z.setMetadata("SFDRUGS_CUSTOM_MOB", new FixedMetadataValue(plugin, "corporate_executive"));
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
                @SuppressWarnings("unused")
                CorporateWorker Worker = new CorporateWorker(plugin, z);
            }
        });
    }

    public Skill SelfHeal(World w) {
        LivingEntity e = this.getEntity();
        SFDrugs plugin = SFDrugs.getPlugin(SFDrugs.class);

        return new Skill(Skill.SkillType.SEQUENCED, new BukkitRunnable() {
            @SuppressWarnings("unused")
            final
            int times_ran = 0;
            @SuppressWarnings("unused")
            final
            Speak speak = new Speak(e, e.getNearbyEntities(20, 20, 20), Speak.format("&d&l[Corporate Executive]:&r &dDamage detected to exterior. Administrating first aid."));

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
