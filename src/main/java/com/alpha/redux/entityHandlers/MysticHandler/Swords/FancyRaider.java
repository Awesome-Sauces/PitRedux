package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.FancyRaiderLore;

public class FancyRaider {
    public FancyRaider(ReduxDamageEvent event){

        FancyRaiderLore fancyRaiderLore = new FancyRaiderLore();

        SwordEnchant fancyraider = new SwordEnchant(event, "fancyraider") {
            @Override
            public void ThreeAction() {
                fancyRaiderLore.run(event, 3);
            }
            @Override
            public void TwoAction() {
                fancyRaiderLore.run(event, 2);

            }
            @Override
            public void OneAction() {
                fancyRaiderLore.run(event, 1);
            }
        };

        fancyraider.run();
    }
}
