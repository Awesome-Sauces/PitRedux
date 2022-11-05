package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.SolitudeLore;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Solitude {

    ReduxPlayer player;


    public Solitude(ReduxDamageEvent event, ReduxPlayer player){
        this.player = player;
        PantEnchant solitude = new PantEnchant(event, player, "soli") {
            @Override
            public void OneAction() {
                redux.solitudeLore.run(event, 1);
            }

            @Override
            public void TwoAction() {
                redux.solitudeLore.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                redux.solitudeLore.run(event, 3);
            }
        };

        solitude.run();




    }

}
