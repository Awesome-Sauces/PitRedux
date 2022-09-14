package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.DiamondStompLore;
import org.bukkit.Material;

public class DiamondStomp {
    public DiamondStomp(ReduxDamageEvent event){

        DiamondStompLore diamondStompLore = new DiamondStompLore();
        
        SwordEnchant diamondStomp = new SwordEnchant(event, "diamond") {
            @Override
            public void ThreeAction() {
                diamondStompLore.run(event, 3);
            }
            @Override
            public void TwoAction() {
                diamondStompLore.run(event, 2);

            }
            @Override
            public void OneAction() {
                diamondStompLore.run(event, 1);
            }
        };

        diamondStomp.run();
    }
}
