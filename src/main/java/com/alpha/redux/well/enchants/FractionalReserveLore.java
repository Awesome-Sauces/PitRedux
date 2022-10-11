package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class FractionalReserveLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        event.subtractReduxDamage(event.getReduxDamage()*((float)(7+((level-1)*15))/100));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Fractional Reserve" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(7+((level-1)*15));

        String lore = "&9Fractional Reserve" + tier + "\n" +
                "&7Receive &9-1% damage&7 per\n" +
                "&650,000g &7you have (&9-" + multiplier + "%\n" +
                "&7max)" + "\n&7";

        return colorCode(lore);
    }
}