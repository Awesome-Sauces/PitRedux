package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxBowEvent;
import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class ExplosiveLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
    }

    public void bow(ReduxBowEvent event, int level){

    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Explosive" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(5-(level));

        String lore = "&dRARE! &9Explosive" + tier + "\n" +
                "&7Arrows go BOOM! ("+multiplier+"s cooldown)"
                + "\n&7";

        return colorCode(lore);
    }
}