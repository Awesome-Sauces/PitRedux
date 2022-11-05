package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;

public class PitPocket {
    public PitPocket(ReduxDamageEvent event){
        SwordEnchant shark = new SwordEnchant(event, "pitpocket") {
            @Override
            public void ThreeAction() {
                redux.pitPocketLore.run(event, 3);
            }
            @Override
            public void TwoAction() {
                redux.pitPocketLore.run(event, 2);
            }
            @Override
            public void OneAction() {
                redux.pitPocketLore.run(event, 1);
            }
        };
        shark.run();
    }
}
