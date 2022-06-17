package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;

public class PitBlob {
    public PitBlob(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant pitBlob = new PantEnchant(event, player, "blob") {
            @Override
            public void OneAction() {



            }

            @Override
            public void TwoAction() {



            }

            @Override
            public void ThreeAction() {



            }
        };
        pitBlob.run();
    }
}
