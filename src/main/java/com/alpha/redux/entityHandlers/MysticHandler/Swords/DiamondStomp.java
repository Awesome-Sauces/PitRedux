package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.Material;

public class DiamondStomp {
    public DiamondStomp(ReduxDamageEvent event){
        
        SwordEnchant diamondStomp = new SwordEnchant(event, "diamond") {
            @Override
            public void ThreeAction() {
                if(event.getDefenders().getHelmet() != null && event.getDefenders().getHelmet().getType().equals(Material.DIAMOND_HELMET) || event.getDefenders().getChestplate() != null &&  event.getDefenders().getChestplate().getType().equals(Material.DIAMOND_CHESTPLATE) || event.getDefenders().getLeggings() != null && event.getDefenders().getLeggings().getType().equals(Material.DIAMOND_LEGGINGS) || event.getDefenders().getBoots() != null && event.getDefenders().getBoots().getType().equals(Material.DIAMOND_BOOTS))
                    this.event.addReduxDamage(event.getReduxDamage() * .25);
            }
            @Override
            public void TwoAction() {
                if(event.getDefenders().getHelmet() != null && event.getDefenders().getHelmet().getType().equals(Material.DIAMOND_HELMET) || event.getDefenders().getChestplate() != null &&  event.getDefenders().getChestplate().getType().equals(Material.DIAMOND_CHESTPLATE) || event.getDefenders().getLeggings() != null && event.getDefenders().getLeggings().getType().equals(Material.DIAMOND_LEGGINGS) || event.getDefenders().getBoots() != null && event.getDefenders().getBoots().getType().equals(Material.DIAMOND_BOOTS))
                    this.event.addReduxDamage(event.getReduxDamage() * .12);

            }
            @Override
            public void OneAction() {
                if(event.getDefenders().getHelmet() != null && event.getDefenders().getHelmet().getType().equals(Material.DIAMOND_HELMET) || event.getDefenders().getChestplate() != null &&  event.getDefenders().getChestplate().getType().equals(Material.DIAMOND_CHESTPLATE) || event.getDefenders().getLeggings() != null && event.getDefenders().getLeggings().getType().equals(Material.DIAMOND_LEGGINGS) || event.getDefenders().getBoots() != null && event.getDefenders().getBoots().getType().equals(Material.DIAMOND_BOOTS))
                    this.event.addReduxDamage(event.getReduxDamage() * .06);
            }
        };

        diamondStomp.run();
    }
}
