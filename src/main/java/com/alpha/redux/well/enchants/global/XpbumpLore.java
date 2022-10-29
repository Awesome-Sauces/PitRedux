package com.alpha.redux.well.enchants.global;

import com.alpha.redux.DeathHandler.ReduxDeathEvent;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.EnchantRarity;
import com.alpha.redux.well.enchants.PitEnchant;

import java.util.ArrayList;
import java.util.List;

import static com.alpha.redux.events.boards.integerToRoman;

public class XpbumpLore extends PitEnchant {
    @Override
    public void run(ReduxDamageEvent event, int level) {

    }

    public void run(ReduxDeathEvent event){

        List<String> penchants = new ArrayList<>();
        List<String> senchants = new ArrayList<>();

        ReduxPlayer player = event.getAttacker();

        if(player.getPantEnchants() != null) penchants = player.getPantEnchants();
        if(player.getSwordEnchants() != null) senchants = player.getSwordEnchants();

        String PANT_SWEATY = "";
        String SWORD_SWEATY = "";

        for(String ench : penchants){
            if(ench.contains("xpb")){
                PANT_SWEATY = ench;
            }
        }

        for(String ench : senchants){
            if(ench.contains("xpb")){
                SWORD_SWEATY = ench;
            }
        }

        if(PANT_SWEATY.contains("xpb")){
            int level = PANT_SWEATY.length() - PANT_SWEATY.replaceAll("I", "").length();

            double xp = (double) 2*level;
            event.addXp((int) xp);
        }

        if(SWORD_SWEATY.contains("xpb")){
            int level = SWORD_SWEATY.length() - SWORD_SWEATY.replaceAll("I", "").length();

            double xp = (double) 2*level;
            event.addXp((int) xp);
        }
    }


    @Override
    public void init() {
        EnchantRarity rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9XP Bump" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(2*level);

        String lore = "&9XP Bump" + tier + "\n" +
                "&7Earn &b+" + multiplier + " XP &7per\n" +
                "&7kill" + "\n&7";

        return colorCode(lore);
    }
}
