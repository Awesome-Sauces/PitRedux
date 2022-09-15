package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class LifestealLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        int multiplier = 0;

        if (level > 2) {
            multiplier += (level*4) + 1;
        }else {multiplier += level*4;}

        multiplier = multiplier / 100;

        event.getAttacker().getPlayerObject().setHealth(Math.min(event.getAttacker().getPlayerObject().getHealth()
                        + Math.min(((event.getAttacker().getPlayerObject().getHealth()/2) * multiplier), 3),
                event.getAttacker().getPlayerObject().getMaxHealth()));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = "";

        if (level > 2) {
            multiplier += String.valueOf((level*4) + 1);
        }else {multiplier += String.valueOf(level*4);}

        String lore = "&9Lifesteal" + tier + "\n" +
                "&7Heal for &c" + multiplier + "%&7 of damage dealt up" +
                "\n&7to &c1.5\u2764";

        return colorCode(lore);
    }
}
