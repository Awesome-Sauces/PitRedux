package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Peroxide {
    public Peroxide(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant peroxide = new PantEnchant(event, player, "peroxide") {
            @Override
            public void OneAction() {
                giveRegen(event.getDefenders().getPlayerObject(), 1, 10);
                event.getDefenders().getPlayerObject().setHealth(Math.min(event.getDefenders().getPlayerObject().getHealth()+.5, event.getDefenders().getPlayerObject().getMaxHealth()));
            }

            @Override
            public void TwoAction() {
                giveRegen(event.getDefenders().getPlayerObject(), 1, 5);
            }

            @Override
            public void ThreeAction() {
                giveRegen(event.getDefenders().getPlayerObject(), 2, 10);
            }
        };
        peroxide.run();
    }

    private void giveRegen(Player player, int power, int time){
        if(!player.hasPotionEffect(PotionEffectType.REGENERATION)){
            player.removePotionEffect(PotionEffectType.REGENERATION);
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, time*20, Math.max(power-1, 0), true, true));
        }
    }
}
