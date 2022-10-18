package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxBowEvent;
import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class VolleyLore extends PitEnchant{

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

        return "&dRARE! &9Volley" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level+2);

        String lore = "&dRARE! &9Volley" + tier + "\n" +
                "&7Shoot &f"+multiplier+" arrows &7at once" + "\n&7";

        return colorCode(lore);
    }
}

