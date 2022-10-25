package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.DavidGoliathLore;
import com.alpha.redux.well.enchants.DiamondAllergyLore;

public class DiamondAllergy {
    DiamondAllergyLore diamondAllergyLore = new DiamondAllergyLore();

    public DiamondAllergy(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant booboo = new PantEnchant(event, player, "diamondallergy") {
            @Override
            public void OneAction() {
                diamondAllergyLore.run(event, 1);
            }

            @Override
            public void TwoAction() {
                diamondAllergyLore.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                diamondAllergyLore.run(event, 3);
            }
        };
        booboo.run();
    }
}