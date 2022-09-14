package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.ItemEvents.sharkCalc.getSharkPlayers;
import static com.alpha.redux.events.boards.integerToRoman;

public class SharkLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        int multiplier = 0;

        if (level > 2) {
            multiplier += (level * 2) + 1;
        }else {multiplier += level*2;}

        multiplier = multiplier / 100;

        event.addReduxDamage(event.getReduxDamage() *
                (Math.min(getSharkPlayers(event.getAttacker().getPlayerObject()), 18) * multiplier ));
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = "";

        if (level > 2) {
            multiplier += String.valueOf((level * 2) + 1);
        }else {multiplier += String.valueOf(level*2);}

        String lore = "&9Shark" + tier + "\n" +
                "&7Deal &c+"+multiplier+"%&7 damage per other" +
                "\n&7player below &c6\u2764&7 within 12" + "\n&7blocks";

        return colorCode(lore);
    }
}
