package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.chatManager.rank;
import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.MysticHandler.Pants.data.PantMaps;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.RetroGravityMicrocosmLore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;
import java.util.Map;

public class RetroGravityMicrocosm {

    RetroGravityMicrocosmLore retro = new RetroGravityMicrocosmLore();

    public RetroGravityMicrocosm(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant retrogravitymicrocosm = new PantEnchant(event, player, "rgm") {
            @Override
            public void OneAction() {
                retro.run(event, 1);
            }

            @Override
            public void TwoAction() {
                retro.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                retro.run(event, 3);
            }
        };
        retrogravitymicrocosm.run();
    }
    public boolean criticalHit(ReduxDamageEvent event){
        Player player = event.getDefenders().getPlayerObject();

        if(!event.getAttacker().getPlayerObject().isOnGround()){
            if(PantMaps.hitCounter.containsKey(String.valueOf(player.getUniqueId()))){
                PantMaps.hitCounter.put(String.valueOf(player.getUniqueId()), PantMaps.hitCounter.get(String.valueOf(player.getUniqueId())) + 1);
            }else{
                PantMaps.hitCounter.put(String.valueOf(player.getUniqueId()), 1);
            }

            return PantMaps.hitCounter.get(String.valueOf(player.getUniqueId())) >= 3;
        }
        return !event.getAttacker().getPlayerObject().isOnGround();
    }

    public void runIt(Player attacker, Player defender, double health, double trueDmg){
        PantMaps.hitCounter.put(String.valueOf(defender.getUniqueId()), 0);
        attacker.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lRGM! &7Proc'd against " + rank.getNameColor(defender) + defender.getDisplayName() + "&7!"));
        Sounds.RGM.play(attacker.getLocation());
        Sounds.RGM.play(defender.getLocation());
        defender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lRGM! &7Procced against " + rank.getNameColor(attacker) + attacker.getDisplayName() + "&7!"));
        defender.setHealth(Math.min(defender.getHealth() + health, defender.getMaxHealth()));

        EntityDamageByEntityEvent events = new EntityDamageByEntityEvent(defender, attacker,
                EntityDamageEvent.DamageCause.MAGIC, trueDmg);
        Bukkit.getServer().getPluginManager().callEvent(events);
        if(!events.isCancelled()) {
            attacker.setHealth(Math.max(attacker.getHealth() - trueDmg, 0));
        }
    }

}
