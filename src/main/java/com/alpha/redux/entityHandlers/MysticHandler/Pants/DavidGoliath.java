package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.BooBooLore;
import com.alpha.redux.well.enchants.DavidGoliathLore;

public class DavidGoliath {
    DavidGoliathLore davidGoliath = new DavidGoliathLore();

    public DavidGoliath(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant booboo = new PantEnchant(event, player, "david") {
            @Override
            public void OneAction() {
                davidGoliath.run(event, 1);
            }

            @Override
            public void TwoAction() {
                davidGoliath.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                davidGoliath.run(event, 3);
            }
        };
        booboo.run();
    }
}
