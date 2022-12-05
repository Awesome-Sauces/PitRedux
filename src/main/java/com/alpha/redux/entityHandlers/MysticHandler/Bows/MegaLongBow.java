package com.alpha.redux.entityHandlers.MysticHandler.Bows;

import com.alpha.redux.entityHandlers.MysticHandler.BowEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxBowEvent;
import com.alpha.redux.playerdata.economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import static com.alpha.redux.entityHandlers.ReduxPlayerHandler.playerExists;

public class MegaLongBow {

    public MegaLongBow(EntityShootBowEvent event){

        BowEnchant megaLongBow = new BowEnchant(event, "mlb") {
            @Override
            public void ThreeAction() {

                Arrow arrow = (Arrow) bowEvent.getProjectile();
                Player shooter = (Player) bowEvent.getEntity();
                ReduxPlayer player = playerExists(shooter);

                if (player.getMlbCD()){
                    player.setMlbCD();

                    arrow.setCritical(true);
                    arrow.setVelocity(shooter.getLocation().getDirection().multiply(2.90));
                    arrow.setShooter(shooter);

                    giveJump(shooter, 4,2);


                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.setMlbCD();
                        }
                    }.runTaskLater(economy.getPlugin(), 20L);
                }
            }
            @Override
            public void TwoAction() {


                Arrow arrow = (Arrow) bowEvent.getProjectile();
                Player shooter = (Player) bowEvent.getEntity();
                ReduxPlayer player = playerExists(shooter);

                if (player.getMlbCD()){
                    player.setMlbCD();

                    arrow.setCritical(true);
                    arrow.setVelocity(shooter.getLocation().getDirection().multiply(2.90));
                    arrow.setShooter(shooter);

                    giveJump(shooter, 3,2);


                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.setMlbCD();
                        }
                    }.runTaskLater(economy.getPlugin(), 20L);
                }

            }
            @Override
            public void OneAction() {

                Arrow arrow = (Arrow) bowEvent.getProjectile();
                Player shooter = (Player) bowEvent.getEntity();
                ReduxPlayer player = playerExists(shooter);

                if (player.getMlbCD()){
                    player.setMlbCD();

                    arrow.setCritical(true);
                    arrow.setVelocity(shooter.getLocation().getDirection().multiply(2.90));
                    arrow.setShooter(shooter);

                    giveJump(shooter, 2,2);


                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.setMlbCD();
                        }
                    }.runTaskLater(economy.getPlugin(), 20L);
                }

            }
        };

        megaLongBow.run();
    }

    private void giveJump(Player player, int power, int time){
            player.removePotionEffect(PotionEffectType.JUMP);
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, time*20, Math.max(power-1, 0), true, true));
    }
}
