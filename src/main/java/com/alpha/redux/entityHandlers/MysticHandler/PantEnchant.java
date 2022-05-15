package com.alpha.redux.entityHandlers.MysticHandler;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public abstract class PantEnchant {


    public ReduxDamageEvent event;
    String enchant;
    Boolean hasEnchant = false;
    String enchantTier = "none";

    public PantEnchant(ReduxDamageEvent event, String enchant){
        this.event = event;
        this.enchant = enchant;

        for(String ench : event.getDefenders().getPantEnchants())
            if (ench.contains(this.enchant)) {
                this.hasEnchant = true;
                this.enchantTier = ench.replace(this.enchant, "");
                break;
            }

        if (event.getDefenders().getPlayerObject().hasPotionEffect(PotionEffectType.POISON)) hasEnchant = false;


    }

    public void OneAction(){

    }

    public void TwoAction(){

    }

    public void ThreeAction(){

    }

    public void run() {
        if(hasEnchant){
            switch (this.enchantTier){
                case "III":
                    ThreeAction();
                    break;
                case "II":
                    TwoAction();
                    break;
                case "I":
                    OneAction();
                    break;
            }
        }
    }
}
