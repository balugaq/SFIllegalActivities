package me.cworldstar.sfdrugs.implementations.bosses.entities;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.loot.CorporationEnemyLootTable;
import me.cworldstar.sfdrugs.utils.Items;
import me.cworldstar.sfdrugs.utils.Speak;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CorporationEnemy {
    private static Zombie Zombie;

    public CorporationEnemy(SFDrugs Plugin, Zombie z) {
        z.setCustomName(ChatColor.translateAlternateColorCodes('&', "&7&lCorporate Security Robot"));
        z.setCanPickupItems(false);
        z.setMaxHealth(300.0);
        z.setHealth(300.0);
        z.setRemoveWhenFarAway(false);
        z.setMetadata("SFDRUGS_CUSTOM_MOB", new FixedMetadataValue(Plugin, "corporate_security_robot"));
        z.setAdult();
        z.setCanPickupItems(false);
        z.setAbsorptionAmount(200.0);
        z.setLootTable(new CorporationEnemyLootTable(Plugin));
        BossBar EnemyBossBar = Bukkit.getServer().createBossBar(ChatColor.translateAlternateColorCodes('&', "&a&lCorporate Security Robot"), BarColor.WHITE, BarStyle.SEGMENTED_12, BarFlag.DARKEN_SKY, BarFlag.CREATE_FOG);
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
        }.runTaskTimer(Plugin, 0L, 20L);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (z.isDead()) {
                    this.cancel();
                } else if (z.hasAI() & z.getTarget() != null) {
                    int RandomNumber = new Random().nextInt(3);
                    switch (RandomNumber) {
                        case 0:
                            break;
                        case 1:
                            new Speak(z, z.getNearbyEntities(10.0, 10.0, 10.0), "&7&l[ Corporate Security Robot ]:&r &7Target Confirmed.");
                            z.getWorld().playSound(z.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 1F, 0.5F);
                            z.getWorld().spawnParticle(Particle.DRAGON_BREATH, z.getLocation(), 30, 6, 2, 6);
                            z.getWorld().createExplosion(z.getTarget().getLocation().add(new Location(z.getWorld(), z.getTarget().getLocation().getX(), z.getTarget().getLocation().getY() + 2, z.getTarget().getLocation().getZ())), 2L, false, false, z);
                            break;
                        case 2:
                            new Speak(z, z.getNearbyEntities(10.0, 10.0, 10.0), "&7&l[ Corporate Security Robot ]:&r &7Damage Detected. Beginning self repair.");
                            z.setAI(false);
                            z.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub
                                    z.setAI(true);
                                    z.setInvulnerable(true);
                                    z.getWorld().playSound(z.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1F, 0.5F);
                                    z.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, z.getLocation(), 30, 6, 2, 6);
                                    z.getWorld().createExplosion(z.getLocation(), 8L, false, false, z);
                                    z.setInvulnerable(false);
                                }

                            }.runTaskLater(Plugin, 100L);
                            break;
                        case 3:
                            new Speak(z, z.getNearbyEntities(10.0, 10.0, 10.0), "&7&l[ Corporate Security Robot ]:&r &7Enemy located. Subjugating...");
                            Snowball LaserProjectile = z.launchProjectile(Snowball.class);
                            LaserProjectile.setItem(new ItemStack(Material.DIAMOND_SWORD));
                            Vector source = z.getLocation().getDirection().normalize().multiply(50);
                            Vector v = z.getTarget().getLocation().toVector().subtract(source);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    LaserProjectile.setVelocity(v);
                                    LaserProjectile.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, LaserProjectile.getLocation(), 4);
                                    if (LaserProjectile.isDead()) {
                                        this.cancel();
                                    }
                                }
                            }.runTaskTimer(Plugin, 0L, 5L);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    LaserProjectile.remove();
                                }
                            }.runTaskLater(Plugin, 100L); // Remove after 10 seconds
                            break;
                    }


                }
            }

        }.runTaskTimer(Plugin, 0L, 200L);
        z.getEquipment().setArmorContents(new ItemStack[]{
                Items.CORPORATION_ROBOT_BOOTS,
                Items.CORPORATION_ROBOT_LEGGINGS,
                Items.CORPORATION_ROBOT_CHESTPLATE,
                Items.CORPORATION_ROBOT_HELMET,
        });
        z.getEquipment().setItemInMainHand(Items.CORPORATION_LASER_SWORD);
        z.getEquipment().setItemInOffHand(Items.CORPORATION_LASER_SWORD);
        CorporationEnemy.Zombie = z;
    }

    public Zombie GetEnemy() {
        return CorporationEnemy.Zombie;
    }
}
