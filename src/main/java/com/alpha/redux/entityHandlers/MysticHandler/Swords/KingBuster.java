package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.KingBusterLore;

public class KingBuster {

    public KingBuster(ReduxDamageEvent event){
        SwordEnchant kingBuster = new SwordEnchant(event, "king") {
            @Override
            public void ThreeAction() {
                redux.kingBusterLore.run(event, 3);
            }
            @Override
            public void TwoAction() {
                redux.kingBusterLore.run(event, 2);
            }
            @Override
            public void OneAction() {
                redux.kingBusterLore.run(event, 1);
            }
        };
        kingBuster.run();
    }
}
