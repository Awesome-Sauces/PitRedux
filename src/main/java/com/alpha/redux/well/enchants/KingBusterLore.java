package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class KingBusterLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        if(halfHealth(event)){
            event.addReduxDamage(event.getReduxDamage()  * ((float) Math.max((level * 7) - 1, 7) / 100));
        }
    }

    private boolean halfHealth(ReduxDamageEvent event){
        double damage = event.getReduxDamage() + event.getReduxTrueDamage();
        return event.getDefenders().getPlayerObject().getHealth() - damage <= 10;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(Math.max((level*7)-1, 7));

        String lore = "&9King Buster" + tier + "\n" +
                "&7Deal &c+" + multiplier + "%&7 damage vs. players" +  "\n&7above 50% HP";

        return colorCode(lore);
    }
}
