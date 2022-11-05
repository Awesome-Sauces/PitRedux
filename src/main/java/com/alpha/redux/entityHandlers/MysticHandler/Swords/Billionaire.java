package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.events.boards;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.BillionaireLore;

import static com.alpha.redux.playerdata.economy.getEconomy;
import static com.alpha.redux.playerdata.economy.removeEconomy;

public class Billionaire {

    public Billionaire(ReduxDamageEvent event){
        SwordEnchant billionaire = new SwordEnchant(event, "bill") {
            @Override
            public void ThreeAction() {
                redux.billionaireLore.run(event, 3);
            }
            @Override
            public void TwoAction() {
                redux.billionaireLore.run(event, 2);
            }
            @Override
            public void OneAction() {
                redux.billionaireLore.run(event, 1);
            }
        };

        billionaire.run();
    }

}
