package com.alpha.redux.entityHandlers.MysticHandler;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public abstract class SwordEnchant {


    public ReduxDamageEvent event;
    String enchant;
    Boolean hasEnchant = false;
    String enchantTier = "none";

    public SwordEnchant(ReduxDamageEvent event, String enchant){
        this.event = event;
        this.enchant = enchant;

        for(String ench : event.getAttackerSwordEnchants())
            if (ench.contains(this.enchant)) {
                this.hasEnchant = true;
                this.enchantTier = ench.replace(this.enchant, "");
                break;
            }

        if (event.getAttacker().getPlayerObject().hasPotionEffect(PotionEffectType.POISON)) hasEnchant = false;


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
