package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.PeroxideLore;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Peroxide {

    PeroxideLore peroxidel = new PeroxideLore(); 

    public Peroxide(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant peroxide = new PantEnchant(event, player, "peroxide") {
            @Override
            public void OneAction() {
               peroxidel.run(event, 1);
            }

            @Override
            public void TwoAction() {
                peroxidel.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                peroxidel.run(event, 3);
            }
        };
        peroxide.run();
    }

}
