package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.playerdata.bounties;

import static com.alpha.redux.events.boards.integerToRoman;

public class BillyLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        double damage = (2+level);


        if(bounties.BountiesMap.containsKey(event.getDefenders().getPlayerUUID())){
            double bounty = (double) Math.min(5000, bounties.BountiesMap.get(event.getDefenders().getPlayerUUID())) / 1000;
            event.subtractReduxDamage((damage/100)*bounty);
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

        return "&9Billy" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(2+level);

        String lore = "&9Billy" + tier + "\n" +
                "&7Receive &9-"+multiplier+"%&7 damage per\n" +
                "&61,000g bounty" + "\n&7";

        return colorCode(lore);
    }
}

