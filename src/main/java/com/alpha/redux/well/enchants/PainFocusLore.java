package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class PainFocusLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        double multiplier = 0;

        if (level > 2) {
            multiplier = level+2;
        }else{multiplier = level;}

        int maxHealth = (int) event.getAttacker().getPlayerObject().getMaxHealth();
        int currentHealth = (int) event.getAttacker().getPlayerObject().getHealth();

        int hearts = Math.max((maxHealth-currentHealth), 1);

        event.addReduxDamage(event.getReduxDamage()*(hearts*(multiplier/100)));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Pain Focus" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = "";

        if (level > 2) {
            multiplier = String.valueOf(level+2);
        }else{multiplier = String.valueOf(level);}

        String lore = "&9Pain Focus" + tier + "\n" +
                "&7Deal &c+" + multiplier +  "%&7 damage per &c\u2764" + "\n&7you're missing" + "\n&7";

        return colorCode(lore);
    }
}
