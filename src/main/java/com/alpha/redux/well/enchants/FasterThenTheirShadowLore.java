package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxBowEvent;
import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class FasterThenTheirShadowLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
    }

    public void bow(ReduxBowEvent event, int level){

    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Faster than their shadow" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(4+(Math.round(level*.5)));
        String speed = String.valueOf(integerToRoman(level+1));

        String lore = "&9Faster than their shadow" + tier + "\n" +
                "&7Hitting &f1 &7shot without\n" +
                "&7missing grants &eSpeed "+speed+" &7("+multiplier+"s)"
                + "\n&7";

        return colorCode(lore);
    }
}
