package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.entity.Player;

import static com.alpha.redux.events.boards.integerToRoman;

public class CriticallyFunkyLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
      
        double damage = .0001;

        if (level > 2) {
            damage += (level*7) + 9;
        }else {damage += level*7;}

        double dmg = 80-((level-1)*15);

        if(criticalHit(event.getAttacker().getPlayerObject().getPlayer())){
            event.getDefenders().setPlayerIncrease(damage/100);
            event.setReduxDamage(event.getReduxDamage() * (dmg/100));
        }
    
    }

    private boolean criticalHit(Player player){
        return !player.isOnGround();
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Critically Funky" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(80-((level-1)*15));
        String damage = "";

        if (level > 2) {
            damage += String.valueOf((level*7) + 9);
        }else {damage += String.valueOf(level*7);}

        String lore = "&9Critically Funky" + tier + "\n" +
                "&7Critical hits against you deal\n" +
                "&9" + multiplier + "% &7of the damage they\n" +
                "&7normally would and empower your\n" +
                "&7next strike for &c+" + damage + "%&7 damage" + "\n&7";

        return colorCode(lore);
    }
}