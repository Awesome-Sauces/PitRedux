package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.FancyRaiderLore;

public class FancyRaider {
    public FancyRaider(ReduxDamageEvent event){

        SwordEnchant fancyraider = new SwordEnchant(event, "fancyraider") {
            @Override
            public void ThreeAction() {
                redux.fancyraiderLore.run(event, 3);
            }
            @Override
            public void TwoAction() {
                redux.fancyraiderLore.run(event, 2);

            }
            @Override
            public void OneAction() {
                redux.fancyraiderLore.run(event, 1);
            }
        };

        fancyraider.run();
    }
}
