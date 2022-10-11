package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.FractionalReserveLore;

public class FractionalReserve {

    FractionalReserveLore fractionalreserve = new FractionalReserveLore();

    public FractionalReserve(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant fractionalReserve = new PantEnchant(event, player, "frac") {
            @Override
            public void OneAction() {
                fractionalreserve.run(event, 1);
            }

            @Override
            public void TwoAction() {
                fractionalreserve.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                fractionalreserve.run(event, 3);
            }
        };
        fractionalReserve.run();
    }
}
