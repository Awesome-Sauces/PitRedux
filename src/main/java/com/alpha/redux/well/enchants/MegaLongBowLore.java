package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxBowEvent;
import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class MegaLongBowLore extends PitEnchant{

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

        return "&dRARE! &9Mega Longbow" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(1+(Math.round(level*.5)));
        String jump = String.valueOf(integerToRoman(level+1));

        String lore = "&dRARE! &9Mega Longbow" + tier + "\n" +
                "&7One shot per second, this bow is\n" +
                "&7automatically fully drawn and\n" +
                "&7grants &aJump Boost "+jump+" &7("+multiplier+"s)" + "\n&7";

        return colorCode(lore);
    }
}
