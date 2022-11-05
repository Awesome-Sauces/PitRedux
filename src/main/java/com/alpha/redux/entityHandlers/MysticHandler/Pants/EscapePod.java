package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.EscapePodLore;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class EscapePod {
    public EscapePod(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant escapePod = new PantEnchant(event, player, "escape") {
            @Override
            public void OneAction() {
                redux.escapePodLore.run(event, 1);
            }

            @Override
            public void TwoAction() {
                redux.escapePodLore.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                redux.escapePodLore.run(event, 3);
            }
        };
        escapePod.run();
    }

}
