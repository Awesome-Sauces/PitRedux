package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;

public class SelfCheckout {
    public SelfCheckout(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant cricket = new PantEnchant(event, player, "self-checkout") {
            @Override
            public void OneAction() {
                redux.selfCheckoutLore.run(event, 1);
            }

            @Override
            public void TwoAction() {
                redux.selfCheckoutLore.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                redux.selfCheckoutLore.run(event, 3);
            }
        };
        cricket.run();
    }
}
