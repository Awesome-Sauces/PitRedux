package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class PeroxideLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        event.subtractReduxDamage(event.getReduxDamage()*((float)(1+((level-1)*.5))/100));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Peroxide" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(integerToRoman(Math.max(level, 0)));

        String time = "";

        if (level > 1) {time = "8";}else{time="5";}

        String lore = "&9Peroxide" + tier + "\n" +
                "&7Gain &cRegen "+multiplier+" &7("+time+"s) when hit" + "\n&7";

        return colorCode(lore);
    }
}
