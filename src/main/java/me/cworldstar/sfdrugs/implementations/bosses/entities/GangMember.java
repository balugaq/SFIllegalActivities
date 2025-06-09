package me.cworldstar.sfdrugs.implementations.bosses.entities;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.loot.CorporationEnemyLootTable;
import me.cworldstar.sfdrugs.utils.Constants;
import me.cworldstar.sfdrugs.utils.ParticleUtils;
import me.cworldstar.sfdrugs.utils.RandomUtils;
import me.cworldstar.sfdrugs.utils.Speak;
import me.cworldstar.sfdrugs.utils.Texts;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GangMember {
    public GangMember(SFDrugs plugin, Zombie z) {
        z.setCustomName(ChatColor.translateAlternateColorCodes('&', Texts.gm_1));
        z.setMaxHealth(750.0);
        z.setHealth(750.0);
        z.setRemoveWhenFarAway(false);
        z.setAdult();
        z.setMetadata(Constants.SfDrugsCustomMob, new FixedMetadataValue(plugin, Constants.red_wolves_gangster));
        z.setCanPickupItems(false);
        z.setLootTable(new CorporationEnemyLootTable(plugin));
        BossBar EnemyBossBar = Bukkit.getServer().createBossBar(ChatColor.translateAlternateColorCodes('&', Texts.gm_1), BarColor.RED, BarStyle.SEGMENTED_12);
        EnemyBossBar.setVisible(true);
        EnemyBossBar.setProgress(1.0);
        List<Player> Players = new ArrayList<>();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (z.isDead()) {
                    EnemyBossBar.setVisible(false);
                    this.cancel();
                } else {
                    double Health = Double.parseDouble(new DecimalFormat("#.###").format(z.getHealth() / z.getMaxHealth()));
                    EnemyBossBar.setProgress(Health);
                    for (Entity e : z.getNearbyEntities(20.0, 20.0, 20.0)) {
                        if (e instanceof Player) {
                            if (!Players.contains((Player) e)) {
                                Players.add((Player) e);
                                EnemyBossBar.addPlayer((Player) e);
                            }
                        }
                    }
                    for (Player p : EnemyBossBar.getPlayers()) {
                        if (!Players.contains(p)) {
                            EnemyBossBar.removePlayer(p);
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);


        ItemStack ZombieHead = SlimefunUtils.getCustomHead(Constants.gm_head_1);
        ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);

        LeatherArmorMeta BootsMeta = (LeatherArmorMeta) Boots.getItemMeta();
        new Speak(BootsMeta, Texts.ets_2_gm_2_sgm_4);
        BootsMeta.setColor(Color.RED);
        BootsMeta.setUnbreakable(true);
        Boots.setItemMeta(BootsMeta);

        ItemStack Leggings = new ItemStack(Material.LEATHER_LEGGINGS);

        LeatherArmorMeta LeggingsMeta = (LeatherArmorMeta) Leggings.getItemMeta();
        new Speak(LeggingsMeta, Texts.ets_3_gm_3_sgm_3);
        LeggingsMeta.setColor(Color.RED);
        LeggingsMeta.setUnbreakable(true);
        Leggings.setItemMeta(LeggingsMeta);

        ItemStack Chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        Chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 20);

        LeatherArmorMeta ChestplateMeta = (LeatherArmorMeta) Chestplate.getItemMeta();
        new Speak(ChestplateMeta, Texts.ets_2_gm_2_sgm_4);
        ChestplateMeta.setColor(Color.BLACK);
        ChestplateMeta.setUnbreakable(true);
        Chestplate.setItemMeta(ChestplateMeta);
        z.getEquipment().setArmorContents(new ItemStack[]{
                Boots,
                Leggings,
                Chestplate,
                ZombieHead
        });
        new BukkitRunnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (z.isDead()) {
                    this.cancel();
                }
                ParticleUtils.SpawnInCircle(z.getLocation().add(0, 1, 0), Particle.SOUL_FIRE_FLAME, 0.5, 30, 2);
            }

        }.runTaskTimer(plugin, 0, 5L);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (z.isDead()) {
                    this.cancel();
                } else if (z.hasAI() & z.getTarget() != null && z.getTarget() != null) {
                    int RandomNumber = new Random().nextInt(3);
                    switch (RandomNumber) {
                        case 1:
                            new Speak(z, z.getNearbyEntities(15.0, 15.0, 15.0), Texts.gm_4);
                            z.getTarget().teleport(z.getLocation().add(new Random().nextInt(3), 0, new Random().nextInt(3)));
                            z.getWorld().playSound(z.getTarget().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.5F, 0.5F);
                            z.setInvulnerable(true);
                            z.getWorld().createExplosion(z.getLocation(), 3, true, false);
                            z.setInvulnerable(false);
                            for (Entity entities : z.getNearbyEntities(3, 3, 3)) {
                                z.swingMainHand();
                                z.getTarget().setVelocity(z.getTarget().getLocation().toVector().normalize().multiply(-1).multiply(20));
                                if (entities instanceof LivingEntity) {
                                    ((LivingEntity) entities).damage(new Random().nextDouble() * 20, z);
                                }
                            }
                        case 2:
                            new Speak(z, z.getNearbyEntities(15.0, 15.0, 15.0), Texts.gm_5);
                            for (int i = 0; i <= 3; i++) {
                                LlamaSpit LaserProjectile = z.launchProjectile(LlamaSpit.class);
                                LaserProjectile.setMetadata(Constants.SfDrugsIsLaserProjectile, new FixedMetadataValue(plugin, RandomUtils.nextInt(10).floatValue()));
                                Vector source = z.getLocation().getDirection().normalize().multiply(50);
                                Vector v = z.getTarget().getLocation().toVector().subtract(source);
                                BukkitTask LaserProjectileVelocityTask = new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        LaserProjectile.setVelocity(v);
                                        LaserProjectile.getWorld().spawnParticle(Particle.DRAGON_BREATH, LaserProjectile.getLocation(), 2);
                                        if (LaserProjectile.isDead()) {
                                            this.cancel();
                                        }
                                    }
                                }.runTaskTimer(plugin, 0L, 1L);
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        if (!LaserProjectile.isDead()) {
                                            LaserProjectile.remove();
                                            this.cancel();
                                            return;
                                        }
                                        if (!LaserProjectileVelocityTask.isCancelled()) {
                                            LaserProjectileVelocityTask.cancel();
                                        }
                                        this.cancel();
                                    }
                                }.runTaskLater(plugin, 100L); // Remove after 5 seconds
                            }
                        case 0:
                        default:
                            break;
                    }
                }
            }

        }.runTaskTimer(plugin, 0L, 60L);
    }
}
