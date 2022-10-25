package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.Material;

import static com.alpha.redux.events.boards.integerToRoman;

public class DiamondAllergyLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        double damage = level*10;

        if(event.getAttacker().getPlayerObject().getItemInHand() != null){
            if(event.getAttacker().getPlayerObject().getItemInHand().getType().equals(Material.DIAMOND_SWORD) ||
                    event.getAttacker().getPlayerObject().getItemInHand().getType().equals(Material.DIAMOND_SPADE)){
                event.subtractReduxDamage(event.getReduxDamage()*(damage/100));
            }
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

        return "&9Diamond Allergy" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level*10);

        String lore = "&9Diamond Allergy" + tier + "\n" +
                "&7Receive &9-"+multiplier+"%&7 damage from\n" +
                "&7diamond weapons" + "\n&7";

        return colorCode(lore);
    }
}
