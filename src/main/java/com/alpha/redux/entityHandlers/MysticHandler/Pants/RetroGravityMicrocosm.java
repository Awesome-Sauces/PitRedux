package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.chatManager.rank;
import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;
import java.util.Map;

public class RetroGravityMicrocosm {

    public static Map<String, Integer> hitCounter = new HashMap<String, Integer>();

    public RetroGravityMicrocosm(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant retrogravitymicrocosm = new PantEnchant(event, player, "rgm") {
            @Override
            public void OneAction() {
                if(criticalHit(event)){
                    runIt(event.getAttacker().getPlayerObject(), event.getDefenders().getPlayerObject(), 2, 1);
                }
            }

            @Override
            public void TwoAction() {
                if(criticalHit(event)){
                    runIt(event.getAttacker().getPlayerObject(), event.getDefenders().getPlayerObject(), 3, 2);
                }
            }

            @Override
            public void ThreeAction() {
                if(criticalHit(event)){
                    runIt(event.getAttacker().getPlayerObject(), event.getDefenders().getPlayerObject(), 4, 3);
                }
            }
        };
        retrogravitymicrocosm.run();
    }

    private boolean criticalHit(ReduxDamageEvent event){
        Player player = event.getDefenders().getPlayerObject();

        if(!event.getAttacker().getPlayerObject().isOnGround()){
            if(hitCounter.containsKey(String.valueOf(player.getUniqueId()))){
                hitCounter.put(String.valueOf(player.getUniqueId()), hitCounter.get(String.valueOf(player.getUniqueId())) + 1);
            }else{
                hitCounter.put(String.valueOf(player.getUniqueId()), 1);
            }

            return hitCounter.get(String.valueOf(player)) >= 3;
        }

        return !player.isOnGround();
    }

    private void runIt(Player attacker, Player defender, double health, double trueDmg){
        hitCounter.put(String.valueOf(defender.getUniqueId()), 0);
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
