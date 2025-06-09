package me.cworldstar.sfdrugs.utils;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;
import java.util.Random;

public class RandomUtils {
    private static Random random;

    public RandomUtils() {
        RandomUtils.random = new Random();
    }

    public static Random getRandom() {
        return RandomUtils.random;
    }

    public static Integer nextInt(int i) {
        if (Integer.signum(i) == -1) {
            return RandomUtils.random.nextInt((i * -1));
        } else {
            return RandomUtils.random.nextInt(i);
        }
    }

    public static String selectRandom(List<String> idleDialog) {
        return idleDialog.get(RandomUtils.random.nextInt(idleDialog.size()));
    }

    //public static Block[] getSurroundingBlocks(Location l) {
    //	Block[] BlockList = {};
    //	Block origin = l.getBlock();
    //	for(int i; i<=3; i++) {
    //		BlockList
    //	}ppp
    //
    //	return;
    //}
    public static Location RandomLocation(World w, int i, int j, int k) {
        // TODO Auto-generated method stub
        return new Location(w, RandomUtils.nextInt(i + i) - i, RandomUtils.nextInt((j + j) + 1) - j, RandomUtils.nextInt(k + k) - k);
    }
}
