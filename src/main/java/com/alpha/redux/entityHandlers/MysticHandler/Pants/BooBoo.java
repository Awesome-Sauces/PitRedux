package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.BooBooLore;

public class BooBoo {

    BooBooLore booBooLore = new BooBooLore();

    public BooBoo(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant booboo = new PantEnchant(event, player, "booboo") {
            @Override
            public void OneAction() {
                booBooLore.run(event, 1);
            }

            @Override
            public void TwoAction() {
                booBooLore.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                booBooLore.run(event, 3);
            }
        };
        booboo.run();
    }

}

