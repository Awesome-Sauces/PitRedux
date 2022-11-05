package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.BooBooLore;
import com.alpha.redux.well.enchants.DavidGoliathLore;

public class DavidGoliath {
    public DavidGoliath(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant booboo = new PantEnchant(event, player, "david") {
            @Override
            public void OneAction() {
                redux.davidGoliathLore.run(event, 1);
            }

            @Override
            public void TwoAction() {
                redux.davidGoliathLore.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                redux.davidGoliathLore.run(event, 3);
            }
        };
        booboo.run();
    }
}
