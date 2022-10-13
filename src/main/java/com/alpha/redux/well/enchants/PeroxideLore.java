package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.alpha.redux.events.boards.integerToRoman;

public class PeroxideLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        int time = 1;

        if (level > 1) {time = 8;}else{time=5;}

        giveRegen(event.getDefenders().getPlayerObject(), Math.max(level-1, 1), time);

        if (level==1) event.getDefenders().getPlayerObject().setHealth(Math.min(event.getDefenders().getPlayerObject().getHealth()+.5, event.getDefenders().getPlayerObject().getMaxHealth()));
    }

    private void giveRegen(Player player, int power, int time){
        if(!player.hasPotionEffect(PotionEffectType.REGENERATION)){
            player.removePotionEffect(PotionEffectType.REGENERATION);
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, time*20, Math.max(power-1, 0), true, true));
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

        return "&9Peroxide" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(integerToRoman(Math.max(level-1, 1)));

        String time = "";

        if (level > 1) {time = "8";}else{time="5";}

        String lore = "&9Peroxide" + tier + "\n" +
                "&7Gain &cRegen "+multiplier+" &7("+time+"s) when hit" + "\n&7";

        return colorCode(lore);
    }
}
