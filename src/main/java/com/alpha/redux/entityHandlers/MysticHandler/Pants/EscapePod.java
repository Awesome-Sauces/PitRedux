package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class EscapePod {
    public EscapePod(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant escapePod = new PantEnchant(event, player, "escape") {
            @Override
            public void OneAction() {
                if(event.getDefenders().getEscape()){
                    giveRegen(event.getDefenders().getPlayerObject(), 3, 25);
                    event.getDefenders().getPlayerObject().setVelocity(event.getDefenders().getPlayerObject().getVelocity().add(new Vector(0, 10, 0)));
                }

            }

            @Override
            public void TwoAction() {
                if(event.getDefenders().getEscape()){
                    giveRegen(event.getDefenders().getPlayerObject(), 3, 30);
                    event.getDefenders().getPlayerObject().setVelocity(event.getDefenders().getPlayerObject().getVelocity().add(new Vector(0, 10, 0)));
                }
            }

            @Override
            public void ThreeAction() {
                if(event.getDefenders().getEscape()){
                    giveRegen(event.getDefenders().getPlayerObject(), 4, 35);
                    event.getDefenders().getPlayerObject().setVelocity(event.getDefenders().getPlayerObject().getVelocity().add(new Vector(0, 10, 0)));
                }
            }
        };
        escapePod.run();
    }

    private void giveRegen(Player player, int power, int time){
        player.removePotionEffect(PotionEffectType.REGENERATION);
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, time*20, Math.max(power-1, 0), true, true));
    }
}
