package com.alpha.redux.well.enchants;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.Bukkit;

import static com.alpha.redux.events.boards.integerToRoman;

public class BillionaireLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        int gold = 0;

        if (level > 2) {
            gold += (level*100)+50;
        }else{gold+=level*100;}

        double multiplier = getDamageMultiplier(level);

        if (event.getAttacker().getPlayerGold() >= gold){
            event.getAttacker().setPlayerGold((int) (event.getAttacker().getPlayerGold() - gold));
            event.getAttacker().refreshScoreBoard();
            Sounds.BILLIONAIRE.play(event.getAttacker().getPlayerObject());
            event.setReduxDamage(event.getReduxDamage() * (multiplier));
        }
    }

    public double getDamageMultiplier(int enchantLvl) {
        return (double) Math.round((1 + (double) enchantLvl / 3) * 100) / 100;
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Billionaire" + tier;
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
                        "&cdamage&7 but cost &6" + gold + "g" + "\n&7";

        return colorCode(lore);
    }
}
