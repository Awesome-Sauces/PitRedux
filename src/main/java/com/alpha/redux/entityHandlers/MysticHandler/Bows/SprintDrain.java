package com.alpha.redux.entityHandlers.MysticHandler.Bows;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.MysticHandler.BowEnchant;
import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxBowEvent;
import com.alpha.redux.eventManagers.ReduxDamageEvent;

public class SprintDrain {
    public SprintDrain(ReduxBowEvent event){
        BowEnchant sprintDrain = new BowEnchant(event, "bill") {
            @Override
            public void ThreeAction() {



            }
            @Override
            public void TwoAction() {



            }
            @Override
            public void OneAction() {



            }
        };

        sprintDrain  .run();
    }
}
