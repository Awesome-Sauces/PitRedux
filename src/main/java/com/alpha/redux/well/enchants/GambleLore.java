package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class GambleLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level);

        String lore = "&dRARE! &9Gamble!" + tier + "\n" +
                "&d50% chance &7to deal &c"+multiplier+"\u2764&7 true\n" +
                "&7damage to whoever you hit, or to\n" + "&7yourself";

        return colorCode(lore);
    }
}
