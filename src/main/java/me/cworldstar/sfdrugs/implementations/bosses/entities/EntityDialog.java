package me.cworldstar.sfdrugs.implementations.bosses.entities;

import me.cworldstar.sfdrugs.utils.RandomUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntityDialog {

    private final Map<Personality, ArrayList<String>> Dialog = new HashMap<>();
    private final String EntityName;
    private final Personality EntityPersonality;

    public EntityDialog(String FormattedEntityName, Personality Personality) {

        if (Personality.equals(EntityDialog.Personality.RANDOM)) {
            switch (RandomUtils.nextInt(3)) {
                case 1:
                    this.EntityPersonality = EntityDialog.Personality.SAD;
                    break;
                case 2:
                    this.EntityPersonality = EntityDialog.Personality.NEUTRAL;
                    break;
                case 3:
                    this.EntityPersonality = EntityDialog.Personality.HAPPY;
                    break;
                case 0:
                default:
                    this.EntityPersonality = EntityDialog.Personality.AGGRESSIVE;
                    break;

            }
        } else {
            this.EntityPersonality = Personality;
        }
        this.EntityName = FormattedEntityName;
        this.Dialog.putIfAbsent(EntityDialog.Personality.AGGRESSIVE, new ArrayList<>());
        this.Dialog.putIfAbsent(EntityDialog.Personality.SAD, new ArrayList<>());
        this.Dialog.putIfAbsent(EntityDialog.Personality.NEUTRAL, new ArrayList<>());
        this.Dialog.putIfAbsent(EntityDialog.Personality.HAPPY, new ArrayList<>());


        //THIS SHOULD NEVER BE ACCESSED! HERE FOR PLAUSIBLE DENIABILITY!
        this.Dialog.putIfAbsent(EntityDialog.Personality.RANDOM, new ArrayList<>());
    }

    public String randomDialog() {
        return EntityName.concat(this.Dialog.get(this.EntityPersonality).get(RandomUtils.nextInt(this.Dialog.get(this.EntityPersonality).size())));
    }

    public void registerDialog(Personality P, String formattedDialog) {
        this.Dialog.get(P).add(formattedDialog);
    }

    public Personality getPersonality() {
        return this.EntityPersonality;
    }

    public void registerAllDialogs(Personality P, ArrayList<String> dialogs) {
        // TODO Auto-generated method stub
        for (String dialog : dialogs) {
            this.Dialog.get(P).add(dialog);
        }
    }

    public void registerAllDialogs(Personality personality, String[] dialogList) {
        // TODO Auto-generated method stub
        for (String dialog : dialogList) {
            this.Dialog.get(personality).add(dialog);
        }
    }

    public enum Personality {
        AGGRESSIVE,
        SAD,
        NEUTRAL,
        HAPPY,
        RANDOM
    }

}
