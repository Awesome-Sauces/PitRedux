package com.alpha.redux.well.enchants;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class BillionaireLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        int gold = 0;

        if (level > 2) {
            gold += (level*100)+50;
        }else{gold+=level*100;}

        int multiplier = 0;

        if (level > 1) {
            multiplier += (level*.33)+1.01;
        }else {multiplier += (level*.33)+1;}

        if (event.getAttacker().getPlayerGold() >= gold){
            event.getAttacker().setPlayerGold((int) (event.getAttacker().getPlayerGold() - gold));
            event.getAttacker().refreshScoreBoard();
            Sounds.BILLIONAIRE.play(event.getAttacker().getPlayerObject());
            event.setReduxDamage(event.getReduxDamage() * multiplier);
        }
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String lore(int level) {

        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = "";

        if (level > 1) {
            multiplier += String.valueOf((level*.33)+1.01);
        }else {multiplier += String.valueOf((level*.33)+1);}

        String gold = "";

        if (level > 2) {
            gold += String.valueOf((level*100)+50);
        }else{gold+=String.valueOf(level*100);}

        String lore = "&dRARE! &9Billionaire" + tier + "\n" +
                        "&7Hits with this sword deal &c" + multiplier + "x\n" +
                        "&cdamage&7 but cost &6" + gold + "g";

        return colorCode(lore);
    }
}
