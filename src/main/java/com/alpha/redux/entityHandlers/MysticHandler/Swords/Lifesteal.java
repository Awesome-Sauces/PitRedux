package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.LifestealLore;
import org.bukkit.Bukkit;

public class Lifesteal {
    public Lifesteal(ReduxDamageEvent event){

        SwordEnchant lifesteal = new SwordEnchant(event, "ls") {
            @Override
            public void ThreeAction() {
                redux.lifestealLore.run(event, 3);
            }
            @Override
            public void TwoAction() {
                redux.lifestealLore.run(event, 2);
            }
            @Override
            public void OneAction() {
                redux.lifestealLore.run(event, 1);
            }
        };

        lifesteal.run();


    }
}
