package com.alpha.redux.UpgradesNpc.perks;

import com.alpha.redux.DeathHandler.ReduxDeathEvent;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.playerdata.economy;
import com.alpha.redux.redux;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class Vampire extends PitPerk {

    public Vampire(){
        this.setRefID("vampire");
        this.setMaterial(Material.FERMENTED_SPIDER_EYE);
        this.setName(colorCode("&aVampire"));
        this.setLore(colorCode("&7Don't earn golden apples.\n" +
                "&7Heal &c0.5❤ &7 on hit.\n" +
                "&7Tripled on arrow crit.\n" +
                "&cRegen I &7(8s) on kill."));
        this.setCost(4000);
        this.setLevel(60);
    }

    @Override
    public PerkExecute getPerkExecute() {
        return new PerkExecute(){
            @Override
            public void run(ReduxDamageEvent event){
                ReduxPlayer player = event.getAttacker();


                if (player.getVampireCD()){
                    player.setVampireCD();
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.setVampireCD();
                        }
                    }.runTaskLater(economy.getPlugin(), 3L);
                    player.getPlayerObject().setHealth(Math.min(player.getPlayerObject().getHealth()+1, player.getPlayerObject().getMaxHealth()));
                }

                /*
                if(player.getPerks().contains(redux.vampire.getRefID())){
                    //player.getPlayerObject().sendMessage("Vampire Worked");
                    player.getPlayerObject().setHealth(Math.min(player.getPlayerObject().getHealth()+1, 20));
                }

                 */

            }

            @Override
            public void run(ReduxDeathEvent event){
                ReduxPlayer player = event.getAttacker();

                if(!player.getPlayerObject().hasPotionEffect(PotionEffectType.REGENERATION)){
                    //player.getPlayerObject().removePotionEffect(PotionEffectType.REGENERATION);
                    player.getPlayerObject().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 8*20, 0, true, true));
                }

                /*
                if(player.getPerks().contains(redux.vampire.getRefID())){
                    player.getPlayerObject().sendMessage("Vampire Worked");
                    if(!player.getPlayerObject().hasPotionEffect(PotionEffectType.REGENERATION)){
                        player.getPlayerObject().removePotionEffect(PotionEffectType.REGENERATION);
                        player.getPlayerObject().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 8*20, 0, true, true));
                    }
                }

                 */

            }
        };
    }


}

