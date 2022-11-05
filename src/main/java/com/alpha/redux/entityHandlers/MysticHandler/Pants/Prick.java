package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;

public class Prick {
    public Prick(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant cricket = new PantEnchant(event, player, "prick") {
            @Override
            public void OneAction() {
                redux.prickLore.run(event, 1);
            }

            @Override
            public void TwoAction() {
                redux.prickLore.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                redux.prickLore.run(event, 3);
            }
        };
        cricket.run();
    }
}
