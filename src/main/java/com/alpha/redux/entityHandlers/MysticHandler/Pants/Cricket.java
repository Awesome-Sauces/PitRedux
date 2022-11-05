package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.BooBooLore;
import com.alpha.redux.well.enchants.CricketLore;

public class Cricket {

    public Cricket(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant cricket = new PantEnchant(event, player, "cricket") {
            @Override
            public void OneAction() {
                redux.cricketLore.run(event, 1);
            }

            @Override
            public void TwoAction() {
                redux.cricketLore.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                redux.cricketLore.run(event, 3);
            }
        };
        cricket.run();
    }
}
