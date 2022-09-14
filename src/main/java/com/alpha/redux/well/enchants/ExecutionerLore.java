package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class ExecutionerLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level);

        String lore = "&dRARE! &9Executioner III" + tier + "\n" +
                "&7Hitting an enemy to below &c" + multiplier + "\u2764" +
                "\n&7instantly kills them";

        return colorCode(lore);
    }
}
