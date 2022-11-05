package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;

public class Punisher {
    public Punisher(ReduxDamageEvent event){
        SwordEnchant kingBuster = new SwordEnchant(event, "punisher") {
            @Override
            public void ThreeAction() {
                redux.punisherLore.run(event, 3);
            }
            @Override
            public void TwoAction() {
                redux.punisherLore.run(event, 2);
            }
            @Override
            public void OneAction() {
                redux.punisherLore.run(event, 1);
            }
        };
        kingBuster.run();
    }
}
