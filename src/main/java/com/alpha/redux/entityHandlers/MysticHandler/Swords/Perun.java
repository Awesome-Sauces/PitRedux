package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.PerunLore;

import java.util.HashMap;

import static com.alpha.redux.apis.strikeLight.strikeLightningForPlayers;

public class Perun {

    public static HashMap<String, Integer> hitCounter = new HashMap<>();

    PerunLore perunLore = new PerunLore();

    public Perun(ReduxDamageEvent event){
        SwordEnchant perun = new SwordEnchant(event, "perun") {
            @Override
            public void ThreeAction() {
                perunLore.run(event, 3);
            }

            public void TwoAction() {
                perunLore.run(event, 2);
            }

            public void OneAction() {
                perunLore.run(event, 1);
            }
        };

        perun.run();
    }
}
