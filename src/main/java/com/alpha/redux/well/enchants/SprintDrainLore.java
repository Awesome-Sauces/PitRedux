package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxBowEvent;
import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class SprintDrainLore extends PitEnchant{

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

        return "&9Sprint Drain" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(4+(Math.round(level*.5)));
        String speed = String.valueOf(integerToRoman(Math.max(level-1,1)));

        String lore = "&9Sprint Drain" + tier + "\n" +
                "&7Arrow shots gran you &eSpeed "+speed+"\n" +
                "&7("+multiplier+"s) and apply &8Slowness I\n" +
                "&7(3s)" + "\n&7";

        return colorCode(lore);
    }
}

