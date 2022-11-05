package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;

public class Grasshopper {
    public Grasshopper(ReduxDamageEvent event){
        SwordEnchant shark = new SwordEnchant(event, "grasshopper") {
            @Override
            public void ThreeAction() {
                redux.grasshopperLore.run(event, 3);
            }
            @Override
            public void TwoAction() {
                redux.grasshopperLore.run(event, 2);
            }
            @Override
            public void OneAction() {
                redux.grasshopperLore.run(event, 1);
            }
        };
        shark.run();
    }
}
