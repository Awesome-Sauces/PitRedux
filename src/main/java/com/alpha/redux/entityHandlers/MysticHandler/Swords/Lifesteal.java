package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.LifestealLore;
import org.bukkit.Bukkit;

public class Lifesteal {
    public Lifesteal(ReduxDamageEvent event){

        LifestealLore lifestealLore = new LifestealLore();

        SwordEnchant lifesteal = new SwordEnchant(event, "ls") {
            @Override
            public void ThreeAction() {
                lifestealLore.run(event, 3);
            }
            @Override
            public void TwoAction() {
                lifestealLore.run(event, 2);
            }
            @Override
            public void OneAction() {
                lifestealLore.run(event, 1);
            }
        };

        lifesteal.run();


    }
}
