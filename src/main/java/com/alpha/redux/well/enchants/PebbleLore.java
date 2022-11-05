package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class PebbleLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        double damage = Math.max(4+((level-1)*3), 1);

        event.subtractReduxDamage(event.getReduxDamage()*(damage/100));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Pebble" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(10*level);

        String lore = "&9Pebble" + tier + "\n" +
                "&7Picked up gold rewards &6+" + multiplier + "g" + "\n&7";

        return colorCode(lore);
    }
}
