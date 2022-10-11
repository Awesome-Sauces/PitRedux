package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class RetroGravityMicrocosmLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        event.subtractReduxDamage(event.getReduxDamage()*((float)(25+((level-1)*25))/100));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Retro-Gravity Microcosm" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(.5+((level-1)*.25));

        String take = String.valueOf(.5+((level-1)*.5));

        String lore = "&dRARE! &9Retro-Gravity Microcosm" + tier + "\n" +
                "&7When a player hits you from\n" +
                "&7above ground &e3 times &fin a row:\n" +
                "&7You heal &c"+multiplier+"\u2764\n" +
                "&7They take &c"+take+"\u2764 &ftrue damage" + "\n&7";

        return colorCode(lore);
    }
}