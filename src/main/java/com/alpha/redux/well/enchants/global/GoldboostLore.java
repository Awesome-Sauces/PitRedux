package com.alpha.redux.well.enchants.global;

import com.alpha.redux.DeathHandler.ReduxDeathEvent;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.EnchantRarity;
import com.alpha.redux.well.enchants.PitEnchant;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

import static com.alpha.redux.events.boards.integerToRoman;

public class GoldboostLore extends PitEnchant {
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
            if(ench.contains("gb")){
                PANT_SWEATY = ench;
            }
        }

        for(String ench : senchants){
            if(ench.contains("gb")){
                SWORD_SWEATY = ench;
            }
        }

        if(PANT_SWEATY.contains("gb")){
            int level = PANT_SWEATY.length() - PANT_SWEATY.replaceAll("I", "").length();

            double gold = 15*level;
            event.addGold((int) (event.getGold()*(gold/100)));
        }

        if(SWORD_SWEATY.contains("gb")){
            int level = SWORD_SWEATY.length() - SWORD_SWEATY.replaceAll("I", "").length();

            double gold = (15*level);

            event.addGold((int) (event.getGold()*(gold/100)));
        }
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Gold Boost" + tier;
    }

    @Override
    public void init() {
        EnchantRarity rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(15*level);

        String lore = "&9Gold Boost" + tier + "\n" +
                "&7Earn &6+" + multiplier + "% gold (g)&7 from kill" + "\n&7";

        return colorCode(lore);
    }
}
