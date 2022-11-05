package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.ComboDamageLore;

import java.util.HashMap;

public class ComboDamage {

    public static HashMap<String, Integer> hitCounter = new HashMap<>();



    public ComboDamage(ReduxDamageEvent event){
        SwordEnchant combodamage = new SwordEnchant(event, "combodamage") {
            @Override
            public void ThreeAction() {
                redux.combodamageLore.run(event, 3);
            }

            public void TwoAction() {
                redux.combodamageLore.run(event, 2);
            }

            public void OneAction() {
                redux.combodamageLore.run(event, 1);
            }
        };

        combodamage.run();
    }
}
