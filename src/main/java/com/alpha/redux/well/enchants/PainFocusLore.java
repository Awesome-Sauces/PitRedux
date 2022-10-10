package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class PainFocusLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        int multiplier = 0;

        if (level > 2) {
            multiplier = level+2;
        }else{multiplier = level;}

        multiplier = multiplier / 100;

        event.addReduxDamage(Math.min((event.getAttacker().getPlayerObject().getMaxHealth() -
                event.getAttacker().getPlayerObject().getHealth()) / 2, 2) * multiplier);
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
