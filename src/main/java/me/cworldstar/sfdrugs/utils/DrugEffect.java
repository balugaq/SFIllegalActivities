package me.cworldstar.sfdrugs.utils;

import org.bukkit.potion.PotionEffectType;

public class DrugEffect {

    private final int PotionEndTime;
    private final int amplifier;
    private final int interval;
    private final int NauseaBegin;
    private final int NauseaEnd;

    public DrugEffect(PotionEffectType type, int PotionEndTime, int amplifier, int interval, int NauseaBegin, int NauseaEnd) {
        // TODO Auto-generated constructor stub
        this.PotionEndTime = PotionEndTime;
        this.amplifier = amplifier;
        this.interval = interval;
        this.NauseaBegin = NauseaBegin;
        this.NauseaEnd = NauseaEnd;
    }

    private void LingeringPotionListener() {

    }
}
