package com.alpha.redux.entityHandlers.MysticHandler.Bows;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.MysticHandler.BowEnchant;
import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxBowEvent;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SprintDrain {
    public SprintDrain(ReduxBowEvent event){
        BowEnchant sprintDrain = new BowEnchant(event, "sprint") {
            @Override
            public void ThreeAction() {

                giveSpeed(event.getAttacker().getPlayerObject(), 2, 7);
                giveSlow(event.getDefender().getPlayerObject(), 1, 3);


            }
            @Override
            public void TwoAction() {

                giveSpeed(event.getAttacker().getPlayerObject(), 1, 5);
                giveSlow(event.getDefender().getPlayerObject(), 1, 3);

            }
            @Override
            public void OneAction() {

                giveSpeed(event.getAttacker().getPlayerObject(), 1, 3);

            }
        };

        sprintDrain.run();
    }

    private void giveSpeed(Player player, int power, int time){
        if(!player.hasPotionEffect(PotionEffectType.SPEED)){
            player.removePotionEffect(PotionEffectType.SPEED);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, time*20, Math.max(power-1, 0), true, true));
        }
    }

    private void giveSlow(Player player, int power, int time){
        if(!player.hasPotionEffect(PotionEffectType.SLOW)){
            player.removePotionEffect(PotionEffectType.SLOW);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, time*20, Math.max(power-1, 0), true, true));
        }
    }
}
