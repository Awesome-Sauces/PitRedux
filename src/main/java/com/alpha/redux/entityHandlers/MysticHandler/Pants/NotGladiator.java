package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.NotGladiatorLore;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class NotGladiator {

    NotGladiatorLore gladiator = new NotGladiatorLore();

    public NotGladiator(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant notGladiator = new PantEnchant(event, player, "glad") {
            @Override
            public void OneAction() {
                gladiator.run(event, 1);
            }

            @Override
            public void TwoAction() {
                gladiator.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                gladiator.run(event, 3);
            }
        };
        notGladiator.run();
    }

}
