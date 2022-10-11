package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.EscapePodLore;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class EscapePod {

    EscapePodLore escapepod = new EscapePodLore();

    public EscapePod(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant escapePod = new PantEnchant(event, player, "escape") {
            @Override
            public void OneAction() {
                escapepod.run(event, 1);
            }

            @Override
            public void TwoAction() {
                escapepod.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                escapepod.run(event, 3);
            }
        };
        escapePod.run();
    }

}
