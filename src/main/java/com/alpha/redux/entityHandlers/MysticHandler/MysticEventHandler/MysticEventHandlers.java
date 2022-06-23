package com.alpha.redux.entityHandlers.MysticHandler.MysticEventHandler;

import com.alpha.redux.entityHandlers.MysticHandler.EnchantRuntime;
import com.alpha.redux.eventManagers.ReduxBowEvent;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MysticEventHandlers implements Listener {

    @EventHandler
    public static void callMeleeEvent(ReduxDamageEvent event){
        List<String> AttackerPants = event.getAttackerPantEnchants();
        List<String> AttackerSword = event.getAttackerSwordEnchants();

        List<String> DefenderPants = event.getDefenderPantEnchants();

        if(!AttackerPants.isEmpty()) {
            new EnchantRuntime(event, "PANT").run();
        }


        if(!AttackerSword.isEmpty()) {
            new EnchantRuntime(event, "SWORD").run();
        }

        if(!DefenderPants.isEmpty()) {

            new EnchantRuntime(event, "PANT").run();
        }

    }

    @EventHandler
    public static void callBowEvent(ReduxBowEvent event){

        new EnchantRuntime(event, "BOW").bowRun();


    }

    @EventHandler
    public void callShootEvent(EntityShootBowEvent event){
        if(event.getEntity() instanceof Player){

            new EnchantRuntime(event).bowShoot();

        }
    }



}
