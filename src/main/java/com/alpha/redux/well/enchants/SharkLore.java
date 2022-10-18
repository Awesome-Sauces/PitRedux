package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import static com.alpha.redux.ItemEvents.sharkCalc.getSharkPlayers;
import static com.alpha.redux.events.boards.integerToRoman;

public class SharkLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        double multiplier = 1;

        if (level > 2) {
            multiplier += (level * 2) + 1;
        }else {multiplier += level*2;}


        event.addReduxDamage(event.getReduxDamage() *
                ((Math.min(getSharkPlayers(event.getAttacker().getPlayerObject()), 8) * (multiplier/100) )));


    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Shark" + tier;
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
                "\n&7player below &c6\u2764&7 within 12" + "\n&7blocks" + "\n&7";

        return colorCode(lore);
    }
}
