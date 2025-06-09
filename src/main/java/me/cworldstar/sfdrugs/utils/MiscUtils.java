package me.cworldstar.sfdrugs.utils;

import java.util.ArrayList;

public class MiscUtils {

    public static int[] IntegerRange(int min, int max) {

        ArrayList<Integer> range = new ArrayList<>();

        for (int i = 0; i <= max; i++) {
            range.add(i);
        }

        return range.stream().mapToInt(i -> i).toArray();

    }

    public static int PositiveInteger(int integer) {

        int neg_check = Integer.signum(integer);

        if (neg_check == -1) {
            return (integer * -1);
        }
        return integer;


    }

}
