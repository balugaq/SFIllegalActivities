package me.cworldstar.sfdrugs.implementations.bosses.entities;

import me.cworldstar.sfdrugs.SFDrugs;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BossBarHandler {
    public BossBarHandler(Zombie z) {
        SFDrugs plugin = SFDrugs.getPlugin(SFDrugs.class);
        BossBar EnemyBossBar = Bukkit.getServer().createBossBar(ChatColor.translateAlternateColorCodes('&', "&d&lCorporate Executive"), BarColor.WHITE, BarStyle.SEGMENTED_12, BarFlag.PLAY_BOSS_MUSIC);
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
                    double Health = Double.parseDouble(new DecimalFormat("#.###").format(z.getHealth() / z.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()));
                    EnemyBossBar.setProgress(Health);
                    for (Entity e : z.getNearbyEntities(30.0, 30.0, 30.0)) {
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
        }.runTaskTimer(plugin, 0L, 10L);
    }
}
