package com.alpha.redux.entityHandlers.MysticHandler;

import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Objects;

public abstract class PantEnchant {


    public ReduxDamageEvent event;
    String enchant;
    Boolean hasEnchant = false;
    String enchantTier = "none";
    ReduxPlayer player;

    public PantEnchant(ReduxDamageEvent event, ReduxPlayer player, String enchant){
        this.event = event;
        this.enchant = enchant;
        this.player = player;

        for(String ench : player.getPantEnchants())
            if (ench.contains(this.enchant)) {
                this.hasEnchant = true;
                this.enchantTier = ench.replace(this.enchant, "");
                break;
            }

        if (player.getPlayerObject().hasPotionEffect(PotionEffectType.POISON)) hasEnchant = false;


    }

    public void OneAction(){

    }

    public void TwoAction(){

    }

    public void ThreeAction(){

    }

    public void run() {
        if(hasEnchant){

            // Making it so the attacker's damage isn't negatively affected by their own pants
            if(event.getAttacker() == this.player && !Objects.equals(this.enchant, "reg") ||
                    event.getAttacker() == this.player && !Objects.equals(this.enchant, "venom")) return;
            // Making attack only pant enchants not trigger for defender
            if(event.getDefenders() == this.player && this.enchant.equals("reg") ||
                    event.getDefenders() == this.player && this.enchant.equals("venom")) return;


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
