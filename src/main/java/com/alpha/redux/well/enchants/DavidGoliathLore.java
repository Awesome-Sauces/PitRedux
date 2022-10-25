package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;
import static com.alpha.redux.playerdata.bounties.BountiesMap;

public class DavidGoliathLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        if(BountiesMap.containsKey(event.getAttacker().getPlayerUUID()) &&
        BountiesMap.get(event.getAttacker().getPlayerUUID()) > 0){
            double damage = level*10;
            event.subtractReduxDamage(event.getReduxDamage()*(damage/100));
        }

    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9David and Goliath" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level*10);

        String lore = "&9David and Goliath" + tier + "\n" +
                "&7Receive &9-" + multiplier + "% &7damage from\n" +
                "&7players with a bounty" + "\n&7";

        return colorCode(lore);
    }
}
