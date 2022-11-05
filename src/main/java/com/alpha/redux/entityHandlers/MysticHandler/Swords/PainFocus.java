package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.PainFocusLore;

public class PainFocus {

    public PainFocus(ReduxDamageEvent event){
        SwordEnchant painfocus = new SwordEnchant(event, "pf") {
            @Override
            public void ThreeAction() {
                redux.painFocusLore.run(event, 3);
            }
            @Override
            public void TwoAction() {
                redux.painFocusLore.run(event, 2);
            }
            @Override
            public void OneAction() {
                redux.painFocusLore.run(event, 1);
            }
        };

        painfocus.run();
    }
}
