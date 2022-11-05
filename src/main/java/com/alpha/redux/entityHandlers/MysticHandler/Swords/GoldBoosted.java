package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;

public class GoldBoosted {
    public GoldBoosted(ReduxDamageEvent event){
        SwordEnchant shark = new SwordEnchant(event, "goldandboosted") {
            @Override
            public void ThreeAction() {
                redux.goldBoostedLore.run(event, 3);
            }
            @Override
            public void TwoAction() {
                redux.goldBoostedLore.run(event, 2);
            }
            @Override
            public void OneAction() {
                redux.goldBoostedLore.run(event, 1);
            }
        };
        shark.run();
    }
}
