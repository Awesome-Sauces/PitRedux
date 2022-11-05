package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.EnchantRarity;
import com.alpha.redux.well.enchants.PitEnchant;

import static com.alpha.redux.events.boards.integerToRoman;

public class Billy {
    public Billy(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant cricket = new PantEnchant(event, player, "billy") {
            @Override
            public void OneAction() {
                redux.billyLore.run(event, 1);
            }

            @Override
            public void TwoAction() {
                redux.billyLore.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                redux.billyLore.run(event, 3);
            }
        };
        cricket.run();
    }
}

