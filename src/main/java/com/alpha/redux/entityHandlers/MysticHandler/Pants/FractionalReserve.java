package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.FractionalReserveLore;

public class FractionalReserve {

    public FractionalReserve(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant fractionalReserve = new PantEnchant(event, player, "frac") {
            @Override
            public void OneAction() {
                redux.fractionalReserveLore.run(event, 1);
            }

            @Override
            public void TwoAction() {
                redux.fractionalReserveLore.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                redux.fractionalReserveLore.run(event, 3);
            }
        };
        fractionalReserve.run();
    }
}
