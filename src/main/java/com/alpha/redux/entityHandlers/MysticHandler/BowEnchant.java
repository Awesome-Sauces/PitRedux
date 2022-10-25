package com.alpha.redux.entityHandlers.MysticHandler;

import com.alpha.redux.eventManagers.ReduxBowEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Objects;

import static com.alpha.redux.well.loreChecker.CheckEnchantOnBow;

public class BowEnchant {


    public ReduxBowEvent event;

    public EntityShootBowEvent bowEvent;

    String enchant;
    Boolean hasEnchant = false;
    String enchantTier = "none";

    public BowEnchant(ReduxBowEvent event, String enchant){
        this.event = event;
        this.enchant = enchant;

        for(String ench : event.getEnchants())
            if (ench.contains(this.enchant)) {
                this.hasEnchant = true;
                this.enchantTier = ench.replace(this.enchant, "");
                break;
            }


    }

    public BowEnchant(EntityShootBowEvent event, String enchant){
        this.enchant = enchant;
        this.bowEvent = event;

        for(String ench : Objects.requireNonNull(getEnchants((Player) event.getEntity())))
            if (ench.contains(this.enchant)) {
                this.hasEnchant = true;
                this.enchantTier = ench.replace(this.enchant, "");
                break;
            }


    }

    private List<String> getEnchants(Player attacker){
        if(attacker.getItemInHand() != null && attacker.getItemInHand().getType().equals(Material.BOW) &&
                attacker.getItemInHand().getItemMeta() != null && attacker.getItemInHand().getItemMeta().getLore() != null){
            return CheckEnchantOnBow(attacker.getItemInHand().getItemMeta().getLore());
        }
        return null;
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
