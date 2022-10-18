package com.alpha.redux.entityHandlers.MysticHandler.Bows;

import com.alpha.redux.entityHandlers.MysticHandler.BowEnchant;
import com.alpha.redux.eventManagers.ReduxBowEvent;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FasterThanTheirShadow {
    public FasterThanTheirShadow(ReduxBowEvent event){
        BowEnchant sprintDrain = new BowEnchant(event, "ftts") {
            @Override
            public void ThreeAction() {

                giveSpeed(event.getAttacker().getPlayerObject(), 3, 7);

            }
            @Override
            public void TwoAction() {

                giveSpeed(event.getAttacker().getPlayerObject(), 2, 5);

            }
            @Override
            public void OneAction() {

                giveSpeed(event.getAttacker().getPlayerObject(), 1, 5);

            }
        };

        sprintDrain.run();
    }

    private void giveSpeed(Player player, int power, int time){

            player.removePotionEffect(PotionEffectType.SPEED);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, time*20, Math.max(power-1, 0), true, true));
    }
}
