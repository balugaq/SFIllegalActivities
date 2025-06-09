package me.cworldstar.sfdrugs.implementations.bosses.entities;

import me.cworldstar.sfdrugs.utils.RandomUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntityDialog {

    private final Map<Personality, ArrayList<String>> Dialog = new HashMap<>();
    private final String EntityName;
    private final Personality EntityPersonality;

    @SuppressWarnings("static-access")
    public EntityDialog(String FormattedEntityName, Personality Personality) {

        if (Personality.equals(Personality.RANDOM)) {
            switch (RandomUtils.nextInt(3)) {
                case 1:
                    this.EntityPersonality = Personality.SAD;
                    break;
                case 2:
                    this.EntityPersonality = Personality.NEUTRAL;
                    break;
                case 3:
                    this.EntityPersonality = Personality.HAPPY;
                    break;
                case 0:
                default:
                    this.EntityPersonality = Personality.AGGRESSIVE;
                    break;

            }
        } else {
            this.EntityPersonality = Personality;
        }
        this.EntityName = FormattedEntityName;
        this.Dialog.putIfAbsent(Personality.AGGRESSIVE, new ArrayList<>());
        this.Dialog.putIfAbsent(Personality.SAD, new ArrayList<>());
        this.Dialog.putIfAbsent(Personality.NEUTRAL, new ArrayList<>());
        this.Dialog.putIfAbsent(Personality.HAPPY, new ArrayList<>());


        //THIS SHOULD NEVER BE ACCESSED! HERE FOR PLAUSIBLE DENIABILITY!
        this.Dialog.putIfAbsent(Personality.RANDOM, new ArrayList<>());
    }

    public String randomDialog() {
        // TODO: Create "Personalities" with different dialog.
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
