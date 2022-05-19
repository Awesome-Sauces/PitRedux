package com.alpha.redux.entityHandlers.MysticHandler.MysticEventHandler;

import com.alpha.redux.entityHandlers.MysticHandler.EnchantRuntime;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MysticEventHandlers implements Listener {

    @EventHandler
    public static void callMeleeEvent(ReduxDamageEvent event){
        List<String> AttackerPants = event.getAttackerPantEnchants();
        List<String> AttackerSword = event.getAttackerSwordEnchants();

        List<String> DefenderPants = event.getDefenderPantEnchants();
        List<String> DefenderSword = event.getDefenderSwordEnchants();

        if(!AttackerPants.isEmpty()) {
            new EnchantRuntime(event.getAttacker(), event.getDefenders(), event, "PANT").run();
        }


        if(!AttackerSword.isEmpty()) {
            new EnchantRuntime(event.getAttacker(), event.getDefenders(), event, "SWORD").run();
        }

        if(!DefenderPants.isEmpty()) {
            new EnchantRuntime(event.getAttacker(), event.getDefenders(), event, "PANT").run();
        }

    }



}
