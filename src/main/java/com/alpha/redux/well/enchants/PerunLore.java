package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class PerunLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

    }

    @Override
    public String lore(int level) {

        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level);

        String hit = "";

        if (level > 1){
            hit = "fourth";
        }else {hit = "fifth";}

        String lore = "&dRARE! &9Combo: Perun's Wrath" + tier + "\n" +
                "&7Each &e" + hit + "&7 hit strikes" +
                "\n&elightning&7 for &c" + multiplier + "\u2764&7." +
                "\n&7&oLightning deals true damage";

        return colorCode(lore);
    }
}
